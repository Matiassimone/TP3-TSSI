package com.company;

import java.sql.*;

/**
 * @author sim
 * */


public class MySQL {

    private Connection bd = DriverManager.getConnection("jdbc:mysql://localhost:3306/juegoDeCartas", "root", "");



    public MySQL () throws SQLException {

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }



    public void insertarGanador (Jugador ganador) throws SQLException {

        String strQuery = "INSERT INTO ganadores(nombre, puntuacion) VALUES('" +ganador.getNombre()+ "', "+ganador.getPuntosTotales()+")";

        PreparedStatement statement = this.bd.prepareStatement(strQuery);

        statement.executeUpdate();
    }
}
