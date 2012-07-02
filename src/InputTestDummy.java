import java.util.ArrayList;


public class InputTestDummy implements Input {

	
	static ArrayList<Integer> classes = new ArrayList<Integer>();
	static ArrayList<ArrayList<Integer>> examples = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> classIsZero = new ArrayList<Integer>();	
	ArrayList<ArrayList<Integer>> classIsZeroWrapper = new ArrayList<ArrayList<Integer>>();
	
	@Override
	public void loadTestData(String testFileName) {
		
		classIsZero.add(0);
		classIsZero.add(1);

		classIsZeroWrapper.add(classIsZero);
		
	}

	@Override
	public void loadTrainingData(String trainingFileName) {
		ArrayList<Integer> ex1 = new ArrayList<Integer>();
		ex1.add(0);
		ex1.add(1);
		
		examples.add(ex1);
		classes.add(0);
		
		ArrayList<Integer> ex2 = new ArrayList<Integer>();
		ex2.add(1);
		ex2.add(0);
		
		examples.add(ex2);
		classes.add(1);
		
	}

	@Override
	public ArrayList<ArrayList<Integer>> getTrainingDataAttributes() {
		return examples;
	}

	@Override
	public ArrayList<Integer> getTrainingDataClasses() {
		return classes;
	}

	@Override
	public ArrayList<ArrayList<Integer>> getTestDataAttributes() {
		// TODO Auto-generated method stub
		return classIsZeroWrapper;
	}

}
