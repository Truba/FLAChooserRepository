package hr.fer.zemris.FLAChooser.GenProg;

import java.util.List;
import java.util.Random;

public interface INode {

	public void init(int depth, boolean full, Random rand, int len);
	
	public void create(List<String> nodes);

	public int setDepth();

	public int getNumOfNode();

	public double run(double[] xVector);

	public INode copy();

	public INode getNode(int depth, Random rand);

	public INode mutate(int depth, Random rand, int len);

	public INode putInAt(INode node, int depth, Random rand);

	public int getDepth();
	
	public String nodesAsString();

}
