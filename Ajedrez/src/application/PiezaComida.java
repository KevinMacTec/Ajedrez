package application;

public class PiezaComida {
	private Pieza p;
	private Coordenadas c;
	private int t;
	
	public PiezaComida(Pieza pieza, Coordenadas coordenadas, int turno){
		this.p = pieza;
		this.c = coordenadas;
		this.t = turno;
	}
	
	public Pieza getPieza(){
		return this.p;
	}
	
	public Coordenadas getCoordenada(){
		return this.c;
	}
	
	public int getTurno(){
		return this.t;
	}
	
	public void setPieza(Pieza pieza){
		this.p = pieza;
	}
	
	public void setCoordenadas(Coordenadas coordenadas){
		this.c = coordenadas;
	}
	
	public void setTurno(int turno){
		this.t = turno;
	}
}
