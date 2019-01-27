import cv2
import numpy
import math
from enum import Enum
from gripball import GripDetectBall
import time
#import networktables

#5737 python code running on raspberry pi coprocessor
cam = cv2.VideoCapture(0) #init cameras

data = {}

while True:
    _,img = cam.read()
    ball = GripDetectBall()
    ball.process(img)
    ballOutput = ball.find_blobs_output
    if ballOutput != []:
        c = 0
        maximumSize = 0
        for i in range(len(ballOutput)):
            if ballOutput[i].size > maximumSize:
                c = i
                maximumSize = ballOutput[i].size
        key = ballOutput[c]
        data["ballPtX"] = key.pt[0]
        data["ballSize"] = key.size
        print(data)
        time.sleep(.1)
    
