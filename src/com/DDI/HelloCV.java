package com.DDI;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Core;


public class HelloCV
{
	public static void main(String[] args)
	{
		// load the OpenCV native library
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		// create and print on screen a 3x3 identity matrix
		System.out.println("Create a 3x3 identity matrix...");
		Mat mat = Mat.eye(8, 8, CvType.CV_8UC1);
		System.out.println("mat = " + mat.dump());
		System.out.println("Done!");
	}
}