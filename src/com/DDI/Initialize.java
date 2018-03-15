package com.DDI;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import org.opencv.core.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.CvType;


public class Initialize
{
	public static void main(String[] args)
	{
		System.load("/home/" + System.getProperty("user.name") + "/libs/build/lib" + Core.NATIVE_LIBRARY_NAME + ".so");
		Mat src = Imgcodecs.imread(System.getProperty("user.dir") + args[0]);
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_RGBA2GRAY);
		Imgproc.Canny(gray, gray, 50, 200);
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(gray, contours, hierarchy, Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE);
		for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++) {
		    Imgproc.drawContours(src, contours, contourIdx, new Scalar(0, 0, 255), -1);
		}
		Imgcodecs.imwrite(System.getProperty("user.dir") + args[1], src);
		
		
		Mat image = Imgcodecs.imread(System.getProperty("user.dir") + args[0]);
		Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB);

		List<Mat> rgbPlanes = new ArrayList<Mat>();
		Core.split(image, rgbPlanes);

		MatOfInt histSize = new MatOfInt(256);

		final MatOfFloat histRange = new MatOfFloat(0f, 256f);

		boolean accumulate = false;

		Map<String, Mat> hists = new HashMap<String, Mat>();
		hists.put("r", new Mat());
		hists.put("g", new Mat());
		hists.put("b", new Mat());
		int hist_w = 400;
		int hist_h = 300;
		long bin_w = Math.round((double) hist_w / 256);
		Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3);

		int index = 0;
		List<Scalar> scalars = new ArrayList<Scalar>();
		scalars.add(new Scalar(255,   0,   0));
		scalars.add(new Scalar(0,   255,   0));
		scalars.add(new Scalar(0,     0, 255));
		for (Mat hist: hists.values()) {
			Imgproc.calcHist(rgbPlanes, new MatOfInt(0), new Mat(), hist, histSize, histRange, accumulate);
			Core.normalize(hist, hist, 3, histImage.rows(), Core.NORM_MINMAX);
			for (int i = 1; i < 256; i++) {
				Point p1 = new Point(bin_w * (i - 1), hist_h - Math.round(hist.get(i - 1, 0)[0]));
				Point p2 = new Point(bin_w * (i), hist_h - Math.round(hist.get(i, 0)[0]));
				Imgproc.line(histImage, p1, p2, scalars.get(index), 2, 8, 0);
//		        Imgproc.line(histImage, p1, p2, new Scalar(255, 0, 0), 2, 8, 0);
			}
			System.out.print(index);
			index++;
		}

		Imgcodecs.imwrite(System.getProperty("user.dir") + args[2], histImage);
	}
}