package hr.fer.zemris.FLAChooser.GenProg.SymRegr.Nodes;

import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.GenProg.SymRegr.NodeCreator;

import java.util.List;
import java.util.Random;

public class LeafNode implements INode {
	
	public int indInInputVector;
	public double value;
	public boolean useConstant;
	
	
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
		if(useConstant){
			return value;
		}
		return xVector[indInInputVector];
	}

	@Override
	public INode copy() {
		LeafNode novi = new LeafNode();
		novi.depth = this.depth;
		novi.indInInputVector = this.indInInputVector;
		novi.useConstant = this.useConstant;
		novi.value = this.value;
		return novi;
	}

	@Override
	public INode getNode(int depth, Random rand) {
		return this;
	}

	@Override
	public INode mutate(int depth, Random rand, int len) {
		return NodeCreator.newAction(rand,len);
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
		return (useConstant) ? "1|"+value : "0|"+indInInputVector;
	}

}
