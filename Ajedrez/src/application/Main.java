package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		stage.setResizable(false);
		//scene.getStylesheets().add(getClass().getResource("/application.css"));
		stage.setTitle("Ajedrez");
		Image icono = new Image("icono.png");
		stage.getIcons().add(icono);
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

/*
package application;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
public class Main {
	
	//FALTA, esto es temporal para probar la logica, hacerlo en terminal con representacion en ASCII
    public static void imprimirTablero(Tablero t){ // terminal
    	 Pieza[][] tablero = t.getTablero();
    	for(int k=1;k<=4*8;k++){
    		System.out.print("=");
    	}
    	
    	System.out.print("\n");
    	for(int i=7;i>=0;i--) {
	    	for(int j=0;j<=7;j++) {
	    		System.out.print("|");
	    		if(tablero[i][j] == null) {
	    			System.out.print("  ");
	    		}else{
	    			switch (tablero[i][j].getClass().getSimpleName()) {
	    				case "Peon":
	    					System.out.print("P");
	    				break;
	    				case "Caballo":
	    					System.out.print("C");
	    				break;
	    				case "Alfil":
	    					System.out.print("A");
	    				break;
	    				case "Torre":
	    					System.out.print("T");
		    			break;
	    				case "Reina":
	    					System.out.print("R");
		    			break;
	    				default:
	    					System.out.print("r");
		    			break;
		    		}
	    			if(tablero[i][j].getColor()){
	    				System.out.print("b");
					}else{
						System.out.print("n");
					}
	    		}
	    		System.out.print("|");
	    		
	    	}
	    	System.out.print(" "+(i+1));
	    	System.out.print("\n");
	    	for(int k=1;k<=4*8;k++){
	    		System.out.print("=");
	    	}
	    	System.out.print("\n");
    	}
    	for(int k=0;k<=7;k++){
    		System.out.print(" ");
    		switch (k){
    			case 0:
    				System.out.print("a");
    			break;
    			case 1:
    				System.out.print("b");
        		break;
    			case 2:
    				System.out.print("c");
        		break;
    			case 3:
    				System.out.print("d");
        		break;
    			case 4:
    				System.out.print("e");
        		break;
    			case 5:
    				System.out.print("f");
        		break;
    			case 6:
    				System.out.print("g");
        		break;
    			default:
    				System.out.print("h");
        		break;
    		}
    		System.out.print("  ");
    	}
    	System.out.print("\n");
    	System.out.print("\n");
    	if(t.existeJugada()){
    		System.out.print("Turno de ");
        	if(t.getTurno()){
        		System.out.print("Blancas");	
        	}else{
        		System.out.print("Negras");
        	}
        	System.out.print("\n");
    	}
    }
    
    /*public static void imprimirCoordenadas(Set<Coordenadas> coordenadas){ //terminal
    	Iterator<Coordenadas> it = coordenadas.iterator(); // si es null, no se puede hacer coordenadas.iterador()
    	System.out.print("{");
    	while(it.hasNext()){
    		imprimirCoordenada(it.next());
    		if (it.hasNext()) {
                System.out.print(";");
            }
    	}
    	System.out.print("}");
    }
    
    /*public static void imprimirCoordenada(Coordenadas c){ // terminal
    	//System.out.print("("+(c.getFila()+1)+",");
    	String caracter;
    	switch (c.getColumna()){
    	case 0:
			caracter = "a";
		break;
    	case 1:
			caracter = "b";
		break;
    	case 2:
			caracter = "c";
		break;
    	case 3:
			caracter = "d";
		break;
    	case 4:
			caracter = "e";
		break;
    	case 5:
			caracter = "f";
		break;
    	case 6:
			caracter = "g";
		break;
    	default:
			caracter = "h";
		break;
    	}
    	System.out.print(caracter+")");
    }

    /*public static void imprimirMovimientosPosibles(Tablero t){ // terminal
    	Set<Coordenadas> piezas = t.getPiezas(t.getTurno());
    	Iterator<Coordenadas> it = piezas.iterator();
    	Coordenadas c;
    	while(it.hasNext()){
    		c = it.next();
    		System.out.print(t.getTablero()[c.getFila()][c.getColumna()].getClass().getSimpleName()+" ");
    		imprimirCoordenada(c);
    		System.out.print(" --> ");
    		imprimirCoordenadas(t.movimientosPosiblesUnaPieza(c.getFila(), c.getColumna()));
    		System.out.print("\n");
    	}
    }
    
    /*public static Coordenadas elegirPieza(Tablero t, Set<Coordenadas> piezas, Scanner scanner){ // terminal
    	Coordenadas c = new Coordenadas(8,8);
    	boolean existeFila = false;
    	boolean existePieza = false;
    	Integer fila = 0;
    	String columna = "";
    	while(!existePieza){
    		//System.out.print("\n");
    		existeFila = false;
    		while(!existeFila){
            	//System.out.print("Elegir la fila (numero desde 1 hasta 8): ");
            	//fila = scanner.nextInt();
            	//scanner.nextLine();
        		if(fila>=1 && fila<=8){
        			existeFila = true;
        		}
    		}
        	//System.out.print("Elegir la columna (caracter desde 'a' hasta 'h'), ingresa otro caracter si quieres elegir fila denuevo: ");
        	//columna = scanner.next();
        	//scanner.nextLine();
    		switch(columna){
        		case "a":
        			c = new Coordenadas(fila-1,0);
        		break;
        		case "b":
        			c = new Coordenadas(fila-1,1);
            	break;
        		case "c":
        			c = new Coordenadas(fila-1,2);
            	break;
        		case "d":
        			c = new Coordenadas(fila-1,3);
            	break;
        		case "e":
        			c = new Coordenadas(fila-1,4);
            	break;
        		case "f":
        			c = new Coordenadas(fila-1,5);
            	break;
        		case "g":
        			c = new Coordenadas(fila-1,6);
            	break;
        		case "h":
        			c = new Coordenadas(fila-1,7);
            	break;
            	default:
            		c = new Coordenadas(8,8); //no existe, no es necesario pero por si acaso
            	break;
    		}
    		if(piezas.contains(c)){
				existePieza = true;
			}else{
				existePieza = false;
			}	
    	}
    	return c;
    }
    
    //precondicion: ini tiene una pieza
    /*public static Coordenadas elegirMovimiento(Tablero t, Coordenadas ini, Scanner scanner){ // FALTA: JavaFX
    	Set<Coordenadas> movimientos = t.movimientosPosiblesUnaPieza(ini.getFila(), ini.getColumna());
    	Coordenadas fin = new Coordenadas(8,8); //coordenada no existente
    	while(!movimientos.contains(fin)){
    		System.out.print("\nElige a donde quieres mover la pieza");
        	fin = elegirPieza(t,movimientos,scanner);
    	}
    	System.out.print("\n");
    	return fin;
    }
    
    /*public static Pieza coronacion(boolean color, Scanner scanner){ // terminal
    	//System.out.print("elegir el n�mero correspondiente a la pieza que se quiere coronar (Reina = 1, Torre = 2, Caballo = 3, Alfil = 4) "); // FALTA: JavaFX
    	int numero = 1;
    	//numero = scanner.nextInt();
    	Pieza pieza;
    	switch (numero){
    		case 2:
    			if(color){
    				pieza = new Torre(color,"/torre_blanca.png");
    			}else{
    				pieza = new Torre(color,"/torre_negra.png");
    			}
        	break;
    		case 3:
    			if(color){
    				pieza = new Caballo(color,"/caballo_blanco.png");
    			}else{
    				pieza = new Caballo(color,"/caballo_negro.png");
    			}
        	break;
    		case 4:
    			if(color){
    				pieza = new Alfil(color,"/alfil_blanco.png");
    			}else{
    				pieza = new Alfil(color,"/alfil_negro.png");
    			}
        	break;
        	default:
    			if(color){
    				pieza = new Reina(color,"/reina_blanca.png");
    			}else{
    				pieza = new Reina(color,"/reina_negra.png");
    			}
        	break;
    	}    
    	return pieza;
    }
    
 // Precondicion: Se asume que hay una pieza en las coordenadas ini. El movimiento es v�lido, tanto en el sentido de que es un lugar donde la pieza se 
    // puede mover porque as� es su movimiento, como el hecho de que requiera condiciones especiales para moverse
    // (enroque, an passant, bloqueo de jaque o movimiento que no ponga en jaque al rey) 
    public static void ejecutarMovimiento(Tablero t, Coordenadas ini, Coordenadas fin, int movEspecial,Scanner scanner) { // FALTA: JavaFX
    	Pieza[][] tablero = t.getTablero();
    	boolean color = tablero[ini.getFila()][ini.getColumna()].getColor();
    	Coordenadas anPassant_ini = new Coordenadas(fin.getFila(),fin.getColumna());
    	Coordenadas anPassant_fin = new Coordenadas(fin.getFila(),fin.getColumna());
		switch (movEspecial) {
        	case 1: //Si es enroque izq
        		if(color){
        			t.actualizarPieza(new Coordenadas(0,0), new Coordenadas(0,3));
        		}else{
        			t.actualizarPieza(new Coordenadas(7,0), new Coordenadas(7,3));
        		}
			break;
        	case 2: //Si es enroque der
        		if(color){
        			t.actualizarPieza(new Coordenadas(0,7), new Coordenadas(0,5));
        		}else{
        			t.actualizarPieza(new Coordenadas(7,7), new Coordenadas(7,5));
        		}
			break;
        	case 3: //Si es AnPassant
        		if(color){
        			anPassant_ini.setFila(6);
            		anPassant_fin.setFila(fin.getFila()-1);
        		}else{
        			anPassant_ini.setFila(1);
            		anPassant_fin.setFila(fin.getFila()+1);
        		}
        		Pieza p = tablero[anPassant_fin.getFila()][anPassant_fin.getColumna()];
        		Movimiento m1 = new Movimiento(anPassant_ini,anPassant_fin,p);
        		if(color && anPassant_ini.getFila() == (fin.getFila()+1) && t.getMovimientos().get(t.getMovimientos().size()-1).equals(m1)) { // si el ultimo movimiento permite un anPassant
        			t.borrarPieza(anPassant_fin);
        		}else if(!color && anPassant_ini.getFila() == (fin.getFila()-1) && t.getMovimientos().get(t.getMovimientos().size()-1).equals(m1)){
        			t.borrarPieza(anPassant_fin);
        		}
			break;
			default:
				//no hace nada
			break;
		}
		if(color && tablero[ini.getFila()][ini.getColumna()].getClass().getSimpleName().equals("Peon") && (fin.getFila())==7){
			Pieza pieza = null;
			//pieza = coronacion(color,scanner); // FALTA: JavaFX
			t.borrarPieza(ini);
			t.borrarPieza(fin);
			t.agregarPieza(fin, pieza);
			tablero[fin.getFila()][fin.getColumna()].setPrimerMovimiento(false);
		}else if (!color && tablero[ini.getFila()][ini.getColumna()].getClass().getSimpleName().equals("Peon") && (fin.getFila())==0){
			Pieza pieza = null;
			//pieza = coronacion(color,scanner); // FALTA: JavaFX
			t.borrarPieza(ini);
			t.borrarPieza(fin);
			t.agregarPieza(fin, pieza);
			tablero[fin.getFila()][fin.getColumna()].setPrimerMovimiento(false);
		}else {
			t.actualizarPieza(ini,fin);
		}
    	Movimiento m = new Movimiento(ini,fin,tablero[fin.getFila()][fin.getColumna()]);
    	t.getMovimientos().add(m);
    }
    
    /*public static void imprimirMovimientos(List<Movimiento> movimientos){ // terminal
		Iterator<Movimiento> it = movimientos.iterator();
		Movimiento imprimir;
		while(it.hasNext()){
			imprimir = it.next();
			System.out.print(imprimir.getPieza().getClass().getSimpleName()+" ");
			imprimirCoordenada(imprimir.getIni());
			System.out.print(" --> ");
			imprimirCoordenada(imprimir.getFin());
			System.out.print(" ; ");
		}
		System.out.print("\n\n");
    }
    
    /*public static void imprimirUltimoMovimiento(List<Movimiento> movimientos){
		Movimiento imprimir = movimientos.get(movimientos.size()-1);
		System.out.print(imprimir.getPieza().getClass().getSimpleName()+" ");
		imprimirCoordenada(imprimir.getIni());
		System.out.print(" --> ");
		imprimirCoordenada(imprimir.getFin());
		System.out.print(" ; \n\n");
    }
    
  //FALTA: adelantar o retroceder una lista de movimientos (y piezas comidas)
    
    // precondición: dado un tablero finalizado avanza o retrocede los movimientos hechos
    // como no le pase una copia por valor de tablero, mov y comida, me va a modificar todo y no quiero eso
    // FALTA: terminar y testear esta función, tengo que crear funciones auxiliares para poder avanzar o retroceder y no usar agregarPieza() borrarPieza() o actualizarPieza()
    // Esas 3 funciones me modifican mov,comida, y otros valores que no quiero
    /*
    private static void reconstruirMovimientos(Tablero t,int direccion,Scanner scanner){ //direccion: otro = terminar funcion, 1 = adelante, 0 = atras
    	List<Movimiento> mov = t.getMovimientos();
    	List<PiezaComida> comida = t.getPiezasComidas();
    	int indiceMov = mov.size()-1;
    	int indicePiezaComida = comida.size()-1;
    	Movimiento m;
    	PiezaComida pc;
    	//pc.getTurno() siempre es <= indiceMov
    	switch(direccion){
    		case 0:
    			if(indiceMov>=0){
    	    		
    	    		m = mov.get(indiceMov);
    	    		pc = comida.get(indicePiezaComida);
    	    		while(pc.getTurno()==indiceMov){
    	    			//t.agregarPieza(m.getFin(),pc.getPieza());
    	    			//t.actualizarPieza(m.getFin(), m.getIni()); //regresa hacia atras
    	    			indicePiezaComida--;
    	    			pc = comida.get(indicePiezaComida);
    	    		}
    	    		indiceMov--;
    	    		
    	    	}
    		break;
    		case 1:
    			if(indiceMov<mov.size()){
    	    		
    	    		m = mov.get(indiceMov);
    	    		pc = comida.get(indicePiezaComida);
    	    		while(pc.getTurno()==indiceMov){
    	    			// No estoy seguro que ejecutarMovimiento no me modifique cosas que no quiera
    	    			// No es ejecutarMovimiento porque en caso de coronacion, ya se que pieza se elige
    	    			//ejecutarMovimiento(t,m.getIni(),m.getFin(),t.movimientoEspecial(m.getIni(),m.getFin()),scanner);
    	    			//t.actualizarPieza(m.getIni(), m.getFin()); // en caso de enroque no se esta moviendo la torre
    	    			//t.agregarPieza(m.getFin(),pc.getPieza());
    	    			indicePiezaComida++;
    	    			pc = comida.get(indicePiezaComida);
    	    		}
    	    		indiceMov++;
    	    		
    	    	}
    		break;
    		default:
    			return;
    	}
    	
    }
    
	
    public static boolean obtenerTipoTablero(){
		boolean resultado = true;
		//FALTA: Interaccion JavaFX
		return resultado;
	}
    
	public static void main(String[] args){
		//gameloop
		//System.out.print("Quiere jugar una partida normal o personalizada? (1 = normal / 0 = personalizada) "); //JavaFX
		Tablero t = new Tablero(obtenerTipoTablero());
		Coordenadas ini = new Coordenadas(8,8);
		Coordenadas fin = new Coordenadas(8,8);
		Coordenadas vacia = new Coordenadas(8,8);
		//int contador = 0; // esta linea es de prueba
		while(t.existeJugada()){
			//limpiarPantalla(); // no se como se hace en terminal
			//imprimirTablero(t);
			t.piezasClavadas(t.getTurno());
			//imprimirMovimientosPosibles(t);
			System.out.print("\nElige la pieza que quieres mover");
			do{
				//ini = elegirPieza(t,t.getPiezas(t.getTurno()),scanner); //elige entre las piezas disponibles // FALTA: JavaFX
				//fin = elegirMovimiento(t, ini,scanner); //para dicha pieza, elige el conjunto de movimientos // FALTA: JavaFX
			}while(fin == vacia);
			// FALTA crear casos tests, borrar pantalla para volver imprimir y revisar si se ingresa mal al elegir
			ejecutarMovimiento(t,ini,fin,t.movimientoEspecial(ini, fin), scanner); // FALTA: JavaFX
			//imprimirMovimientos(t.getMovimientos());
			//imprimirUltimoMovimiento(t.getMovimientos());
			fin = vacia;
			t.setTurno(!t.getTurno());
		}
		//imprimirTablero(t); // FALTA: JavaFX
		if(t.jaqueMate(t.getTurno()) != 2){
			if(!t.getTurno()){
				//System.out.print("Gana Blancas"); // FALTA: JavaFX
			}else{
				//System.out.print("Gana Negra"); // FALTA: JavaFX
			}
		}else{
			//System.out.print("Empate"); // FALTA: JavaFX
		}
		
	}
}*/
