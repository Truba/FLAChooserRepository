package hr.fer.zemris.FLAChooser;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.IAmTrainer;
import hr.fer.zemris.FLAChooser.Interfaces.IDataModifiers;
import hr.fer.zemris.FLAChooser.Interfaces.IGetData;
import hr.fer.zemris.FLAChooser.Interfaces.INeuralNetwork;
import hr.fer.zemris.FLAChooser.Interfaces.ITrainingAlgorithm;
import hr.fer.zemris.FLAChooser.NeuralNetwork.NeuralNetwork;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class NeuralNetworkTrainer implements IAmTrainer{

	static INeuralNetwork neurNet;
	static IDataModifiers dataModify;
	static IGetData dataGeter;
	static ITrainingAlgorithm trainingAlg;
	static List<DataParticle> data;
	
	static double[] bestVector;
	
	static int numOfWeights;	
	
	public static void main(String[] args) {
		initAll();
		trainingAlg.start();		
		
		bestVector = trainingAlg.getBestVector();
		
		if(Parameters.shouldPrintBestToFile){
			printBestToFile(bestVector);
		}	
	}
	@Override
	public String getNumOfCorrect() {
		main(null);
		String resoults = neurNet.getResoults(bestVector);
		return resoults;
	}

	private static void initAll() {
		neurNet = new NeuralNetwork();
		neurNet.setNods(Parameters.NodsInLayers);
		numOfWeights = neurNet.getWeightsCount();
		
		dataGeter = Parameters.trainingSet;
		data = dataGeter.getData(Parameters.trainingDataPath);
		
		dataModify = Parameters.dataModifier;
		dataModify.modyfyData(data);
		neurNet.set(data,dataModify);
		
		trainingAlg = Parameters.trainAlg;
		trainingAlg.set(numOfWeights,neurNet);
	}
	
	private static void printBestToFile(double[] bestVector) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(Parameters.BestUnitPrintFile, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		for(int i=0; i < bestVector.length; i++) writer.print(bestVector[i]+" ");		
		writer.println();
		writer.println();
		writer.println(neurNet.show(bestVector));
		writer.close();
		
	}

}
