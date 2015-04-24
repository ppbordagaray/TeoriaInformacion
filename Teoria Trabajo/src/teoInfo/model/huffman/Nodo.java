package teoInfo.model.huffman;

public class Nodo implements Comparable<Nodo>{
	
	Simbolo s;
	double prob;
	ArbolHuffman izq;
	ArbolHuffman der;
	
	public Nodo(Simbolo s)
	{
		this.s=s;
		this.prob=s.getProb();
		izq=null;
		der=null;
	}
	
	public Nodo()
	{
		s=null;
		prob=0.0;
	}

	public Simbolo getS() 
	{
		return s;
	}

	public void setS(Simbolo s) 
	{
		this.s = s;
	}

	public double getProb() 
	{
		return prob;
	}

	public void setProb(double prob) 
	{
		this.prob = prob;
	}

	public ArbolHuffman getIzq() 
	{
		return izq;
	}

	public void setIzq(ArbolHuffman izq) 
	{
		this.izq = izq;
		this.prob+=izq.getProb();
	}

	public ArbolHuffman getDer() 
	{
		return der;
	}

	public void setDer(ArbolHuffman der) 
	{
		this.der = der;
		this.prob+=der.getProb();
	}

	@Override
	public int compareTo(Nodo arg0)
	{
		if(this.prob>arg0.getProb())
			return 1;
		else
			return 0;
	}

}
