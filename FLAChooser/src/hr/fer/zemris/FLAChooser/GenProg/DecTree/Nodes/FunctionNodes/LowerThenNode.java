package hr.fer.zemris.FLAChooser.GenProg.DecTree.Nodes.FunctionNodes;

import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.Nodes.FunctionNode;

public class LowerThenNode extends FunctionNode {

	@Override
	public double run(double[] xVector) {
		if(xVector[indInInputVector] < valueToCompare){
			return first.run(xVector);
		}
		return second.run(xVector);
	}

	@Override
	public String nodesAsString() {
		return indInInputVector+"|<|"+valueToCompare +" " +first.nodesAsString()+" "+second.nodesAsString();
		
	}

	@Override
	public INode copy() {
		LowerThenNode novi = new LowerThenNode();
		novi.indInInputVector = this.indInInputVector;
		novi.valueToCompare = this.valueToCompare;
		novi.depth = this.depth;
		novi.nodesBelow = this.nodesBelow;
		novi.first = this.first.copy();
		novi.second = this.second.copy();
		return novi;
	}

}
