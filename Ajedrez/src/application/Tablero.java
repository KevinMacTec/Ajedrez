package application;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Tablero {
    private Pieza[][] tablero;
    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;
    // Crear una lista de tama�o din�mico
    private List<Movimiento> movimientos;
    private List<PiezaComida> piezasComidas;
    private Set<Coordenadas> blancas;
    private Set<Coordenadas> negras;
    private Coordenadas reyBlanco;
    private Coordenadas reyNegro;
    private boolean turno;

    public Tablero(boolean standart) {
        tablero = new Pieza[FILAS][COLUMNAS];
        if(standart){
        	List<Pieza> piezas = new ArrayList<>();
        	List<Coordenadas> coordenadas = new ArrayList<>();
        	Pieza p;
        	Coordenadas c;
        	
        	p = new Torre(true,"torre_blanca.png");
        	c = new Coordenadas(0,0);
        	piezas.add(p);
    		coordenadas.add(c);
        	
        	p = new Caballo(true,"caballo_blanco.png");
        	c = new Coordenadas(0,1);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Alfil(true,"alfil_blanco.png");
        	c = new Coordenadas(0,2);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Reina(true,"reina_blanca.png");
        	c = new Coordenadas(0,3);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Rey(true,"rey_blanco.png");
        	c = new Coordenadas(0,4);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Alfil(true,"alfil_blanco.png");
        	c = new Coordenadas(0,5);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Caballo(true,"caballo_blanco.png");
        	c = new Coordenadas(0,6);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Torre(true,"torre_blanca.png");
        	c = new Coordenadas(0,7);
        	piezas.add(p);
    		coordenadas.add(c);
        	
        	for(int i=0;i<8;i++){
        		p = new Peon(true,"peon_blanco.png");
        		c = new Coordenadas(1,i);
        		piezas.add(p);
        		coordenadas.add(c);
        	}
        	    		
        	for(int i=0;i<8;i++){
        		p = new Peon(false,"peon_negro.png");
        		c = new Coordenadas(6,i);
        		piezas.add(p);
        		coordenadas.add(c);
        	}
        	
        	p = new Torre(false,"torre_negra.png");
        	c = new Coordenadas(7,0);
        	piezas.add(p);
    		coordenadas.add(c);
        	
        	p = new Caballo(false,"caballo_negro.png");
        	c = new Coordenadas(7,1);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Alfil(false,"alfil_negro.png");
        	c = new Coordenadas(7,2);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Reina(false,"reina_negra.png");
        	c = new Coordenadas(7,3);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Rey(false,"rey_negro.png");
        	c = new Coordenadas(7,4);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Alfil(false,"alfil_negro.png");
        	c = new Coordenadas(7,5);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Caballo(false,"caballo_negro.png");
        	c = new Coordenadas(7,6);
        	piezas.add(p);
    		coordenadas.add(c);
    		
    		p = new Torre(false,"torre_negra.png");
        	c = new Coordenadas(7,7);
        	piezas.add(p);
    		coordenadas.add(c);
    		
        	boolean turno = true;
        	inicializarTablero(piezas,coordenadas,turno);
        }else{
        	//crearTableroPersonalizado(scanner); //terminal
        	/*
			List<Pieza> piezas = new ArrayList<>();
        	List<Coordenadas> coordenadas = new ArrayList<>();
        	boolean turno;
        	piezas = obtenerPiezas();
        	coordenadas = obtenerCoordenadas();
        	turno = obtenerTurno();
			inicializarTablero(piezas,coordenadas,turno);
        	*/
        }
    }
    
    //precondicion: piezas.size() == coordenadas.size()
    public void inicializarTablero(List<Pieza> piezas, List<Coordenadas> coordenadas, boolean turno){ //creado especialmente para casos Tests
    	Pieza[][] tablero = this.getTablero();
    	Iterator<Pieza> it = piezas.iterator();
    	Iterator<Coordenadas> it2 = coordenadas.iterator();
    	Pieza p;
    	Coordenadas c;
    	
    	// Inicializar el tablero como null
    	for (int fil = 0; fil < FILAS; fil++) {
    		for (int col = 0; col < COLUMNAS; col++) {
                tablero[fil][col] = null;
            }
        }
    	
    	this.setMovimientos(new ArrayList<>());
        this.setPiezasComidas(new ArrayList<>());
        this.setBlancas(new HashSet<>());
        this.setNegras(new HashSet<>());
        this.setTurno(turno);
        
        
    	while(it.hasNext() && it2.hasNext()){
    		p = it.next();
    		c = it2.next();

        	this.agregarPieza(c, p);
    	}
    }

    public Pieza[][] getTablero(){
    	return this.tablero;
    }
    
    public List<Movimiento> getMovimientos() {
    	return this.movimientos;
    }
    
    public Set<Coordenadas> getPiezas(boolean color){
    	if(color){
    		return this.blancas;
    	}else{
    		return this.negras;
    	}
    }
    
    public Coordenadas getRey(boolean color) {
    	if(color) {
    		return this.reyBlanco;
    	}else{
    		return this.reyNegro;
    	}
    }
    
    public boolean getTurno(){
    	return this.turno;
    }
    
    public List<PiezaComida> getPiezasComidas(){
    	return this.piezasComidas;
    }
    
    public void setTurno(boolean t){
    	this.turno = t;
    }
    
    //Precondici�n: p!=rey
    public void borrarPieza(Coordenadas c){
    	Pieza p = this.getTablero()[c.getFila()][c.getColumna()];
    	if(p != null) {
    		boolean color = p.getColor();
    		PiezaComida comida = new PiezaComida(p,c,this.getMovimientos().size());
    		this.getPiezasComidas().add(comida);
            this.getPiezas(color).remove(c);
            this.getTablero()[c.getFila()][c.getColumna()] = null;
    	}
    }
    
    //Precondici�n: En la coordenada c pertenece al tablero y no tiene otra pieza aparte de la nueva p
    public void agregarPieza(Coordenadas c, Pieza p){
    	if(p!=null){
    		boolean color = p.getColor();
        	this.getPiezas(color).add(c);
        	this.getTablero()[c.getFila()][c.getColumna()] = p;
        	if(p.getClass().getSimpleName().equals("Rey")){
        		this.setRey(c, color);
        	}
    	}
    }
    
    public void setRey(Coordenadas c, boolean color) {
    	if(color) {
    		this.reyBlanco = c;
    	}else{
    		this.reyNegro = c;
    	}
    }
    
    public void setMovimientos(List<Movimiento> mov){
    	this.movimientos = mov;
    }
    
    public void setPiezasComidas(List<PiezaComida> comidas){
    	this.piezasComidas = comidas;
    }
    
    public void setBlancas(Set<Coordenadas> blanca){
    	this.blancas = blanca;
    }
    
    public void setNegras(Set<Coordenadas> negra){
    	this.negras = negra;
    }
    
    //Precondicion: ini existe en List<Coordenadas> (ini tiene una pieza)
    public void actualizarPieza(Coordenadas ini, Coordenadas fin){
    	Pieza p = this.getTablero()[ini.getFila()][ini.getColumna()];
    	if(p.getClass().getSimpleName().equals("Rey")){
    		this.setRey(fin, p.getColor());
    	}
    	this.borrarPieza(fin);
    	p.setPrimerMovimiento(false);
    	this.agregarPieza(fin,p);
    	this.borrarPieza(ini);
    }
    
    //crear tablero personalizado, Esta función tiene que tener interacción con el usuario (sin System.out.print ni scanner)
    // precondicion: el tablero es null en las coordenadas ingresadas para cada pieza
    /*
    public void crearTableroPersonalizado(Scanner scanner){ //funcion guia para un entorno gráfico avanzado
    	int aceptar;
    	int cantidad;
    	int fila;
    	int turno;
    	String columna;
    	Coordenadas c;
    	
		Pieza[][] tablero = this.getTablero();
		    	
    	// Inicializar el tablero como null
    	for (int fil = 0; fil < FILAS; fil++) {
    		for (int col = 0; col < COLUMNAS; col++) {
                tablero[fil][col] = null;
            }
        }
    	
    	this.setMovimientos(new ArrayList<>());
        this.setPiezasComidas(new ArrayList<>());
        this.setBlancas(new HashSet<>());
        this.setNegras(new HashSet<>());
    	
    	for(int k=0;k<2;k++){
			for(int i=0;i<5;i++){
				System.out.print("Desea ingresar ");
				switch (i){
					case 0:
						System.out.print("Peones");
					break;
					case 1:
						System.out.print("Caballos");
					break;
					case 2:
						System.out.print("Alfiles");
					break;
					case 3:
						System.out.print("Torres");
					break;
					case 4:
						System.out.print("Reina");
					break;
				}
				if(k==0){
					System.out.print(" Blancos");
				}else{
					System.out.print(" Negros");
				}
				System.out.print("? (1 = si, 0 = no): ");
				aceptar = scanner.nextInt();
		    	scanner.nextLine();
		    	if(aceptar==1){
		    		System.out.print("Cuantos?: ");
		    		cantidad = scanner.nextInt();
		        	scanner.nextLine();
		    		switch(i){
		    			case 0:
		    				if(cantidad>8){
		    					cantidad = 8;
		    				}else if(cantidad<1){
		    					cantidad = 1;
		    				}
		    			break;
		    			default:
		    				if(cantidad<1){
		    					cantidad = 1;
		    				}
		    			break;
		    		}
		    		for(int j=1;j<=cantidad;j++){
		    			System.out.print("Ingrese fila de la pieza " + j + "(numero del 1 a 8): ");
		    			fila = scanner.nextInt();
		            	scanner.nextLine();
		            	if(fila<1){
		            		fila = 1;
		            	}else if (fila >8){
		            		fila = 8;
		            	}
		            	
		    			System.out.print("Ingrese columna de la pieza " + j + "(caracter del 'a' al 'h'): ");
		    			columna = scanner.next();
		            	scanner.nextLine();
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
		            		default:
		            			c = new Coordenadas(fila-1,7);
		                	break;
		        		}
		        		Pieza p;
		        		if(k==0){ //blancas
		        			switch(i){
		        				case 0:
		        					p = new Peon(true,"/peon_blanco.png");
		        				break;
		        				case 1:
		        					p = new Caballo(true,"/caballo_blanco.png");
		        				break;
		        				case 2:
		        					p = new Alfil(true,"/alfil_blanco.png");
		        				break;
		        				case 3:
		        					p = new Torre(true,"/torre_blanca.png");
		        				break;
		        				default:
		        					p = new Reina(true,"/reina_blanca.png");
		        				break;
		        			}
		        		}else{
		        			switch(i){
		        				case 0:
		        					p = new Peon(false,"/peon_negro.png");
		        				break;
		        				case 1:
		        					p = new Caballo(false,"/caballo_negro.png");
		        				break;
		        				case 2:
		        					p = new Alfil(false,"/alfil_negro.png");
		        				break;
		        				case 3:
		        					p = new Torre(false,"/torre_negra.png");
		        				break;
		        				default:
		        					p = new Reina(false,"/reina_negra.png");
		        				break;
		        			}
		        		}
		        		this.agregarPieza(c, p);
		    		}
		    	}
			}
			System.out.print("Coordenadas del Rey");
    		if(k==0){
				System.out.print(" Blanco\n");
			}else{
				System.out.print(" Negro\n");
			}
    		System.out.print("Ingrese fila del rey: ");
			fila = scanner.nextInt();
        	scanner.nextLine();
			System.out.print("Ingrese columna del rey: ");
			columna = scanner.next();
        	scanner.nextLine();
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
        		default:
        			c = new Coordenadas(fila-1,7);
            	break;
    		}
    		
    		if(k==0){
    			this.agregarPieza(c,new Rey(true,"/rey_blanco.png"));
    		}else{
    			this.agregarPieza(c,new Rey(false,"/rey_negro.png"));
    		}
    	}
    	System.out.print("De quien es el turno? (1 = Blanca, 0 = Negras): ");
		turno = scanner.nextInt();
    	scanner.nextLine();
    	this.setTurno(turno==1);
    }
    */
    
    public boolean hayPieza(int fila, int columna) {
    	Pieza[][] tablero = this.getTablero();
    	return tablero[fila][columna] != null;
    }
    
    public boolean hayPiezaDistintoColor(int fila, int columna, boolean color) {
    	Pieza[][] tablero = this.getTablero();
    	return hayPieza(fila,columna) && tablero[fila][columna].getColor() != color;
    }
    
    public boolean hayPiezaIgualColor(int fila, int columna, boolean color) {
    	Pieza[][] tablero = this.getTablero();
    	return hayPieza(fila,columna) && tablero[fila][columna].getColor() == color;
    }
    
    // para no hacer 2 funciones amenazaEnCasilla que retorne bool por solo 1, y una que retorne multiples, le paso una nueva entrada y con eso checkeo 1 o 2 amenazas
    // dada una casilla, revisa si esa casilla esta amenazada por !color (si le pasa blanco, entonces revisa amenaza de negro)
    public int amenazaEnCasilla(int fila, int columna, boolean color, boolean multiple) { // retorna la cantidad de amenazas en esa casilla. Si es m�s de 2, retorna 2.
    	int resultado = 0;
    	Pieza[][] tablero = this.getTablero();
    	boolean flag = false; //para ahorrar tiempo, optimizo la implementacion de chequeo en una casilla
    	boolean flag2 = true; //para que no siga revisando direcciones que ya no pueden ser amenaza
    	int filaAux = fila;
    	int columnaAux = columna;
    	int iter = 0; //max 7
    	int amenazas;
    	if(multiple){
    		amenazas = 2;
    	}else{
    		amenazas = 1;
    	}
    	while(resultado<amenazas && iter < 8) { // las 8 posibles direcciones de una casilla (2 en horizontal, 2 en vertical y 2 en cada diagonal (son 2 diagonales))
    		switch (iter) {
    			case 0: //horizontal izquierda
    				while(flag2 && resultado<amenazas && columnaAux>0) {
    					columnaAux--;
    					if(!flag && columna-columnaAux == 1) {
    						flag = true;
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey"))) {
    						    resultado++;						
    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag2 = false;
    						}
    					}else{
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    							resultado++;
    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag2 = false;
    						}
    					}
    				}
    			break;
    			case 1: //horizontal derecha
    				while(flag2 && resultado<amenazas && columnaAux<7) {
    					columnaAux++;
    					if(!flag && columnaAux-columna == 1) {
    						flag = true;
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey"))) {
    						    resultado++;						
    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag2 = false;
    						}
    					}else{
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    							resultado++;
    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag2 = false;
    						}
    					}
    				}
    			break;
    			case 2: //vertical arriba
    				while(flag2 && resultado<amenazas && filaAux<7) {
    					filaAux++;
    					if(!flag && filaAux-fila == 1) {
    						flag = true;
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey"))) {
    						    resultado++;						
    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag2 = false;
    						}
    					}else{
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    							resultado++;
    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag2 = false;
    						}
    					}
    				}
    			break;
    			case 3: //vertical abajo
    				while(flag2 && resultado<amenazas && filaAux>0) {
    					filaAux--;
    					if(!flag && fila-filaAux == 1) {
    						flag = true;
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey"))) {
    						    resultado++;						
    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag2 = false;
    						}
    					}else{
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    							resultado++;
    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag2 = false;
    						}
    					}
    				}
    			break;
    			case 4: //diagonal arriba izquierda 
        			if(color){
        				while(flag2 && resultado<amenazas && (columnaAux>0 && filaAux<7)) {
        					columnaAux--;
        					filaAux++;
        					if(!flag && columna-columnaAux == 1) {
        						flag = true;
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Peon"))) {
        						    resultado++;						
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}else{
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") )) {
        							resultado++;
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}
        				}
        			}else{
        				while(flag2 && resultado<amenazas && (columnaAux>0 && filaAux<7)) {
        					columnaAux--;
        					filaAux++;
        					if(!flag && columna-columnaAux == 1) {
        						flag = true;
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey"))) {
        						    resultado++;						
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}else{
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") )) {
        							resultado++;
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}
        				}
        			}
    			break;
    			case 5: //diagonal arriba derecha
    				if(color){
        				while(flag2 && resultado<amenazas && (columnaAux<7 && filaAux<7)) {
        					columnaAux++;
        					filaAux++;
        					if(!flag && columnaAux-columna == 1) {
        						flag = true;
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Peon"))) {
        						    resultado++;						
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}else{
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") )) {
        							resultado++;
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}
        				}
        			}else{
        				while(flag2 && resultado<amenazas && (columnaAux<7 && filaAux<7)) {
        					columnaAux++;
        					filaAux++;
        					if(!flag && columnaAux-columna == 1) {
        						flag = true;
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey"))) {
        						    resultado++;						
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}else{
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") )) {
        							resultado++;
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}
        				}
        			}
    			break;
    			case 6: //diagonal abajo izquierda
    				if(color){
        				while(flag2 && resultado<amenazas && (columnaAux>0 && filaAux>0)) {
        					columnaAux--;
        					filaAux--;
        					if(!flag && columna-columnaAux == 1) {
        						flag = true;
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey"))) {
        						    resultado++;						
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}else{
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") )) {
        							resultado++;
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}
        				}
        			}else{
        				while(flag2 && resultado<amenazas && (columnaAux>0 && filaAux>0)) {
        					columnaAux--;
        					filaAux--;
        					if(!flag && columna-columnaAux == 1) {
        						flag = true;
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Peon"))) {
        						    resultado++;						
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}else{
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") )) {
        							resultado++;
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}
        				}
        			}
    			break;
    			case 7: //diagonal abajo derecha
    				if(color){
        				while(flag2 && resultado<amenazas && (columnaAux<7 && filaAux>0)) {
        					columnaAux++;
        					filaAux--;
        					if(!flag && columnaAux-columna == 1) {
        						flag = true;
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey"))) {
        						    resultado++;						
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}else{
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") )) {
        							resultado++;
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}
        				}
        			}else{
        				while(flag2 && resultado<amenazas && (columnaAux<7 && filaAux>0)) {
        					columnaAux++;
        					filaAux--;
        					if(!flag && columnaAux-columna == 1) {
        						flag = true;
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Rey") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Peon"))) {
        						    resultado++;						
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}else{
        						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina") )) {
        							resultado++;
        						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
        							flag2 = false;
        						}
        					}
        				}
        			}
    			break;
    		}
    		flag = false;
    		flag2 = true;
    		filaAux = fila;
        	columnaAux = columna;
    		iter++;
    	}
    	if(resultado<amenazas){ //revisa todas las casillas donde puede haber un caballo de otro color
        	if(fila>0) {
        		if(columna>1) {
        			if(hayPiezaDistintoColor(fila-1,columna-2,color) && tablero[fila-1][columna-2].getClass().getSimpleName().equals("Caballo")) {
        				resultado++;
        			}
        		}
        		if(resultado<amenazas && columna<6) {
        			if(hayPiezaDistintoColor(fila-1,columna+2,color) && tablero[fila-1][columna+2].getClass().getSimpleName().equals("Caballo")) {
        				resultado++;
        			}
        		}
        	}
        	if(resultado<amenazas && fila>1) {
        		if(columna>0) {
        			if(hayPiezaDistintoColor(fila-2,columna-1,color) && tablero[fila-2][columna-1].getClass().getSimpleName().equals("Caballo")) {
        				resultado++;
        			}
        		}
        		if(resultado<amenazas && columna<7) {
        			if(hayPiezaDistintoColor(fila-2,columna+1,color) && tablero[fila-2][columna+1].getClass().getSimpleName().equals("Caballo")) {
        				resultado++;
        			}
        		}
        	}
        	if(resultado<amenazas && fila<6) {
        		if(columna>0) {
        			if(hayPiezaDistintoColor(fila+2,columna-1,color) && tablero[fila+2][columna-1].getClass().getSimpleName().equals("Caballo")) {
        				resultado++;
        			}
        		}
        		if(resultado<amenazas && columna<7) {
        			if(hayPiezaDistintoColor(fila+2,columna+1,color) && tablero[fila+2][columna+1].getClass().getSimpleName().equals("Caballo")) {
        				resultado++;
        			}
        		}
        	}
        	if(resultado<amenazas && fila<7) {
        		if(columna>1) {
        			if(hayPiezaDistintoColor(fila+1,columna-2,color) && tablero[fila+1][columna-2].getClass().getSimpleName().equals("Caballo")) {
        				resultado++;
        			}
        		}
        		if(resultado<amenazas && columna<6) {
        			if(hayPiezaDistintoColor(fila+1,columna+2,color) && tablero[fila+1][columna+2].getClass().getSimpleName().equals("Caballo")) {
        				resultado++;
        			}
        		}
        	}
		}
    	return resultado;
    }
    
    public boolean noHayPiezasEnroque(boolean color, boolean izquierda) {
    	boolean resultado = false;
    	if(color){
    		if(izquierda) {
    			resultado = !hayPieza(0,1) && !hayPieza(0,2) && !hayPieza(0,3);
    		}else{
    			resultado = !hayPieza(0,5) && !hayPieza(0,6);
    		}
    	}else{
    		if(izquierda){
    			resultado = !hayPieza(7,1) && !hayPieza(7,2) && !hayPieza(7,3);
    		}else{
    			resultado = !hayPieza(7,5) && !hayPieza(7,6);
    		}
    	}
    	return resultado;
    }
    
    public boolean noHayAmenazaEnroque(boolean color, boolean izquierda) {
    	if(color) {
    		if(izquierda){
    			return amenazaEnCasilla(0,2,color,false) == 0 && amenazaEnCasilla(0,3,color,false) == 0 && amenazaEnCasilla(0,4,color,false) == 0;
    		}else{
    			return amenazaEnCasilla(0,5,color,false) == 0 && amenazaEnCasilla(0,6,color,false) == 0 && amenazaEnCasilla(0,4,color,false) == 0;
    		}
    		//amenazaEnCasilla(0,4,color,false) revisa que no est� en Jaque rey blanco
    		
    	}else{
    		if(izquierda){
    			return amenazaEnCasilla(7,2,color,false) == 0 && amenazaEnCasilla(7,3,color,false) == 0 && amenazaEnCasilla(7,4,color,false) == 0;
    		}else{
    			return amenazaEnCasilla(7,5,color,false) == 0 && amenazaEnCasilla(7,6,color,false) == 0 && amenazaEnCasilla(7,4,color,false) == 0;
    		}
    		//amenazaEnCasilla(7,4,color,false) revisa que no est� en Jaque rey negro
    	}
    }
    
    //recibe el color y dependiendo del color, revisa si es posible, y recibe izquierda para saber de que lado quiere enrocar
    public boolean enroque(boolean color,boolean izquierda) {
    	Pieza[][] tablero = this.getTablero();
    	Coordenadas rey = this.getRey(color);
    	Coordenadas torre;
    	if(color){
    		if(izquierda){
    			torre = new Coordenadas(0,0);
    		}else{
    			torre = new Coordenadas(0,7);
    		}
    	}else{
    		if(izquierda){
    			torre = new Coordenadas(7,0);
    		}else{
    			torre = new Coordenadas(7,7);
    		}
    	}
    	boolean resultado;
    	
    	if(tablero[rey.getFila()][rey.getColumna()].getPrimerMovimiento() && hayPieza(torre.getFila(),torre.getColumna()) && tablero[torre.getFila()][torre.getColumna()].getClass().getSimpleName().equals("Torre") && tablero[torre.getFila()][torre.getColumna()].getPrimerMovimiento() && noHayPiezasEnroque(color,izquierda) && noHayAmenazaEnroque(color,izquierda)) {
    		resultado = true;
    	}else{
    		resultado = false; 
    	}
    	return resultado;
    }
    
    // precondicion: c es la coordenada de un peon. capturaAlPaso no chequea si el peon está clavado (eso lo hace movimientosPosiblesUnaPieza)
    public boolean capturaAlPaso(Coordenadas c,boolean izquierda) { //izquierda significa que el peon se mueve en ese sentido al capturar (pov jugador)
    	Pieza[][] tablero = this.getTablero();
    	boolean color = tablero[c.getFila()][c.getColumna()].getColor();
    	List<Movimiento> movimientos = this.getMovimientos();
    	int ultimo = movimientos.size()-1;
    	if(ultimo<0){
    		return false;
    	}
    	boolean resultado;
    	if(color) { 
    		resultado = (movimientos.get(ultimo).getPieza().getClass().getSimpleName().equals("Peon")) && (movimientos.get(ultimo).getIni().getFila() == 6) && (movimientos.get(ultimo).getFin().getFila() == 4);
    		if(izquierda && movimientos.get(ultimo).getFin().getColumna()<7){
    			resultado = resultado && c.getFila() == 4 && c.getColumna() == movimientos.get(ultimo).getFin().getColumna()+1;
    		}else if(!izquierda && movimientos.get(ultimo).getFin().getColumna()>0){
    			resultado = resultado && c.getFila() == 4 && c.getColumna() == movimientos.get(ultimo).getFin().getColumna()-1;
    		}else{
    			resultado = false;
    		}
    	}else{
    		resultado = movimientos.get(ultimo).getPieza().getClass().getSimpleName().equals("Peon") && movimientos.get(ultimo).getIni().getFila() == 1 && movimientos.get(ultimo).getFin().getFila() == 3;
    		if(izquierda && movimientos.get(ultimo).getFin().getColumna()>0){
    			resultado = resultado && c.getFila() == 3 && c.getColumna() == movimientos.get(ultimo).getFin().getColumna()-1;
    		}else if(!izquierda && movimientos.get(ultimo).getFin().getColumna()<7){
    			resultado = resultado && c.getFila() == 3 && c.getColumna() == movimientos.get(ultimo).getFin().getColumna()+1;
    		}else{
    			resultado = false;
    		}
    	}
    	return resultado;
    }
    
    // Se asume que esta en jaque por ninguna o una pieza pero jamas se llama a esta funci�n si est� en jaque por 2 piezas
    public Set<Coordenadas> bloquearJaque(boolean color){
    	Set<Coordenadas> coordenadas = new HashSet<>();
    	Coordenadas rey = this.getRey(color);
    	Pieza[][] tablero = this.getTablero();
    	int filaAux = rey.getFila();
    	int columnaAux = rey.getColumna();
    	int iter = 0;
    	boolean flag = false; // ayuda a optimizar la validacion de que revisar en una direccion ha terminado
    	boolean flag2 = false; // ayuda a optimizar la validacion de que revisar mientras abs(columna-columnaAux)==1 para los peones
    	
    	while(coordenadas.isEmpty() && iter < 8) { // las 8 posibles direcciones de una casilla (2 en horizontal, 2 en vertical y 2 en cada diagonal (son 2 diagonales))
    		switch (iter) {
				case 0: //horizontal izquierda
					while(!flag && columnaAux>0) {
						columnaAux--;
						coordenadas.add(new Coordenadas(filaAux,columnaAux));
						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
							flag = true;
						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
							flag = true;
							coordenadas = new HashSet<>();
						}
					}
				break;
				case 1: //horizontal derecha
					while(!flag && columnaAux<7) {
						columnaAux++;
						coordenadas.add(new Coordenadas(filaAux,columnaAux));
						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
							flag = true;
						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
							flag = true;
							coordenadas = new HashSet<>();
						}
					}
				break;
				case 2: //vertical arriba
					while(!flag && filaAux<7) {
						filaAux++;
						coordenadas.add(new Coordenadas(filaAux,columnaAux));
						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
							flag = true;
						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
							flag = true;
							coordenadas = new HashSet<>();
						}
					}
				break;
				case 3: //vertical abajo
					while(!flag && filaAux>0) {
						filaAux--;
						coordenadas.add(new Coordenadas(filaAux,columnaAux));
						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Caballo"))) {
							flag = true;
						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
							flag = true;
							coordenadas = new HashSet<>();
						}
					}
				break;
				case 4: //diagonal arriba izquierda
	    			if(color){
	    				while(!flag && (columnaAux>0 && filaAux<7)) {
	    					columnaAux--;
	    					filaAux++;
	    					coordenadas.add(new Coordenadas(filaAux,columnaAux));
	    					if(!flag2 && rey.getColumna()-columnaAux == 1) {
	    						flag2 = true;
	    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Peon") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
	    							flag = true;
	    						}else if(hayPiezaIgualColor(filaAux,columnaAux,color)){
	    							flag = true;
	    							coordenadas = new HashSet<>();
	    						}
	    					}else{
	    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
	    							flag = true;
	    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
	    							flag = true;
	    							coordenadas = new HashSet<>();
	    						}
	    					}
	    				}
	    			}else{
	    				while(!flag && (columnaAux>0 && filaAux<7)) {
	    					columnaAux--;
	    					filaAux++;
	    					coordenadas.add(new Coordenadas(filaAux,columnaAux));
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    							flag = true;
    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag = true;
    							coordenadas = new HashSet<>();
    						}
	    				}
	    			}
				break;
				case 5: //diagonal arriba derecha
					if(color){
	    				while(!flag && (columnaAux<7 && filaAux<7)) {
	    					columnaAux++;
	    					filaAux++;
	    					coordenadas.add(new Coordenadas(filaAux,columnaAux));
	    					if(!flag2 && columnaAux-rey.getColumna() == 1) {
	    						flag2 = true;
	    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Peon") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
	    							flag = true;
	    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
	    							flag = true;
	    							coordenadas = new HashSet<>();
	    						}
	    					}else{
	    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
	    							flag = true;
	    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
	    							flag = true;
	    							coordenadas = new HashSet<>();
	    						}
	    					}
	    				}
	    			}else{
	    				while(!flag && (columnaAux<7 && filaAux<7)) {
	    					columnaAux++;
	    					filaAux++;
	    					coordenadas.add(new Coordenadas(filaAux,columnaAux));
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    							flag = true;
    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag = true;
    							coordenadas = new HashSet<>();
    						}
	    				}
	    			}
				break;
				case 6: //diagonal abajo izquierda
					if(color){
						while(!flag && (columnaAux>0 && filaAux>0)) {
	    					columnaAux--;
	    					filaAux--;
	    					coordenadas.add(new Coordenadas(filaAux,columnaAux));
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    							flag = true;
    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag = true;
    							coordenadas = new HashSet<>();
    						}
	    				}
	    			}else{
	    				while(!flag && (columnaAux>0 && filaAux>0)) {
	    					columnaAux--;
	    					filaAux--;
	    					coordenadas.add(new Coordenadas(filaAux,columnaAux));
	    					if(!flag2 && rey.getColumna()-columnaAux == 1) {
	    						flag2 = true;
	    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Peon") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
	    							flag = true;
	    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
	    							flag = true;
	    							coordenadas = new HashSet<>();
	    						}
	    					}else{
	    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
	    							flag = true;
	    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
	    							flag = true;
	    							coordenadas = new HashSet<>();
	    						}
	    					}
	    				}
	    			}
				break;
				case 7: //diagonal abajo derecha
					if(color){
						while(!flag && (columnaAux<7 && filaAux>0)) {
	    					columnaAux++;
	    					filaAux--;
	    					coordenadas.add(new Coordenadas(filaAux,columnaAux));
    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    							flag = true;
    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    							flag = true;
    							coordenadas = new HashSet<>();
    						}
	    				}
	    			}else{
	    				while(!flag && (columnaAux<7 && filaAux>0)) {
	    					columnaAux++;
	    					filaAux--;
	    					coordenadas.add(new Coordenadas(filaAux,columnaAux));
	    					if(!flag2 && columnaAux-rey.getColumna() == 1) {
	    						flag2 = true;
	    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Peon") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
	    							flag = true;
	    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
	    							flag = true;
	    							coordenadas = new HashSet<>();
	    						}
	    					}else{
	    						if(hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
	    							flag = true;
	    						}else if(this.hayPiezaIgualColor(filaAux,columnaAux,color)){
	    							flag = true;
	    							coordenadas = new HashSet<>();
	    						}
	    					}
	    				}
	    			}
				break;
    		}
    		if(!flag){ //si se fue de límite y no encontro una pieza, entonces esa dirección no es
    			coordenadas = new HashSet<>();
    		}
    		flag = false;
    		flag2 = false;
    		filaAux = rey.getFila();
        	columnaAux = rey.getColumna();
    		iter++;
    	}
    	
    	return coordenadas;
    }//retorna todas las coordenadas que se pueden bloquear
    
    // precondicion: la pieza en la coordenada c está clavada
    public Set<Coordenadas> movimientoPiezaClavada(Coordenadas c, boolean color){
    	Set<Coordenadas> bloquear = new HashSet<Coordenadas>();
    	Coordenadas rey = this.getRey(color);
    	int fila = rey.getFila()-c.getFila();
    	int columna = rey.getColumna()-c.getColumna();
    	int filaAux = 0;
    	int columnaAux = 0;
    	if(fila>0){ //arriba
    		filaAux = -1;
    	}else if(fila<0){//abajo
    		filaAux = 1;
    	}
    	if(columna>0){ //izquierda
    		columnaAux = -1;
    	}else if(columna<0){//derecha
    		columnaAux = 1;
    	}
    	fila = rey.getFila()+filaAux;
    	columna = rey.getColumna()+columnaAux;
    	Coordenadas direccion;
    	do{
    		direccion = new Coordenadas(fila,columna);
    		bloquear.add(direccion);
    		fila = fila+filaAux;
        	columna = columna+columnaAux;
    	}while(!hayPiezaDistintoColor(fila,columna,color));
    	direccion = new Coordenadas(fila,columna);
		bloquear.add(direccion);
    	return bloquear;
    }
    
    //Esta funcion se llama cada vez que se realiza un movimiento (antes de llamar ejecutaMovimiento)
    public void piezasClavadas(boolean color){
    	Coordenadas rey = this.getRey(color);
    	Pieza[][] tablero = this.getTablero();
    	int filaAux = rey.getFila();
    	int columnaAux = rey.getColumna();
    	int cont = 0; //sirve para contar la cantidad de piezas del mismo color en una misma direccion, si son 2 o m�s, entonces no est� clavado
    	int iter = 0;
    	boolean flag = false;
    	
    	Coordenadas posClavada;
    	Iterator<Coordenadas> it = this.getPiezas(color).iterator();
    	while(it.hasNext()){
    		posClavada = it.next();
    		tablero[posClavada.getFila()][posClavada.getColumna()].setClavado(false);
    	}
    	
    	posClavada = null; // cuando se clava un elemento en alguna direccion, solo se clava uno, no se pueden clavar multiples
    	while(iter < 8) { // las 8 posibles direcciones de una casilla (2 en horizontal, 2 en vertical y 2 en cada diagonal (son 2 diagonales))
    		switch (iter) {
    			case 0: //horizontal izquierda
    				while(!flag && columnaAux>0) {
    					columnaAux--;
    					if(cont==0 && this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    						posClavada = new Coordenadas(filaAux,columnaAux);
    						cont++;
    					}else if(cont==0 && this.hayPiezaDistintoColor(filaAux,columnaAux,color)){
    						flag = true;
    					}else if(cont==1 && this.hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    						flag = true;
    					}else if(cont==1 && this.hayPiezaIgualColor(filaAux,columnaAux,color)) {
    						posClavada = null;
    						flag = true;
    					}
    				}
    			break;
    			case 1: //horizontal derecha
    				while(!flag && columnaAux<7) {
    					columnaAux++;
    					if(cont==0 && this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    						posClavada = new Coordenadas(filaAux,columnaAux);
    						cont++;
    					}else if(cont==0 && this.hayPiezaDistintoColor(filaAux,columnaAux,color)){
    						flag = true;
    					}else if(cont==1 && this.hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    						flag = true;
    					}else if(cont==1 && this.hayPiezaIgualColor(filaAux,columnaAux,color)) {
    						posClavada = null;
    						flag = true;
    					}
    				}
    			break;
    			case 2: //vertical arriba
    				while(!flag && filaAux<7) {
    					filaAux++;
    					if(cont==0 && this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    						posClavada = new Coordenadas(filaAux,columnaAux);
    						cont++;
    					}else if(cont==0 && this.hayPiezaDistintoColor(filaAux,columnaAux,color)){
    						flag = true;
    					}else if(cont==1 && this.hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    						flag = true;
    					}else if(cont==1 && this.hayPiezaIgualColor(filaAux,columnaAux,color)) {
    						posClavada = null;
    						flag = true;
    					}
    				}
    			break;
    			case 3: //vertical abajo
    				while(!flag && filaAux>0) {
    					filaAux--;
    					if(cont==0 && this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    						posClavada = new Coordenadas(filaAux,columnaAux);
    						cont++;
    					}else if(cont==0 && this.hayPiezaDistintoColor(filaAux,columnaAux,color)){
    						flag = true;
    					}else if(cont==1 && this.hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Torre") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    						flag = true;
    					}else if(cont==1 && this.hayPiezaIgualColor(filaAux,columnaAux,color)) {
    						posClavada = null;
    						flag = true;
    					}
    				}
    			break;
    			case 4: //diagonal arriba izquierda
    				while(!flag && (columnaAux>0 && filaAux<7)) {
    					columnaAux--;
    					filaAux++;
    					if(cont==0 && this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    						posClavada = new Coordenadas(filaAux,columnaAux);
    						cont++;
    					}else if(cont==0 && this.hayPiezaDistintoColor(filaAux,columnaAux,color)){
    						flag = true;
    					}else if(cont==1 && this.hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    						flag = true;
    					}else if(cont==1 && this.hayPiezaIgualColor(filaAux,columnaAux,color)) {
    						posClavada = null;
    						flag = true;
    					}
    				}
    			break;
    			case 5: //diagonal arriba derecha
    				while(!flag && (columnaAux<7 && filaAux<7)) {
    					columnaAux++;
    					filaAux++;
    					if(cont==0 && this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    						posClavada = new Coordenadas(filaAux,columnaAux);
    						cont++;
    					}else if(cont==0 && this.hayPiezaDistintoColor(filaAux,columnaAux,color)){
    						flag = true;
    					}else if(cont==1 && this.hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    						flag = true;
    					}else if(cont==1 && this.hayPiezaIgualColor(filaAux,columnaAux,color)) {
    						posClavada = null;
    						flag = true;
    					}
    				}
    			break;
    			case 6: //diagonal abajo izquierda
    				while(!flag && (columnaAux>0 && filaAux>0)) {
    					columnaAux--;
    					filaAux--;
    					if(cont==0 && this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    						posClavada = new Coordenadas(filaAux,columnaAux);
    						cont++;
    					}else if(cont==0 && this.hayPiezaDistintoColor(filaAux,columnaAux,color)){
    						flag = true;
    					}else if(cont==1 && this.hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    						flag = true;
    					}else if(cont==1 && this.hayPiezaIgualColor(filaAux,columnaAux,color)) {
    						posClavada = null;
    						flag = true;
    					}
    				}
    			break;
    			case 7: //diagonal abajo derecha	
    				while(!flag && (columnaAux<7 && filaAux>0)) {
    					columnaAux++;
    					filaAux--;
    					if(cont==0 && this.hayPiezaIgualColor(filaAux,columnaAux,color)){
    						posClavada = new Coordenadas(filaAux,columnaAux);
    						cont++;
    					}else if(cont==0 && this.hayPiezaDistintoColor(filaAux,columnaAux,color)){
    						flag = true;
    					}else if(cont==1 && this.hayPiezaDistintoColor(filaAux,columnaAux,color) && (tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Alfil") || tablero[filaAux][columnaAux].getClass().getSimpleName().equals("Reina"))) {
    						flag = true;
    					}else if(cont==1 && this.hayPiezaIgualColor(filaAux,columnaAux,color)) {
    						posClavada = null;
    						flag = true;
    					}
    				}
    			break;
    		}
    		if(flag && posClavada!=null) {
    			tablero[posClavada.getFila()][posClavada.getColumna()].setClavado(true);
    		}
    		posClavada = null;
    		flag = false;
    		cont = 0;
    		filaAux = rey.getFila();
        	columnaAux = rey.getColumna();
    		iter++;
    	}
    }
    
    public Set<Coordenadas> movimientosPosiblesUnaPieza(int fila, int columna) { //Retorna un arreglo de coordenadas de movimientos posibles para una pieza
    	Pieza[][] tablero = this.getTablero();
    	Pieza p = tablero[fila][columna];
    	Coordenadas c = new Coordenadas(fila,columna);
    	boolean mov;
    	int filaAux;
    	int columnaAux;
    	int amenazas = this.amenazaEnCasilla(this.getRey(p.getColor()).getFila(), this.getRey(p.getColor()).getColumna(), p.getColor(), true); //solo revisa las amenazas del rey color
    	// Crear una lista de tama�o din�mico de posibles movimientos
        Set<Coordenadas> posMov = new HashSet<>();
        Set<Coordenadas> bloquear = new HashSet<>();
        
        /*
        HECHO: Tengo que recordar que en caso de estar en Jaque, solo se puede mover el Rey o piezas que bloqueen la trayectoria (no se bloquea el caballo pero si se lo come).
        HECHO: Por tanto, si se est� en jaque, no se puede mover la pieza a menos que "bloquee la trayectoria de jaque"
        HECHO: Para atajarse de los jaques, junto todos los movimientos, y luego saco todos aquellos que no pertenezcan al Set<Coordenadas> bloquear
        
        HECHO: Adem�s, el rey no se puede mover a casillas que esten en alcance de piezas enemigas
        HECHO: Tampoco se pueden mover piezas que est�n "clavadas"
        */
        
    	if(p != null) {
	    	switch (p.getClass().getSimpleName()) {
		        case "Peon": 
		            // Realizar movimiento espec�fico para pe�n
		        	if(amenazas<2){ //
			        	if(p.getColor()) {
			        		if(fila<7) { // no existe un peon blanco en fila 7 (8 te�rica, ya que va de 0 a 7 real) pero por si acaso
			        			if(!hayPieza(fila+1,columna)) {
			        				posMov.add(new Coordenadas(fila+1,columna));
			        				if(fila == 1 && !hayPieza(fila+2,columna)) { //fila+2 = 3
				        				posMov.add(new Coordenadas(fila+2,columna));
				        			}
			        			}
			        			if(columna<7 && hayPiezaDistintoColor(fila+1,columna+1,p.getColor())) {
			        				posMov.add(new Coordenadas(fila+1,columna+1));
			        			}
			        			if(columna>0 && hayPiezaDistintoColor(fila+1,columna-1,p.getColor())) {
			        				posMov.add(new Coordenadas(fila+1,columna-1));
			        			}
			        			// "an passant" de blancas
			        			if(capturaAlPaso(c,true)) {
			        				posMov.add(new Coordenadas(fila+1,columna-1));
			        			}
			        			if(capturaAlPaso(c,false)) {
			        				posMov.add(new Coordenadas(fila+1,columna+1));
			        			}
			        		}
			        	}else{
			        		if(fila>0) { // lo mismo que en el primer if
			        			if(!hayPieza(fila-1,columna)) {
			        				posMov.add(new Coordenadas(fila-1,columna));
			        				if(fila == 6 && !hayPieza(4,columna)) {
				        				posMov.add(new Coordenadas(fila-2,columna)); //fila-2 = 4
				        			}
			        			}
			        			if(columna<7 && hayPiezaDistintoColor(fila-1,columna+1,p.getColor())) {
			        				posMov.add(new Coordenadas(fila-1,columna+1));
			        			}
			        			if(columna>0 && hayPiezaDistintoColor(fila-1,columna-1,p.getColor())) {
			        				posMov.add(new Coordenadas(fila-1,columna-1));
			        			}
			        			// "an passant" de negras
			        			if(capturaAlPaso(c,true)) {
			        				posMov.add(new Coordenadas(fila-1,columna+1));
			        			}
			        			if(capturaAlPaso(c,false)) {
			        				posMov.add(new Coordenadas(fila-1,columna-1));
			        			}
			        		}
			        	}
		        	}
		            break;
		            
		        case "Caballo":
		            // Realizar movimiento espec�fico para caballo
		        	if(amenazas<2) {
			        	if(fila>0) {
			        		if(columna>1) {
			        			if(!hayPiezaIgualColor(fila-1,columna-2,p.getColor())) {
			        				posMov.add(new Coordenadas(fila-1,columna-2));
			        			}
			        		}
			        		if(columna<6) {
			        			if(!hayPiezaIgualColor(fila-1,columna+2,p.getColor())) {
			        				posMov.add(new Coordenadas(fila-1,columna+2));
			        			}
			        		}
			        	}
			        	if(fila>1) {
			        		if(columna>0) {
			        			if(!hayPiezaIgualColor(fila-2,columna-1,p.getColor())) {
			        				posMov.add(new Coordenadas(fila-2,columna-1));
			        			}
			        		}
			        		if(columna<7) {
			        			if(!hayPiezaIgualColor(fila-2,columna+1,p.getColor())) {
			        				posMov.add(new Coordenadas(fila-2,columna+1));
			        			}
			        		}
			        	}
			        	if(fila<6) {
			        		if(columna>0) {
			        			if(!hayPiezaIgualColor(fila+2,columna-1,p.getColor())) {
			        				posMov.add(new Coordenadas(fila+2,columna-1));
			        			}
			        		}
			        		if(columna<7) {
			        			if(!hayPiezaIgualColor(fila+2,columna+1,p.getColor())) {
			        				posMov.add(new Coordenadas(fila+2,columna+1));
			        			}
			        		}
			        	}
			        	if(fila<7) {
			        		if(columna>1) {
			        			if(!hayPiezaIgualColor(fila+1,columna-2,p.getColor())) {
			        				posMov.add(new Coordenadas(fila+1,columna-2));
			        			}
			        		}
			        		if(columna<6) {
			        			if(!hayPiezaIgualColor(fila+1,columna+2,p.getColor())) {
			        				posMov.add(new Coordenadas(fila+1,columna+2));
			        			}
			        		}
			        	}
		        	}
		            break;
		            
		        case "Alfil":
		            // Realizar movimiento espec�fico para alfil
		        	if(amenazas<2) {
			        	for(int i=0; i<4; i++) {
			        		mov = true;
			        		filaAux = fila;
			        		columnaAux = columna;
			        		switch (i) {
				                case 0: // Arriba izquierda
				                    while(mov && filaAux<7 && columnaAux>0) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux+1,columnaAux-1)) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux-1));
				                    	}else if(hayPiezaDistintoColor(filaAux+1,columnaAux-1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux-1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux++;
				                    	columnaAux--;
				                    }
				                    break;
				                case 1: // Arriba derecha
				                	while(mov && filaAux<7 && columnaAux<7) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux+1,columnaAux+1)) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux+1));
				                    	}else if(hayPiezaDistintoColor(filaAux+1,columnaAux+1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux+1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux++;
				                    	columnaAux++;
				                    }
				                    break;
				                case 2: // Abajo izquierda 
				                	while(mov && filaAux>0 && columnaAux>0) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux-1,columnaAux-1)) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux-1));
				                    	}else if(hayPiezaDistintoColor(filaAux-1,columnaAux-1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux-1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux--;
				                    	columnaAux--;
				                    }
				                    break;
				                case 3: // Abajo derecha
				                	while(mov && filaAux>0 && columnaAux<7) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux-1,columnaAux+1)) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux+1));
				                    	}else if(hayPiezaDistintoColor(filaAux-1,columnaAux+1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux+1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux--;
				                    	columnaAux++;
				                    }
				                	break;
			        		}
			        	}
		        	}
		            break;
		            
		        case "Torre":
		            // Realizar movimiento espec�fico para torre
		        	if(amenazas<2) {
			        	for(int i=0; i<4; i++) {
			        		mov = true;
			        		filaAux = fila;
			        		columnaAux = columna;
			        		switch (i) {
				                case 0: // Arriba
				                    while(mov && filaAux<7) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux+1,columnaAux)) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux));
				                    	}else if(hayPiezaDistintoColor(filaAux+1,columnaAux,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux++;
				                    }
				                    break;
				                case 1: // Abajo
				                	while(mov && filaAux>0) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux-1,columnaAux)) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux));
				                    	}else if(hayPiezaDistintoColor(filaAux-1,columnaAux,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux--;
				                    }
				                    break;
				                case 2: // Izquierda 
				                	while(mov && columnaAux>0) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux,columnaAux-1)) {
				                    		posMov.add(new Coordenadas(filaAux,columnaAux-1));
				                    	}else if(hayPiezaDistintoColor(filaAux,columnaAux-1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux,columnaAux-1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	columnaAux--;
				                    }
				                    break;
				                case 3: // Derecha
				                	while(mov && columnaAux<7) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux,columnaAux+1)) {
				                    		posMov.add(new Coordenadas(filaAux,columnaAux+1));
				                    	}else if(hayPiezaDistintoColor(filaAux,columnaAux+1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux,columnaAux+1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	columnaAux++;
				                    }
				                	break;
			        		}
			        	}
		        	}
		            break;
		            
		        case "Reina":
		            // Realizar movimiento espec�fico para reina (copia alfil + torre)
		        	
		        	if(amenazas<2) {
			        	// Realizar movimiento espec�fico para alfil
			        	for(int i=0; i<4; i++) {
			        		mov = true;
			        		filaAux = fila;
			        		columnaAux = columna;
			        		switch (i) {
				                case 0: // Arriba izquierda
				                    while(mov && filaAux<7 && columnaAux>0) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux+1,columnaAux-1)) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux-1));
				                    	}else if(hayPiezaDistintoColor(filaAux+1,columnaAux-1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux-1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux++;
				                    	columnaAux--;
				                    }
				                    break;
				                case 1: // Arriba derecha
				                	while(mov && filaAux<7 && columnaAux<7) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux+1,columnaAux+1)) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux+1));
				                    	}else if(hayPiezaDistintoColor(filaAux+1,columnaAux+1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux+1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux++;
				                    	columnaAux++;
				                    }
				                    break;
				                case 2: // Abajo izquierda 
				                	while(mov && filaAux>0 && columnaAux>0) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux-1,columnaAux-1)) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux-1));
				                    	}else if(hayPiezaDistintoColor(filaAux-1,columnaAux-1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux-1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux--;
				                    	columnaAux--;
				                    }
				                    break;
				                case 3: // Abajo derecha
				                	while(mov && filaAux>0 && columnaAux<7) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux-1,columnaAux+1)) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux+1));
				                    	}else if(hayPiezaDistintoColor(filaAux-1,columnaAux+1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux+1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux--;
				                    	columnaAux++;
				                    }
				                	break;
			        		}
			        	}
			        	
			        	// Realizar movimiento espec�fico para torre
			        	for(int i=0; i<4; i++) {
			        		mov = true;
			        		filaAux = fila;
			        		columnaAux = columna;
			        		switch (i) {
				                case 0: // Arriba
				                    while(mov && filaAux<7) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux+1,columnaAux)) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux));
				                    	}else if(hayPiezaDistintoColor(filaAux+1,columnaAux,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux+1,columnaAux));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux++;
				                    }
				                    break;
				                case 1: // Abajo
				                	while(mov && filaAux>0) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux-1,columnaAux)) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux));
				                    	}else if(hayPiezaDistintoColor(filaAux-1,columnaAux,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux-1,columnaAux));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	filaAux--;
				                    }
				                    break;
				                case 2: // Izquierda 
				                	while(mov && columnaAux>0) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux,columnaAux-1)) {
				                    		posMov.add(new Coordenadas(filaAux,columnaAux-1));
				                    	}else if(hayPiezaDistintoColor(filaAux,columnaAux-1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux,columnaAux-1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	columnaAux--;
				                    }
				                    break;
				                case 3: // Derecha
				                	while(mov && columnaAux<7) { // Mientras est� dentro del tablero y se pueda seguir moviendo
				                    	if(!hayPieza(filaAux,columnaAux+1)) {
				                    		posMov.add(new Coordenadas(filaAux,columnaAux+1));
				                    	}else if(hayPiezaDistintoColor(filaAux,columnaAux+1,p.getColor())) {
				                    		posMov.add(new Coordenadas(filaAux,columnaAux+1));
				                    		mov = false;
				                    	}else{
				                    		mov = false;
				                    	}
				                    	columnaAux++;
				                    }
				                	break;
			        		}
			        	}
		        	}
		            break;
		            
		        case "Rey":
		            // Realizar movimiento espec�fico para rey
		        	if(fila>0) {
		        		if(columna>0) {
		        			if(!hayPiezaIgualColor(fila-1,columna-1,p.getColor()) && this.amenazaEnCasilla(fila-1,columna-1,p.getColor(),false)==0) {
		        				posMov.add(new Coordenadas(fila-1,columna-1));
		        			}
		        		}
		        		if(!hayPiezaIgualColor(fila-1,columna,p.getColor()) && this.amenazaEnCasilla(fila-1,columna,p.getColor(),false)==0) {
		        			posMov.add(new Coordenadas(fila-1,columna));
		        		}
		        		if(columna<7) {
		        			if(!hayPiezaIgualColor(fila-1,columna+1,p.getColor()) && this.amenazaEnCasilla(fila-1,columna+1,p.getColor(),false)==0) {
		        				posMov.add(new Coordenadas(fila-1,columna+1));
		        			}
		        		}
		        	}
		        	if(fila<7) {
		        		if(columna>0) {
		        			if(!hayPiezaIgualColor(fila+1,columna-1,p.getColor()) && this.amenazaEnCasilla(fila+1,columna-1,p.getColor(),false)==0) {
		        				posMov.add(new Coordenadas(fila+1,columna-1));
		        			}
		        		}
		        		if(!hayPiezaIgualColor(fila+1,columna,p.getColor()) && this.amenazaEnCasilla(fila+1,columna,p.getColor(),false)==0) {
		        			posMov.add(new Coordenadas(fila+1,columna));
		        		}
		        		if(columna<7) {
		        			if(!hayPiezaIgualColor(fila+1,columna+1,p.getColor()) && this.amenazaEnCasilla(fila+1,columna+1,p.getColor(),false)==0) {
		        				posMov.add(new Coordenadas(fila+1,columna+1));
		        			}
		        		}
		        	}
		        	if(columna>0) {
		        		if(!hayPiezaIgualColor(fila,columna-1,p.getColor()) && this.amenazaEnCasilla(fila,columna-1,p.getColor(),false)==0) {
		        			posMov.add(new Coordenadas(fila,columna-1));
		        		}
		        	}
		        	if(columna<7) {
		        		if(!hayPiezaIgualColor(fila,columna+1,p.getColor()) && this.amenazaEnCasilla(fila,columna+1,p.getColor(),false)==0) {
		        			posMov.add(new Coordenadas(fila,columna+1));
		        		}
		        	}
		        	if(this.enroque(p.getColor(),true)) { // enroque izquierdo
		        		if(p.getColor()) { // es blanco
		        			posMov.add(new Coordenadas(0,2));
		        		}else {
		        			posMov.add(new Coordenadas(7,2));
		        		}
		        	}
		        	if(this.enroque(p.getColor(),false)) { // enroque derecho
		        		if(p.getColor()) { // es blanco
		        			posMov.add(new Coordenadas(0,6));
		        		}else {
		        			posMov.add(new Coordenadas(7,6));
		        		}
		        	}
		            break;
	        }
    	}
    	
    	if(amenazas==0 && p.getClavado() && !p.getClass().getSimpleName().equals("Rey")){
    		bloquear = this.movimientoPiezaClavada(c,p.getColor());
    		posMov.retainAll(bloquear);
        }else if((amenazas==2 || (amenazas==1 && p.getClavado())) && !p.getClass().getSimpleName().equals("Rey")){
    		posMov = new HashSet<>();
    	}else if(amenazas==1 && !p.getClavado() && !p.getClass().getSimpleName().equals("Rey")){ //si se esta en jaque por 1 pieza, entonces se puede bloquear
    		bloquear = this.bloquearJaque(p.getColor());
    		posMov.retainAll(bloquear);
    	}
    	return posMov;
    }
    
    //Precondicion: fila y columna representa la posici�n del rey
    public boolean estaEnJaque(int fila, int columna) {
    	Pieza[][] tablero = this.getTablero();
    	boolean color = tablero[fila][columna].getColor();
    	return this.amenazaEnCasilla(fila,columna,color,false)>0;
    }
    
    //Precondicion: fila y columna representa la posici�n del rey
    public boolean estaEnMultipleJaque(int fila, int columna) { // devuelve true si est� en jaque por m�s de una pieza
    	Pieza[][] tablero = this.getTablero();
    	boolean color = tablero[fila][columna].getColor();
    	return this.amenazaEnCasilla(fila,columna,color,true)>1;
    }
    
    public int jaqueMate(boolean color){
    	int resultado = 2; //0 == No JaqueMate; 1 == JaqueMate; 2 == Empate
    	Coordenadas rey = this.getRey(color);
    	Set<Coordenadas> piezas = this.getPiezas(color);
    	Iterator<Coordenadas> it = piezas.iterator();
    	Coordenadas pieza;
		while(resultado==2 && it.hasNext()){
			pieza = it.next();
			if(this.movimientosPosiblesUnaPieza(pieza.getFila(),pieza.getColumna()).size() != 0){
				resultado = 0;
			}
		}
		if(!it.hasNext() && resultado!=0 && this.estaEnJaque(rey.getFila(),rey.getColumna())){
			resultado = 1;
		}
		return resultado;
    }
        
    // retorna {1,2,3} dependiendo del enroque (izq, der) o an passant
    public int movimientoEspecial(Coordenadas ini, Coordenadas fin){
    	Pieza[][] tablero = this.getTablero();
    	Pieza p = tablero[ini.getFila()][ini.getColumna()];
		if(p.getClass().getSimpleName().equals("Rey")) {
			Coordenadas torre = new Coordenadas(fin.getFila(),fin.getColumna());
			if(ini.getColumna()-fin.getColumna()<(-1)){ //si el rey se mueve dos pasos hacia la derecha
				torre.setColumna(7);
			}else if(ini.getColumna()-fin.getColumna()>1){ //si el rey se mueve dos pasos hacia la izquierda
				torre.setColumna(0);
			}else{
				return 4; //es un movimiento normal de rey
			}
    		if(torre.getColumna() == 0 && tablero[torre.getFila()][torre.getColumna()].getPrimerMovimiento() && tablero[ini.getFila()][ini.getColumna()].getPrimerMovimiento()){
    			return 1;
    		}else if(torre.getColumna() == 7 && tablero[torre.getFila()][torre.getColumna()].getPrimerMovimiento() && tablero[ini.getFila()][ini.getColumna()].getPrimerMovimiento()){
    			return 2;
    		}// sino, no es un enroque
		}
		
		if(p.getClass().getSimpleName().equals("Peon")){
			Coordenadas anPassant_ini = new Coordenadas(fin.getFila(),fin.getColumna());
	    	Coordenadas anPassant_fin = new Coordenadas(fin.getFila(),fin.getColumna());
    		if(p.getColor()){
        		anPassant_ini.setFila(6);
            	anPassant_fin.setFila(fin.getFila()-1);
    		}else{
        		anPassant_ini.setFila(1);
            	anPassant_fin.setFila(fin.getFila()+1);
        	}
    		Pieza p2 = tablero[anPassant_fin.getFila()][anPassant_fin.getColumna()];
    		Movimiento m1 = new Movimiento(anPassant_ini,anPassant_fin,p2);
    		if(p.getColor() && anPassant_ini.getFila() == (fin.getFila()+1) && this.movimientos.get(movimientos.size()-1).equals(m1)) { // si el ultimo movimiento permite un anPassant
    			return 3;
    		}else if(!p.getColor() && anPassant_ini.getFila() == (fin.getFila()-1) && this.movimientos.get(movimientos.size()-1).equals(m1)){
    			return 3;
    		}
		}
    	
    	return 4; // en otro caso 
    }
    
    public boolean existeJugada(){
    	boolean resultado = false;
    	Iterator<Coordenadas> it = this.getPiezas(this.getTurno()).iterator();
    	Coordenadas coord;
    	while(!resultado && it.hasNext()){
    		coord = it.next();
    		if(this.movimientosPosiblesUnaPieza(coord.getFila(), coord.getColumna()).size() > 0){
    			resultado = true;
    		}
    	}
    	return resultado;
    }
    
    //ver si necesito conversi�n de logica <--> gridpane y gridpane <--> grafica

    // M�todo para imprimir el tablero realmente no estar� ac�, sino en la clase de JavaFX
    // Cuando se ejecute un movimiento en el JavaFX, se tendr� que actualizar el tablero, y la lista de movimientos
    
}