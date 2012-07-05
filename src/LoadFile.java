import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Diese Klasse läd das Test und Trainingsfile. 
 * @author Keppi
 *
 */
public class LoadFile {

	
	
	private static BufferedReader reader;
	
	private ArrayList<String> trainingDataClassification 	= new ArrayList<String>();
	private ArrayList<ArrayList<String>> trainingDataWords 	= new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> testDataWords 		= new ArrayList<ArrayList<String>>();
	
	
	/**
	 * Diese Methode erstellt das LoadFile Objekt und läd die zwei Textdokumente.
	 * Die Daten könenn über die drei Getter abgerufen werden.
	 * @param pathToFiles
	 * @param testFileName
	 * @param trainingFileName
	 * @return
	 */
	public static LoadFile getInstance(String pathToFiles, String testFileName, String trainingFileName){
		LoadFile loadFile = new LoadFile();
		loadFile.loadTestData(loadFile.getReader(pathToFiles, testFileName));
		loadFile.loadTrainingData(loadFile.getReader(pathToFiles, trainingFileName));
		return loadFile;
	}
	
	/**
	 * Needs a filepath to the data folder as parameter.
	 */
	private BufferedReader getReader(String pathName, String filename) {
		File file = new File(pathName + "\\" + filename);
		try {	
			reader = new BufferedReader(new FileReader(file));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return reader;			
		}
	}
	
	/**
	 * Läd eine Zeile Text in die zwei Arrays für classification ( erstes Wort) und Wörter ( alle anderen Wörter)
	 * @param reader
	 */
	private void loadTrainingData(BufferedReader reader){
		try {
			String rawLine;
			while ( ( rawLine = reader.readLine() ) != null){
				String[] oneLineOfText = rawLine.split("\"");
				trainingDataClassification.add(oneLineOfText[0].trim());
				oneLineOfText = oneLineOfText[1].split(" ");
				ArrayList<String> oneParsedLineOfText = new ArrayList<String>();
				for(int i = 0 ; i < oneLineOfText.length ; i++){
					oneParsedLineOfText.add(oneLineOfText[i].trim());
				}
				trainingDataWords.add(oneParsedLineOfText);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Läd eine Zeile Text als Stringlsite in die Arraylist für Testdaten.
	 */
	private void loadTestData(BufferedReader reader){
		try {
			String rawLine;
			while ( ( rawLine = reader.readLine() ) != null){
				String[] oneLineOfText = rawLine.split(" ");
				ArrayList<String> oneParsedLineOfText = new ArrayList<String>();
				for(int i = 0 ; i < oneLineOfText.length ; i++){
					oneParsedLineOfText.add(oneLineOfText[i].trim());
				}
				testDataWords.add(oneParsedLineOfText);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	public ArrayList<String> getTrainingDataClassification() {
		return trainingDataClassification;
	}

	public ArrayList<ArrayList<String>> getTrainingDataWords() {
		return trainingDataWords;
	}

	public ArrayList<ArrayList<String>> getTestDataWords() {
		return testDataWords;
	}
	
	
}
