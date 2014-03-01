package hr.fer.zemris.FLAChooser.TrainingAlgorithm;

import java.util.ArrayList;
import java.util.Random;

public interface IStrategy {

	Unit getMutantVector(Unit best, ArrayList<Unit> pop, double f, Random rand, int i);

}
