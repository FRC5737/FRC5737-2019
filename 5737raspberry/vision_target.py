import cv2
import numpy
import math
from enum import Enum

class GripPipeline:
    """
    An OpenCV pipeline generated by GRIP.
    """
    
    def __init__(self):
        """initializes all values to presets or None if need to be set
        """

        self.__resize_image_width = 220.0
        self.__resize_image_height = 140.0
        self.__resize_image_interpolation = cv2.INTER_CUBIC

        self.resize_image_output = None

        self.__rgb_threshold_input = self.resize_image_output
        self.__rgb_threshold_red = [217.62140287769785, 255.0]
        self.__rgb_threshold_green = [222.8956834532374, 255.0]
        self.__rgb_threshold_blue = [227.4820143884892, 255.0]

        self.rgb_threshold_output = None

        self.__cv_erode_src = self.rgb_threshold_output
        self.__cv_erode_kernel = None
        self.__cv_erode_anchor = (-1, -1)
        self.__cv_erode_iterations = 1.0
        self.__cv_erode_bordertype = cv2.BORDER_CONSTANT
        self.__cv_erode_bordervalue = (-1)

        self.cv_erode_output = None

        self.__cv_dilate_src = self.cv_erode_output
        self.__cv_dilate_kernel = None
        self.__cv_dilate_anchor = (-1, -1)
        self.__cv_dilate_iterations = 1.0
        self.__cv_dilate_bordertype = cv2.BORDER_CONSTANT
        self.__cv_dilate_bordervalue = (-1)

        self.cv_dilate_output = None

        self.__cv_rectangle_src = self.cv_dilate_output
        self.__cv_rectangle_pt1 = (0, 0)
        self.__cv_rectangle_pt2 = (0, 0)
        self.__cv_rectangle_color = (0.0, 0.0, 0.0, 0.0)
        self.__cv_rectangle_thickness = 0.0
        self.__cv_rectangle_linetype = cv2.LINE_8
        self.__cv_rectangle_shift = 0.0

        self.cv_rectangle_output = None

        self.__find_lines_input = self.cv_rectangle_output

        self.find_lines_output = None


    def process(self, source0):
        """
        Runs the pipeline and sets all outputs to new values.
        """
        # Step Resize_Image0:
        self.__resize_image_input = source0
        (self.resize_image_output) = self.__resize_image(self.__resize_image_input, self.__resize_image_width, self.__resize_image_height, self.__resize_image_interpolation)

        # Step RGB_Threshold0:
        self.__rgb_threshold_input = self.resize_image_output
        (self.rgb_threshold_output) = self.__rgb_threshold(self.__rgb_threshold_input, self.__rgb_threshold_red, self.__rgb_threshold_green, self.__rgb_threshold_blue)

        # Step CV_erode0:
        self.__cv_erode_src = self.rgb_threshold_output
        (self.cv_erode_output) = self.__cv_erode(self.__cv_erode_src, self.__cv_erode_kernel, self.__cv_erode_anchor, self.__cv_erode_iterations, self.__cv_erode_bordertype, self.__cv_erode_bordervalue)

        # Step CV_dilate0:
        self.__cv_dilate_src = self.cv_erode_output
        (self.cv_dilate_output) = self.__cv_dilate(self.__cv_dilate_src, self.__cv_dilate_kernel, self.__cv_dilate_anchor, self.__cv_dilate_iterations, self.__cv_dilate_bordertype, self.__cv_dilate_bordervalue)

        # Step CV_rectangle0:
        self.__cv_rectangle_src = self.cv_dilate_output
        (self.cv_rectangle_output) = self.__cv_rectangle(self.__cv_rectangle_src, self.__cv_rectangle_pt1, self.__cv_rectangle_pt2, self.__cv_rectangle_color, self.__cv_rectangle_thickness, self.__cv_rectangle_linetype, self.__cv_rectangle_shift)

        # Step Find_Lines0:
        self.__find_lines_input = self.cv_rectangle_output
        (self.find_lines_output) = self.__find_lines(self.__find_lines_input)


    @staticmethod
    def __resize_image(input, width, height, interpolation):
        """Scales and image to an exact size.
        Args:
            input: A numpy.ndarray.
            Width: The desired width in pixels.
            Height: The desired height in pixels.
            interpolation: Opencv enum for the type fo interpolation.
        Returns:
            A numpy.ndarray of the new size.
        """
        return cv2.resize(input, ((int)(width), (int)(height)), 0, 0, interpolation)

    @staticmethod
    def __rgb_threshold(input, red, green, blue):
        """Segment an image based on color ranges.
        Args:
            input: A BGR numpy.ndarray.
            red: A list of two numbers the are the min and max red.
            green: A list of two numbers the are the min and max green.
            blue: A list of two numbers the are the min and max blue.
        Returns:
            A black and white numpy.ndarray.
        """
        out = cv2.cvtColor(input, cv2.COLOR_BGR2RGB)
        return cv2.inRange(out, (red[0], green[0], blue[0]),  (red[1], green[1], blue[1]))

    @staticmethod
    def __cv_erode(src, kernel, anchor, iterations, border_type, border_value):
        """Expands area of lower value in an image.
        Args:
           src: A numpy.ndarray.
           kernel: The kernel for erosion. A numpy.ndarray.
           iterations: the number of times to erode.
           border_type: Opencv enum that represents a border type.
           border_value: value to be used for a constant border.
        Returns:
            A numpy.ndarray after erosion.
        """
        return cv2.erode(src, kernel, anchor, iterations = (int) (iterations +0.5),
                            borderType = border_type, borderValue = border_value)

    @staticmethod
    def __cv_dilate(src, kernel, anchor, iterations, border_type, border_value):
        """Expands area of higher value in an image.
        Args:
           src: A numpy.ndarray.
           kernel: The kernel for dilation. A numpy.ndarray.
           iterations: the number of times to dilate.
           border_type: Opencv enum that represents a border type.
           border_value: value to be used for a constant border.
        Returns:
            A numpy.ndarray after dilation.
        """
        return cv2.dilate(src, kernel, anchor, iterations = (int) (iterations +0.5),
                            borderType = border_type, borderValue = border_value)

    @staticmethod
    def __cv_rectangle(src, pt1, pt2, color, thickness, line_type, shift):
        """Draws a rectangle on an image.
        Args:
            src: The numpy.ndarray the will have a rectangle drawn on it.
            pt1: A list of two numbers that represent one corner of the rectangle.
            pt2: The opposite corner.
            color: A list of three numbers that represent the color of the image.
            thickness: A number.
            line_type: Opencv enum for the type of line.
            Shift: Number of decimal places in the points.
        Returns:
            A numpy.ndarray with a rectangle on it.
        """
        if(color == None):
            color = (1.0,1.0,1.0)
        shift_int = (int) (shift+0.5)
        pt1_int = ((int)(pt1[0]),(int)(pt1[1]))
        pt2_int = ((int)(pt2[0]),(int)(pt2[1]))
        return cv2.rectangle(src, pt1_int, pt2_int, color, thickness=(int)(thickness+0.5),
                            lineType=line_type, shift=shift_int)

    class Line:

        def __init__(self, x1, y1, x2, y2):
            self.x1 = x1
            self.y1 = y1
            self.x2 = x2
            self.y2 = y2

        def length(self):
            return numpy.sqrt(pow(self.x2 - self.x1, 2) + pow(self.y2 - self.y1, 2))

        def angle(self):
            return math.degrees(math.atan2(self.y2 - self.y1, self.x2 - self.x1))
    @staticmethod
    def __find_lines(input):
        """Finds all line segments in an image.
        Args:
            input: A numpy.ndarray.
        Returns:
            A filtered list of Lines.
        """
        detector = cv2.createLineSegmentDetector()
        if (len(input.shape) == 2 or input.shape[2] == 1):
            lines = detector.detect(input)
        else:
            tmp = cv2.cvtColor(input, cv2.COLOR_BGR2GRAY)
            lines = detector.detect(tmp)
        output = []
        if len(lines) != 0:
            for i in range(1, len(lines[0])):
                tmp = GripPipeline.Line(lines[0][i, 0][0], lines[0][i, 0][1],
                                lines[0][i, 0][2], lines[0][i, 0][3])
                output.append(tmp)
        return output



