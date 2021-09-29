package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;

public class AnalyticsCounter {

	public static void main(String args[]) throws Exception {

		DataProcessor dataProcessor = new DataProcessorImpl();

		List<String> symptoms = dataProcessor.GetSymptoms("Project02Eclipse/symptoms.txt");

		Map<String, Integer> processedData = dataProcessor.CalculateOccurence(symptoms);

		dataProcessor.WriteToFile(processedData, "Project02Eclipse/results.out");
	}
}
