import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class NaiveBayesMain {

	private static String testFileName = "tst.txt";
	private static String trainingFileName = "trg.txt";
	private static String outputFileName = "out.txt";
	private static String pathToFiles = "E:";
	
	private static boolean multi = true;
	
	private static Input input = new InputData();	
	private static Classifier classifier = new NaiveBaseImpl();
	
	/**
	 * @param 
	 * else default values apply
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		parseArgs(args);
		
		input = input.getInstance(pathToFiles, testFileName, trainingFileName, outputFileName);
		
		if(!multi){
			flattenInputData();
		}
		
		classifier.trainClassifier(input.getTrainingDataAttributes(), input.getTrainingDataClasses());
		ArrayList<Integer> classifications = classifier.classify(input.getTestDataAttributes());
		
		input.writeToFile(classifications);
		
		if(testFileName.equals(trainingFileName)){
			double acc = calculateAccuracy(classifications);
			System.out.println("The prediction accuracy of this test run is: " + acc);
		}
		
		System.out.println("finished");
	}

	private static void flattenInputData() {
		
		for(ArrayList<Integer> testLine : input.getTestDataAttributes()){
			for(Integer attribute : testLine){
				attribute = Math.min(1, attribute);
			}
		}
		for(ArrayList<Integer> testLine : input.getTrainingDataAttributes()){
			for(Integer attribute : testLine){
				attribute = Math.min(1, attribute);
			}
		}
		
	}

	private static double  calculateAccuracy(ArrayList<Integer> classifications) {
		ArrayList<Integer> originalClassification = input.getTrainingDataClasses();
		int countCorrect = 0;
		
		for ( int i  = 0 ; i < originalClassification.size() ; i++){
			if(originalClassification.get(i).equals(classifications.get(i))){
				countCorrect++;
			}
		}
		
		return ((double) countCorrect) / ((double) classifications.size() );
	}

	private static void parseArgs(String[] args) {
		List<String> argsList = Arrays.asList(args);
		for(int i = 0 ; i <  (argsList.size() - 1 ) ; i ++){
			List<String> nextTuple = argsList.subList(i, i+2);
			parseArgsTuple(nextTuple);
		}
	}
	
	/**
	 * parses one tuple from args.
	 * @param nextTuple
	 */
	private static void parseArgsTuple(List<String> nextTuple) {
		if(nextTuple.get(0).equals("-tst")){
			testFileName = nextTuple.get(1);
		}
		if(nextTuple.get(0).equals("-trg")){
			trainingFileName = nextTuple.get(1);
		}
		if(nextTuple.get(0).equals("-out")){
			outputFileName = nextTuple.get(1);
		}
		if(nextTuple.get(0).equals("-path")){
			pathToFiles = nextTuple.get(1);
		}
		if(nextTuple.get(0).equals("-multi")){
			if(nextTuple.get(1).equals("t")){
				multi = true;
			}
			if(nextTuple.get(1).equals("f")){
				multi = false;
			}
		}
		if(nextTuple.get(0).equals("-help")){
			System.out.println("-tst: name of testfile");
			System.out.println("-trg: name of the trainings file");			
			System.out.println("-out: name of the outputfile");	
			System.out.println("-path: path to all relevant files");
			System.out.println("-multi: use multinomial model");
		}
	}
	
	
}
