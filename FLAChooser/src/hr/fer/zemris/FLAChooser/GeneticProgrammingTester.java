package hr.fer.zemris.FLAChooser;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.GenProg.DecisionTreeGP;
import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.NodeCreator;
import hr.fer.zemris.FLAChooser.Interfaces.IGeneticProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class GeneticProgrammingTester {

	private static IGeneticProgram gp;
	private static INode bestRoot;
	private static List<DataParticle> data;
	
	public static void main(String[] args) {

		readBestVector(Parameters.BestUnitPrintFile);
		
		data = Parameters.testgSet.getData(Parameters.testDataPath);
		Parameters.dataModGP.modyfyData(data);
		
		gp = Parameters.geneticProgram;
		gp.setData(data);
		
		System.out.println(gp.show(bestRoot));

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
		
		String[] tree = line.trim().split(" +");
		
		LinkedList<String> nods = new LinkedList<>();
		for(String e: tree) nods.add(e);
		
		bestRoot = (Parameters.geneticProgram instanceof DecisionTreeGP) ? NodeCreator.create(nods) : hr.fer.zemris.FLAChooser.GenProg.SymRegr.NodeCreator.create(nods);
		
	}

}
