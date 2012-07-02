import java.util.ArrayList;


public class ClassifyTest {

	
	
	static ArrayList<Integer> classes = new ArrayList<Integer>();
	static ArrayList<ArrayList<Integer>> examples = new ArrayList<ArrayList<Integer>>();
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {


		createTestData();
		test();
		
		
	}

	private static void test() throws Exception {
		Classifier classy  = new NaiveBaseImpl();
		classy.trainClassifier(examples, classes);
	}

	private static void createTestData() {
		int classSize = 2;
		int attSize = 3;
		int numberOfExamples = 4;
		
		for(int i = 0 ; i <numberOfExamples ; i++){
			
			//createExample
			ArrayList<Integer> attributes = new ArrayList<Integer>();
			for(int j = 0 ; j <attSize ; j++){
				attributes.add(j);
			}
			
			examples.add(attributes);
			classes.add(randomInt(classSize));
			
		}
		
		// TODO Auto-generated method stub
		
	}

	private static int randomInt(int range) {
		return (int) (Math.random()*range);
	}

}
