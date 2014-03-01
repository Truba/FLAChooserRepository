package hr.fer.zemris.FLAChooser.TrainingAlgorithm;

import hr.fer.zemris.FLAChooser.Parameters;
import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.INeuralNetwork;
import hr.fer.zemris.FLAChooser.Interfaces.ITrainingAlgorithm;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiferencialEvolution implements ITrainingAlgorithm {
	
	

	private int dim;
	private int popNum;
	private int maxGenNoChamdge;
	private INeuralNetwork neurNet;
	
	private IStrategy strategy;
	
	private double F;
	private double Cr;
	private double initMin;
	private double initMax;
	
	private Random rand;
	private Unit best;
	
	private List<DataParticle> dataStart;
	private List<DataParticle> dataCrosValid;
	

	@Override
	public void set(int numOfWeights, INeuralNetwork neurNet) {
		
		this.dim = numOfWeights;		
		this.neurNet = neurNet;
		
		this.popNum = Parameters.NumOfUnits;
		this.maxGenNoChamdge = Parameters.MaxNumOfGenWithoutChaindge;
		this.strategy = Parameters.strategy;
		this.F = Parameters.F;
		this.Cr = Parameters.Cr;
		this.initMax = Parameters.initMax;
		this.initMin = Parameters.initMin;		
		
		this.rand = new Random();
		
		if(Parameters.enableCrossVal){
			dataStart = neurNet.getData();
			dataCrosValid = Parameters.testgSet.getData(Parameters.testDataPath);
			Parameters.dataModifier.modyfyData(dataCrosValid);
		}
		
	}
	
	@Override
	public double[] getBestVector() {
		return best.vector;
	}

	@Override
	public void start() {
		ArrayList<Unit> pop = initPopulation();
		evaluate(pop);
		best = getBest(pop);
		for(int iter = 0, iterCh = 0; iterCh < maxGenNoChamdge; iter++, iterCh++){
			ArrayList<Unit> popNew = new ArrayList<>();			
			for (int i=0; i<pop.size(); i++){
				
				Unit v = strategy.getMutantVector(best,pop,F,rand,i);
				
				//crossing
				Unit trial = crossing(pop.get(i),v);
				
				//choosing
				if(trial.fitnes <= pop.get(i).fitnes){
					popNew.add(trial);
				}
				else{
					popNew.add(pop.get(i));
				}				
			}
			Unit bestTest = getBest(popNew);
			if(bestTest.fitnes < best.fitnes){
				// NEW BEST
				System.out.println("Error: "+best.fitnes+" Iter: "+(iter+1));
				best = bestTest;
				iterCh = 0;
				if (Parameters.enableCrossVal) testSetIt();
			}
			pop = popNew;			
		}

	}

	private void testSetIt() {		
		neurNet.set(dataCrosValid, Parameters.dataModifier);
		double err = neurNet.calcANN(getBestVector());
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File(Parameters.CrossValidationErrorFile), true));
			pw.println(err);
			pw.close();
		} catch (Exception e){e.printStackTrace();}
		
		neurNet.set(dataStart, Parameters.dataModifier);
	}

	private Unit crossing(Unit unit, Unit v) {
		Unit child = new Unit(dim);
		for(int i=0; i<dim; i++){
			child.vector[i] = (rand.nextDouble() < Cr) ? v.vector[i] : unit.vector[i];
		}
		int ind = rand.nextInt(dim);
		child.vector[ind] = v.vector[ind];
		evalOne(child);
		return child;
	}

	private Unit getBest(ArrayList<Unit> pop) {
		Unit best = pop.get(0);
		for (int i=0; i<pop.size(); i++){
			if(pop.get(i).fitnes < best.fitnes){
				best = pop.get(i);
			}
		}
		return best;

	}

	private void evaluate(ArrayList<Unit> pop) {
		for(int i=0; i<pop.size(); i++){			
			evalOne(pop.get(i));
		}		
		
	}

	private void evalOne(Unit unit) {
		unit.fitnes = neurNet.calcANN(unit.vector);
		
	}

	private ArrayList<Unit> initPopulation() {
		ArrayList<Unit> pop = new ArrayList<>();
		for(int i=0; i<popNum; i++){
			Unit unit = randomUnit();
			pop.add(unit);
		}
		return pop;
	}

	private Unit randomUnit() {
		Unit unit = new Unit(dim);
		for (int i=0; i<dim; i++){
			unit.vector[i] = initMin + (initMax-initMin)*rand.nextDouble();
		}
		return unit;
	}

}
