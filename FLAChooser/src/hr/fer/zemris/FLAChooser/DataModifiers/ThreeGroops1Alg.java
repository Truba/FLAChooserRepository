package hr.fer.zemris.FLAChooser.DataModifiers;

import java.util.List;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.IDataModifiers;

/**
 * 
 * 001 -> [0 , 1/3)
 * 010 -> [1/3 , 2/3)
 * 100 -> [2/3 , 1)
 *
 */
public class ThreeGroops1Alg implements IDataModifiers {

	@Override
	public void modyfyData(List<DataParticle> data) {
		for (DataParticle current : data) {
			if(current.yVector[0]<1./3.){
				current.yVector[0] = 0;
				current.yVector[1] = 0;
				current.yVector[2] = 1;
			}
			else if(current.yVector[0]<2./3.){
				current.yVector[0] = 0;
				current.yVector[1] = 1;
				current.yVector[2] = 0;
			}
			else{
				current.yVector[0] = 1;
				current.yVector[1] = 0;
				current.yVector[2] = 0;
			}
				
		}

	}

	@Override
	public String modify(double value) {
		return (value < 0.5) ? "0" : "1";
	}

	@Override
	public Integer numOfGroops() {
		return 3;
	}

}
