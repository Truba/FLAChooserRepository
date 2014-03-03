package hr.fer.zemris.FLAChooser.TrainingAlgorithm.DifEvol.Strategies;

import java.util.ArrayList;
import java.util.Random;

import hr.fer.zemris.FLAChooser.TrainingAlgorithm.DifEvol.IStrategy;
import hr.fer.zemris.FLAChooser.TrainingAlgorithm.DifEvol.Unit;

public class BestOne implements IStrategy {

	@Override
	public Unit getMutantVector(Unit best, ArrayList<Unit> pop, double F, Random rand, int i) {
		
		int ind1, ind2;
		
		do{
			ind1 = rand.nextInt(pop.size());
		}while(ind1==i);
		
		do{
			ind2 = rand.nextInt(pop.size());
		}while(ind2==i || ind2==ind1);
		
		Unit r1 = pop.get(ind1);
		Unit r2 = pop.get(ind2);		
		
		return  mutatedVector(best,r1,r2,F,best.vector.length);// best/1
	}

	private Unit mutatedVector(Unit best, Unit r1, Unit r2, double F, int dim) {
		Unit v = new Unit(dim);
		for(int i=0; i<dim; i++){
			v.vector[i] = best.vector[i] + F*(r1.vector[i]-r2.vector[i]);
		}
		return v;
	}

}
