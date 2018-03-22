package com.DDI;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.opencv.core.Point;

import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.core.Core;
import org.opencv.core.CvType;


public class Initialize
{
	public static void main(String[] args) {
		System.load("/home/" + System.getProperty("user.name") + "/libs/build/lib" + Core.NATIVE_LIBRARY_NAME + ".so");
		String string = "{\"rows\":256,\"cols\":1,\"type\":5,\"data\":\"480.0|3.0042515|3.008503|3.0042515|3.0042515|3.0170057|3.008503|3.01488|3.0170057|3.0191314|3.0063772|3.01488|3.0212572|3.008503|3.0106285|3.0106285|3.0127542|3.0106285|3.0127542|3.008503|3.0063772|3.008503|3.0021257|3.0127542|3.0106285|3.0021257|3.0106285|3.0106285|3.0042515|3.0127542|3.0063772|3.0106285|3.0042515|3.023383|3.0127542|3.008503|3.008503|3.0021257|3.0042515|3.0063772|3.0063772|3.0063772|3.0106285|3.0063772|3.0170057|3.0063772|3.008503|3.008503|3.0063772|3.0042515|3.0021257|3.0127542|3.0042515|3.0042515|3.0042515|3.0063772|3.008503|3.0063772|3.0021257|3.0063772|3.0063772|3.0042515|3.0042515|3.0|3.0042515|3.0021257|3.0|3.0|3.0|3.0021257|3.0021257|3.0042515|3.0|3.0021257|3.0042515|3.008503|3.0|3.0042515|3.0063772|3.0021257|3.0021257|3.0021257|3.0021257|3.0|3.008503|3.0|3.0|3.0|3.0|3.0|3.0042515|3.0042515|3.0|3.0021257|3.0021257|3.0|3.0042515|3.0042515|3.0|3.0|3.0|3.0021257|3.0042515|3.0|3.0021257|3.0|3.0|3.0|3.0|3.0021257|3.0|3.0042515|3.0|3.0|3.0|3.0021257|3.0021257|3.0|3.0|3.0063772|3.0042515|3.0021257|3.0|3.0021257|3.0|3.0021257|3.0042515|3.0|3.0021257|3.0021257|3.0|3.0042515|3.0021257|3.0021257|3.0021257|3.0|3.0021257|3.0042515|3.0021257|3.008503|3.0042515|3.0|3.0|3.008503|3.0021257|3.0021257|3.0|3.0|3.0021257|3.0021257|3.0021257|3.0042515|3.0021257|3.0063772|3.0042515|3.0021257|3.0|3.0|3.0|3.0|3.0063772|3.0042515|3.0042515|3.0042515|3.008503|3.0063772|3.0063772|3.0042515|3.0063772|3.0021257|3.0|3.0|3.0021257|3.0021257|3.0|3.0063772|3.0021257|3.0021257|3.0021257|3.0|3.0|3.0021257|3.0|3.0|3.0|3.0|3.0|3.0|3.0|3.0|3.0|3.0021257|3.0021257|3.0|3.0042515|3.0042515|3.0021257|3.0042515|3.0021257|3.0021257|3.0|3.0|3.0042515|3.0|3.0021257|3.0|3.0|3.0063772|3.0042515|3.0021257|3.0|3.0|3.0|3.0021257|3.0|3.0|3.0|3.0|3.0|3.0021257|3.0021257|3.0|3.0|3.0|3.0021257|3.0|3.0|3.0|3.0|3.0|3.0|3.0|3.0|3.0|3.0|3.0|3.0021257|3.0|3.0021257|3.0021257|3.0|3.0|3.0|3.0|3.0|3.0021257|3.0|3.0|3.0|3.0|3.0|3.0|3.0021257|3.0021257|3.0021257|3.418766|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0|0.0\"}";
		System.out.println(Initialize.matFromJson(string).dump());
	}
	public static void mainx(String[] args)
	{
//		String srcstr = args[0];
//		String contourstr = args[1];
//		String histstr = args[2]; 
		
		
		
		String srcstr = "/images/elsa.jpg";
		String contourstr = "/images/conv-elsa.jpg";
		String histstr = "/images/hist-elsa.jpg"; 
		String dir = "/images/";
		String filename = "elsa.jpg";
		
		System.load("/home/" + System.getProperty("user.name") + "/libs/build/lib" + Core.NATIVE_LIBRARY_NAME + ".so");
		
		// test start
				String string = "{\"rows\":256,\"cols\":1,\"type\":5,\"data\":\"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AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\\u003d\\u003d\"}";
				System.out.println(Initialize.matFromJson(string));
				// test end
				
		Mat src = Imgcodecs.imread(System.getProperty("user.dir") + srcstr);
		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_RGBA2GRAY);
		Imgproc.Canny(gray, gray, 50, 200);
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		System.out.println(srcstr + ": Finding contours");
		Imgproc.findContours(gray, contours, hierarchy, Imgproc.RETR_TREE,Imgproc.CHAIN_APPROX_SIMPLE);
		for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++) {
		    Imgproc.drawContours(src, contours, contourIdx, new Scalar(0, 0, 255), -1);
		    Initialize.writeHistogram(src, contours, contourIdx, dir, "contour-histogram-" + contourIdx + filename);
		}
		System.out.println(srcstr + ": Writing contoured image");
		Imgcodecs.imwrite(System.getProperty("user.dir") + contourstr, src);
		System.out.println(srcstr + ": Writing contoured image DONE");
		
		Mat image = Imgcodecs.imread(System.getProperty("user.dir") + srcstr);
		Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB);

		List<Mat> rgbPlanes = new ArrayList<Mat>();
		System.out.println(srcstr + ": Splitting RGB Planes");
		Core.split(image, rgbPlanes);
		System.out.println(srcstr + ": Splitting RGB Planes DONE");

		MatOfInt histSize = new MatOfInt(256);

		final MatOfFloat histRange = new MatOfFloat(0f, 256f);

		boolean accumulate = false;

		Map<String, Mat> hists = new HashMap<String, Mat>();
		hists.put("r", new Mat());
		hists.put("g", new Mat());
		hists.put("b", new Mat());
		int hist_w = 640;
		int hist_h = 480;
		long bin_w = Math.round((double) hist_w / 256);
		Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3);

		int index = 0;
		List<Scalar> scalars = new ArrayList<Scalar>();
		scalars.add(new Scalar(255,   0,   0));
		scalars.add(new Scalar(0,   255,   0));
		scalars.add(new Scalar(0,     0, 255));
		for (Mat hist: hists.values()) {
			System.out.println(srcstr + ": Histogram generation for channel "+ index);
			List<Mat> plane = new ArrayList<Mat>();
			plane.add(rgbPlanes.get(index));
			Imgproc.calcHist(plane, new MatOfInt(0), new Mat(), hist, histSize, histRange, accumulate);
			Core.normalize(hist, hist, 3, histImage.rows(), Core.NORM_MINMAX);
			for (int i = 1; i < 256; i++) {
				Point p1 = new Point(bin_w * (i - 1), hist_h - Math.round(hist.get(i - 1, 0)[0]));
				Point p2 = new Point(bin_w * (i), hist_h - Math.round(hist.get(i, 0)[0]));
				Imgproc.line(histImage, p1, p2, scalars.get(index), 1, Core.LINE_AA, 0);	
				
//		        Imgproc.line(histImage, p1, p2, new Scalar(255, 0, 0), 2, 8, 0);
			}
			System.out.println(srcstr + ": Histogram generation for channel "+ index + " DONE");
			System.out.print(index);
			index++;
		}

		System.out.println(srcstr + ": Histogram image creation started");
		System.out.println(histstr + ": Histogram image name " + histImage.cols() + " " + histImage.rows());
		Imgcodecs.imwrite(System.getProperty("user.dir") + histstr, histImage);
		System.out.println(srcstr + ": Histogram image creation done");
	}
	
	public static void writeHistogram(Mat image, List<MatOfPoint> contour, int contourIdx, String dirname, String filename) {
		Mat mask = new Mat(image.rows(), image.cols(), image.type());
		
		Imgproc.drawContours(mask, contour, contourIdx, new Scalar(0, 0, 255), -1);
		
		Mat crop = new Mat(image.rows(), image.cols(), image.type());
		image.copyTo(crop, mask);
		Core.normalize(mask.clone(), mask, 0.0, 255.0, Core.NORM_MINMAX, CvType.CV_8UC1);

//		Imgcodecs.imwrite(System.getProperty("user.dir") + dirname + "cropped" + filename, crop);
//		Imgcodecs.imwrite(System.getProperty("user.dir") + dirname + "mask" + filename, mask);
		
		MatOfInt histSize = new MatOfInt(256);
		final MatOfFloat histRange = new MatOfFloat(0f, 256f);

		boolean accumulate = false;
		
		
		
		List<Mat> rgbPlanes = new ArrayList<Mat>();
		Core.split(crop, rgbPlanes);
		Map<String, Mat> hists = new HashMap<String, Mat>();
		hists.put("r", new Mat());
		hists.put("g", new Mat());
		hists.put("b", new Mat());
		int hist_w = 640;
		int hist_h = 480;
		long bin_w = Math.round((double) hist_w / 256);
		Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3);

		int index = 0;
		List<Scalar> scalars = new ArrayList<Scalar>();
		scalars.add(new Scalar(255,   0,   0));
		scalars.add(new Scalar(0,   255,   0));
		scalars.add(new Scalar(0,     0, 255));
		for (Mat hist: hists.values()) {
			List<Mat> plane = new ArrayList<Mat>();
			plane.add(rgbPlanes.get(index));
			Imgproc.calcHist(plane, new MatOfInt(0), new Mat(), hist, histSize, histRange, accumulate);
			Core.normalize(hist, hist, 3, histImage.rows(), Core.NORM_MINMAX);
			System.out.println("------------------");
			System.out.println(hist.dump());
			System.out.println(Initialize.matToJson(hist));
			System.out.println("------------------");
			for (int i = 1; i < 256; i++) {
				Point p1 = new Point(bin_w * (i - 1), hist_h - Math.round(hist.get(i - 1, 0)[0]));
				Point p2 = new Point(bin_w * (i), hist_h - Math.round(hist.get(i, 0)[0]));
				Imgproc.line(histImage, p1, p2, scalars.get(index), 1, Core.LINE_AA, 0);	
			}
			System.out.println(": Histogram generation for channel "+ index + " DONE");
			System.out.print(index);
			index++;
		}
//		Imgcodecs.imwrite(System.getProperty("user.dir") + dirname + filename, histImage);
	}
	
	public static String matToJson(Mat mat){        
	    JsonObject obj = new JsonObject();

	    if(mat.isContinuous()){
	        int cols = mat.cols();
	        int rows = mat.rows();
	        int elemSize = (int) mat.elemSize();    

	        float[] data = new float[cols * rows * elemSize];
	        mat.get(0, 0, data);
	        ByteBuffer buffer = ByteBuffer.allocate(data.length * 4);
	        String dataString = "";
	        
	        for (int i = 0 ; i < data.length; i++) {
	        	buffer.putFloat(data[i]);	
	        	dataString += data[i];
	        	if (i!= (data.length-1)) {
	        		dataString += "|";
	        	}
	        }

	        obj.addProperty("rows", mat.rows()); 
	        obj.addProperty("cols", mat.cols()); 
	        obj.addProperty("type", mat.type());

	        // We cannot set binary data to a json object, so:
	        // Encoding data byte array to Base64.
//	        String dataString = Base64.getEncoder().encodeToString(buffer.array());
	        
	        
	       

	        obj.addProperty("data", dataString);            

	        Gson gson = new Gson();
	        String json = gson.toJson(obj);

	        return json;
	    } else {
	        System.out.println("Mat not continuous.");
	    }
	    return "{}";
	}

	public static Mat matFromJson(String json){
	    JsonParser parser = new JsonParser();
	    JsonObject JsonObject = parser.parse(json).getAsJsonObject();

	    int rows = JsonObject.get("rows").getAsInt();
	    int cols = JsonObject.get("cols").getAsInt();
	    int type = JsonObject.get("type").getAsInt();

	    String dataString = JsonObject.get("data").getAsString();       
	    float[] data = new float[rows * cols * 1];
	    String[] data_b = dataString.split("\\|");
	    for(int i = 0; i < data_b.length; i++) {
	    	try {
	    		data[i] = Float.parseFloat(data_b[i]);
	    	}catch (Exception e) {
	    	}
	    }
	    		
	    Mat mat = new Mat(rows, cols, type);
	    mat.put(0, 0, data);

	    return mat;
	}
}