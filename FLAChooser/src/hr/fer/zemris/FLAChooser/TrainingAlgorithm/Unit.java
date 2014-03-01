package hr.fer.zemris.FLAChooser.TrainingAlgorithm;

public class Unit {
	
	public double[] vector;
	public double fitnes;
	
	public Unit(int dim){
		vector = new double[dim];
	}
	
	public Unit(Unit cloneThis){
		this.fitnes = cloneThis.fitnes;
		this.vector = new double[cloneThis.vector.length];
		for (int i=0; i<cloneThis.vector.length; i++){
			this.vector[i] = cloneThis.vector[i];
		}
	}

}
