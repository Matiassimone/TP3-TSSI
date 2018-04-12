package com.company;

/**
 * @author sim
 * */

public class Carta {

    private int numero;
    private String palo;



    public Carta (int numero, String palo) {

        this.numero = numero;
        this.palo = palo;
    }



    public int getNumero () {
        return numero;
    }

    public String getPalo () {
        return palo;
    }

}
