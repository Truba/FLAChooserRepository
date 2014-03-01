package hr.fer.zemris.FLAChooser.Data;

import hr.fer.zemris.FLAChooser.Interfaces.IGetData;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IrisData implements IGetData{

	@Override
	public List<DataParticle> getData(String file) {
		ArrayList<DataParticle> data = new ArrayList<>();
		Scanner sc = null;
		try {
			 sc = new Scanner(new File(file), "UTF-8");
			  } catch (FileNotFoundException e) {
			   System.out.println("Ne mogu nac datoteku");
			  }
		//(4.8,3.0,1.4,0.1):(1,0,0)
		String line;
		while(sc.hasNextLine()){
			DataParticle dp = new DataParticle();
			line = sc.nextLine().trim();
			line = line.substring(1, line.length()-1);//  4.8,3.0,1.4,0.1):(1,0,0
			String[] in = (line.split("\\)"))[0].split(",");
			if (in.length !=4) System.err.println("in nije 4!");
			dp.xVector = new double[4];
			for(int i=0; i< in.length; i++){
				dp.xVector[i]=Double.parseDouble(in[i]);
			}
			String[] out = (line.split("\\("))[1].split(",");
			if (out.length !=3) System.err.println("out nije 3!");
			dp.yVector = new double[3];
			for(int i=0; i< out.length; i++){
				dp.yVector[i]=Double.parseDouble(out[i]);
			}
			data.add(dp);
		}
		sc.close();
		return data;
	}
	
	

}
