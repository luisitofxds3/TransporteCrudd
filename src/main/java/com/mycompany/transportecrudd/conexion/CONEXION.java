
package com.mycompany.transportecrudd.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CONEXION {

    private static final String URL =
            "jdbc:mysql://localhost:3306/db_paqueteria";

    private static final String USUARIO = System.getenv("DB_USER");
private static final String CLAVE = System.getenv("DB_PASSWORD");
    public static Connection conectar() {
        try {
            System.out.println("Usuario configurado: " + USUARIO);
System.out.println("Password configurado: "
        + (CLAVE != null && !CLAVE.isEmpty()));
            Connection con = DriverManager.getConnection(
                    URL, USUARIO, CLAVE
            );

            System.out.println("Conexion exitosa con MySQL");
            return con;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
