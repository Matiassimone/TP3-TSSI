package com.company;

import java.util.ArrayList;
import java.util.Observable;

/**
 * @author sim
 * */


public class Jugador extends Observable implements Runnable {

    private Partida partida;

    private String nombre;
    private ArrayList<Carta> cartas = new ArrayList<>();
    private Carta mano = null;
    private int puntosTotales = 0;
    private Thread hilo;



    public  Jugador (Partida partida, String nombre) {

        this.partida = partida;
        this.nombre = nombre;
    }

    public Jugador (int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }



    public void addCartaUsada (Carta carta) {
        this.cartas.add(carta);
    }

    public void start () {

        if (this.hilo == null) {

            this.hilo = new Thread(this);
            hilo.start();
        }
    }

    public ArrayList<Carta> getCartas () {
        return this.cartas;
    }

    public void setCartas (ArrayList<Carta> cartas) {
        this.cartas = cartas;
    }

    public String getNombre () {
        return nombre;
    }

    public void setNombre (String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosTotales () {
        return this.puntosTotales;
    }

    public void setPuntosTotales (int puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public void sumarPuntosTotales () {

        this.puntosTotales = this.mano.getNumero() + this.puntosTotales;
    }

    @Override
    public void run() {

        int rondaActual = this.partida.getRonda();

        while (partida.isGame()) {

            if (this.partida.getDealerListo() == 1) {

                while (this.mano == null) {

                    try {

                        Thread.sleep(50);
                        this.mano = this.partida.sacarCartaMesa();

                        System.out.println("\nEl jugador " + this.nombre + " agarro la carta " + this.mano.getPalo() + " " + this.mano.getNumero());
                        this.partida.jugadorListo();


                    } catch (InterruptedException e) {
                    }
                }
            }

            if (this.partida.getRonda() > rondaActual) {


                rondaActual = rondaActual +1 ;
                sumarPuntosTotales();

                setChanged();

                String mensaje = "\n\tEl jugador "+ getNombre() +" tiene un total de "+ getPuntosTotales()+" puntos.\n";
                notifyObservers(mensaje);

                addCartaUsada(this.mano);
                this.mano = null;
            }

        }

        Jugador rtn = new Jugador(this.puntosTotales);
        rtn.setNombre(this.nombre);
        rtn.setCartas(this.cartas);

        this.partida.selectGanador(rtn);

    }

}

