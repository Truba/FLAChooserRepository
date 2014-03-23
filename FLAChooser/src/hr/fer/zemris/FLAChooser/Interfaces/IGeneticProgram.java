package hr.fer.zemris.FLAChooser.Interfaces;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.GenProg.INode;

import java.util.List;

public interface IGeneticProgram {
	
	public void setData(List<DataParticle> data);

	public void start();

	public INode getBestRoot();
	
	public double calc(INode root);

	public StringBuilder show(INode bestRoot);

	public String getResoults(INode bestRoot);
	
	

}
