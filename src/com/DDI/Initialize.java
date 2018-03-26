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
	public static void main(String[] argsx) {
		// String[] args = {"generate", "/images/elsa.jpg", "/images/conv-elsa.jpg",
		// "/images/hist-elsa.jpg", "33333"};
		 String[] args = {"compare","0a3a164e821a-4f53-a825-98a3aec3b167","c4828efb1bf1-4b1c-b6d6-b670aa57df70","9cd1e8619759-4fde-b433-810965895976","7741354beaa4-42ee-ac49-1024415caaa4","83d19565ffc9-4c7a-bbae-82e142ab26f3","280c68ab595f-4831-9807-737325ef8a1e","2585c5887b16-47fb-a22c-ba9c3cf3197b","c9ff1fb58dd3-4785-a3b7-6124d64d0040","a33fdc88cc36-43b7-be54-54d44fa9ee24","3f247e217cae-48c3-8db1-392d168f64da","51616b971877-4747-ab41-a8d8b5f6df75","a31fcaf21a94-476f-81ca-4981c90ff1dd","db5bfbba06c5-45e6-a324-061526679dc9","2f0f121bb6b2-4cb9-9c98-4235cfa7d6ed","7caee2fd257c-4c62-8a6d-d41fff55a5bd","34a491845c04-4856-bda0-017f5e6fb1c8","e056786745fa-4d70-8cb6-5943ab04f15e","f49744194d02-4d0e-808c-88b91b2e85a5","ff1e17396e57-4f60-895a-6d2ff3a4506c","0a3a164e821a-4f53-a825-98a3aec3b167","6743e75b7c5e-4873-bbd4-cd6d074164e0","39291ce498c8-4d2f-bad2-1cddac2818b3","02879888e9c1-4a98-9a6a-c75be4f2acbe","5376521f51db-42c2-8f64-de23fe92dc96","ce0af9e49fc8-4609-ba4c-d03ff6f15e19","88838292f071-4c90-89b3-bc09409bc148","57fe874fbc92-44f0-9adf-52256f988e34","bd6b9f2d3821-429d-8fc5-004edb53c191","e52ed4466483-4a42-b88e-023c5cb90b1c","7418e4658902-431d-b7fb-e794a0af85a2","52bc29bcb0af-4496-be84-338d2af113a9","8a585974c668-4a24-b1c1-dfc5439f7b23","0a328dd18ec1-486b-9d01-3e8545ac0054","fa9310440f7d-4897-9a1c-cb1374bf49ac"};
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

		while (index < args.length) {
			JsonObject compareObjJSON = new JsonObject();
			try {
				List<Document> toCompareDoc = (List<Document>) imagesCollection.find(eq("_id", args[index]))
						.into(new ArrayList<Document>());
				Document toCompareImage = toCompareDoc.get(0);
				ArrayList<String> toCompareContours = (ArrayList<String>) toCompareImage.get("contours");
				int numberOfComparisons = 0;
			
				if (originalContours.size() < toCompareContours.size()) {
					numberOfComparisons = originalContours.size();
				} else {
					numberOfComparisons = toCompareContours.size();
				}
				
				compareObjJSON.addProperty("count_orig", originalContours.size());
				compareObjJSON.addProperty("count_target", toCompareContours.size());

				String comparisons_r = "[";
				String comparisons_g = "[";
				String comparisons_b = "[";
				
				for (int i = 0; i < numberOfComparisons; i++) {
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

		// Draw contours on the source image
		for (int contourIdx = 0; contourIdx < contours.size(); contourIdx++) {
			Imgproc.drawContours(src, contours, contourIdx, new Scalar(0, 0, 255), -1);
			String[] contours_str = Initialize.jsonOfContour(src, contours, contourIdx);
			Document contourDocument = new Document();
			contourDocument.append("_id", imageId + contourIdx);
			contourDocument.append("contours_r", contours_str[0]);
			contourDocument.append("contours_g", contours_str[1]);
			contourDocument.append("contours_b", contours_str[2]);
			contoursCollection.insertOne(contourDocument);
			contourIds.add(imageId + contourIdx);
		}
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