package hr.fer.zemris.FLAChooser.DataModifiers;

import java.util.List;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.IDataModifiers;

public class FiftyFiftyDataModifier implements IDataModifiers {

	@Override
	public void modyfyData(List<DataParticle> data) {
		for (DataParticle current : data) {
			for (int i = 0; i < current.yVector.length; i++) {
				current.yVector[i] = (current.yVector[i]<0.5) ? 0 : 1;
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
