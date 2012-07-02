import java.util.ArrayList;


public interface Classifier {

	
	public void trainClassifier(ArrayList<ArrayList<Integer>> trainingData, ArrayList<Integer> classes ) throws Exception;
	
	public int classify(ArrayList<Integer> example) throws Exception;
	
	
	
}
