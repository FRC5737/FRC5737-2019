import cv2
import numpy
import math
import time

from enum import Enum
from ballcontour import GripBallContour

#import networktables

#5737 python code running on raspberry pi coprocessor
#This is the code that will actually be called

cam = cv2.VideoCapture(1) #init cameras
data = {} #The values that will be sent to the roborio
ball = GripBallContour()

while True:
    _,img = cam.read()
    #img = cv2.imread('/Users/mark/Desktop/WechatIMG152.jpeg',3)
    ball.process(img)
    contoursFull = ball.filter_contours_0_output
    contoursExtra = ball.filter_contours_1_output
    #Remove all outer contours (Probably could do this faster with hierarchy, but not gonna bother for now)
    for i in contoursFull[:]:
        for j in contoursExtra:
            res = cv2.matchShapes(i, j, cv2.CONTOURS_MATCH_I2,0)
            if res == 0:
                contoursFull.remove(i)
                
    '''#Convex hull it Technically not needed
    contourFinal = []
    for i in contoursFull:
        contoursFinal.append(cv2.convexHull(i,False))'''

    #Minimum enclosing circles for speed, useless if use hough circle
    ballFinal = []
    for i in contoursFull:
        (x,y),radius = cv2.minEnclosingCircle(i)
        center = (int(x),int(y))
        radius = int(radius)
        ballFinal.append((center,radius))
    #Draw circles for debug
    for i in ballFinal:
        ball.cv_resize_output = cv2.circle(ball.cv_resize_output,i[0],i[1],(0,255,0),2)
        
    #cv2.drawContours(ball.cv_resize_output, contoursFinal, -1, (0,255,0), 3)
    cv2.imshow("Cont",ball.cv_resize_output)
    cv2.waitKey(10)

def circle(contour):
    (x,y),radius = cv.minEnclosingCircle(contour)
    center = (int(x),int(y))
    radius = int(radius)
    return (center,radius)
    


 
    
