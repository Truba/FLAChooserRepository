package hr.fer.zemris.FLAChooser;

import java.awt.Toolkit;

public class AllTogetherGP {
	
	public static void main(String[] args) {
		Shifter.main(null);
		GeneticProgrammingTrainer.main(null);
		GeneticProgrammingTester.main(null);
		
		Toolkit.getDefaultToolkit().beep(); 

	}

}
