package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public class AnalyticsCounter {

	public static void main(String args[]) throws Exception {

		DataProcessor dataProcessor = new DataProcessorImpl();

		List<String> symptoms = dataProcessor.getSymptoms("Project02Eclipse/symptoms.txt");

		Map<String, Integer> processedData = dataProcessor.calculateOccurence(symptoms);

		dataProcessor.writeToFile(processedData, "Project02Eclipse/results.out");
	}
}
