package hr.fer.zemris.FLAChooser.Interfaces;

import hr.fer.zemris.FLAChooser.Data.DataParticle;

import java.util.List;

public interface INeuralNetwork {
	
	public int getWeightsCount();

	public double calcANN(double[] vector);

	public void setNods(int[] nodsInLayers);

	public void set(List<DataParticle> data, IDataModifiers dataModify);
	
	public StringBuilder show(double[] vector);
	
	public List<DataParticle> getData();

}
