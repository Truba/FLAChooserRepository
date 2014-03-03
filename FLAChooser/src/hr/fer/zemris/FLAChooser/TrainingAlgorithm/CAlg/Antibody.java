package hr.fer.zemris.FLAChooser.TrainingAlgorithm.CAlg;

public class Antibody implements Comparable<Antibody>{
	
	public double[] x;
	public double fitnes;
	
	public Antibody(int dim){
		x= new double[dim];
	}
	
	public Antibody(Antibody clone){
		this.fitnes = clone.fitnes;
		this.x= new double[clone.x.length];
		for(int i=0; i<clone.x.length; i++){
			this.x[i]=clone.x[i];
		}
	}

	@Override
	public int compareTo(Antibody o) {
		if(this.fitnes < o.fitnes) {
			return -1;
		}
		if(this.fitnes > o.fitnes) {
			return 1;
		}
		return 0;
	}
}
