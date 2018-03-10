package com.DDI;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;


public class HelloCV
{
	public static void main(String[] args)
	{
		// load the OpenCV native library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// create and print on screen a 3x3 identity matrix
		System.out.println("Create a 3x3 identity matrix...");
		Mat mat = Imgcodecs.imread("./images/elsa.jpg");

		System.out.println("Image read, now dumping");
		
		Mat src = mat;
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_RGBA2GRAY);

		Imgproc.Canny(gray, gray, 50, 200);
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		// find contours:
		Imgproc.findContours(gray, contours, hierarchy, Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE);
		for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++) {
		    Imgproc.drawContours(src, contours, contourIdx, new Scalar(0, 0, 255), -1);
		}
		Imgcodecs.imwrite("./images/elsac.jpg", src);
		System.out.println("Done! " + contours.size());
	}
}