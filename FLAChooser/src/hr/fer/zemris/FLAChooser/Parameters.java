package hr.fer.zemris.FLAChooser;

import hr.fer.zemris.FLAChooser.Data.OneAlgGetData;
import hr.fer.zemris.FLAChooser.DataModifiers.FiftyFifty1Alg;
import hr.fer.zemris.FLAChooser.DataModifiers.FiftyFiftyDataModifier;
import hr.fer.zemris.FLAChooser.GenProg.DecisionTreeGP;
import hr.fer.zemris.FLAChooser.GenProg.SymbolicRegresionGP;
import hr.fer.zemris.FLAChooser.Interfaces.IDataModifiers;
import hr.fer.zemris.FLAChooser.Interfaces.IGeneticProgram;
import hr.fer.zemris.FLAChooser.Interfaces.IGetData;
import hr.fer.zemris.FLAChooser.Interfaces.ITrainingAlgorithm;
import hr.fer.zemris.FLAChooser.TrainingAlgorithm.ClonAlg;
import hr.fer.zemris.FLAChooser.TrainingAlgorithm.DiferencialEvolution;
import hr.fer.zemris.FLAChooser.TrainingAlgorithm.DifEvol.IStrategy;
import hr.fer.zemris.FLAChooser.TrainingAlgorithm.DifEvol.Strategies.BestOne;

public class Parameters {
	
	//shifter
	public static final double percForTrainingSet = 0.5; //(1 - percForTrainingSet) for test set
	
	//files
	public static final String initialData = "logAlg/alg3.data";
	public static final String trainingDataPath = "log/training.data";
	public static final String testDataPath = "log/test.data";
	public static final String BestUnitPrintFile = "log/vectorPrint.data";
	public static final String CrossValidationErrorFile = "log/validationSet.data";
	public static final IGetData trainingSet = new OneAlgGetData();
	public static final IGetData testgSet = new OneAlgGetData();
	
	//cross validation on run
	public static final boolean enableCrossVal = true;
	
	//neural network
	public static final int[] NodsInLayers = {14,8,20,8,2};
	public static final IDataModifiers dataModifier = new FiftyFifty1Alg();
	
	//training algorithm
	public static final ITrainingAlgorithm trainAlg = new ClonAlg();
	public static final int NumOfUnits = 100;
	public static final int MaxNumOfGenWithoutChaindge = 1000;
	public static final double stoppingFitnes = 0;
	public static final double initMin = -10;
	public static final double initMax = 10;
	
	//differential evolution	
	public static final IStrategy strategy = new BestOne();
	public static final double F= 0.5; //[0,2)
	public static final double Cr = 0.25; //[0,1]
	
	//clonal selection algorithm
	public static final int d = 20; // broj novih slucanoNapravljenih antitjela dodan u svaku populaciju
	public static final double beta = 5; //  >= 1/popSize u svakom trenutku -> najbolje >= 1
	public static final double ro = 0.5; // parametar u hipermutaciji
	
	//genetic programming	
	public static final IGeneticProgram geneticProgram = new SymbolicRegresionGP();
	public static final IDataModifiers dataModGP = new FiftyFiftyDataModifier();
	public static final int PopSize = 600;
	public static final int MaxGenNoChaindge = 1500; //actually stop is MaxGenNoChaindge*PopSize new units created
	public static final double minFitnessToStop = 0;
	public static final int Turnament = 4;
	public static final int MockTournament = 3;
	public static final double Mutation = 0.2;
	public static final double Plagiary = 1.05;//1 + something small
	public static final int maxNodes = 200;
	public static final int maxDepth = 13;
	public static final int initDepth = 4;	
	public static final double minVal = -1;
	public static final double maxVal = 1;
	
	//decision tree
	public static final double[] possibleLeafValues = {0,1};// {0,1} if 2 groups // {0,0.5,1} if 3 groups
	
	//symbolic regression
	public static final boolean useConstantsAsleafs = true; //false-> leaf are only input vector elements
	public static final double percOfConstanstsInLeafs = 0.25; //values are [minVal, maxVal]
	public static final String[] possibleFunctions = {"/","+","-","*","sin","exp","ln","log10"};
	// implemented functions: + - * / sin exp ln log10
	public static final boolean useSigmoidFunctionToScaleOutput = false;
	
	

}
