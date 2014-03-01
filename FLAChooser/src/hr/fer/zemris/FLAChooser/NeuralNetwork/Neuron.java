package hr.fer.zemris.FLAChooser.NeuralNetwork;

public class Neuron {

	public double net;
	public double fnet;

	public Neuron() {

	}

	public Neuron(double net) {
		this.net = net;

	}

	public Neuron(double net, double fnet) {
		this.net = net;
		this.fnet = fnet;
	}

}