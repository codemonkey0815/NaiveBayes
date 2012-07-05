import java.util.ArrayList;


public class NaiveBayesMain {

	private static String testFileName = "tst.txt";
	private static String trainingFileName = "trg.txt";
	private static String outputFileName = "out.txt";
	
	private static Input input = new InputTestDummy();	
	private static Classifier classifier = new NaiveBaseImpl();
	
	/**
	 * @param 
	 * else default values appy
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
//		LoadFile load = LoadFile.getInstance("E:", "tst.txt", "trg.txt");
		
		
		input.loadTestData(testFileName);
		input.loadTrainingData(trainingFileName);
		
		classifier.trainClassifier(input.getTrainingDataAttributes(), input.getTrainingDataClasses());
		ArrayList<Integer> classifications = classifier.classify(input.getTestDataAttributes());
		
		input.writeToFile(classifications);
		System.out.println("finished");
	}
}
