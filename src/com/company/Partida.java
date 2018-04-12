package com.company;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Random;

/**
 * @author sim
 * */

public class Partida implements Observer {

    private ArrayList<Carta> cartasEnMesa = new ArrayList<>();
    private int ronda = 0;
    private int cantJugadores;
    private boolean game = true;
    private int jugadoresListos = 0;
    private int dealerListo = 0;
    private Jugador ganador = new Jugador(0);
    private MySQL bd;


    public Partida (int cantJugadores, MySQL bd) {

        this.cantJugadores = cantJugadores;
        this.bd = bd;
    }



    public void endGame () throws SQLException {

        this.game = false;

        while (this.jugadoresListos < this.cantJugadores){

        }

        System.out.println("\nLa partida ah finalizado");
        System.out.println("\n\n\tEl ganador es "+ this.ganador.getNombre()+" !!!");

        Jugador ganador = getGanador();
        bd.insertarGanador(ganador);
    }

    public synchronized Carta sacarCartaMesa () {

        Carta rtn = null;

        if (!this.cartasEnMesa.isEmpty()) {

            Random aleatorio = new Random(System.currentTimeMillis());
            int intAletorio = aleatorio.nextInt(this.cartasEnMesa.size());

            rtn = this.cartasEnMesa.remove(intAletorio);
        }

        notifyAll();
        return rtn;
    }

    public synchronized void ponerCartaMesa (Carta carta) {

        System.out.println("CARTA EN MESA: "+carta.getNumero() +" "+ carta.getPalo());
        this.cartasEnMesa.add(carta);
    }

    public int getCantJugadores() {
        return this.cantJugadores;
    }

    public synchronized int getRonda() {
        return this.ronda;
    }

    public ArrayList<Carta> getCartasEnMesa (){
        return this.cartasEnMesa;
    }

    public boolean isGame() {
        return game;
    }

    public synchronized void jugadorListo () {

        this.jugadoresListos = this.jugadoresListos +1 ;

        if (this.jugadoresListos == this.cantJugadores) {

            this.jugadoresListos = 0;
            this.ronda = this.ronda + 1;
            setDealerListo(0);
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {

        String mensaje = (String) arg;
        System.out.println(mensaje);
    }

    public int getDealerListo() {
        return this.dealerListo;
    }

    public void setDealerListo(int dealerListo) {
        this.dealerListo = dealerListo;
    }

    public void selectGanador (Jugador jugador) {

        Jugador newGanador = new Jugador(0);

        newGanador.setNombre(jugador.getNombre());
        newGanador.setPuntosTotales(jugador.getPuntosTotales());
        newGanador.setCartas(jugador.getCartas());

        if(this.ganador.getPuntosTotales() < newGanador.getPuntosTotales()) {

            this.ganador = newGanador;

        }

        this.jugadoresListos = this.jugadoresListos +1;
    }

    public Jugador getGanador () {
        return  this.ganador;
    }
}

