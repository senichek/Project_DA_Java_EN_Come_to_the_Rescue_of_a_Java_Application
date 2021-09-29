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

public class DataProcessorImpl implements DataProcessor {

	/*
	 * Removed "Parameterized Constructor", if we pass the filepath to the function
	 * instead of the Constructor it will let us be more flexible, I guess;
	 */

	public DataProcessorImpl() {
	}

	@Override
	public List<String> GetSymptoms(String filepath) {
		ArrayList<String> result = new ArrayList<String>();

		if (filepath != null) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(filepath));
				String line = reader.readLine();

				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		Collections.sort(result); // Sort alphabetically

		return result;
	}

	@Override
	public Map<String, Integer> CalculateOccurence(List<String> symptoms) {
		// LinkedHashMap maintains insertion order, so that the alphabetical order of
		// symptoms remains preserved;
		Map<String, Integer> result = new LinkedHashMap<>();

		symptoms.forEach(symptomName -> {
			int occurrences = Collections.frequency(symptoms, symptomName);
			result.put(symptomName, occurrences);
		});
		return result;
	}

	@Override
	public void WriteToFile(Map<String, Integer> data, String filepath) throws IOException {
		File file = new File(filepath);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		data.forEach((key, value) -> {
			try {
				bufferedWriter.write(key + "=" + value);
				bufferedWriter.newLine();
			} catch (IOException e) {
				System.out.println("Couldn't write to file. Error!");
				e.printStackTrace();
			}
		});

		bufferedWriter.close();
		fileWriter.close();
		System.out.println("The data has been written to file");
	}
}
