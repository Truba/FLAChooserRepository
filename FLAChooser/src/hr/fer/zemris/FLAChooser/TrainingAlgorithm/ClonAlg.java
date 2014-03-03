package hr.fer.zemris.FLAChooser.TrainingAlgorithm;

import hr.fer.zemris.FLAChooser.Parameters;
import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.INeuralNetwork;
import hr.fer.zemris.FLAChooser.Interfaces.ITrainingAlgorithm;
import hr.fer.zemris.FLAChooser.TrainingAlgorithm.CAlg.Antibody;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ClonAlg implements ITrainingAlgorithm {
	
	private int n;
	private int d;
	private int maxGenNoChamdge;
	private INeuralNetwork ffann;
	private int dim;
	
	private double ro=0.5;
	private double beta=5;
	private double xMin;
	private double xMax;
	private Random rand;
	
	private static Antibody best;
	
	private List<DataParticle> dataStart;
	private List<DataParticle> dataCrosValid;

	@Override
	public void set(int numOfWeights, INeuralNetwork neurNetow) {
		this.dim = numOfWeights;		
		this.ffann = neurNetow;
		
		this.n = Parameters.NumOfUnits;
		this.maxGenNoChamdge = Parameters.MaxNumOfGenWithoutChaindge;
		this.xMin = Parameters.initMax;
		this.xMax = Parameters.initMin;	
		
		this.d = Parameters.d;
		this.beta = Parameters.beta;
		this.ro = Parameters.ro;
		
		this.rand = new Random();
		
		if(Parameters.enableCrossVal){
			dataStart = ffann.getData();
			dataCrosValid = Parameters.testgSet.getData(Parameters.testDataPath);
			Parameters.dataModifier.modyfyData(dataCrosValid);
			File f = new File(Parameters.CrossValidationErrorFile);
			f.delete();
		}

	}

	@Override
	public void start() {
		ArrayList<Antibody> pop = initiate();
		evaluate(pop);
		best = new Antibody(pop.get(0));
		System.out.println("Error: "+best.fitnes+" Iter: 0 (inital dataset)");
		
		for(int iter = 0, iterCh = 0; iterCh < maxGenNoChamdge && best.fitnes > Parameters.stoppingFitnes; iter++, iterCh++){
			ArrayList<Antibody> pClo = new ArrayList<>();
			pClo.add(best);//elitizam
			
			//kloniranje
			for(int i=0; i<pop.size(); i++){
				int many = (int) Math.round((beta*pop.size())/(i+1));
				for(int j=0; j<many; j++){
					pClo.add(new Antibody(pop.get(i)));
				}
			}
			
			//hipermutacije
			for(int i=1; i<pClo.size(); i++){//i od 1 da best ostane isti
				double p = Math.exp(-ro*pClo.get(i).fitnes);
				for(int j=0; j<dim; j++){
					if (rand.nextDouble() < p){
						pClo.get(i).x[j] += -1 +2*rand.nextDouble();
					}
				}
			}
			//evaluate
			evaluate(pClo);
			
			//makni visak
			for(int i=pClo.size()-1; i>=n;i--){
				pClo.remove(i);
			}
			
			//stvori nove i ubaci
			for(int i=pClo.size()-1; i>=pClo.size()-d;i--){
				Antibody ab = randAntibody();
				pClo.set(i, ab);
			}
			//evaluate
			evaluate(pClo);
			
			//nova genetacija
			Antibody bestTest = pClo.get(0);
			
			if(bestTest.fitnes < best.fitnes){
				// NEW BEST
				System.out.println("Error: "+best.fitnes+" Iter: "+(iter+1));
				best = new Antibody(bestTest);
				iterCh = 0;
				if (Parameters.enableCrossVal) testSetIt();
			}
			pop = pClo;
			
		}

	}

	private void testSetIt() {
		ffann.set(dataCrosValid, Parameters.dataModifier);
		double err = ffann.calcANN(getBestVector());
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(new File(Parameters.CrossValidationErrorFile), true));
			pw.println(err);
			pw.close();
		} catch (Exception e){e.printStackTrace();}
		
		ffann.set(dataStart, Parameters.dataModifier);
		
	}

	private void evaluate(ArrayList<Antibody> pop) {
		for(int i=0; i<pop.size(); i++){
			pop.get(i).fitnes = ffann.calcANN(pop.get(i).x);
		}
		Collections.sort(pop);
		
	}

	private ArrayList<Antibody> initiate() {
		ArrayList<Antibody> res = new ArrayList<>();
		for(int i=0; i<n; i++){
			Antibody ab = randAntibody();			
			res.add(ab);
		}
		return res;
	}

	private Antibody randAntibody() {
		Antibody ab = new Antibody(dim);
		for (int j=0; j<dim; j++){
			ab.x[j]= xMin + (xMax-xMin)*rand.nextDouble();
		}
		return ab;
	}

	@Override
	public double[] getBestVector() {
		return best.x;
	}

}
