package hr.fer.zemris.FLAChooser.GenProg;

public class Unit {
	
	public INode root;
	public double fitnes;
	public double parentCorrect;
	public double correct;
	
	public Unit copy() {
		Unit novi = new Unit();
		novi.fitnes = this.fitnes;
		novi.parentCorrect = this.parentCorrect;
		novi.correct = this.correct;
		novi.root = this.root.copy();
		return novi;
	}

}
