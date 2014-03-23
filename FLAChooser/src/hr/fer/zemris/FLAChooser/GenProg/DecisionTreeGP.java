package hr.fer.zemris.FLAChooser.GenProg;

import hr.fer.zemris.FLAChooser.Parameters;
import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.NodeCreator;
import hr.fer.zemris.FLAChooser.Interfaces.IGeneticProgram;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DecisionTreeGP implements IGeneticProgram {
	
	private List<DataParticle> data;
	private int maxGenNoChandge;
	private int popNumber;
	private double minFitnes;
	
	private static int Turnament;
	public double mut;
	public double mockTur;
	public double plagiranje;
	public int maxCvorova;
	public int maxDublina;
	public int pocetnaDubina;	
	
	private Unit best;	
	private Random rand;
	
	private List<DataParticle> crossValidationData;
	

	@Override
	public void setData(List<DataParticle> data) {
		this.data = data;
		
	}

	@Override
	public void start() {
		maxGenNoChandge = Parameters.MaxGenNoChaindge;
		popNumber = Parameters.PopSize;
		minFitnes = Parameters.minFitnessToStop;
		Turnament = Parameters.Turnament;
		mut = Parameters.Mutation;
		mockTur = Parameters.MockTournament;
		plagiranje = Parameters.Plagiary;
		maxCvorova = Parameters.maxNodes;
		maxDublina = Parameters.maxDepth;
		pocetnaDubina = Parameters.initDepth;
		
		rand = new Random();
		if(Parameters.enableCrossVal){
			crossValidationData = Parameters.testgSet.getData(Parameters.testDataPath);
			Parameters.dataModGP.modyfyData(crossValidationData);
			File f = new File(Parameters.CrossValidationErrorFile);
			f.delete();
		}
		algorithm();
		
	}

	private void algorithm() {
		ArrayList<Unit> pop = initialPopulation();
		evaluate(pop);
		best = getBest(pop);
		System.out.println("Error: "+best.fitnes+" Iter: 0 (inital dataset)");
		if (Parameters.enableCrossVal) testSetIt();
		for(int iter = 0, iterCh = 0; iterCh < maxGenNoChandge*popNumber && best.fitnes > minFitnes; iter++, iterCh++){
			
			Unit child = crossMutEval(pop);
			int deadMenId = getLoserId(pop);
			
			pop.set(deadMenId, child);
			
			
			if(child.fitnes < best.fitnes){
				best = child;
				System.out.println("Error: "+child.fitnes+" Iter: "+(iter+1));
				iterCh = 0;
				if (Parameters.enableCrossVal) testSetIt();				
			}
//			System.out.println("Error: "+best.fitnes+" Iter: "+(iter+1) +" "+ child.root.getDepth());
		}
		
		
	}

	private void testSetIt() {
		List<DataParticle> dataSave = data;
		data = crossValidationData;
		double err = calc(best.root);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File(Parameters.CrossValidationErrorFile), true));
			pw.println(err);
			pw.close();
		} catch (Exception e){e.printStackTrace();}
		
		data = dataSave;
		
	}

	private Unit crossMutEval(ArrayList<Unit> pop) {
		//crossover
		Unit parent1 = getParent(pop);
		Unit parent2 = getParent(pop);
		Unit child = parent1.copy();
		INode cvor = parent2.root.getNode(rand.nextInt(parent2.root.getDepth()),rand);
		child.root.putInAt(cvor,rand.nextInt(child.root.getDepth()),rand);	
		
		//testing
		if (child.root.getNumOfNode() > maxCvorova || child.root.setDepth() > maxDublina){
			child  = parent1.copy(); 
			child.fitnes = plagiranje*child.correct;
			return child;
		}
		
		//mutation
		if(rand.nextDouble() < mut){
			child.root.mutate(rand.nextInt(child.root.getDepth()),rand,data.get(0).xVector.length);
		}
		
		//testing
		if (child.root.getNumOfNode() > maxCvorova || child.root.setDepth() > maxDublina){
			child  = parent1.copy();
			child.fitnes = plagiranje*child.correct;
			return child;
		}
		
		//evaluation
		child.correct  = calc(child.root);
		child.fitnes = (child.correct == child.parentCorrect) ? plagiranje*child.correct : child.correct;
		child.parentCorrect = child.correct;
		return child;
	}

	private Unit getParent(ArrayList<Unit> pop) {
		int ind = rand.nextInt(pop.size());
		for (int i=1; i<Turnament; i++){
			int indTest = rand.nextInt(pop.size());
			ind = (pop.get(indTest).fitnes < pop.get(ind).fitnes) ? indTest : ind;
		}		
		return pop.get(ind);
	}
	
	private int getLoserId(ArrayList<Unit> pop) {
		int ind = rand.nextInt(pop.size());
		for (int i=1; i<mockTur; i++){
			int indTest = rand.nextInt(pop.size());
			ind = (pop.get(indTest).fitnes > pop.get(ind).fitnes) ? indTest : ind;
		}		
		return ind;
	}

	private Unit getBest(ArrayList<Unit> pop) {
		double fBest = pop.get(0).fitnes;
		int ind = 0;
		for (int i=1; i<pop.size(); i++){
			if(pop.get(i).fitnes <= fBest){
				fBest = pop.get(i).fitnes;
				ind = i;
			}
		}
		return pop.get(ind);
	}

	private void evaluate(ArrayList<Unit> pop) {
		for(Unit unit : pop){
			unit.correct = calc(unit.root);
			unit.fitnes = unit.correct;
			unit.parentCorrect = unit.correct;
		}
		
	}

	private ArrayList<Unit> initialPopulation() {
		ArrayList<Unit> pop = new ArrayList<>();
		int numDubina = pocetnaDubina - 1;
		int jedDubMet = popNumber / (numDubina * 2);
		for(int i=2; i<=pocetnaDubina; i++){
			for(int j=0; j<jedDubMet; j++){
				Unit unit = new Unit();
				untInit(i, true,unit);
				pop.add(unit);
			}
			for(int j=0; j<jedDubMet; j++){
				Unit unit = new Unit();
				untInit(i, false,unit);
				pop.add(unit);
			}
		}
		
		return pop;
	}

	private void untInit(int i, boolean b, Unit unit) {
		unit.root = NodeCreator.newFunction(rand,data.get(0).xVector.length);
		unit.root.init(i-1,b,rand,data.get(0).xVector.length);
		unit.root.getNumOfNode();
		unit.root.setDepth();
		
	}

	@Override
	public INode getBestRoot() {
		return best.root;
	}

	@Override
	public double calc(INode root) {
		double err = 0;
		for (int i=0; i<data.size(); i++){
			err += Math.abs(	data.get(i).yVector[0] - root.run(data.get(i).xVector)	);
		}
		err /= data.size();
		return err;
	}

	@Override
	public StringBuilder show(INode bestRoot) {
		int guess = 0;
		boolean guessBool;
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<data.size();i++){
			
			double output = bestRoot.run(data.get(i).xVector);
			
			String y = "(";
			String t = "(";
			
			y +=Parameters.dataModGP.modify(output);
			t +=Parameters.dataModGP.modify(data.get(i).yVector[0]);
			
			guessBool = y.equals(t);
			if(guessBool) guess++;
			sb.append(	"Test: "+(i+1)+" t="+t+"), y="+y+") EQU: "+guessBool	).append("\n");
			
		}	
		sb.append("Num of correct: "+ guess+", num of false: "+(data.size()-guess));
		
		return sb;
	}

	@Override
	public String getResoults(INode bestRoot) {
		int guess = 0;
		double avgErr = 0;
		
		for(int i=0; i<data.size();i++){
			
			double output = bestRoot.run(data.get(i).xVector);
			avgErr += (data.get(i).yVector[0] - output)*(data.get(i).yVector[0] - output);
			
			String y = Parameters.dataModGP.modify(output);
			String t = Parameters.dataModGP.modify(data.get(i).yVector[0]);
			if(y.equals(t)) guess++;
			
			
			
		}	
		return guess+" "+avgErr;
	}

}
