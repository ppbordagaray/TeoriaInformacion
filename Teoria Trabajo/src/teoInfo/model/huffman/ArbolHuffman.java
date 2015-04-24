package teoInfo.model.huffman;

import java.util.HashMap;

public class ArbolHuffman implements Comparable<ArbolHuffman>{
	
	Nodo arbol;
	
	public ArbolHuffman(Simbolo s) //el primer constructor tiene un simbolo 
	{
		arbol=new Nodo(s);         //(este constructor se utiliza solo en las hojas)
	}
	public ArbolHuffman()      //Crea el arbol con un nodo sin simbolo;
	{
		arbol=new Nodo();
	}
	
	public void addIzq(ArbolHuffman izq)
	{
		arbol.setIzq(izq);
	}
	
	public void addDer(ArbolHuffman der)
	{
		arbol.setDer(der);
	}
	
	public Simbolo getSimbolo()
	{
		return this.arbol.getS();
	}
	
	public double getProb()
	{
		return arbol.getProb();
	}
	
	public Nodo getArbol() {
		return arbol;
	}

	public void generarCodigos(HashMap<Simbolo, String> codigos, String cod)
	{
		Nodo aux=this.arbol;
		generarCodigos(codigos, cod, aux);
		
	}
	//Genera los codigos apartir de un arbol y los almacena en la hash teniendo como clave un
	//Simbolo
	private void generarCodigos(HashMap<Simbolo, String> codigos, String cod, Nodo aux)
	{
		if(aux!=null)
		{
			if(aux.getDer()==null && aux.getIzq()==null)
				codigos.put(aux.getS(), cod);
			else
				if(aux.getDer()!=null && aux.getIzq()!=null)
				{
					aux.getDer().generarCodigos(codigos, cod+'1');
					aux.getIzq().generarCodigos(codigos, cod+'0');
				}
				else
					if(aux.getDer()==null)
						aux.getIzq().generarCodigos(codigos, cod+'0');
					else
						aux.getDer().generarCodigos(codigos, cod+'1');
		}
	}

	@Override
	public int compareTo(ArbolHuffman arg0) //Implemento para poder comparar dos arboles por prob
	{
		if(this.getProb()>arg0.getProb())
			return 1;
		else 
			return 0;
	
	}
}
