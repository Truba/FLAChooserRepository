package hr.fer.zemris.FLAChooser.DataModifiers;

import java.util.List;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.IDataModifiers;

/**
 * 
 * 01 -> <0,5
 * 10 -> =>0,5
 *
 */
public class FiftyFifty1Alg implements IDataModifiers {

	@Override
	public void modyfyData(List<DataParticle> data) {
		for (DataParticle current : data) {
			if(current.yVector[0]<0.5){
				current.yVector[0] = 0;
				current.yVector[1] = 1;
			}
			else{
				current.yVector[0] = 1;
				current.yVector[1] = 0;
			}
				
		}

	}

	@Override
	public String modify(double value) {
		return (value < 0.5) ? "0" : "1";
	}

	@Override
	public Integer numOfGroops() {
		return 2;
	}

}
