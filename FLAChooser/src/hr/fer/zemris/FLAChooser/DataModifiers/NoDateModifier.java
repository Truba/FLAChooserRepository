package hr.fer.zemris.FLAChooser.DataModifiers;

import java.util.List;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.IDataModifiers;

public class NoDateModifier implements IDataModifiers {

	@Override
	public void modyfyData(List<DataParticle> data) {
		return;

	}

	@Override
	public String modify(double value) {
		//round on two decimals
		return "" + Math.round(value*100.)/100.;
	}

	@Override
	public Integer numOfGroops() {
		return null;
	}

}
