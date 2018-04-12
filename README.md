    /* //////////////////////////////////////////////////////////////////////////////////////////////////////
    *                                             TP3
    *
    * Se persistio los resultados del juego en MySQL, ya que es es la base de datos de la cual tenemos mas
    * conocimiento hasta la fecha.
    *
    * El juego consiste en una partida por turnos, por cada turno el Dealer reparte cartas segun la cantidad 
    * de jugadores que esten jugando dicho juego. Estas cartas se agregan a la partida, para luego los jugadores
    * puedan tomar las cartas.
    * Una ves tomadas todas las cartas repartidas por el Dealer, la Partida verifica cuantos son los puntos totales
    * de cada uno de los jugadores, aunuciando el puntaje.
    * Terminado esto, los jugadores avisan que se debera jugar la siguente ronda, para que el Dealer, reparta
    * nuevamente cartas en la Partida. Esto se realizara hasta que el Dealer se quede sin cartas, o este no tenga
    * las cartas suficientes para repartir.
    *
    * Una vez finalizado el juego, la Partida comprueba quien es el jugador con mas cantidad de puntos, para luego
    * persistir los datos del Jugador ganador.
    *
    /* //////////////////////////////////////////////////////////////////////////////////////////////////////
