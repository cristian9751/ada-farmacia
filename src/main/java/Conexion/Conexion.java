/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author cripoponc
 */
public class Conexion {
    private static Connection conexion = null;
    
    /**
     * Constructor  que modifica el parametro estatico conexion para obtener la conexion a la bd
     */
    private Conexion() {
        String JDBC_URL="jdbc:mysql://adadam.mariadb.database.azure.com:3306/db_farmacia?useSSL=true&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        String JDBC_USER="ada@adadam";
        String JDBC_PASSWORD = "t3MNUpz27eq5PBO";
        
        try {
            conexion = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    /***
     * Comprueba si la instancia conexion no es nula
     * @return  Devuelve la instancia conexion
     */
    public static Connection getConnection() {
        if(conexion == null) {
            new Conexion();
        }
        
        return conexion;
    }
    
    public static void closeConnection() {
        if(conexion != null) {
            conexion = null;
        }
    }

    public static boolean queryDone(int registry ) {
        boolean res = false;
        if(registry > 0)
            res = true;

        return res;
    }

}
