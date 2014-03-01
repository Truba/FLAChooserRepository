package hr.fer.zemris.FLAChooser.GenProg.DecTree;

import hr.fer.zemris.FLAChooser.Parameters;
import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.Nodes.FunctionNode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.Nodes.LeafNode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.Nodes.FunctionNodes.HigherThenNode;
import hr.fer.zemris.FLAChooser.GenProg.DecTree.Nodes.FunctionNodes.LowerThenNode;

import java.util.List;
import java.util.Random;

public class NodeCreator {

	public static INode newFunction(Random rand, int len) {
		FunctionNode fn;
		int fun = rand.nextInt(2);
		if(fun == 0){
			fn =  new HigherThenNode();
		}
		else{
			fn = new LowerThenNode();
		}
		fn.indInInputVector = rand.nextInt(len);
		fn.valueToCompare = Parameters.minVal + (Parameters.maxVal-Parameters.minVal)*rand.nextDouble();
		//unit.vector[i] = initMin + (initMax-initMin)*rand.nextDouble();
		return fn;
		
	}

	public static INode newAction(Random rand) {
		LeafNode leaf = new LeafNode();
		leaf.category = Parameters.possibleLeafValues[rand.nextInt(Parameters.possibleLeafValues.length)];
		return leaf;
	}

	public static INode create(List<String> nods) {
		String[] node = nods.get(0).split("\\|");
		if(node.length==1){
			LeafNode leaf = new LeafNode();
			leaf.category = Double.parseDouble(node[0]);
			nods.remove(0);
			return leaf;
		}
		FunctionNode fun;
		switch (node[1]) {
		case ">":
			fun = new HigherThenNode();
			break;

		default:
			fun = new LowerThenNode();
			break;
		}
		fun.indInInputVector = Integer.parseInt(node[0]);
		fun.valueToCompare = Double.parseDouble(node[2]);
		nods.remove(0);
		fun.create(nods);
		return fun;
	}

}
