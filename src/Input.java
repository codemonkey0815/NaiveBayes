import java.util.ArrayList;


public interface Input {

	
	public Input getInstance(String pathToFiles, String testFileName, String trainingFileName, String outputFileName);
	
	
	/**
	 * @return
	 * This returns the training data in the following format:
	 * Each entry in the outer list represents one training example.
	 * Each example has a list of attributes. 
	 * The list is the same for every example.
	 * Each entry in the list states how often this attribute appears in the example.
	 */
	public ArrayList<ArrayList<Integer>> getTrainingDataAttributes();
	/**
	 * @return
	 * Each entry in the list represents one example.
	 * The number represents the class. 
	 * Classes are numbered from 0 to 3.
	 */
	
	public ArrayList<Integer> getTrainingDataClasses();
	/**
	 * @return
	 * This returns the test data in the following format:
	 * Each entry in the outer list represents one test example.
	 * Each example has a list of attributes. 
	 * The list is the same for every example.
	 * Each entry in the list states how often this attribute appears in the example.
	 */	
	public ArrayList<ArrayList<Integer>> getTestDataAttributes();
	
	public void writeToFile(ArrayList<Integer> classifications);
}
