package hr.fer.zemris.FLAChooser.GenProg.SymRegr;

import hr.fer.zemris.FLAChooser.Parameters;
import hr.fer.zemris.FLAChooser.GenProg.INode;
import hr.fer.zemris.FLAChooser.GenProg.SymRegr.Nodes.LeafNode;
import hr.fer.zemris.FLAChooser.GenProg.SymRegr.Nodes.OneChildFunctionNode;
import hr.fer.zemris.FLAChooser.GenProg.SymRegr.Nodes.TwoChildFunctionNode;

import java.util.List;
import java.util.Random;

public class NodeCreator {

	public static INode newFunction(Random rand) {
		int ind = rand.nextInt(Parameters.possibleFunctions.length);
		String function = Parameters.possibleFunctions[ind];
		return getFunctionType(function);
	}

	public static INode newAction(Random rand, int len) {
		LeafNode leaf = new LeafNode();
		if(Parameters.useConstantsAsleafs && rand.nextDouble() < Parameters.percOfConstanstsInLeafs) {
			leaf.useConstant = true;
			leaf.value = Parameters.minVal + (Parameters.maxVal-Parameters.minVal)*rand.nextDouble();
			return leaf;
		}
		leaf.useConstant = false;
		leaf.indInInputVector = rand.nextInt(len);
		return leaf;
	}

	public static INode create(List<String> nods) {
		String[] node = nods.get(0).split("\\|");
		if(node.length==1){
			INode func =  getFunctionType(node[0]);
			nods.remove(0);
			func.create(nods);
			return func;
		}
		LeafNode leaf = new LeafNode();
		if(node[0].equals("1")){
			leaf.useConstant = true;
			leaf.value = Double.parseDouble(node[1]);
		}
		else{//node[0].equals("0")
			leaf.useConstant = false;
			leaf.indInInputVector = Integer.parseInt(node[1]);
		}
		
		return leaf;
	}
	
	private static INode getFunctionType(String function){
		if(function.equals("/") || function.equals("+") || function.equals("-") || function.equals("*")) {
			return new TwoChildFunctionNode(function);
		}		
		return new OneChildFunctionNode(function);
	}
	
	

}
