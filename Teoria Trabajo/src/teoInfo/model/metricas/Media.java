package teoInfo.model.metricas;

import java.util.HashMap;
import java.util.List;

import teoInfo.model.huffman.Simbolo;

public class Media {
	public static String formula;
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public static double calcular(List<Simbolo> fs, HashMap<Simbolo,String> codificacion){
		double longMedia = 0.0;
		formula = new String();
		for(int i=0; i<fs.size(); i++){
			formula = formula +" + "+ fs.get(i).getProb() +" * "+((String)codificacion.get(fs.get(i))).length();
  			longMedia = longMedia + fs.get(i).getProb() * ((String)codificacion.get(fs.get(i))).length();
  		}
		return longMedia;
	}
}
