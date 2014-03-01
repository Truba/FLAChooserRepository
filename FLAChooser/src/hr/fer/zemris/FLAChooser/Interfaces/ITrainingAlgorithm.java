package hr.fer.zemris.FLAChooser.Interfaces;


public interface ITrainingAlgorithm {

	void set(int numOfWeights,	INeuralNetwork neurNetow);

	void start();

	double[] getBestVector();
	
	

}
