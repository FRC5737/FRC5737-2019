import cv2
import numpy
import math
from enum import Enum

class GripBallContour:
    """
    An OpenCV pipeline generated by GRIP.
    """
    
    def __init__(self):
        """initializes all values to presets or None if need to be set
        """

        self.__cv_resize_dsize = (0, 0)
        self.__cv_resize_fx = 0.5
        self.__cv_resize_fy = 0.5
        self.__cv_resize_interpolation = cv2.INTER_LINEAR

        self.cv_resize_output = None

        self.__hsv_threshold_0_input = self.cv_resize_output
        self.__hsv_threshold_0_hue = [3.237410071942446, 21.576960589531488]
        self.__hsv_threshold_0_saturation = [144.19657021007205, 255.0]
        self.__hsv_threshold_0_value = [144.42310526911896, 255.0]

        self.hsv_threshold_0_output = None

        self.__cv_erode_0_src = self.hsv_threshold_0_output
        self.__cv_erode_0_kernel = None
        self.__cv_erode_0_anchor = (-1, -1)
        self.__cv_erode_0_iterations = 10.0
        self.__cv_erode_0_bordertype = cv2.BORDER_CONSTANT
        self.__cv_erode_0_bordervalue = (-1)

        self.cv_erode_0_output = None

        self.__cv_dilate_0_src = self.cv_erode_0_output
        self.__cv_dilate_0_kernel = None
        self.__cv_dilate_0_anchor = (-1, -1)
        self.__cv_dilate_0_iterations = 30.0
        self.__cv_dilate_0_bordertype = cv2.BORDER_CONSTANT
        self.__cv_dilate_0_bordervalue = (-1)

        self.cv_dilate_0_output = None

        self.__mask_0_input = self.cv_resize_output
        self.__mask_0_mask = self.cv_dilate_0_output

        self.mask_0_output = None

        self.__hsv_threshold_1_input = self.mask_0_output
        self.__hsv_threshold_1_hue = [3.3898305084745766, 15.638297872340429]
        self.__hsv_threshold_1_saturation = [141.66666666666666, 255.0]
        self.__hsv_threshold_1_value = [122.45762711864407, 255.0]

        self.hsv_threshold_1_output = None

        self.__cv_dilate_1_src = self.hsv_threshold_1_output
        self.__cv_dilate_1_kernel = None
        self.__cv_dilate_1_anchor = (-1, -1)
        self.__cv_dilate_1_iterations = 8.0
        self.__cv_dilate_1_bordertype = cv2.BORDER_CONSTANT
        self.__cv_dilate_1_bordervalue = (-1)

        self.cv_dilate_1_output = None

        self.__cv_erode_1_src = self.cv_dilate_1_output
        self.__cv_erode_1_kernel = None
        self.__cv_erode_1_anchor = (-1, -1)
        self.__cv_erode_1_iterations = 8.0
        self.__cv_erode_1_bordertype = cv2.BORDER_CONSTANT
        self.__cv_erode_1_bordervalue = (-1)

        self.cv_erode_1_output = None

        self.__mask_1_input = self.mask_0_output
        self.__mask_1_mask = self.cv_erode_1_output

        self.mask_1_output = None

        self.__blur_input = self.mask_1_output
        self.__blur_type = BlurType.Gaussian_Blur
        self.__blur_radius = 2.8301886792452837

        self.blur_output = None

        self.__cv_laplacian_src = self.blur_output
        self.__cv_laplacian_ksize = 5.0
        self.__cv_laplacian_scale = 2.0
        self.__cv_laplacian_delta = 1.0
        self.__cv_laplacian_bordertype = cv2.BORDER_DEFAULT

        self.cv_laplacian_output = None

        self.__hsl_threshold_input = self.cv_laplacian_output
        self.__hsl_threshold_hue = [0.0, 180.0]
        self.__hsl_threshold_saturation = [0.0, 255.0]
        self.__hsl_threshold_luminance = [69.63276836158191, 255.0]

        self.hsl_threshold_output = None

        self.__cv_erode_2_src = self.hsl_threshold_output
        self.__cv_erode_2_kernel = None
        self.__cv_erode_2_anchor = (-1, -1)
        self.__cv_erode_2_iterations = 1.0
        self.__cv_erode_2_bordertype = cv2.BORDER_CONSTANT
        self.__cv_erode_2_bordervalue = (-1)

        self.cv_erode_2_output = None

        self.__cv_dilate_2_src = self.cv_erode_2_output
        self.__cv_dilate_2_kernel = None
        self.__cv_dilate_2_anchor = (-1, -1)
        self.__cv_dilate_2_iterations = 7.0
        self.__cv_dilate_2_bordertype = cv2.BORDER_CONSTANT
        self.__cv_dilate_2_bordervalue = (-1)

        self.cv_dilate_2_output = None

        self.__find_contours_0_input = self.cv_dilate_2_output
        self.__find_contours_0_external_only = False

        self.find_contours_0_output = None

        self.__filter_contours_0_contours = self.find_contours_0_output
        self.__filter_contours_0_min_area = 5000.0
        self.__filter_contours_0_min_perimeter = 0.0
        self.__filter_contours_0_min_width = 50.0
        self.__filter_contours_0_max_width = 5000.0
        self.__filter_contours_0_min_height = 50.0
        self.__filter_contours_0_max_height = 5000.0
        self.__filter_contours_0_solidity = [49.90583804143125, 100.0]
        self.__filter_contours_0_max_vertices = 1000000.0
        self.__filter_contours_0_min_vertices = 1.0
        self.__filter_contours_0_min_ratio = 0.0
        self.__filter_contours_0_max_ratio = 20.0

        self.filter_contours_0_output = None

        self.__find_contours_1_input = self.cv_dilate_2_output
        self.__find_contours_1_external_only = True

        self.find_contours_1_output = None

        self.__filter_contours_1_contours = self.find_contours_1_output
        self.__filter_contours_1_min_area = 5000.0
        self.__filter_contours_1_min_perimeter = 0.0
        self.__filter_contours_1_min_width = 0.0
        self.__filter_contours_1_max_width = 1000.0
        self.__filter_contours_1_min_height = 0.0
        self.__filter_contours_1_max_height = 1000.0
        self.__filter_contours_1_solidity = [0, 100]
        self.__filter_contours_1_max_vertices = 1000000.0
        self.__filter_contours_1_min_vertices = 0.0
        self.__filter_contours_1_min_ratio = 0.0
        self.__filter_contours_1_max_ratio = 1000.0

        self.filter_contours_1_output = None


    def process(self, source0):
        """
        Runs the pipeline and sets all outputs to new values.
        """
        # Step CV_resize0:
        self.__cv_resize_src = source0
        (self.cv_resize_output) = self.__cv_resize(self.__cv_resize_src, self.__cv_resize_dsize, self.__cv_resize_fx, self.__cv_resize_fy, self.__cv_resize_interpolation)

        # Step HSV_Threshold0:
        self.__hsv_threshold_0_input = self.cv_resize_output
        (self.hsv_threshold_0_output) = self.__hsv_threshold(self.__hsv_threshold_0_input, self.__hsv_threshold_0_hue, self.__hsv_threshold_0_saturation, self.__hsv_threshold_0_value)

        # Step CV_erode0:
        self.__cv_erode_0_src = self.hsv_threshold_0_output
        (self.cv_erode_0_output) = self.__cv_erode(self.__cv_erode_0_src, self.__cv_erode_0_kernel, self.__cv_erode_0_anchor, self.__cv_erode_0_iterations, self.__cv_erode_0_bordertype, self.__cv_erode_0_bordervalue)

        # Step CV_dilate0:
        self.__cv_dilate_0_src = self.cv_erode_0_output
        (self.cv_dilate_0_output) = self.__cv_dilate(self.__cv_dilate_0_src, self.__cv_dilate_0_kernel, self.__cv_dilate_0_anchor, self.__cv_dilate_0_iterations, self.__cv_dilate_0_bordertype, self.__cv_dilate_0_bordervalue)

        # Step Mask0:
        self.__mask_0_input = self.cv_resize_output
        self.__mask_0_mask = self.cv_dilate_0_output
        (self.mask_0_output) = self.__mask(self.__mask_0_input, self.__mask_0_mask)

        # Step HSV_Threshold1:
        self.__hsv_threshold_1_input = self.mask_0_output
        (self.hsv_threshold_1_output) = self.__hsv_threshold(self.__hsv_threshold_1_input, self.__hsv_threshold_1_hue, self.__hsv_threshold_1_saturation, self.__hsv_threshold_1_value)

        # Step CV_dilate1:
        self.__cv_dilate_1_src = self.hsv_threshold_1_output
        (self.cv_dilate_1_output) = self.__cv_dilate(self.__cv_dilate_1_src, self.__cv_dilate_1_kernel, self.__cv_dilate_1_anchor, self.__cv_dilate_1_iterations, self.__cv_dilate_1_bordertype, self.__cv_dilate_1_bordervalue)

        # Step CV_erode1:
        self.__cv_erode_1_src = self.cv_dilate_1_output
        (self.cv_erode_1_output) = self.__cv_erode(self.__cv_erode_1_src, self.__cv_erode_1_kernel, self.__cv_erode_1_anchor, self.__cv_erode_1_iterations, self.__cv_erode_1_bordertype, self.__cv_erode_1_bordervalue)

        # Step Mask1:
        self.__mask_1_input = self.mask_0_output
        self.__mask_1_mask = self.cv_erode_1_output
        (self.mask_1_output) = self.__mask(self.__mask_1_input, self.__mask_1_mask)

        # Step Blur0:
        self.__blur_input = self.mask_1_output
        (self.blur_output) = self.__blur(self.__blur_input, self.__blur_type, self.__blur_radius)

        # Step CV_Laplacian0:
        self.__cv_laplacian_src = self.blur_output
        (self.cv_laplacian_output) = self.__cv_laplacian(self.__cv_laplacian_src, self.__cv_laplacian_ksize, self.__cv_laplacian_scale, self.__cv_laplacian_delta, self.__cv_laplacian_bordertype)

        # Step HSL_Threshold0:
        self.__hsl_threshold_input = self.cv_laplacian_output
        (self.hsl_threshold_output) = self.__hsl_threshold(self.__hsl_threshold_input, self.__hsl_threshold_hue, self.__hsl_threshold_saturation, self.__hsl_threshold_luminance)

        # Step CV_erode2:
        self.__cv_erode_2_src = self.hsl_threshold_output
        (self.cv_erode_2_output) = self.__cv_erode(self.__cv_erode_2_src, self.__cv_erode_2_kernel, self.__cv_erode_2_anchor, self.__cv_erode_2_iterations, self.__cv_erode_2_bordertype, self.__cv_erode_2_bordervalue)

        # Step CV_dilate2:
        self.__cv_dilate_2_src = self.cv_erode_2_output
        (self.cv_dilate_2_output) = self.__cv_dilate(self.__cv_dilate_2_src, self.__cv_dilate_2_kernel, self.__cv_dilate_2_anchor, self.__cv_dilate_2_iterations, self.__cv_dilate_2_bordertype, self.__cv_dilate_2_bordervalue)

        # Step Find_Contours0:
        self.__find_contours_0_input = self.cv_dilate_2_output
        (self.find_contours_0_output) = self.__find_contours(self.__find_contours_0_input, self.__find_contours_0_external_only)

        # Step Filter_Contours0:
        self.__filter_contours_0_contours = self.find_contours_0_output
        (self.filter_contours_0_output) = self.__filter_contours(self.__filter_contours_0_contours, self.__filter_contours_0_min_area, self.__filter_contours_0_min_perimeter, self.__filter_contours_0_min_width, self.__filter_contours_0_max_width, self.__filter_contours_0_min_height, self.__filter_contours_0_max_height, self.__filter_contours_0_solidity, self.__filter_contours_0_max_vertices, self.__filter_contours_0_min_vertices, self.__filter_contours_0_min_ratio, self.__filter_contours_0_max_ratio)

        # Step Find_Contours1:
        self.__find_contours_1_input = self.cv_dilate_2_output
        (self.find_contours_1_output) = self.__find_contours(self.__find_contours_1_input, self.__find_contours_1_external_only)

        # Step Filter_Contours1:
        self.__filter_contours_1_contours = self.find_contours_1_output
        (self.filter_contours_1_output) = self.__filter_contours(self.__filter_contours_1_contours, self.__filter_contours_1_min_area, self.__filter_contours_1_min_perimeter, self.__filter_contours_1_min_width, self.__filter_contours_1_max_width, self.__filter_contours_1_min_height, self.__filter_contours_1_max_height, self.__filter_contours_1_solidity, self.__filter_contours_1_max_vertices, self.__filter_contours_1_min_vertices, self.__filter_contours_1_min_ratio, self.__filter_contours_1_max_ratio)


    @staticmethod
    def __cv_resize(src, d_size, fx, fy, interpolation):
        """Resizes an Image.
        Args:
            src: A numpy.ndarray.
            d_size: Size to set the image.
            fx: The scale factor for the x.
            fy: The scale factor for the y.
            interpolation: Opencv enum for the type of interpolation.
        Returns:
            A resized numpy.ndarray.
        """
        return cv2.resize(src, d_size, fx=fx, fy=fy, interpolation=interpolation)

    @staticmethod
    def __hsv_threshold(input, hue, sat, val):
        """Segment an image based on hue, saturation, and value ranges.
        Args:
            input: A BGR numpy.ndarray.
            hue: A list of two numbers the are the min and max hue.
            sat: A list of two numbers the are the min and max saturation.
            lum: A list of two numbers the are the min and max value.
        Returns:
            A black and white numpy.ndarray.
        """
        out = cv2.cvtColor(input, cv2.COLOR_BGR2HSV)
        return cv2.inRange(out, (hue[0], sat[0], val[0]),  (hue[1], sat[1], val[1]))

    @staticmethod
    def __mask(input, mask):
        """Filter out an area of an image using a binary mask.
        Args:
            input: A three channel numpy.ndarray.
            mask: A black and white numpy.ndarray.
        Returns:
            A three channel numpy.ndarray.
        """
        return cv2.bitwise_and(input, input, mask=mask)

    @staticmethod
    def __blur(src, type, radius):
        """Softens an image using one of several filters.
        Args:
            src: The source mat (numpy.ndarray).
            type: The blurType to perform represented as an int.
            radius: The radius for the blur as a float.
        Returns:
            A numpy.ndarray that has been blurred.
        """
        if(type is BlurType.Box_Blur):
            ksize = int(2 * round(radius) + 1)
            return cv2.blur(src, (ksize, ksize))
        elif(type is BlurType.Gaussian_Blur):
            ksize = int(6 * round(radius) + 1)
            return cv2.GaussianBlur(src, (ksize, ksize), round(radius))
        elif(type is BlurType.Median_Filter):
            ksize = int(2 * round(radius) + 1)
            return cv2.medianBlur(src, ksize)
        else:
            return cv2.bilateralFilter(src, -1, round(radius), round(radius))

    @staticmethod
    def __cv_laplacian(src, size, scale, delta, border_type):
        """Performs a Laplacian on the image.
        Args:
            src: A numpy.ndarray.
            size: Odd number that is size of the kernel.
            scale: Scaling factor for Laplacian.
            delta: Offset for values in Laplacian.
            border_type: Opencv enum.
        Returns:
            The result as a numpy.ndarray.
        """
        return cv2.Laplacian(src, 0, ksize=(int)(size+0.5), scale=scale, delta=delta,
                            borderType=border_type)

    @staticmethod
    def __hsl_threshold(input, hue, sat, lum):
        """Segment an image based on hue, saturation, and luminance ranges.
        Args:
            input: A BGR numpy.ndarray.
            hue: A list of two numbers the are the min and max hue.
            sat: A list of two numbers the are the min and max saturation.
            lum: A list of two numbers the are the min and max luminance.
        Returns:
            A black and white numpy.ndarray.
        """
        out = cv2.cvtColor(input, cv2.COLOR_BGR2HLS)
        return cv2.inRange(out, (hue[0], lum[0], sat[0]),  (hue[1], lum[1], sat[1]))

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
    def __find_contours(input, external_only):
        """Sets the values of pixels in a binary image to their distance to the nearest black pixel.
        Args:
            input: A numpy.ndarray.
            external_only: A boolean. If true only external contours are found.
        Return:
            A list of numpy.ndarray where each one represents a contour.
        """
        if(external_only):
            mode = cv2.RETR_EXTERNAL
        else:
            mode = cv2.RETR_LIST
        method = cv2.CHAIN_APPROX_SIMPLE
        im2, contours, hierarchy =cv2.findContours(input, mode=mode, method=method)
        return contours

    @staticmethod
    def __filter_contours(input_contours, min_area, min_perimeter, min_width, max_width,
                        min_height, max_height, solidity, max_vertex_count, min_vertex_count,
                        min_ratio, max_ratio):
        """Filters out contours that do not meet certain criteria.
        Args:
            input_contours: Contours as a list of numpy.ndarray.
            min_area: The minimum area of a contour that will be kept.
            min_perimeter: The minimum perimeter of a contour that will be kept.
            min_width: Minimum width of a contour.
            max_width: MaxWidth maximum width.
            min_height: Minimum height.
            max_height: Maximimum height.
            solidity: The minimum and maximum solidity of a contour.
            min_vertex_count: Minimum vertex Count of the contours.
            max_vertex_count: Maximum vertex Count.
            min_ratio: Minimum ratio of width to height.
            max_ratio: Maximum ratio of width to height.
        Returns:
            Contours as a list of numpy.ndarray.
        """
        output = []
        for contour in input_contours:
            x,y,w,h = cv2.boundingRect(contour)
            if (w < min_width or w > max_width):
                continue
            if (h < min_height or h > max_height):
                continue
            area = cv2.contourArea(contour)
            if (area < min_area):
                continue
            if (cv2.arcLength(contour, True) < min_perimeter):
                continue
            hull = cv2.convexHull(contour)
            solid = 100 * area / cv2.contourArea(hull)
            if (solid < solidity[0] or solid > solidity[1]):
                continue
            if (len(contour) < min_vertex_count or len(contour) > max_vertex_count):
                continue
            ratio = (float)(w) / h
            if (ratio < min_ratio or ratio > max_ratio):
                continue
            output.append(contour)
        return output


BlurType = Enum('BlurType', 'Box_Blur Gaussian_Blur Median_Filter Bilateral_Filter')

