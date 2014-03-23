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
	public static double percForTrainingSet = 0.5; //(1 - percForTrainingSet) for test set
	public static boolean shiftRandom = false;
	public static boolean lastToTraining = false; //only matters if shiftRandom == false 
	
	//files
	public static String initialData = "logAlg/alg3.data";
	public static String trainingDataPath = "log/training.data";
	public static String testDataPath = "log/test.data";
	public static String BestUnitPrintFile = "log/vectorPrint.data";
	public static String CrossValidationErrorFile = "log/validationSet.data";
	public static IGetData trainingSet = new OneAlgGetData();
	public static IGetData testgSet = new OneAlgGetData();
	
	//should print best to BestUnitPrintFile
	public static boolean shouldPrintBestToFile = false;
	
	//cross validation on run
	public static boolean enableCrossVal = false;
	
	//neural network
	public static int[] NodsInLayers = {14,8,20,8,2};
	public static IDataModifiers dataModifier = new FiftyFifty1Alg();
	
	//training algorithm
	public static ITrainingAlgorithm trainAlg = new ClonAlg();
	public static int NumOfUnits = 100;
	public static int MaxNumOfGenWithoutChaindge = 1000;
	public static double stoppingFitnes = 0;
	public static double initMin = -10;
	public static double initMax = 10;
	
	//differential evolution	
	public static IStrategy strategy = new BestOne();
	public static double F= 0.5; //[0,2)
	public static double Cr = 0.25; //[0,1]
	
	//clonal selection algorithm
	public static int d = 20; // broj novih slucanoNapravljenih antitjela dodan u svaku populaciju
	public static double beta = 5; //  >= 1/popSize u svakom trenutku -> najbolje >= 1
	public static double ro = 0.5; // parametar u hipermutaciji
	
	//genetic programming	
	public static IGeneticProgram geneticProgram = new SymbolicRegresionGP();
	public static IDataModifiers dataModGP = new FiftyFiftyDataModifier();
	public static int PopSize = 600;
	public static int MaxGenNoChaindge = 500; //actually stop is MaxGenNoChaindge*PopSize new units created
	public static double minFitnessToStop = 0;
	public static int Turnament = 4;
	public static int MockTournament = 3;
	public static double Mutation = 0.2;
	public static double Plagiary = 1.05;//1 + something small
	public static int maxNodes = 200;
	public static int maxDepth = 13;
	public static int initDepth = 4;	
	public static double minVal = -1;
	public static double maxVal = 1;
	
	//decision tree
	public static double[] possibleLeafValues = {0,1};// {0,1} if 2 groups // {0,0.5,1} if 3 groups
	
	//symbolic regression
	public static boolean useConstantsAsleafs = true; //false-> leaf are only input vector elements
	public static double percOfConstanstsInLeafs = 0.25; //values are [minVal, maxVal]
	public static String[] possibleFunctions = {"/","+","-","*","sin","exp","ln","log10"};
	// implemented functions: + - * / sin exp ln log10
	public static boolean useSigmoidFunctionToScaleOutput = false;
	
	

}
