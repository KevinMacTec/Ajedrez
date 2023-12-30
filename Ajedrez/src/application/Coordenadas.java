package application;

import java.util.Objects;

public class Coordenadas {
	private int fila;
	private int columna;

	public Coordenadas(int f, int c) {
		this.fila = f;
		this.columna = c;
	}
	
	public int getFila() {
		return this.fila;
	}
	
	public int getColumna() {
		return this.columna;
	}
	
	public void setFila(int f) {
		this.fila = f;
	}
	
	public void setColumna(int c) {
		this.columna = c;
	}
	
	// Implementación de equals y hashCode para usar Set<Coordenadas> .contains
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordenadas that = (Coordenadas) obj;
        return fila == that.fila && columna == that.columna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }
}
