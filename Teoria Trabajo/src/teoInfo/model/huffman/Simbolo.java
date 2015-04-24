package teoInfo.model.huffman;

public class Simbolo implements Comparable<Simbolo> {
	
	double prob; //Probabilidad del vector estacionario correspondiente al Simbolo
	char simb;   // Simbolo representado como un caracter
	
	public Simbolo(char simb, double prob)
	{
		this.prob=prob;
		this.simb=simb;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

	public char getSimb() {
		return simb;
	}

	public void setSimb(char simb) {
		this.simb = simb;
	}

	@Override
	public int compareTo(Simbolo arg0) {
		if(this.prob>arg0.getProb())
			return 1;
		else
			return 0;
	}
	public String toString(){
		return "p("+simb+")="+prob;
	}
}
