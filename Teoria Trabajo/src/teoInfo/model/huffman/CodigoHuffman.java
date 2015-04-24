package teoInfo.model.huffman;

import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class CodigoHuffman {
	
	HashMap<Simbolo, String> codigos;
	ArbolHuffman arbol;
	
	public ArbolHuffman getArbol() {
		return arbol;
	}
	public void setArbol(ArbolHuffman arbol) {
		this.arbol = arbol;
	}
	public CodigoHuffman()
	{
		codigos=new HashMap<Simbolo, String>();
	}
	//Encapsula los simbolos en ArbolesHuffman y los inserta de manera ordenada en una cola de 
	//prioridad(desde el de menor prob al de mayor
	public PriorityQueue<ArbolHuffman> copiar(List<Simbolo> fs)
	{
		PriorityQueue<ArbolHuffman> prioridad=new PriorityQueue();
		prioridad.add(new ArbolHuffman(new Simbolo('a', 0.0)));//tube que colocar un simbolo de prob 0 debido a que no me ordenaba el primer simbolo
		for(Simbolo s:fs)
		 {
			 ArbolHuffman nuevo=new ArbolHuffman(s);
			 prioridad.add(nuevo);			 
		 }
		prioridad.poll();//Quito el simbolo extra
		return prioridad;
	}
	
	//A partir de la cola ordenada voy tomando los de menor prob y formo un nuevo arbol con la
	//suma de las probabilidad, en n-1 paso obtengo un solo elemento en la cola que es el
	//arbol de Huffman completo
	public HashMap<Simbolo,String> generarCodigoHuffman(List<Simbolo> fs)
	{
		PriorityQueue<ArbolHuffman>prioridad=new PriorityQueue(copiar(fs));
		int contador=prioridad.size();
		int pos=1;
		while(pos<contador)
		{
			ArbolHuffman agregar=new ArbolHuffman();
			agregar.addIzq(prioridad.poll());
			agregar.addDer(prioridad.poll());
			prioridad.add(agregar);
			
			pos++;
		}
		String cod="";
		arbol = (ArbolHuffman)prioridad.poll();
		arbol.generarCodigos(codigos, cod);//llamo al metodo generarCodigo de ArbolHuffman que me retorna en un hash los codigos para cada simbolo.
		return codigos;
	}

}
