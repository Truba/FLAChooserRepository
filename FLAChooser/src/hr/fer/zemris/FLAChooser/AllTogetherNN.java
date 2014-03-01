package hr.fer.zemris.FLAChooser;

import java.awt.Toolkit;

public class AllTogetherNN {

	public static void main(String[] args) {
		Shifter.main(null);
		NeuralNetworkTrainer.main(null);
		NeuralNetworkTester.main(null);
		
		Toolkit.getDefaultToolkit().beep(); 
		 

	}

}
