package hr.fer.zemris.FLAChooser.DataModifiers;

import hr.fer.zemris.FLAChooser.Data.DataParticle;
import hr.fer.zemris.FLAChooser.Interfaces.IDataModifiers;

import java.util.List;


public class ThreeEquGroops implements IDataModifiers{

	@Override
	public void modyfyData(List<DataParticle> data) {
		for (DataParticle current : data) {
			for (int i = 0; i < current.yVector.length; i++) {
				if(current.yVector[i]<1./3.) {
					current.yVector[i] = 0;
				}
				else if(current.yVector[i]<2./3.){
					current.yVector[i] = 0.5;
				}
				else{
					current.yVector[i] = 1;
				}
			}
		}
		
	}

	@Override
	public String modify(double value) {
		if(value < 1./3.) return "0";
		if(value < 2./3.) return "5";
		return "1";
	}

	@Override
	public Integer numOfGroops() {
		return 3;
	}

}
