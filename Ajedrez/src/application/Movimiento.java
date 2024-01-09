package application;

import java.util.Objects;

public class Movimiento {
	private Coordenadas ini;
	private Coordenadas fin;
	private Pieza pieza;
	
	public Movimiento(Coordenadas i, Coordenadas f, Pieza p) {
		this.ini = i;
		this.fin = f;
		this.pieza = p;
	}
	
	public Coordenadas getIni() {
		return this.ini;
	}
	
	public Coordenadas getFin() {
		return this.fin;
	}
	
	public Pieza getPieza() {
		return this.pieza;
	}
	
	public void setIni(Coordenadas i) {
		this.ini = i;
	}
	
	public void setFin(Coordenadas f) {
		this.fin = f;
	}
	
	public void setPieza(Pieza p) {
		this.pieza = p;
	}
	
	// Implementacion de equals en movimiento, (revisar si funciona)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Movimiento that = (Movimiento) obj;
        return that.getIni().equals(ini) && that.getFin().equals(fin) && that.getPieza().getClass().getSimpleName().equals(pieza.getClass().getSimpleName());
    }

}
