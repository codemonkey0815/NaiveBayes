import java.util.ArrayList;


public class NaiveBayesMain {

	private static String testFileName = "tst.txt";
	private static String trainingFileName = "trg.txt";
	private static String outputFileName = "out.txt";
	private static String pathToFiles = "E:";
	
	private static Input input = new InputData();	
	private static Classifier classifier = new NaiveBaseImpl();
	
	/**
	 * @param 
	 * else default values appy
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		input = input.getInstance(pathToFiles, testFileName, trainingFileName);
		
		classifier.trainClassifier(input.getTrainingDataAttributes(), input.getTrainingDataClasses());
		ArrayList<Integer> classifications = classifier.classify(input.getTestDataAttributes());
		
		input.writeToFile(classifications);
		System.out.println("finished");
	}
}
