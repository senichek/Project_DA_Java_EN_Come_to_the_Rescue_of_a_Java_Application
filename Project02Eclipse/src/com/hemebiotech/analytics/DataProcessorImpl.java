package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/** 
 * Implementation of DataProcessor Interface
*/
public class DataProcessorImpl implements DataProcessor {

	public DataProcessorImpl() {
	}

	/**
	 * If no data is available, return an empty List
	 * @param filepath	Location of the file you want to read the data from.
	 * @return 			A raw listing of all Symptoms obtained from a data source, duplicates
	 *         			are possible/probable
	 */
	@Override
	public List<String> getSymptoms(String filepath) {
		BufferedReader reader = null;
		ArrayList<String> result = new ArrayList<String>();

		if (filepath != null) {
			try {
				reader = new BufferedReader(new FileReader(filepath));
				String line = reader.readLine();

				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					reader.close();
				} catch (IOException e) {
					System.out.println("Error closing BufferedReader.");
					e.printStackTrace();
				}
			}
		}

		Collections.sort(result); // Sort alphabetically

		return result;
	}

	/** 
	 * Calculates the number/amount of occurences of each symptom 
	 * @param symptoms 	The collection of symptoms.
	 * @return 			An alphabetically ordered Map of symptom as a key and an amount of occurences as a value.
	*/
	@Override
	public Map<String, Integer> calculateOccurence(List<String> symptoms) {
		// LinkedHashMap maintains insertion order, so that the alphabetical order of
		// symptoms remains preserved;
		Map<String, Integer> result = new LinkedHashMap<>();

		symptoms.forEach(symptomName -> {
			int occurrences = Collections.frequency(symptoms, symptomName);
			result.put(symptomName, occurrences);
		});
		return result;
	}

	/** 
	 * Writes the data/collection to a file
	 * @param data 		A collection of symptoms.
	 * @param filePath	The path to the file you want to write to.
	*/
	@Override
	public void writeToFile(Map<String, Integer> data, String filepath) {
		File file = new File(filepath);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;

		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);

			for (Map.Entry<String, Integer> entry : data.entrySet()) {
				bufferedWriter.write(entry.getKey() + "=" + entry.getValue());
				bufferedWriter.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error closing FileWriter or BufferedWriter.");
				e.printStackTrace();
			}
		}
		System.out.println("The data has been written to file");
	}
}
