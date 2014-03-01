package hr.fer.zemris.FLAChooser.GenProg.SymRegr.Nodes;

import java.util.List;
import java.util.Random;

import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.FunctionRunner;
import hr.fer.zemris.FLAChooser.GenProg.SymRegr.NodeCreator;

public class TwoChildFunctionNode implements INode {
	
	protected INode first;
	protected INode second;
	
	protected String function;
	
	protected int depth;
	protected int nodesBelow;
	
	public TwoChildFunctionNode(String function){
		this.function = function;
	}

	@Override
	public void init(int depth, boolean full, Random rand, int len) {
		if (!full){//growth
			if(rand.nextDouble()<0.4 || depth <=1){
				first = NodeCreator.newAction(rand,len);
			}
			else{
				first = newFunction(depth-1,full,rand,len);
			}
			if(rand.nextDouble()<0.4 || depth <=1){
				second = NodeCreator.newAction(rand,len);
			}
			else{
				second = newFunction(depth-1,full,rand,len);
			}
		}
		else{
			if(depth <=1){
				first = NodeCreator.newAction(rand,len);
			}
			else{
				first = newFunction(depth-1,full,rand,len);
			}
			if(depth <=1){
				second = NodeCreator.newAction(rand,len);
			}
			else{
				second = newFunction(depth-1,full,rand,len);
			}
		}
		
	}

	private INode newFunction(int i, boolean full, Random rand, int len) {
		INode node = NodeCreator.newFunction(rand);
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
	public double run(double[] xVector) {
		return FunctionRunner.twoChildRunner(first.run(xVector),second.run(xVector),function);
	}

	@Override
	public INode copy() {
		TwoChildFunctionNode novi = new TwoChildFunctionNode(function);
		novi.depth = this.depth;
		novi.nodesBelow = this.nodesBelow;
		novi.first = this.first.copy();
		novi.second = this.second.copy();
		return novi;
	}

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
	public String nodesAsString() {
		return function+ " " +first.nodesAsString()+ " "+second.nodesAsString();
	}

}
