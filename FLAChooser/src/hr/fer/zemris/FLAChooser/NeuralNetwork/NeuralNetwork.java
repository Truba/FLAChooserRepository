package hr.fer.zemris.FLAChooser.NeuralNetwork;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.IDataModifiers;
import hr.fer.zemris.FLAChooser.Interfaces.INeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork implements INeuralNetwork{
	
	private int[] neur;
	private IDataModifiers dataModify;
	private ArrayList<Neuron[]> neurons;
	private List<DataParticle> data;
	
	
	@Override
	public void setNods(int[] nodsInLayers) {
		this.neur = nodsInLayers;
		neurons = new ArrayList<>();
		for(int i=0; i<neur.length; i++){
			neurons.add(new Neuron[neur[i]]);
		}
		
	}

	@Override
	public int getWeightsCount() {
		int result = 0;
		for(int i=1; i<neur.length; i++){
			result += neur[i]*neur[i-1];
			result += neur[i];
		}
		return result;
	}

	@Override
	public void set(List<DataParticle> data, IDataModifiers dataModify) {
		this.dataModify = dataModify;
		this.data = data;
		
	}


	@Override
	public double calcANN(double[] weights) {
		double err = 0;
		
		for(int i=0; i<data.size();i++){
			
			double[] ouputs = calcOutputs(data.get(i).xVector, weights, data.get(i).yVector.length);
			
			for(int j=0; j<data.get(i).yVector.length;j++){
				err += (data.get(i).yVector[j]-ouputs[j])*(data.get(i).yVector[j]-ouputs[j]);
				//if(i==0) System.out.println(ouputs[j]);
			}
		}
		err /= data.size();
		
		return err;
	}

	private double[] calcOutputs(double[] inputs, double[] weights, int yLength) {
		double[] outputs = new double[yLength];
		
		putInputs(inputs);
		calcNeurons(weights);
		
		Neuron[] outLayer = neurons.get(neurons.size()-1);
		for(int i=0; i<outputs.length; i++){
			outputs[i] = outLayer[i].fnet;
		}
				
		return outputs;
	}

	private void calcNeurons(double[] weights) {
		int wCout=0;
		for(int i=1; i < neurons.size(); i++){
			for(int j=0; j < neurons.get(i).length; j++){
				Neuron neuro = new Neuron(weights[wCout]);
				wCout++;
				for(int k=0; k<neurons.get(i-1).length; k++){
					neuro.net += neurons.get(i-1)[k].fnet * weights[wCout];
					wCout++;
				}
				neuro.fnet = sigmFunct(neuro.net);
				neurons.get(i)[j] = neuro;
			}
		}
		
	}

	private double sigmFunct(double x) {
		//f(x)=1.0/(1.0+e^(-x))
		double result = 1.0/(1.0+Math.exp(-x));
		return result;
	}

	private void putInputs(double[] inputs) {
		for(int i=0; i<inputs.length; i++){
			neurons.get(0)[i] = new Neuron(inputs[i], inputs[i]);
			//System.out.println(inputs[i]);
		}
		
	}

	@Override
	public StringBuilder show(double[] x) {
		int guess = 0;
		boolean guessBool;
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<data.size();i++){
			
			double[] outputs = calcOutputs(data.get(i).xVector, x, data.get(i).yVector.length);
			
			String y = "(";
			String t = "(";
			
			for(int j=0; j<data.get(i).yVector.length; j++){
				y +=dataModify.modify(outputs[j]);
				t +=dataModify.modify(data.get(i).yVector[j]);
			}
			
			guessBool = y.equals(t);
			if(guessBool) guess++;
			sb.append(	"Test: "+(i+1)+" t="+t+"), y="+y+") EQU: "+guessBool	).append("\n");
			
		}	
		sb.append("Num of correct: "+ guess+", num of false: "+(data.size()-guess));
		
		return sb;
		
	}

	@Override
	public List<DataParticle> getData() {
		return data;
	}

	

	


	
	
	

}
