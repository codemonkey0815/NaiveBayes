import java.util.ArrayList;

/**
 * Just a class for quick testing, Do please ignore, the code is abhorrent. 
 * @author Keppi
 *
 */
public class ClassifyTest {

	
	
	static ArrayList<Integer> classes = new ArrayList<Integer>();
	static ArrayList<ArrayList<Integer>> examples = new ArrayList<ArrayList<Integer>>();
	
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {


//		createTestData();
		trainPosteriorTest();
		Classifier classy  = new NaiveBaseImpl();
		classy.trainClassifier(examples, classes);
		
		ArrayList<Integer> classIsZero = new ArrayList<Integer>();
		classIsZero.add(0);
		classIsZero.add(1);
		ArrayList<ArrayList<Integer>> classIsZeroWrapper = new ArrayList<ArrayList<Integer>>();
		classIsZeroWrapper.add(classIsZero);
		ArrayList<Integer> classification = classy.classify(classIsZeroWrapper);
		if(classification.get(0) == 0){
			System.out.println("Eureka");
		}
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
	
	private static void trainPosteriorTest(){
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
	
	

	private static int randomInt(int range) {
		return (int) (Math.random()*range);
	}

}
