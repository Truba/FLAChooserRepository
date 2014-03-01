package hr.fer.zemris.FLAChooser;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.NeuralNetwork.NeuralNetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class NeuralNetworkTester {

	private static NeuralNetwork neurNet;
	private static double[] bestVector;
	private static List<DataParticle> data;
	

	public static void main(String[] args) {
		
		
		readBestVector(Parameters.BestUnitPrintFile);
		
		data = Parameters.testgSet.getData(Parameters.testDataPath);
		Parameters.dataModifier.modyfyData(data);
		
		neurNet = new NeuralNetwork();
		neurNet.setNods(Parameters.NodsInLayers);
		neurNet.set(data, Parameters.dataModifier);
		
		System.out.println(neurNet.show(bestVector));		

	}

	private static void readBestVector(String file) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(file), "UTF-8");
		} catch (FileNotFoundException e) {
			System.out.println("File not fould");
			e.printStackTrace();
		}
		String line = sc.nextLine();
		sc.close();
		String[] strVector = line.trim().split(" +");
		bestVector = new double[strVector.length];
		for(int i=0; i<strVector.length; i++){
			bestVector[i] = Double.parseDouble(strVector[i]);
			//System.out.println(bestVector[i]);
		}
		

	}

}
