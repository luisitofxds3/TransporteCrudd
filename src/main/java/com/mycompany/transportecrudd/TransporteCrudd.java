
package com.mycompany.transportecrudd;

import com.mycompany.transportecrudd.conexion.CONEXION;
import java.sql.Connection;
import java.sql.SQLException;

public class TransporteCrudd {

    public static void main(String[] args) {

        try (Connection con = CONEXION.conectar()) {

            if (con != null) {
                System.out.println("Base de datos conectada");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
