package hr.fer.zemris.FLAChooser.GenProg.DecTree.Nodes;

import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.NodeCreator;

import java.util.List;
import java.util.Random;

public abstract class FunctionNode implements INode{
	
	protected INode first;
	protected INode second;
	
	public int indInInputVector;
	public double valueToCompare;

	protected int depth;
	protected int nodesBelow;
	
	@Override
	public void init(int depth, boolean full, Random rand, int len) {
		if (!full){//growth
			if(rand.nextDouble()<0.4 || depth <=1){
				first = NodeCreator.newAction(rand);
			}
			else{
				first = newFunction(depth-1,full,rand,len);
			}
			if(rand.nextDouble()<0.4 || depth <=1){
				second = NodeCreator.newAction(rand);
			}
			else{
				second = newFunction(depth-1,full,rand,len);
			}
		}
		else{
			if(depth <=1){
				first = NodeCreator.newAction(rand);
			}
			else{
				first = newFunction(depth-1,full,rand,len);
			}
			if(depth <=1){
				second = NodeCreator.newAction(rand);
			}
			else{
				second = newFunction(depth-1,full,rand,len);
			}
		}
		
	}

	private INode newFunction(int i, boolean full, Random rand, int len) {
		INode node = NodeCreator.newFunction(rand,len);
		node.init(i,full,rand,len);
		return node;
	}

	@Override
	public void create(List<String> nodes) {
		first = NodeCreator.create(nodes);
		second = NodeCreator.create(nodes);
		
		
	}

	@Override
	public int setDepth() {
		depth = Math.max(first.setDepth(), second.setDepth()) + 1;
		return depth;
	}

	@Override
	public int getNumOfNode() {
		nodesBelow = first.getNumOfNode() + second.getNumOfNode();
		return nodesBelow+1;
	}

	@Override
	public abstract double run(double[] xVector);

	@Override
	public abstract INode copy();

	@Override
	public INode getNode(int depth, Random rand) {
		if(depth <=0){
			return this;
		}
		depth--;
		if(rand.nextDouble()<0.5){
			return first.getNode(depth, rand);
		}
		return second.getNode(depth, rand);
	}

	@Override
	public INode mutate(int depth, Random rand, int len) {
		if(depth <=0){
			init(rand.nextInt(this.depth), true, rand, len);
			return this;
		}
		depth--;
		if(rand.nextDouble()<0.5){
			first = first.mutate(depth, rand, len);
			return this;
		}
		second = second.mutate(depth, rand, len);
		return this;
	}

	@Override
	public INode putInAt(INode node, int depth, Random rand) {
		if(depth <=0){
			return node.copy();
		}
		depth--;
		if(rand.nextDouble()<0.5){
			first = first.putInAt(node, depth, rand);
			return this;
		}
		second = second.putInAt(node, depth, rand);
		return this;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public abstract String nodesAsString();

}
