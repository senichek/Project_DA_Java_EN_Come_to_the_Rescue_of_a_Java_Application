package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

/**
 * Anything that will read symptom data from a source The important part is, the
 * return value from the operation, which is a list of strings, that may contain
 * many duplications
 * 
 * The implementation does not need to order the list
 * 
 */
public interface DataProcessor {
	/**
	 * If no data is available, return an empty List
	 * @param filepath	Location of the file you want to read the data from.
	 * @return 			A raw listing of all Symptoms obtained from a data source, duplicates
	 *         are possible/probable
	 */
	List<String> getSymptoms(String filepath);

	/** 
	 * Calculates the number/amount of occurences of each symptom 
	 * @param symptoms 	The collection of symptoms.
	 * @return 			An alphabetically ordered Map of symptom as a key and an amount of occurences as a value.
	*/
	Map<String, Integer> calculateOccurence(List<String> symptoms);

	/** 
	 * Writes the data/collection to a file
	 * @param data 		A collection of symptoms.
	 * @param filePath	The path to the file you want to write to.
	*/
	void writeToFile(Map<String, Integer> data, String filePath);
}