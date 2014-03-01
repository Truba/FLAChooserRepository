package hr.fer.zemris.FLAChooser.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.fer.zemris.FLAChooser.Parameters;
import hr.fer.zemris.FLAChooser.Interfaces.IGetData;

public class OneAlgGetData implements IGetData {

	@Override
	public List<DataParticle> getData(String file) {
		ArrayList<DataParticle> data = new ArrayList<>();
		Scanner sc = null;
		try {
			 sc = new Scanner(new File(file), "UTF-8");
			  } catch (FileNotFoundException e) {
			   System.out.println("Ne mogu nac datoteku");
			  }
		String line;
		while(sc.hasNextLine()){
			DataParticle dp = new DataParticle();
			line = sc.nextLine().trim();
			String[] two = line.split("\\|\\|");
			
			String[] in = two[0].trim().split(" +");
			//if (in.length !=14) System.err.println("in nije 14!");
			dp.xVector = new double[in.length];
			for(int i=0; i< in.length; i++){
				dp.xVector[i]=Double.parseDouble(in[i]);
			}
			
			String[] out = two[1].trim().split(" +");
			//if (out.length !=7) System.err.println("out nije 7!");
			dp.yVector = new double[Parameters.dataModifier.numOfGroops()];
			for(int i=0; i< out.length; i++){
				dp.yVector[i]=Double.parseDouble(out[i]);
			}
			
			data.add(dp);
		}
		sc.close();
		return data;
	}

}
