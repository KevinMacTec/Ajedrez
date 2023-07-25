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
     Precondici�n: Se ejecuta mover solo cuando es v�lido, Tablero implementar� una funcion
     llamada Boolean validarMovimiento(int fila, int columna) donde se le pasa la fila y 
     la columna, y con eso se obtiene la pieza en el Tablero
     */
    public void moverPieza(int fila, int columna) {
    	Pieza p = tablero[fila][columna]; 
    	if(p != null) {
	    	switch (p.getClass().getSimpleName()) {
	        case "Peon":
	            // Realizar movimiento espec�fico para pe�n
	            break;
	        case "Caballo":
	            // Realizar movimiento espec�fico para caballo
	            break;
	        case "Alfil":
	            // Realizar movimiento espec�fico para alfil
	            break;
	        case "Torre":
	            // Realizar movimiento espec�fico para torre
	            break;
	        case "Reina":
	            // Realizar movimiento espec�fico para reina
	            break;
	        case "Rey":
	            // Realizar movimiento espec�fico para rey
	            break;
	        }
    	}
    }

    // M�todo para imprimir el tablero realmente no estar� ac�, sino en la clase de JavaFX
}