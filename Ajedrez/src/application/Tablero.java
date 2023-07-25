package application;

public class Tablero {
    private Pieza[][] tablero;
    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;

    public Tablero() {
        tablero = new Pieza[FILAS][COLUMNAS];
        inicializarTablero();
    }

    private void inicializarTablero() {
        // Inicializar las piezas blancas
        tablero[0][0] = new Torre(true);
        tablero[0][1] = new Caballo(true);
        tablero[0][2] = new Alfil(true);
        tablero[0][3] = new Reina(true);
        tablero[0][4] = new Rey(true);
        tablero[0][5] = new Alfil(true);
        tablero[0][6] = new Caballo(true);
        tablero[0][7] = new Torre(true);

        // Inicializar los peones blancos
        for (int col = 0; col < COLUMNAS; col++) {
            tablero[1][col] = new Peon(true);
        }

        // Inicializar las piezas negras
        tablero[7][0] = new Torre(false);
        tablero[7][1] = new Caballo(false);
        tablero[7][2] = new Alfil(false);
        tablero[7][3] = new Reina(false);
        tablero[7][4] = new Rey(false);
        tablero[7][5] = new Alfil(false);
        tablero[7][6] = new Caballo(false);
        tablero[7][7] = new Torre(false);

        // Inicializar los peones negros
        for (int col = 0; col < COLUMNAS; col++) {
            tablero[6][col] = new Peon(false);
        }
    }
    
    /*
     Precondición: Se ejecuta mover solo cuando es válido, Tablero implementará una funcion
     llamada Boolean validarMovimiento(int fila, int columna) donde se le pasa la fila y 
     la columna, y con eso se obtiene la pieza en el Tablero
     */
    public void moverPieza(int fila, int columna) {
    	Pieza p = tablero[fila][columna]; 
    	if(p != null) {
	    	switch (p.getClass().getSimpleName()) {
	        case "Peon":
	            // Realizar movimiento específico para peón
	            break;
	        case "Caballo":
	            // Realizar movimiento específico para caballo
	            break;
	        case "Alfil":
	            // Realizar movimiento específico para alfil
	            break;
	        case "Torre":
	            // Realizar movimiento específico para torre
	            break;
	        case "Reina":
	            // Realizar movimiento específico para reina
	            break;
	        case "Rey":
	            // Realizar movimiento específico para rey
	            break;
	        }
    	}
    }

    // Método para imprimir el tablero realmente no estará acá, sino en la clase de JavaFX
}