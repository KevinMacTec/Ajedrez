# Ajedrez
Juego de Ajedrez

Proximamente:

- Revisar que capturaAlPaso funcione en su totalidad (para mover y bloquear jaques)
- Lista de movimientos, revisar cuando se come una pieza o corona
- Movimientos horizontales, solo muestra la primera casilla, y no todas
- Crear el GameLoop (sin tener en cuenta JavaFX)
- Probar en consola con un prototipo para testear la lógica
- Crear tableros personalizables para poder crear Tests (muchísimos para cubrir todos los escenarios posibles)
- Pasar a JavaFX (gráfico)
- Ganarle a Nicolás
- Agregarle sonidos
- Crear un editor de tableros, el cual permita ubicar las piezas como uno desee y eligiendo el turno del color que corresponda
- Poder ver repeticiones de partidas dandole hacia atras los movimientos, para ello se necesita una lista adicional de piezas comidas que indique en que momento fue comida para recuperarla regresando hacia atras en la lista de movimientos
- Crear una base de datos para guardar las partidas, y en el momento que se desee, poder revisarlas

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