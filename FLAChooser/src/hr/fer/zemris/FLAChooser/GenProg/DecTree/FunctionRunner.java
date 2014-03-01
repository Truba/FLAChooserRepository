package hr.fer.zemris.FLAChooser.GenProg.DecTree;

public class FunctionRunner {
	
	// implemented functions: / + * - sin e^x log2 log10

	public static double oneChildFunction(double x, String function) {
		if(function.equals("sin")){
			return Math.sin(x);
		}
		if(function.equals("exp")){
			return Math.exp(x);
		}
		if(function.equals("log10")){
			return Math.log10(x);
		}
		if(function.equals("ln")){
			return Math.log(x);
		}
		return 0;
	}

	public static double twoChildRunner(double x1, double x2, String function) {
		if(function.equals("/")){
			return x1 / x2;
		}
		if(function.equals("+")){
			return x1 + x2;
		}
		if(function.equals("-")){
			return x1 - x2;
		}
		if(function.equals("*")){
			return x1 * x2;
		}
		return 0;
	}

}
