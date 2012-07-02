import java.util.ArrayList;


public class NaiveBaseImpl implements Classifier {

	private ArrayList<Integer> countClasses;
	//List contains all attributes
	//For each attribute these is one field per class for counting
	private ArrayList<ArrayList<Integer>> countAttributeByClass;
	
	
	private ArrayList<Double> probabilityClasses = new ArrayList<Double>();
	//Outer Array is for Atributes
	//Inner Array is for Classes
	//Value is Probability of Attribute by Class
	private ArrayList<ArrayList<Double>> conditionalProbabilityOfAttributeByClass = new ArrayList<ArrayList<Double>>();
	
	private boolean isTrained = false;
	
	
	
	
	@Override
	public void trainClassifier(ArrayList<ArrayList<Integer>> trainingData, ArrayList<Integer> classes) throws Exception {
		checkData(trainingData, classes);
		
		createArrayLists( trainingData,  classes);
		
		countAttributesAndClasses(trainingData, classes);
		
		calculateProbabilties();
		
		isTrained = true;
	}


	private void calculateProbabilties() {
		calcutateClassProbabilities();
		calculateAttributeProbababilitiesByClass();
	}


	private void calculateAttributeProbababilitiesByClass() {
		for(int classNumber = 0; classNumber< countClasses.size(); classNumber++){
			calculateAttributeProbabilityForOneClass(classNumber);
		}
	}


	private void calculateAttributeProbabilityForOneClass(int classNumber) {
		int totalAttributeCountForThisClass = 0;
		for(int attributeNumber = 0; attributeNumber < countAttributeByClass.size(); attributeNumber++){
			totalAttributeCountForThisClass += countAttributeByClass.get(attributeNumber).get(classNumber);
		}
		for(int attributeNumber = 0 ; attributeNumber < countAttributeByClass.size(); attributeNumber++){
			conditionalProbabilityOfAttributeByClass.get(attributeNumber).set(classNumber, (double)countAttributeByClass.get(attributeNumber).get(classNumber) / (double)totalAttributeCountForThisClass);
		}
	}


	private void calcutateClassProbabilities() {
		int totalClassCount = 0;
		for(int i = 0 ; i < countClasses.size(); i++){
			totalClassCount += countClasses.get(i);
		}
		for(int i = 0 ; i <countClasses.size(); i++){
			probabilityClasses.set( i, (double) countClasses.get(i) / (double) totalClassCount);
		} 
	}


	/**
	 * Just some presliminary consistency checks
	 * @param trainingData
	 * @param classes
	 * @throws Exception 
	 */
	private void checkData(ArrayList<ArrayList<Integer>> trainingData,
			ArrayList<Integer> classes) throws Exception {
		checkAttributeSizes(trainingData);
		checkExampleAndClassSize(trainingData, classes);
	}


	private void checkExampleAndClassSize(
			ArrayList<ArrayList<Integer>> trainingData,
			ArrayList<Integer> classes) throws Exception {
		if(trainingData.size() != classes.size()){
			throw new Exception("size of examples and of classes do not match");
		}
	}


	private void checkAttributeSizes(ArrayList<ArrayList<Integer>> trainingData)
			throws Exception {
		int attributeLength = trainingData.get(0).size();
		for(ArrayList<Integer> example: trainingData){
			if(example.size() != attributeLength){
				throw new Exception("not all examples have the same list of attributes");
			}
		}
	}


	private void countAttributesAndClasses(ArrayList<ArrayList<Integer>> trainingData, ArrayList<Integer> classes) {
		countAttributes(trainingData, classes);
		countClasses(classes);
		
	}


	private void countClasses(ArrayList<Integer> classes) {
		for(Integer exampleClass: classes){
			countClasses.set(exampleClass, countClasses.get(exampleClass) + 1 );
		}
	}


	private void countAttributes(ArrayList<ArrayList<Integer>> trainingData,
			ArrayList<Integer> classes) {
		for(int example = 0; example < trainingData.size(); example++){
			trainOneInstance(trainingData.get(example), classes.get(example));
		}
	}


	private void trainOneInstance(ArrayList<Integer> exampleAttributes, Integer exampleClass) {
		for(int attributeNumber = 0 ; attributeNumber < exampleAttributes.size(); attributeNumber++){
			addTheValuesOfThisAttributeToTheClassForThatAttribute(exampleClass, attributeNumber, exampleAttributes.get(attributeNumber));
		}
	}


	private void addTheValuesOfThisAttributeToTheClassForThatAttribute(
			Integer exampleClass, int attributeNumber, Integer attributeValue) {
		countAttributeByClass.get(attributeNumber).set(exampleClass, 
				( countAttributeByClass.get(attributeNumber).get(exampleClass) + attributeValue ) );
	}


	private void createArrayLists(ArrayList<ArrayList<Integer>> trainingData,
			ArrayList<Integer> classes) {
		countAttributeByClass = new ArrayList<ArrayList<Integer>>();
		countClasses = new ArrayList<Integer>();
		
		createClassCounter(classes);
		
		createCountAttributeByClass(trainingData, classes);
		
		createConditionalProbability();
		createClassProbability();
	}


	private void createConditionalProbability() {
		for(int i = 0 ; i < countAttributeByClass.size(); i++){
			ArrayList<Double> tmp = new ArrayList<Double>();
			for(int classSize = 0; classSize < countClasses.size(); classSize++){
				tmp.add(0.0);
			}
			conditionalProbabilityOfAttributeByClass.add(tmp);
		}
	}


	private void createClassProbability() {
		for(int i = 0 ; i < countClasses.size() ; i++){
			probabilityClasses.add(0.0);
		}
	}


	private void createCountAttributeByClass( ArrayList<ArrayList<Integer>> trainingData, ArrayList<Integer> classes) {
		for(int i = 0 ; i < trainingData.get(0).size(); i++){
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			for(int classSize = 0; classSize < countClasses.size(); classSize++){
				tmp.add(0);
			}
			countAttributeByClass.add(tmp);
		}
	}


	private void createClassCounter(ArrayList<Integer> classes) {
		int maxClass = 0 ; 
		for(int classNumber : classes){
			if(classNumber > maxClass){
				maxClass = classNumber;
			}
		}
		for(int i = 0 ; i < maxClass + 1 ; i++){
			countClasses.add(0);
		}
	}


	@Override
	public int classify(ArrayList<Integer> example) throws Exception {
		if(!isTrained){
			throw new Exception("Classifier not trained.");
		}
		
		
		// TODO Auto-generated method stub
		return 0;
	}

}
