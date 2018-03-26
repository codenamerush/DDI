package com.DDI;

import static com.mongodb.client.model.Filters.eq;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.opencv.core.Core;
import org.opencv.core.CvType;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Initialize {
	public static void main(String[] args) {
//		 String[] args = {"generate", "/images/elsa2.jpeg", "/images/conv-elsa2.jpeg", "/images/hist-elsa2.jpeg", "11111"};
//		 String[] args = {"compare","11111", "22222", "33333"};
		switch (args[0]) {
		case "generate":
			Initialize.generateContourHistograms(args[1], args[2], args[3], args[4]);
			break;
		case "compare":
//			Initialize.compare(args);
			System.out.println(Initialize.compare(args));
			break;
		default:

		}
	}

	@SuppressWarnings("unchecked")
	public static String compare(String[] args) {
		System.load("/home/" + System.getProperty("user.name") + "/libs/build/lib" + Core.NATIVE_LIBRARY_NAME + ".so");
		JsonObject obj = new JsonObject();
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017/loginapp");
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("loginapp");
		MongoCollection<Document> imagesCollection = database.getCollection("images");
		MongoCollection<Document> contoursCollection = database.getCollection("contours");
		int index = 2; // comparing to images start from index 2
		List<Document> documents = null;
		
		List<Document> originalDoc = (List<Document>) imagesCollection.find(eq("_id", args[1]))
				.into(new ArrayList<Document>());
		Document originalImage = originalDoc.get(0);
		ArrayList<String> originalContours = (ArrayList<String>) originalImage.get("contours");
		ArrayList<MatOfPoint> originalBigContours = new ArrayList<MatOfPoint>();
		originalBigContours.add(Initialize.contourFromString((String) originalImage.get("big_contours_0")));
		originalBigContours.add(Initialize.contourFromString((String) originalImage.get("big_contours_1")));
		originalBigContours.add(Initialize.contourFromString((String) originalImage.get("big_contours_2")));
		originalBigContours.add(Initialize.contourFromString((String) originalImage.get("big_contours_3")));
		originalBigContours.add(Initialize.contourFromString((String) originalImage.get("big_contours_4")));

		while (index < args.length) {
			JsonObject compareObjJSON = new JsonObject();
			try {
				List<Document> toCompareDoc = (List<Document>) imagesCollection.find(eq("_id", args[index]))
						.into(new ArrayList<Document>());
				Document toCompareImage = toCompareDoc.get(0);
				ArrayList<String> toCompareContours = (ArrayList<String>) toCompareImage.get("contours");
				
				ArrayList<MatOfPoint> toCompareBigContours = new ArrayList<MatOfPoint>();
				toCompareBigContours.add(Initialize.contourFromString((String) toCompareImage.get("big_contours_0")));
				toCompareBigContours.add(Initialize.contourFromString((String) toCompareImage.get("big_contours_1")));
				toCompareBigContours.add(Initialize.contourFromString((String) toCompareImage.get("big_contours_2")));
				toCompareBigContours.add(Initialize.contourFromString((String) toCompareImage.get("big_contours_3")));
				toCompareBigContours.add(Initialize.contourFromString((String) toCompareImage.get("big_contours_4")));
				
				int numberOfComparisons = 0;
			
				if (originalContours.size() < toCompareContours.size()) {
					numberOfComparisons = originalContours.size();
				} else {
					numberOfComparisons = toCompareContours.size();
				}
				
				compareObjJSON.addProperty("count_orig", originalContours.size());
				compareObjJSON.addProperty("count_target", toCompareContours.size());
				
				double contourScore = 0;
				
				for (int i=0; i < originalBigContours.size(); i++) {
					double bestMatch = 1000;
					for (int j=0; j < originalBigContours.size(); j++) {
						double match = Imgproc.matchShapes(originalBigContours.get(i), toCompareBigContours.get(j), 1, 0);
						if (match < bestMatch) {
							bestMatch = match;
						}
					}
					contourScore += bestMatch;
				}

				String comparisons_r = "[";
				String comparisons_g = "[";
				String comparisons_b = "[";
				
				for (int i = 0; i < originalContours.size(); i++) {
					List<Document> orig_contourDoc = (List<Document>) contoursCollection.find(eq("_id", originalContours.get(i)))
							.into(new ArrayList<Document>());
					Mat orig_r = Initialize.matFromJson((String) orig_contourDoc.get(0).get("contours_r"));
					Mat orig_g = Initialize.matFromJson((String) orig_contourDoc.get(0).get("contours_g"));
					Mat orig_b = Initialize.matFromJson((String) orig_contourDoc.get(0).get("contours_b"));

					List<Document> target_contourDoc = (List<Document>) contoursCollection.find(eq("_id", toCompareContours.get(i)))
							.into(new ArrayList<Document>());
					Mat target_r = Initialize.matFromJson((String) target_contourDoc.get(0).get("contours_r"));
					Mat target_g = Initialize.matFromJson((String) target_contourDoc.get(0).get("contours_g"));
					Mat target_b = Initialize.matFromJson((String) target_contourDoc.get(0).get("contours_b"));

					comparisons_r += Imgproc.compareHist(orig_r, target_r, Imgproc.HISTCMP_BHATTACHARYYA);
					comparisons_g += Imgproc.compareHist(orig_g, target_g, Imgproc.HISTCMP_BHATTACHARYYA);
					comparisons_b += Imgproc.compareHist(orig_b, target_b, Imgproc.HISTCMP_BHATTACHARYYA);

					if (i != (numberOfComparisons - 1)) {
						comparisons_r += ",";
						comparisons_g += ",";
						comparisons_b += ",";
					}
				}
				comparisons_r += "]";
				comparisons_g += "]";
				comparisons_b += "]";
				
				
				compareObjJSON.addProperty("score", contourScore);
				compareObjJSON.addProperty("r", comparisons_r);
				compareObjJSON.addProperty("g", comparisons_g);
				compareObjJSON.addProperty("b", comparisons_b);
				
				obj.add(args[index], compareObjJSON);
				
			} catch(Exception e) {
//				System.out.println("===>> Failure for index: " + index + " Identifier : " + args[index] );
			}
			index++;
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		mongoClient.close();
		return json;
	}
	
	public static MatOfPoint contourFromString(String str) {
		MatOfPoint contour = new MatOfPoint();
		String[] points = str.split("\\|");
		List<Point> pts = new ArrayList<Point>();
		for (int i = 0; i < points.length; i++) {
			String[] coords = points[i].split(",");
			double x = Double.parseDouble(coords[0]);
			double y = Double.parseDouble(coords[1]);
			pts.add(new Point(x,y));
		}
		contour.fromList(pts);
		return contour;
	}

	public static void generateContourHistograms(String srcstr, String contourstr, String histstr, String imageId) {
		MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017/loginapp");
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("loginapp");
		MongoCollection<Document> imagesCollection = database.getCollection("images");
		MongoCollection<Document> contoursCollection = database.getCollection("contours");
		Document document = new Document();

		System.load("/home/" + System.getProperty("user.name") + "/libs/build/lib" + Core.NATIVE_LIBRARY_NAME + ".so");
		Mat src = Imgcodecs.imread(System.getProperty("user.dir") + srcstr);

		document.append("img_path", srcstr);
		document.append("_id", imageId);

		Mat gray = new Mat();
		Imgproc.cvtColor(src, gray, Imgproc.COLOR_RGBA2GRAY);
		// Apply canny filter to detect edges
		Imgproc.Canny(gray, gray, 50, 200);

		// Find contours
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = new Mat();
		Imgproc.findContours(gray, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
		ArrayList<String> contourIds = new ArrayList<String>();

		Map<Double, MatOfPoint> maps = new HashMap<Double, MatOfPoint>();
		ArrayList<Double> list = new ArrayList<Double>();
		ArrayList<MatOfPoint> bigContours = new ArrayList<MatOfPoint>();
		// Draw contours on the source image
		for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++) {
			
			Imgproc.drawContours(src, contours, contourIdx, new Scalar(0, 0, 255), -1);
//			System.out.println(Imgproc.matchShapes(contours.get(contourIdx), pt, 1, 0.0));
			double area = Imgproc.contourArea(contours.get(contourIdx));
			maps.put(area, contours.get(contourIdx));
			list.add(area);
			
			String[] contours_str = Initialize.jsonOfContour(src, contours, contourIdx);
			Document contourDocument = new Document();
			contourDocument.append("_id", imageId + contourIdx);
			contourDocument.append("contours_r", contours_str[0]);
			contourDocument.append("contours_g", contours_str[1]);
			contourDocument.append("contours_b", contours_str[2]);
			contoursCollection.insertOne(contourDocument);
			contourIds.add(imageId + contourIdx);
		}
		
		String[] bigContourList = new String[5];
		Collections.sort(list, Collections.reverseOrder());
		bigContours.add(maps.get(list.get(0)));
		bigContours.add(maps.get(list.get(1)));
		bigContours.add(maps.get(list.get(2)));
		bigContours.add(maps.get(list.get(3)));
		bigContours.add(maps.get(list.get(4)));
		
		for (int i = 0; i < bigContours.size(); i++) {
			List<Point> LOP = bigContours.get(i).toList();
			for (int j = 0; j < LOP.size(); j++) {
				if (bigContourList[i] == null) {
					bigContourList[i] = "";
				}
				bigContourList[i] += LOP.get(j).x;
				bigContourList[i] += ",";
				bigContourList[i] += LOP.get(j).x;
				if ( j != LOP.size()-1) {
					bigContourList[i] += "|";
				}
			}
		}
		
		document.append("big_contours_0", bigContourList[0]);
		document.append("big_contours_1", bigContourList[1]);
		document.append("big_contours_2", bigContourList[2]);
		document.append("big_contours_3", bigContourList[3]);
		document.append("big_contours_4", bigContourList[4]);
		document.append("contours", contourIds);

		// Write contoured image
		document.append("img_contour_path", contourstr);
		Imgcodecs.imwrite(System.getProperty("user.dir") + contourstr, src);

		// Load fresh source image and convert to RGB
		Mat image = Imgcodecs.imread(System.getProperty("user.dir") + srcstr);
		Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB);

		// Split image channels into RGB planes
		List<Mat> rgbPlanes = new ArrayList<Mat>();
		Core.split(image, rgbPlanes);

		// Histogram size and range constants
		MatOfInt histSize = new MatOfInt(256);
		final MatOfFloat histRange = new MatOfFloat(0f, 256f);
		boolean accumulate = false;
		int hist_w = 640;
		int hist_h = 480;
		int index = 0;

		// Collector for storing histogram of each channel
		Map<String, Mat> hists = new HashMap<String, Mat>();
		hists.put("r", new Mat());
		hists.put("g", new Mat());
		hists.put("b", new Mat());
		long bin_w = Math.round((double) hist_w / 256);
		Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3);

		// Declare RGB scalars to be used for drawing RGB Histograms (This generates
		// colors for colored lines)
		List<Scalar> scalars = new ArrayList<Scalar>();
		scalars.add(new Scalar(255, 0, 0));
		scalars.add(new Scalar(0, 255, 0));
		scalars.add(new Scalar(0, 0, 255));

		for (Mat hist : hists.values()) {
			// Extract current channel only
			List<Mat> plane = new ArrayList<Mat>();
			plane.add(rgbPlanes.get(index));

			// Calculate histogram for current channel and normalize to histogram size and
			// range
			Imgproc.calcHist(plane, new MatOfInt(0), new Mat(), hist, histSize, histRange, accumulate);
			Core.normalize(hist, hist, 3, histImage.rows(), Core.NORM_MINMAX);

			// Draw lines connecting histogram points
			for (int i = 1; i < 256; i++) {
				Point p1 = new Point(bin_w * (i - 1), hist_h - Math.round(hist.get(i - 1, 0)[0]));
				Point p2 = new Point(bin_w * (i), hist_h - Math.round(hist.get(i, 0)[0]));
				Imgproc.line(histImage, p1, p2, scalars.get(index), 1, Core.LINE_AA, 0);
			}

			index++;
		}

		document.append("img_hist_path", histstr);
		// Write histogram image
		Imgcodecs.imwrite(System.getProperty("user.dir") + histstr, histImage);
		imagesCollection.insertOne(document);
		mongoClient.close();
		System.out.println("done");
	}

	public static String[] jsonOfContour(Mat image, List<MatOfPoint> contour, int contourIdx) {
		String[] JSONS = new String[3];

		// Prepare a mask
		Mat mask = new Mat(image.rows(), image.cols(), image.type());
		Imgproc.drawContours(mask, contour, contourIdx, new Scalar(0, 0, 255), -1);

		// crop Image
		Mat crop = new Mat(image.rows(), image.cols(), image.type());
		image.copyTo(crop, mask);

		// Split cropped contoured image into RGB planes
		List<Mat> rgbPlanes = new ArrayList<Mat>();
		Core.split(crop, rgbPlanes);

		// Histogram constants
		MatOfInt histSize = new MatOfInt(256);
		final MatOfFloat histRange = new MatOfFloat(0f, 256f);
		boolean accumulate = false;

		// histogram for each channel
		Map<String, Mat> hists = new HashMap<String, Mat>();
		hists.put("r", new Mat());
		hists.put("g", new Mat());
		hists.put("b", new Mat());

		int index = 0;
		for (Mat hist : hists.values()) {
			// Get current channel only
			List<Mat> plane = new ArrayList<Mat>();
			plane.add(rgbPlanes.get(index));

			// Calculate histogram and store in Mat plane object
			Imgproc.calcHist(plane, new MatOfInt(0), new Mat(), hist, histSize, histRange, accumulate);

			// Serialize mat to JSON and store in JSONS object
			JSONS[index] = Initialize.matToJson(hist);
			index++;
		}

		return JSONS;
	}

	public static String matToJson(Mat mat) {
		JsonObject obj = new JsonObject();

		if (mat.isContinuous()) {
			int cols = mat.cols();
			int rows = mat.rows();

			float[] data = new float[cols * rows];
			mat.get(0, 0, data);
			String dataString = "";

			for (int i = 0; i < data.length; i++) {
				dataString += data[i];
				if (i != (data.length - 1)) {
					dataString += "|";
				}
			}

			obj.addProperty("rows", mat.rows());
			obj.addProperty("cols", mat.cols());
			obj.addProperty("type", mat.type());
			obj.addProperty("data", dataString);

			Gson gson = new Gson();
			String json = gson.toJson(obj);

			return json;
		} else {
			System.out.println("Mat not continuous.");
		}
		return "{}";
	}

	public static Mat matFromJson(String json) {
		JsonParser parser = new JsonParser();
		JsonObject JsonObject = parser.parse(json).getAsJsonObject();

		int rows = JsonObject.get("rows").getAsInt();
		int cols = JsonObject.get("cols").getAsInt();
		int type = JsonObject.get("type").getAsInt();

		String dataString = JsonObject.get("data").getAsString();
		float[] data = new float[rows * cols * 1];
		String[] data_b = dataString.split("\\|");
		for (int i = 0; i < data_b.length; i++) {
			try {
				data[i] = Float.parseFloat(data_b[i]);
			} catch (Exception e) {
			}
		}

		Mat mat = new Mat(rows, cols, type);
		mat.put(0, 0, data);

		return mat;
	}
}