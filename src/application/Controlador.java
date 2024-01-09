package application;

import java.util.Iterator;
import java.util.Set;

//import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
//import javafx.util.Duration;

public class Controlador {

    @FXML
    private Text turno;
    
    @FXML
    private Text ganador;
    
    @FXML
    private GridPane tablero;
    
    @FXML
    private VBox fila;
    
    @FXML
    private HBox columna;
    
    @FXML
    private VBox movimientos;
    
    @FXML
    private Button Caballo;
    
    @FXML
    private Button Alfil;
    
    @FXML
    private Button Torre;
    
    @FXML
    private Button Reina;
    
    private Tablero tableroLogico;
    
    private Pieza coronado;
    
    private Movimiento coordCoronado;
	
    public Tablero getTablero(){
    	return this.tableroLogico;
    }
    
    public Pieza getCoronado(){
    	return this.coronado;
    }
    
    public Movimiento getCoordCoronado(){
    	return this.coordCoronado;
    }
    
	public void setTablero(Tablero t){
		this.tableroLogico = t;
	}
	
	public void setCoronado(Pieza p){
		this.coronado = p;
	}
	
	public void setCoordCoronado(Movimiento m){
		this.coordCoronado = m;
	}
	
	private Coordenadas cancelarBoton;

	public Coordenadas getBotonCancelado(){
		return this.cancelarBoton;
	}

	public void setBotonCancelado(Coordenadas b){
		this.cancelarBoton = b;
	}

    public void initialize() throws InterruptedException {
    	// inicializa el tablero logico
    	Tablero t = new Tablero(true);
    	this.setTablero(t);
    	Pieza[][] piezas = tableroLogico.getTablero();
		if(tableroLogico.getTurno()){
			turno.setText("  Blanco");
		}else{
			turno.setText(" Negro");
		}
		// fil = movimiento en la fila (arriba,abajo) ; col/c = movimiento en la columna (izq,der)
		// (lógica a gráfica) piezas[fila][columna] = tablero[col][fila] donde col= 7-columna;
		// (gráfica a lógica) tablero[fila][columna] = piezas[columna][col] donde col= 7-fil;
		String texto;
        for (int f = 0; f < 8; f++) {
        	texto = String.valueOf(8-f);
        	Text text = crearTextoVBox(texto,f);
        	fila.getChildren().add(text);
        	switch(f){
        		case 0:
        			texto = "A";
        		break;
        		case 1:
        			texto = "B";
	        	break;
        		case 2:
        			texto = "C";
        		break;
        		case 3:
        			texto = "D";
	        	break;
        		case 4:
        			texto = "E";
        		break;
        		case 5:
        			texto = "F";
	        	break;
        		case 6:
        			texto = "G";
        		break;
        		default:
        			texto = "H";
	        	break;
        	}
        	text = crearTextoHBox(texto,f);
        	columna.getChildren().add(text);
            for (int c = 0; c < 8; c++) {
                StackPane casilla = createCasilla(f, c, piezas);
                tablero.add(casilla, c, 7-f);
            }
        } //Termina de crear la parte grafica
        
     // Configurar el VBox para expandirse verticalmente
        t.piezasClavadas(t.getTurno());
        this.imprimirTablero();
        //(new PauseTransition(Duration.millis(150))).play(); //se puede testear cuantos milis menos se puede dar
        //el pause es para evitar crasheo al pasar de una jugada a otra exception de null al no darle tiempo a que se cargen las imagenes
	}
    
    private StackPane createCasilla(int f, int c, Pieza[][] piezas) {
		
		Image fondoBlanco = new Image("casilla_blanca.png");
		Image fondoNegro = new Image("casilla_negra.png");
		ImageView blanco = new ImageView(fondoBlanco);
		ImageView negro = new ImageView(fondoNegro);
		
		blanco.setFitHeight(60);
		blanco.setFitWidth(60);
		negro.setFitHeight(60);
		negro.setFitWidth(60);
		
		// Crear un StackPane que contiene una imagen de fondo, un botón, y puede contener otra imagen por arriba
        StackPane casilla = new StackPane();
        // Eliminar cualquier espacio adicional en el StackPane
        casilla.setPrefHeight(60);
        casilla.setPrefWidth(60);
        
        // Crea un boton
		Button button = new Button();
		button.setAlignment(Pos.CENTER);
		button.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
		button.setMinWidth(60);
		button.setMinHeight(60);
		button.setPrefWidth(60);
		button.setPrefHeight(60);
		button.setMaxWidth(60);
		button.setMaxHeight(60);
		button.setMnemonicParsing(false);
		button.setStyle("-fx-background-color: transparent;");
		button.setId(f+""+c);
        
        // El Fondo del label con una imagen
        if((f+c)%2 == 0){ //si es par asigna casilla negra, sino es casilla blanca
        	casilla.getChildren().add(negro);
        }else{
        	casilla.getChildren().add(blanco);
        }

		// Agregar los elementos al StackPane
        casilla.getChildren().add(button);
		
		return casilla;
    }
    
    private Text crearTextoVBox(String s,int indice) {
    	Text texto = new Text(s);
    	// Establecer propiedades de estilo
        texto.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        texto.setStrokeWidth(0.0);
        texto.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
    	if(indice==0){
            // Establecer el margen izquierdo
            VBox.setMargin(texto, new Insets(25.0, 0, 0, 0));
    	}else if(indice<6){
    		// Establecer el margen izquierdo
            VBox.setMargin(texto, new Insets(45.0, 0, 0, 0));
    	}else{
    		// Establecer el margen izquierdo
            VBox.setMargin(texto, new Insets(47.0, 0, 0, 0));
    	}
    	// Establecer fuente
        texto.setFont(Font.font("Arial", 12)); // Puedes ajustar el tamaño y la fuente según tus necesidades
    	return texto;
    }
    
    private Text crearTextoHBox(String s,int indice) {
    	Text texto = new Text(s);
    	// Establecer propiedades de estilo
        texto.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        texto.setStrokeWidth(0.0);
        texto.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
    	if(indice==0){
            // Establecer el margen izquierdo
            HBox.setMargin(texto, new Insets(0, 0, 0, 28.0));
    	}else{
    		// Establecer el margen izquierdo
            HBox.setMargin(texto, new Insets(0, 0, 0, 51.0));
    	}
    	// Establecer fuente
        texto.setFont(Font.font("Arial", 12)); // Puedes ajustar el tamaño y la fuente según tus necesidades
    	return texto;
    }
    
    private void imprimirTablero() {
    	Tablero t = this.getTablero();
    	Pieza[][] piezas = t.getTablero();
    	StackPane sp;
    	Button boton;
    	Node node;
    	
    	ganador.setText("Turno de");
    	if(tableroLogico.getTurno()){
			turno.setText("Blanco");
		}else{
			turno.setText("Negro");
		}
    	
    	for(int f = 0; f < 8; f++) {
	    	for (int c = 0; c < 8; c++) {
	    		node = tablero.getChildren().get(8*f+c+1);
	    		if (node instanceof StackPane) {
		    		sp = (StackPane) node;
		    		boton = (Button) sp.getChildren().get(1);
		    		boton.setGraphic(null);
		    		if(sp.getChildren().size()==3){
		    			sp.getChildren().remove(2);
		    		}
		    		if(piezas[f][c] != null) {
		    			boton.setDisable(false);
	    	        	Image bottonImagen = new Image(piezas[f][c].getImagen());
	    				ImageView imageView = new ImageView(bottonImagen);
	    				imageView.setFitHeight(60);
	    				imageView.setFitWidth(60);
	    				boton.setGraphic(imageView);
	    	        }else{
	    	        	boton.setGraphic(null);
	    	        }
		    		boton.setOnAction(this::elegirPieza);
	    		}else{
	    			//System.out.print("error:"+f+" "+c);
	    		}
	        }
    	}
    	
    	Caballo.setDisable(true);
    	Alfil.setDisable(true);
    	Torre.setDisable(true);
    	Reina.setDisable(true);
    	Caballo.setGraphic(null);
    	Alfil.setGraphic(null);
    	Torre.setGraphic(null);
    	Reina.setGraphic(null);
    	Caballo.setVisible(false);
    	Alfil.setVisible(false);
    	Torre.setVisible(false);
    	Reina.setVisible(false);
    	Caballo.setOnAction(this::obtenerPiezaCoronacion);
    	Alfil.setOnAction(this::obtenerPiezaCoronacion);
    	Torre.setOnAction(this::obtenerPiezaCoronacion);
    	Reina.setOnAction(this::obtenerPiezaCoronacion);
    	Caballo.setStyle("-fx-background-color: transparent;");
    	Alfil.setStyle("-fx-background-color: transparent;");
    	Torre.setStyle("-fx-background-color: transparent;");
    	Reina.setStyle("-fx-background-color: transparent;");
    	this.setCoordCoronado(new Movimiento(new Coordenadas(8,8),new Coordenadas(8,8),null));
    	this.setBotonCancelado(new Coordenadas(8,8));
    	this.setCoronado(null);
    	
    	//para kevin
    	//comenzarPartida();
    	
    	//FALTA: Creo que acá va a ser lo de saber si se gana o no
    	if(!t.existeJugada()) {
			if(t.jaqueMate(t.getTurno()) != 2){
				ganador.setText("Ganan ");
				if(!t.getTurno()){
					turno.setText("Blancas");
					//System.out.print("Gana Blancas"); // FALTA: JavaFX
				}else{
					turno.setText("Negras");
					//System.out.print("Gana Negra"); // FALTA: JavaFX
				}
			}else{
				turno.setText("");
				ganador.setText("Empate");
				//System.out.print("Empate"); // FALTA: JavaFX
			}
    	}
    }
  /*  
    comenzarPartida(){
    	while (!terminaPartida) {
    		origen, dstino = obtenerMovimientoLocal()
    		efectuarMovimiento(origen, destino)
    		
    		obtenerMovimientoDeRemoto()
    		efectuarMovimiento(origen, desitno)
    	}
    }*/
    
    // Precondicion: Se asume que hay una pieza en las coordenadas ini. El movimiento es v�lido, tanto en el sentido de que es un lugar donde la pieza se 
    // puede mover porque as� es su movimiento, como el hecho de que requiera condiciones especiales para moverse
    // (enroque, an passant, bloqueo de jaque o movimiento que no ponga en jaque al rey) 
    public void ejecutarMovimiento(Coordenadas ini, Coordenadas fin, int movEspecial) { // FALTA: JavaFX
    	Tablero t = this.getTablero();
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
			this.setCoordCoronado(new Movimiento(ini,fin,null));
			Caballo.setDisable(false);
			Alfil.setDisable(false);
			Torre.setDisable(false);
			Reina.setDisable(false);
		}else if (!color && tablero[ini.getFila()][ini.getColumna()].getClass().getSimpleName().equals("Peon") && (fin.getFila())==0){
			this.setCoordCoronado(new Movimiento(ini,fin,null));
			Caballo.setDisable(false);
			Alfil.setDisable(false);
			Torre.setDisable(false);
			Reina.setDisable(false);
		}else {
			t.actualizarPieza(ini,fin);
			Movimiento m = new Movimiento(ini,fin,tablero[fin.getFila()][fin.getColumna()]);
	    	t.getMovimientos().add(m);
	    	String turn;
	    	if(t.getTurno()){
	    		turn = "Blanco";
	    		if(m.getPieza().getClass().getSimpleName().equals("Torre") || m.getPieza().getClass().getSimpleName().equals("Reina")){
	    			turn = "Blanca";
	    		}
	    	}else{
	    		turn = "Negro";
	    		if(m.getPieza().getClass().getSimpleName().equals("Torre") || m.getPieza().getClass().getSimpleName().equals("Reina")){
	    			turn = "Negra";
	    		}
	    	}
	    	String posIni = "";
	    	String posFin = "";
	    	switch(ini.getColumna()){
				case 0:
					posIni = "a";
				break;
				case 1:
					posIni = "b";
		    	break;
				case 2:
					posIni = "c";
				break;
				case 3:
					posIni = "d";
		    	break;
				case 4:
					posIni = "e";
				break;
				case 5:
					posIni = "f";
		    	break;
				case 6:
					posIni = "g";
				break;
				default:
					posIni = "h";
		    	break;
			}
	    	switch(fin.getColumna()){
				case 0:
					posFin = "a";
				break;
				case 1:
					posFin = "b";
		    	break;
				case 2:
					posFin = "c";
				break;
				case 3:
					posFin = "d";
		    	break;
				case 4:
					posFin = "e";
				break;
				case 5:
					posFin = "f";
		    	break;
				case 6:
					posFin = "g";
				break;
				default:
					posFin = "h";
		    	break;
			}
	    	posIni = posIni+(ini.getFila()+1);
	    	posFin = posFin+(fin.getFila()+1);
	    	Text mov = new Text(t.getTablero()[fin.getFila()][fin.getColumna()].getClass().getSimpleName()+" "+turn+" de "+posIni+" a "+posFin);
	    	mov.setFont(Font.font("Arial", 12)); // Puedes ajustar el tamaño y la fuente según tus necesidades
	    	mov.setFill(Color.web("#6b3c1c"));
	    	movimientos.getChildren().add(mov);
		}
    	
    }
    
    private void elegirPieza(ActionEvent event) {
        //System.out.println("Botón clicado");
    	Tablero t = this.getTablero();
    	Object evento = event.getSource();
    	
    	if (evento instanceof Button && this.getBotonCancelado().getFila()>7 && this.getCoordCoronado().getIni().getFila()>7) { // si no se ha presionado un boton antes y no se está coronado
    		
            // Convertir el origen a un botón
            Button boton = (Button) evento;

            // Obtener el ID del botón
            String idBoton = boton.getId();
            char idFila = idBoton.charAt(0);
            char idColumna = idBoton.charAt(1);
            int digitoFila = idFila - '0';
            int digitoColumna = idColumna - '0';
            this.setBotonCancelado(new Coordenadas(8,8));
            if(t.getTablero()[digitoFila][digitoColumna]!=null && t.getTablero()[digitoFila][digitoColumna].getColor()==t.getTurno()){
	            Set<Coordenadas> mov = t.movimientosPosiblesUnaPieza(digitoFila, digitoColumna);
	            Coordenadas coordenadas;
	            Node node;
	            StackPane sp;
	            
	            if(!mov.isEmpty()){
	            	this.setBotonCancelado(new Coordenadas(digitoFila,digitoColumna));
		            Iterator<Coordenadas> it = mov.iterator();
		            node = tablero.getChildren().get(8*digitoFila+digitoColumna+1);
		    		if (node instanceof StackPane) {
			    		sp = (StackPane) node;
			            Image movValido = new Image("pieza_a_mover.png");
						ImageView imageView = new ImageView(movValido);
						imageView.setFitHeight(60);
						imageView.setFitWidth(60);
			            sp.getChildren().add(imageView);
		    		}
		            while(it.hasNext()){
		            	coordenadas = it.next();
		            	node = tablero.getChildren().get(8*coordenadas.getFila()+coordenadas.getColumna()+1);
			    		if (node instanceof StackPane) {
				    		sp = (StackPane) node;
				    		boton = (Button) sp.getChildren().get(1);
				            boton.setOnAction(this::moverPieza);
				            Image movValido = new Image("movimiento_valido.png");
							ImageView imageView = new ImageView(movValido);
							imageView.setFitHeight(60);
							imageView.setFitWidth(60);
				            sp.getChildren().add(imageView);
			    		}
		            }
	    		}
    		}
        }else if(evento instanceof Button && (this.getBotonCancelado().getFila()<= 7 || this.getCoordCoronado().getIni().getFila()<=7)){ //si se ha presionado dos veces o se esta coronando y se presiona elegirPieza()
        	Set<Coordenadas> mov;
        	if(this.getBotonCancelado().getFila()<= 7){
        		mov = t.movimientosPosiblesUnaPieza(this.getBotonCancelado().getFila(), this.getBotonCancelado().getColumna());
        	}else{
        		mov = t.movimientosPosiblesUnaPieza(this.getCoordCoronado().getIni().getFila(), this.getCoordCoronado().getIni().getColumna());
        	}
            //precondicion: mov no retorna vacio, entonces it existe
            Iterator<Coordenadas> it = mov.iterator();
            Coordenadas coordenadas;
            Node node;
            StackPane sp;
            Button boton;
            if(this.getBotonCancelado().getFila()<= 7){
            	node = tablero.getChildren().get(8*this.getBotonCancelado().getFila()+this.getBotonCancelado().getColumna()+1);
        	}else{
        		node = tablero.getChildren().get(8*this.getCoordCoronado().getIni().getFila()+this.getCoordCoronado().getIni().getColumna()+1);
        	}
            Caballo.setGraphic(null);
			Caballo.setDisable(true);
			Alfil.setGraphic(null);
			Alfil.setDisable(true);
			Torre.setGraphic(null);
			Torre.setDisable(true);
			Reina.setGraphic(null);
			Reina.setDisable(true);
			Caballo.setVisible(false);
	    	Alfil.setVisible(false);
	    	Torre.setVisible(false);
	    	Reina.setVisible(false);
    		if (node instanceof StackPane) {
	    		sp = (StackPane) node;
	            sp.getChildren().remove(2);
	            boton = (Button) sp.getChildren().get(1);
	            boton.setOnAction(this::elegirPieza);
    		}
            while(it.hasNext()){
            	coordenadas = it.next();
            	node = tablero.getChildren().get(8*coordenadas.getFila()+coordenadas.getColumna()+1);
	    		if (node instanceof StackPane) {
		    		sp = (StackPane) node;
		    		sp.getChildren().remove(2);
		    		boton = (Button) sp.getChildren().get(1);
		            boton.setOnAction(this::elegirPieza);
	    		}
            }
        	this.setBotonCancelado(new Coordenadas(8,8));
        	this.setCoordCoronado(new Movimiento(new Coordenadas(8,8),new Coordenadas(8,8),null));
        }else{
        	//System.out.print("error: no reconoce procedencia del evento");
        }
    	
    }
    
    private void moverPieza(ActionEvent event) { 
    	Tablero t = this.getTablero();
    	Object evento = event.getSource();
    	if(evento instanceof Button && this.getBotonCancelado().getFila()<=7) { // cuando se le llama por primera vez
    		// Convertir el origen a un botón
            Button boton = (Button) evento;
            // Obtener el ID del botón
            String idBoton = boton.getId();
            char idFila = idBoton.charAt(0);
            char idColumna = idBoton.charAt(1);
            int digitoFila = idFila - '0';
            int digitoColumna = idColumna - '0';
            Coordenadas ini = new Coordenadas(this.getBotonCancelado().getFila(),this.getBotonCancelado().getColumna());
            Coordenadas fin = new Coordenadas(digitoFila,digitoColumna);
            //Error a partir de ejecutarMovimiento con coronacion
			// FALTA: Coronacion en JavaFX Error en Coronacion si no se apretan uno de las 4 opciones
	        // FALTA: imprimir los movimientos en el VBox Movimientos
	        //imprimirMovimientos(t.getMovimientos());
			//imprimirUltimoMovimiento(t.getMovimientos());
            this.ejecutarMovimiento(ini,fin,t.movimientoEspecial(ini,fin));
	        if(this.getCoordCoronado().getIni().getFila()>7){ // si no fue coronado entonces
	        	t.setTurno(!t.getTurno());
				t.piezasClavadas(t.getTurno());
		        this.imprimirTablero();
	        }else{ //si fue coronado pero no se ha elegido la pieza
	        	this.setBotonCancelado(new Coordenadas(8,8)); //ahora 8,8 indica que ya fue presionado, y otro significa que no
	        	if(t.getTurno()){
	        		Image caballoImagen = new Image("caballo_blanco.png");
					ImageView imageViewCaballo = new ImageView(caballoImagen);
					imageViewCaballo.setFitHeight(60);
					imageViewCaballo.setFitWidth(60);
					Caballo.setGraphic(imageViewCaballo);
					Caballo.setVisible(true);
					
					Image alfilImagen = new Image("alfil_blanco.png");
					ImageView imageViewAlfil = new ImageView(alfilImagen);
					imageViewAlfil.setFitHeight(60);
					imageViewAlfil.setFitWidth(60);
					Alfil.setGraphic(imageViewAlfil);
					Alfil.setVisible(true);
					
					Image torreImagen = new Image("torre_blanca.png");
					ImageView imageViewTorre = new ImageView(torreImagen);
					imageViewTorre.setFitHeight(60);
					imageViewTorre.setFitWidth(60);
					Torre.setGraphic(imageViewTorre);
					Torre.setVisible(true);
					
					Image reinaImagen = new Image("reina_blanca.png");
					ImageView imageViewReina = new ImageView(reinaImagen);
					imageViewReina.setFitHeight(60);
					imageViewReina.setFitWidth(60);
					Reina.setGraphic(imageViewReina);
					Reina.setVisible(true);
	        	}else{
	        		Image caballoImagen = new Image("caballo_negro.png");
					ImageView imageViewCaballo = new ImageView(caballoImagen);
					imageViewCaballo.setFitHeight(60);
					imageViewCaballo.setFitWidth(60);
					Caballo.setGraphic(imageViewCaballo);
					Caballo.setVisible(true);
					
					Image alfilImagen = new Image("alfil_negro.png");
					ImageView imageViewAlfil = new ImageView(alfilImagen);
					imageViewAlfil.setFitHeight(60);
					imageViewAlfil.setFitWidth(60);
					Alfil.setGraphic(imageViewAlfil);
					Alfil.setVisible(true);
					
					Image torreImagen = new Image("torre_negra.png");
					ImageView imageViewTorre = new ImageView(torreImagen);
					imageViewTorre.setFitHeight(60);
					imageViewTorre.setFitWidth(60);
					Torre.setGraphic(imageViewTorre);
					Torre.setVisible(true);
					
					Image reinaImagen = new Image("reina_negra.png");
					ImageView imageViewReina = new ImageView(reinaImagen);
					imageViewReina.setFitHeight(60);
					imageViewReina.setFitWidth(60);
					Reina.setGraphic(imageViewReina);
					Reina.setVisible(true);
	        	}
	        	//sigue el hilo de ejecución de obtenerPiezaCoronacion
	        	
	        	//solo debería de apretar los 4 botones de elegir pieza?
	        }
    	}else if(evento instanceof Button && this.getBotonCancelado().getFila()>7){ //si ya fue presionado antes significa que ya cargó coordCoronado con valores validos
    		Set<Coordenadas> mov = t.movimientosPosiblesUnaPieza(this.getCoordCoronado().getIni().getFila(), this.getCoordCoronado().getIni().getColumna()); //getBotonCancelado no tiene sentido
    		//precondicion: mov no retorna vacio, entonces it existe
            Iterator<Coordenadas> it = mov.iterator();
            Coordenadas coordenadas;
            Node node;
            StackPane sp;
            Button boton;
            node = tablero.getChildren().get(8*this.getCoordCoronado().getIni().getFila()+this.getCoordCoronado().getIni().getColumna()+1);
    		if (node instanceof StackPane) {
	    		sp = (StackPane) node;
	            sp.getChildren().remove(2);
	            boton = (Button) sp.getChildren().get(1);
	            boton.setOnAction(this::elegirPieza);
    		}
            while(it.hasNext()){
            	coordenadas = it.next();
            	node = tablero.getChildren().get(8*coordenadas.getFila()+coordenadas.getColumna()+1);
	    		if (node instanceof StackPane) {
		    		sp = (StackPane) node;
		    		sp.getChildren().remove(2);
		    		boton = (Button) sp.getChildren().get(1);
		            boton.setOnAction(this::elegirPieza);
	    		}
            }
            //Falta dejar de mostrar las 4 piezas a elegir
            Caballo.setGraphic(null);
			Caballo.setDisable(true);
			Alfil.setGraphic(null);
			Alfil.setDisable(true);
			Torre.setGraphic(null);
			Torre.setDisable(true);
			Reina.setGraphic(null);
			Reina.setDisable(true);
			Caballo.setVisible(false);
	    	Alfil.setVisible(false);
	    	Torre.setVisible(false);
	    	Reina.setVisible(false);
        	this.setCoordCoronado(new Movimiento(new Coordenadas(8,8),new Coordenadas(8,8),null));
    	}else{
        	//System.out.print("error2: no reconoce procedencia del evento");
        }
    }
    
    private void obtenerPiezaCoronacion(ActionEvent event){
    	Tablero t = this.getTablero();
    	Button coronacion = (Button) event.getSource();
    	boolean colorCoronado = this.getTablero().getTurno();
    	switch(coronacion.getId()){
    		case "Caballo":
    			if(colorCoronado){
    				this.setCoronado(new Caballo(colorCoronado,"caballo_blanco.png"));
    	    	}else{
    	    		this.setCoronado(new Caballo(colorCoronado,"caballo_negro.png"));
    	    	}
    		break;
    		case "Alfil":
    			if(colorCoronado){
    				this.setCoronado(new Alfil(colorCoronado,"alfil_blanco.png"));
    	    	}else{
    	    		this.setCoronado(new Alfil(colorCoronado,"alfil_negro.png"));
    	    	}
        	break;
    		case "Torre":
    			if(colorCoronado){
    				this.setCoronado(new Torre(colorCoronado,"torre_blanca.png"));
    	    	}else{
    	    		this.setCoronado(new Torre(colorCoronado,"torre_negra.png"));
    	    	}
        	break;
        	default:
        		if(colorCoronado){
    				this.setCoronado(new Reina(colorCoronado,"reina_blanca.png"));
    	    	}else{
    	    		this.setCoronado(new Reina(colorCoronado,"reina_negra.png"));
    	    	}
        	break;
    	}
    	t.actualizarPieza(this.getCoordCoronado().getIni(),this.getCoordCoronado().getFin());
    	Movimiento m = new Movimiento(this.getCoordCoronado().getIni(),this.getCoordCoronado().getFin(),t.getTablero()[this.getCoordCoronado().getFin().getFila()][this.getCoordCoronado().getFin().getColumna()]);
    	t.getMovimientos().add(m);
    	String turn;
    	if(t.getTurno()){
    		turn = "Blanco";
    	}else{
    		turn = "Negro";
    	}
    	String posIni = "";
    	String posFin = "";
    	switch(this.getCoordCoronado().getIni().getColumna()){
			case 0:
				posIni = "a";
			break;
			case 1:
				posIni = "b";
	    	break;
			case 2:
				posIni = "c";
			break;
			case 3:
				posIni = "d";
	    	break;
			case 4:
				posIni = "e";
			break;
			case 5:
				posIni = "f";
	    	break;
			case 6:
				posIni = "g";
			break;
			default:
				posIni = "h";
	    	break;
		}
    	switch(this.getCoordCoronado().getFin().getColumna()){
			case 0:
				posFin = "a";
			break;
			case 1:
				posFin = "b";
	    	break;
			case 2:
				posFin = "c";
			break;
			case 3:
				posFin = "d";
	    	break;
			case 4:
				posFin = "e";
			break;
			case 5:
				posFin = "f";
	    	break;
			case 6:
				posFin = "g";
			break;
			default:
				posFin = "h";
	    	break;
		}
    	posIni = posIni+(this.getCoordCoronado().getIni().getFila()+1);
    	posFin = posFin+(this.getCoordCoronado().getFin().getFila()+1);
    	Text mov = new Text(t.getTablero()[this.getCoordCoronado().getFin().getFila()][this.getCoordCoronado().getFin().getColumna()].getClass().getSimpleName()+" "+turn+" de "+posIni+" a "+posFin);
    	mov.setFont(Font.font("Arial", 12)); // Puedes ajustar el tamaño y la fuente según tus necesidades
    	mov.setFill(Color.web("#6b3c1c"));
    	movimientos.getChildren().add(mov);
    	t.borrarPieza(this.getCoordCoronado().getFin());
    	t.agregarPieza(this.getCoordCoronado().getFin(), this.getCoronado());
    	t.getTablero()[this.getCoordCoronado().getFin().getFila()][this.getCoordCoronado().getFin().getColumna()].setPrimerMovimiento(false);
    	t.setTurno(!t.getTurno());
		t.piezasClavadas(t.getTurno());
        this.imprimirTablero();
    }
    
    /*//Cronometro
    public class CronometroJavaFX {

	    public static void iniciarCronometro(Stage primaryStage) {
	        StackPane root = new StackPane();
	        Label label = new Label("Tiempo restante: 01:00.000");
	        root.getChildren().add(label);
	
	        // Duración del cronómetro (en milisegundos)
	        long duracionTotalMillis = 60_000;
	
	        // Crear un cronómetro con eventos cada milisegundo
	        Timeline timeline = new Timeline(
	                new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
	                    private long tiempoRestante = duracionTotalMillis;
	
	                    @Override
	                    public void handle(ActionEvent event) {
	                        // Actualizar la etiqueta con el tiempo restante formateado
	                        String tiempoFormateado = formatearTiempo(tiempoRestante);
	                        label.setText("Tiempo restante: " + tiempoFormateado);
	
	                        // Verificar si el tiempo ha llegado a cero
	                        if (tiempoRestante == 0) {
	                            // Detener el cronómetro cuando el tiempo llega a cero
	                            timeline.stop();
	                            label.setText("¡Tiempo agotado!");
	                        }
	
	                        // Decrementar el tiempo restante
	                        tiempoRestante--;
	                    }
	                })
	        );
	
	        // Configurar el cronómetro para repetirse durante la duración total
	        timeline.setCycleCount((int) duracionTotalMillis);
	
	        // Mostrar la escena
	        Scene scene = new Scene(root, 400, 200);
	        primaryStage.setTitle("Cronómetro en JavaFX");
	        primaryStage.setScene(scene);
	
	        // Iniciar el cronómetro
	        timeline.play();
	
	        // Mostrar la ventana principal
	        primaryStage.show();
	    }
	
	    // Método para formatear el tiempo en formato mm:ss.SSS
	    private static String formatearTiempo(long milisegundos) {
	        long minutos = milisegundos / (60 * 1000);
	        long segundos = (milisegundos % (60 * 1000)) / 1000;
	        long milisegundosRestantes = milisegundos % 1000;
	        return String.format("%02d:%02d.%03d", minutos, segundos, milisegundosRestantes);
	    }
	}
    */
}