def Watershed(img,opening,dist,bg): #Taken from official opencv docs
    #This is the watershed code that seperates balls placed closely together
    kernel = numpy.ones((5,5),numpy.uint8)
    #sure_bg = cv2.dilate(opening,kernel,iterations=10)
    sure_bg = bg
    # Finding sure foreground area
    #dist_transform = cv2.distanceTransform(opening,cv2.DIST_L2,5)
    dist_transform = dist
    ret, sure_fg = cv2.threshold(dist_transform,0.5*dist_transform.max(),255,0)
    # Finding unknown region
    sure_fg = numpy.uint8(sure_fg)
    unknown = cv2.subtract(sure_bg,sure_fg)
    # Marker labelling
    ret, markers = cv2.connectedComponents(sure_fg)
    # Add one to all labels so that sure background is not 0, but 1
    markers = markers+1
    # Now, mark the region of unknown with zero
    markers[unknown==255] = 0
    labels = cv2.watershed(img,markers)
    return labels
