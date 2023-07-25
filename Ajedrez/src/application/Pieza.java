package application;

abstract class Pieza {
    private Boolean color; //True = Blanco, False = Negro

    public Pieza(Boolean color) {
        this.color = color;
    }

    // Getters y setters
    public Boolean getColor() {
        return this.color;
    }
    
    /*
    // No hace falta el setColor ya que nunca se cambia, solo se crea
    public void setColor(Boolean color) {
    	this.color = color;
    }
    */
    
}