package teoInfo.model.huffman;

import java.util.*;

public class ObtenerCodigos {
	
	
	//public static void main(String[] args) {
	public ArbolHuffman obtenerCodigos(){	
		Simbolo a=new Simbolo('a',0.078);
		Simbolo b=new Simbolo('b', 0.078);
		Simbolo c=new Simbolo('c',0.784);
		Simbolo d=new Simbolo('d', 0.060);
		List<Simbolo> fs=new ArrayList();

		fs.add(a);
		fs.add(b);
		fs.add(d);
		fs.add(c);
		
		CodigoHuffman codHuf=new CodigoHuffman();
		double longMedia = 0.0;
		HashMap<Simbolo,String> codificacion=new HashMap(codHuf.generarCodigoHuffman(fs));
		for(int i=0; i<fs.size(); i++){
			System.out.println("codigos de "+fs.get(i).getSimb()+": "+codificacion.get(fs.get(i)) + " de longitud: "+ ((String)codificacion.get(fs.get(i))).length());
			longMedia = longMedia + fs.get(i).getProb() * ((String)codificacion.get(fs.get(i))).length();
		}
		System.out.println("Longitud Media: "+ longMedia);
		return codHuf.getArbol();
	}

}
