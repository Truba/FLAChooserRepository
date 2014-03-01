package hr.fer.zemris.FLAChooser;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.Interfaces.IGeneticProgram;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class GeneticProgrammingTrainer {

	private static List<DataParticle> data;
	private static IGeneticProgram gp;
	
	
	public static void main(String[] args) {
		data =  Parameters.trainingSet.getData(Parameters.trainingDataPath);
		Parameters.dataModGP.modyfyData(data);
		
		gp = Parameters.geneticProgram;
		gp.setData(data);
		
		gp.start();
		
		INode bestRoot = gp.getBestRoot();
		printBestToFile(bestRoot);

	}


	private static void printBestToFile(INode bestRoot) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(Parameters.BestUnitPrintFile, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		writer.print(bestRoot.nodesAsString());		
		writer.println();
		writer.println();
		writer.println(gp.show(bestRoot));
		writer.close();
		
	}

}
