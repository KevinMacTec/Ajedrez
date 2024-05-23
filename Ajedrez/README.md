# Ajedrez
Juego de Ajedrez

Posible Bug? (tener cuidado) -> Revisar al crear Imagenes y la concurrencia (aveces crasheo por no darle tiempo a cargar imagenes/botones/nose) //creo que la solución que hice no funciona ya que imprimirTablero() y luego esperar, podría hacer click cuando se está en la función ya que luego espera, y cuando espera es porque la función ya terminó

URGENTE: Refactorizar el código de java a c++ y usar OpenGL2.0 y SDL2 para la parte gráfica.


 FALTA: 
 	Rehacer todo lo gráfico en OpenGL y SDL2 (Ya no se usa mas java ni JavaFX)
 	
StandBy (Proximamente??):
	A partir de una lista de movimientos (valida), adelantar o retroceder jugadas (0 retrocede, 1 adelanta)
	Arreglar la precondición de iniciarTableroPersonalizado (esta' porque soy vago, no debería)
	Crear opcion de Juego personalizado (esto conlleva a crear casos tests con esta nueva función)
	Hacer un menú del Tablero, un Inicio donde se seleccione opciones como: (implica cambiar el main al menu, y el main antiguo a partidaNormal, aparte de crear nuevos fxml)
		Jugar Jugador vs Jugador
		Jugar Personalizado
		Configuraciones
	 		Ajustes gráficos (distribución y/o proporciones, hacerlo responsive?)
	 		Activar/Desactivar Sonidos (regular volumen)
	 		Cambiar el fondo de las casillas o las piezas a elección de usuario (un catálogo)
	Darle CSS bonito a todo. Está muy rústico
	Hacer un tableroPersonalizado en JavaFX
	Ponerle sonido al mover las piezas (Otros: Jaque, Coronación, Enroque, JaqueMate)
	Hacer Drag and Drop de las piezas a las posiciones posibles?
	Realizar la documentación del proyecto de Ajedrez
	Agregar Cronometros (esto está muy lejos en el tiempo)
	Hacer Jugador vs Computadora (esto está muy lejos en el tiempo, 	        minimax, poda alfa beta, eval(Tablero))

Casos Tests:
	Verificar que la coronación se haga correctamente (Verificado en JavaFX)
	Verificar que si el enroque esta amenazado, no se realice (Verificado)
	Verificar que Rey no se mueva a casillas amenazadas (Verificado)
	Verificar que se pueda bloquear un jaque correctamente (Verificado)
	Verificar que solo se pueda mover el Rey cuando está amenazado por 2 piezas a la vez (Verificado)
	Verificar que no se pueda mover una pieza clavada amenos que sea en la linea de la clavada (Verificado)
	Verificar que se termine la partida correctamente (JaqueMate o Empate) (Verificado JaqueMate en JavaFX)
	Verificar que el Rey solo pueda comer piezas que le hacen jaque si no están defendidas (Verificado)

Tests más importantes:

- enroque
- comer la pieza que realiza jaque (bloquearJaque incluye la posición de la pieza amenazante)
- cubrirse jaque (normalmente o con capturaAlPaso)
- no enrocar en jaque
- coronación de peón
- mostrar los movimientos posibles para cada pieza
- mostrar las piezas que pueden bloquear un jaque
- mostrar las piezas clavadas
- mostrar los movimientos posibles del rey

- probar a traves de partidas otras cuestiones mas triviales
