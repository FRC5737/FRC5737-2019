import cv2;
import numpy
import math
import time

from enum import Enum
from ballcontour import GripBallContour

#import networktables

#5737 python code running on raspberry pi coprocessor
#This is the code that will actually be called

def Watershed(img,opening): #Taken from official opencv docs
    #This is the watershed code that seperates balls placed closely together
    kernel = numpy.ones((5,5),numpy.uint8)
    sure_bg = cv2.dilate(opening,kernel,iterations=8)
    # Finding sure foreground area
    dist_transform = cv2.distanceTransform(opening,cv2.DIST_L2,5)
    ret, sure_fg = cv2.threshold(dist_transform,0.7*dist_transform.max(),255,0)
    # Finding unknown region
    sure_fg = numpy.uint8(sure_fg)
    unknown = cv2.subtract(sure_bg,sure_fg)
    # Marker labelling
    ret, markers = cv2.connectedComponents(sure_fg)
    # Add one to all labels so that sure background is not 0, but 1
    markers = markers+1
    # Now, mark the region of unknown with zero
    markers[unknown==255] = 0
    markers = cv2.watershed(img,markers)
    img[markers == -1] = [255,0,0]
    return img

cam = cv2.VideoCapture(0) #init cameras
data = {} #The values that will be sent to the roborio

while True:
    _,img = cam.read()
    ball = GripBallContour()
    ball.process(img)   
    watershed = Watershed(ball.cv_resize_output,ball.cv_dilate_output)
    cv2.imshow("Output1",watershed)
    cv2.waitKey(0)
    


 
    
