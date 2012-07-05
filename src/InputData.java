import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class InputData implements Input{

	ArrayList<Integer> classifications = new ArrayList<Integer>();
	ArrayList<ArrayList<Integer>> trainingDataAttributes = new ArrayList<ArrayList<Integer>>();
	ArrayList<ArrayList<Integer>> testDataAttributes = new ArrayList<ArrayList<Integer>>();
	LoadFile loadFile ;
	
	ArrayList<String> wordsInOrder = new ArrayList<String>();
	ArrayList<Integer> numberOfRepetitionsByWord = new ArrayList<Integer>();
	
	
	
	@Override
	public Input getInstance(String pathToFiles, String testFileName, String trainingFileName) {
		InputData inputData = new InputData();
		inputData.instanitate(pathToFiles, testFileName, trainingFileName);
		return inputData;
	}
	
	
	

	private void instanitate(String pathToFiles, String testFileName,
			String trainingFileName) {
		loadFile = LoadFile.getInstance(pathToFiles, testFileName, trainingFileName);
		parseClassifications();
		countAndOrderWordsInTrainingSample();
		
		sarahsWordmagic();
		
		trainingDataAttributes = encodeOneTextFile(loadFile.getTrainingDataWords());
		testDataAttributes = encodeOneTextFile(loadFile.getTestDataWords());
	}


	/**
	 * Hier werden irgendwann total tolle vorverarbeitungen auf der Liste der beotbachteten Wörter gemacht.
	 * Einfügen sortieren, Filtern einfach alles. Der Rest müsste danach einfach weiter funktinieren.
	 * 
	 * Wichtig hier: 
	 * words in Order sind die gefundenen Wörter im Trainings data set.
	 * numberOfRepetitionsByWord ist die Zahl der Wiederholungen pro wort.
	 * Vorsicht beide Arrays imemr synchron ändern.
	 * 
	 */
	private void sarahsWordmagic() {
		// TODO Auto-generated method stub
		
	}




	private ArrayList<ArrayList<java.lang.Integer>> encodeOneTextFile(ArrayList<ArrayList<java.lang.String>> dataWords) {
		ArrayList<ArrayList<Integer>> encodedText = new ArrayList<ArrayList<Integer>>();
		
		for ( ArrayList<String> textRow : dataWords){
			ArrayList<Integer> encodedTextRow = new ArrayList<Integer>();
			for( int i = 0 ; i < wordsInOrder.size(); i++){
				encodedTextRow.add(0);
			}
			for( String word : textRow){
				int wordPosition = wordsInOrder.lastIndexOf(word);
				if(wordPosition != -1){
					encodedTextRow.set(wordPosition, encodedTextRow.get(wordPosition) + 1 );
				}
			}

			encodedText.add(encodedTextRow);
		}
		
		return encodedText;
	}




	private void countAndOrderWordsInTrainingSample() {
		Map<String, Integer> wordMap = createWordNumberMapping();
		SortedSet<Map.Entry<String,Integer>> wordsSortedByNumberOfRepetitionsAscending= entriesSortedByValues(wordMap);
		setWordsInOrderAndNumberOfRepetitionsPerWord(wordsSortedByNumberOfRepetitionsAscending);
	}

	
	private void setWordsInOrderAndNumberOfRepetitionsPerWord(
			SortedSet<Entry<java.lang.String, java.lang.Integer>> wordsSortedByNumberOfRepetitionsAscending) {
		for(Map.Entry<String, Integer> entry : wordsSortedByNumberOfRepetitionsAscending){
			wordsInOrder.add(entry.getKey());
			numberOfRepetitionsByWord.add(entry.getValue());
		}
	}


	private Map<java.lang.String, java.lang.Integer> createWordNumberMapping() {
		ArrayList<ArrayList<String>> allWords = loadFile.getTrainingDataWords();
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		for(ArrayList<String> lineOfText : allWords){
			for(String word: lineOfText){
				if(wordMap.containsKey(word)){
					wordMap.put(word, wordMap.get(word) + 1);
				} else{
					wordMap.put(word, 1);
				}
			}
		}
		return wordMap;
	}


	/**
	 * 
	 * @param entries sorted by the number of repetitions, starting with the least often.
	 * @return
	 */
	static <String,Integer>SortedSet<Map.Entry<String,Integer>> entriesSortedByValues(Map<String,Integer> map) {
	    SortedSet<Map.Entry<String,Integer>> sortedEntries = new TreeSet<Map.Entry<String,Integer>>(
	        new Comparator<Map.Entry<String,Integer>>() {
	            @Override public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
	                return ((java.lang.Integer) e1.getValue()).compareTo((java.lang.Integer) e2.getValue());
	            }
	        }
	    );
	    sortedEntries.addAll(map.entrySet());
	    return sortedEntries;
	}

	/**
	 * converts classes from String to int beginning with class A = 0 
	 */
	private void parseClassifications() {
		ArrayList<String> classificationStrings = loadFile.getTrainingDataClassification();
		classifications = new ArrayList<Integer>();
		
		for(String classificationString: classificationStrings){
			if(classificationString.equals("A")){
				classifications.add(0);
			}
			if(classificationString.equals("B")){
				classifications.add(1);
			}
			if(classificationString.equals("E")){
				classifications.add(2);
			}
			if(classificationString.equals("V")){
				classifications.add(3);
			}
		}
	}




	@Override
	public ArrayList<ArrayList<Integer>> getTrainingDataAttributes() {
		return trainingDataAttributes;
	}

	@Override
	public ArrayList<Integer> getTrainingDataClasses() {
		return classifications;
	}

	@Override
	public ArrayList<ArrayList<Integer>> getTestDataAttributes() {
		return testDataAttributes;
	}

	@Override
	public void writeToFile(ArrayList<Integer> classifications) {
		// TODO Auto-generated method stub
		
	}

}
