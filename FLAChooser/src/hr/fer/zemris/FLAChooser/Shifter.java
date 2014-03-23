package hr.fer.zemris.FLAChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Shifter {

	private static final String start = Parameters.initialData;
	private static final String trainingFile = Parameters.trainingDataPath;
	private static final String testFile = Parameters.testDataPath;

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<>();

		// reading
		Scanner sc = null;
		try {
			sc = new Scanner(new File(start), "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("File not fould");
			e.printStackTrace();
		}
		while (sc.hasNextLine()) {
			list.add(sc.nextLine().trim());
		}
		sc.close();
		//

		String[] training = new String[(int) Math
				.round(Parameters.percForTrainingSet * list.size())];
		String[] test = new String[list.size() - training.length];

		if (Parameters.shiftRandom) {
			Random rand = new Random();
			// Training set
			for (int i = 0; i < training.length; i++) {
				int ind = rand.nextInt(list.size());
				training[i] = list.get(ind);
				list.remove(ind);
			}

			// Test set
			for (int i = 0; i < test.length; i++) {
				int ind = rand.nextInt(list.size());
				test[i] = list.get(ind);
				list.remove(ind);
			}
		} else if (!Parameters.lastToTraining) {
			// Training set
			for (int i = 0; i < training.length; i++) {
				int ind = 0;
				training[i] = list.get(ind);
				list.remove(ind);
			}

			// Test set
			for (int i = 0; i < test.length; i++) {
				int ind = 0;
				test[i] = list.get(ind);
				list.remove(ind);
			}
		} else {
			// Training set
			for (int i = 0; i < training.length; i++) {
				int ind = list.size()-1;
				training[i] = list.get(ind);
				list.remove(ind);
			}

			// Test set
			for (int i = 0; i < test.length; i++) {
				int ind = list.size()-1;
				test[i] = list.get(ind);
				list.remove(ind);
			}
		}

		printSet(training, trainingFile);
		printSet(test, testFile);

		System.out.println("Done");

	}

	private static void printSet(String[] set, String file) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(file, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < set.length; i++) {
			writer.println(set[i]);
		}
		writer.close();

	}

}
