package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * @author sim
 * */

public class Mazo {

    private ArrayList<Carta> mazoCartas = new ArrayList<>();
    private int cantCartas = 0;



    public Mazo () {

        generarMazoEspaniol();
    }



    public void descartarCartasRestantes () {

        this.mazoCartas = new ArrayList<Carta>();
        this.cantCartas = 0;
    }

    public ArrayList<Carta> getMazoCartas () {
        return mazoCartas;
    }

    public int getCantCartas () {
        return cantCartas;
    }

    public Carta getCartaRandom () {

        Random aleatorio = new Random(System.currentTimeMillis());
        Carta carta = null;

        int intAletorio = aleatorio.nextInt(this.cantCartas);

        carta = this.mazoCartas.remove(intAletorio);

        aleatorio.setSeed(System.currentTimeMillis());
        this.cantCartas = this.mazoCartas.size();
        return carta;
    }

    public void generarMazoEspaniol () {

        Carta carta = null;
        int numero = 1;
        int flag = 0;

        HashMap<Integer, String> palos = new HashMap<>();

        palos.put(0,"Oro");
        palos.put(1, "Espada");
        palos.put(2, "Basto");
        palos.put(3, "Copa");


        while (numero < 13) {

            flag = 0;

            while (flag < 4) {

                carta = new Carta(numero, palos.get(flag));
                this.mazoCartas.add(carta);

                flag++;
            }

            numero++;
        }

        this.cantCartas = this.mazoCartas.size();
    }
}
