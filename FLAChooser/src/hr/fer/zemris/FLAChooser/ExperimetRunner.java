package hr.fer.zemris.FLAChooser;

import hr.fer.zemris.FLAChooser.Interfaces.IAmTrainer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ExperimetRunner {

	/**
	 * Outputs for neural network if files in rows:
	 * num_of_gueesed_classes num_of_guessed_nn_outputs avg_err_from_nn
	 */
	
	/**
	 * Outputs for genetic programming if files in rows:
	 * num_of_gueesed_classes avg_err_from_gp
	 */
	
	
	public static final IAmTrainer trainer = new NeuralNetworkTrainer();
	public static final int Times = 30;
	
	public static final String parametherThatIsBeingTested = "ClonAlg_ro"; //a.k.a. prefix to log files
	private static List<Double> getList() {
		List<Double> list = new ArrayList<Double>();
		//TODO set mutation list
		return list;
	}
	
	
	public static void main(String[] args) {
		List<Double> list = getList();
		for(double value : list){
			//TODO This line below is always in need of change
			Parameters.ro = value;
			String name = parametherThatIsBeingTested+"_"+value;
			runTimesTimes(name);
		}

	}


	private static void runTimesTimes(String name) {
		for(int i=0; i<Times; i++){
			String content =  trainer.getNumOfCorrect();
			printNextLine(name, content);
		}
		
	}

	private static void printNextLine(String name, String content){
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File(name), true));
			pw.println(content);
			pw.close();
		} catch (Exception e){e.printStackTrace();}
	}

}
