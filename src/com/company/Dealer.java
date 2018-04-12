package com.company;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author sim
 * */

public class Dealer extends Thread {

    private Partida partida;

    private Mazo mazo;
    private int rondaActual = 0;


    public Dealer(Partida partida, Mazo mazo) {

        this.partida = partida;
        this.mazo = mazo;
    }


    @Override
    public void run() {

        while (!this.mazo.getMazoCartas().isEmpty()) {

            if (this.rondaActual == this.partida.getRonda()) {

                if (this.mazo.getCantCartas() > this.partida.getCantJugadores()) {

                    while (partida.getCantJugadores() > partida.getCartasEnMesa().size()) {

                        this.partida.ponerCartaMesa(this.mazo.getCartaRandom());
                    }

                if (this.partida.getCartasEnMesa().size() == this.partida.getCantJugadores()) {

                    this.partida.setDealerListo(1);
                    this.rondaActual = this.rondaActual + 1;
                }

                } else {

                    this.mazo.descartarCartasRestantes();
                    this.rondaActual = this.rondaActual +1;
                }
            }
        }

        try {
            partida.endGame();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


