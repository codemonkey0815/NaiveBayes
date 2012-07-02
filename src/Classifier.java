import java.util.ArrayList;


public interface Classifier {

	
	public void trainClassifier(ArrayList<ArrayList<Integer>> trainingData, ArrayList<Integer> classes ) throws Exception;
	
	public ArrayList<Integer> classify(ArrayList<ArrayList<Integer>> example) throws Exception;
	
}
