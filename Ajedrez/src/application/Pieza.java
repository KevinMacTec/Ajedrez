package application;

import java.util.Objects;

abstract class Pieza {
    private boolean color; //True = Blanco, False = Negro
    private boolean clavado; //True = clavado, False = libre
    private boolean primerMovimiento;
    private String imagen;

    public Pieza(boolean color, String imagen) {
        this.color = color;
        this.clavado = false;
        this.primerMovimiento = true;
        this.imagen = imagen;
    }

    // Getters y setters
    public boolean getColor() {
        return this.color;
    }
    
    public boolean getClavado() {
    	return this.clavado;
    }
    
    public String getImagen() {
    	return this.imagen;
    }
    
    public boolean getPrimerMovimiento(){
    	return this.primerMovimiento;
    }
    
    public void setClavado(Boolean c) {
    	this.clavado = c;
    }
    
    public void setPrimerMovimiento(boolean pm){
    	this.primerMovimiento = pm;
    }
    
    /*
    // No hace falta el setColor, ni setImagen ya que nunca se cambia, solo se crea
    public void setColor(Boolean color) {
    	this.color = color;
    }
    */
    
}