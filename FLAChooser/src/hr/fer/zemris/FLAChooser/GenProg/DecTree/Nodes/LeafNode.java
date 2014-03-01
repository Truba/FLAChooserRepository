package hr.fer.zemris.FLAChooser.GenProg.DecTree.Nodes;

import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.NodeCreator;

import java.util.List;
import java.util.Random;

public class LeafNode implements INode{
	
	public double category;
	
	int depth;

	@Override
	public void init(int depth, boolean full, Random rand, int len) {}

	@Override
	public void create(List<String> nodes) {}

	@Override
	public int setDepth() {
		depth = 1;
		return depth;
	}

	@Override
	public int getNumOfNode() {
		return 1;
	}

	@Override
	public double run(double[] xVector) {
		return category;
	}

	@Override
	public INode copy() {
		LeafNode novi = new LeafNode();
		novi.category = this.category;
		novi.depth = this.depth;
		return novi;
	}

	@Override
	public INode getNode(int depth, Random rand) {
		return this;
	}

	@Override
	public INode mutate(int depth, Random rand, int len) {
		return NodeCreator.newAction(rand);
	}

	@Override
	public INode putInAt(INode node, int depth, Random rand) {
		return node.copy();
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public String nodesAsString() {
		return category+"";
	}

}
