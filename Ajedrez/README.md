# Ajedrez
Juego de Ajedrez

Posible Bug? (tener cuidado) -> Revisar al crear Imagenes y la concurrencia (aveces crasheo por no darle tiempo a cargar imagenes/botones/nose) //creo que la solución que hice no funciona ya que imprimirTablero() y luego esperar, podría hacer click cuando se está en la función ya que luego espera, y cuando espera es porque la función ya terminó

URGENTE: Esto no me permite crear un ejecutable ya que siempre tira error
(no funciona el jar). Tengo que arreglar esto para poder crear una versión jugable.


 FALTA: 
 	Verificar (Hecho) -> Crear una lista de piezasComidas, guarda el momento en el que una pieza fue comida (numero de turno,pieza,coordenadas) para poder rehacer un tablero
 	Verificar (Hecho) -> Crear un tablero en el que el usuario pueda asignar la posición de las piezas y el turno
 	(Hecho) -> Modificar la interacción entre usuario y sistema al ingresar la pieza y donde se mueve
 	Aprender parte Gráfica (JavaFX o hacerlo en Javascript+Html) (Hecho en JavaFX)
 	Podría hacer en JavaScript+HTML para subirlo a un posible portfolio
 	
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
