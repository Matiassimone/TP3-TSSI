package com.company;


import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author sim
 * */

public class Main {

    public static void main(String[] args) throws SQLException {

        MySQL bd = new MySQL();

        Partida game = new Partida(3, bd);

        Mazo mazo = new Mazo();
        Dealer dealer = new Dealer(game, mazo);

        Jugador j1Pepe = new Jugador(game, "Pepe");
        Jugador j2Juan = new Jugador(game, "Juan");
        Jugador j3Pedro = new Jugador(game, "Pedro");

        j1Pepe.addObserver(game);
        j2Juan.addObserver(game);
        j3Pedro.addObserver(game);

        dealer.start();

        j1Pepe.start();
        j2Juan.start();
        j3Pedro.start();


    }
}

