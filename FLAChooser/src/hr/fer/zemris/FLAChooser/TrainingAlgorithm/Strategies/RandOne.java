package hr.fer.zemris.FLAChooser.TrainingAlgorithm.Strategies;

import java.util.ArrayList;
import java.util.Random;

import hr.fer.zemris.FLAChooser.TrainingAlgorithm.IStrategy;
import hr.fer.zemris.FLAChooser.TrainingAlgorithm.Unit;

public class RandOne implements IStrategy {

	@Override
	public Unit getMutantVector(Unit best, ArrayList<Unit> pop, double F, Random rand, int i) {
		
		int ind0, ind1, ind2;
		
		do{
			ind0 = rand.nextInt(pop.size());
		}while(ind0==i);
		
		do{
			ind1 = rand.nextInt(pop.size());
		}while(ind1==i || ind1==ind0);
		
		do{
			ind2 = rand.nextInt(pop.size());
		}while(ind2==i || ind2==ind0 || ind2==ind1);
		
		Unit r0 = pop.get(ind0);
		Unit r1 = pop.get(ind1);
		Unit r2 = pop.get(ind2);		
		
		return  mutatedVector(r0,r1,r2,F,r0.vector.length);// rand/1
	}

	private Unit mutatedVector(Unit r0, Unit r1, Unit r2, double F, int dim) {
		Unit v = new Unit(dim);
		for(int i=0; i<dim; i++){
			v.vector[i] = r0.vector[i] + F*(r1.vector[i]-r2.vector[i]);
		}
		return v;
	}

}
