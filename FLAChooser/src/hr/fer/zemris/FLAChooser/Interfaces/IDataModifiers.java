package hr.fer.zemris.FLAChooser.Interfaces;

import hr.fer.zemris.FLAChooser.Data.DataParticle;

import java.util.List;

public interface IDataModifiers {

	void modyfyData(List<DataParticle> data);

	String modify(double value);

	Integer numOfGroops();
	
}
