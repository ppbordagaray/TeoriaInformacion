package teoInfo.util;

import java.util.Iterator;
import java.util.List;

import teoInfo.model.huffman.Simbolo;

public class Util {
	public static boolean fValida(List<Simbolo> fs){
		double suma = 0.0;
		for (Iterator<Simbolo> i = fs.iterator();i.hasNext();) {
			Simbolo s = i.next();
			suma = suma + s.getProb();
		}
		return suma == 1.0;
	}
}
