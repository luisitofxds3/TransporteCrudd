
package com.mycompany.transportecrudd.dao;

import com.mycompany.transportecrudd.conexion.CONEXION;
import com.mycompany.transportecrudd.modelo.Paquete;
import java.sql.*;
import java.util.ArrayList;

public class PaqueteDAO {

    // Guardar paquete
    public boolean guardar(Paquete p) {

        String sql = "INSERT INTO paquete "
                + "(Descripcion, Destinatario, DireccionEnvio, "
                + "idCamionero, idCiudad) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setString(3, p.getDireccionEnvio());
            ps.setInt(4, p.getIdCamionero());
            ps.setInt(5, p.getIdCiudad());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    // Buscar paquete por ID
    public Paquete buscar(int id) {

        String sql = "SELECT * FROM paquete WHERE idPaquete=?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Paquete p = new Paquete();

                    p.setIdPaquete(rs.getInt("idPaquete"));
                    p.setDescripcion(rs.getString("Descripcion"));
                    p.setDestinatario(rs.getString("Destinatario"));
                    p.setDireccionEnvio(rs.getString("DireccionEnvio"));
                    p.setIdCamionero(rs.getInt("idCamionero"));
                    p.setIdCiudad(rs.getInt("idCiudad"));

                    return p;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return null;
    }

    // Modificar paquete
    public boolean modificar(Paquete p) {

        String sql = "UPDATE paquete SET "
                + "Descripcion=?, Destinatario=?, DireccionEnvio=?, "
                + "idCamionero=?, idCiudad=? WHERE idPaquete=?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getDescripcion());
            ps.setString(2, p.getDestinatario());
            ps.setString(3, p.getDireccionEnvio());
            ps.setInt(4, p.getIdCamionero());
            ps.setInt(5, p.getIdCiudad());
            ps.setInt(6, p.getIdPaquete());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    // Eliminar paquete
    public boolean eliminar(int id) {

        String sql = "DELETE FROM paquete WHERE idPaquete=?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    // Mostrar todos los paquetes
    public ArrayList<Paquete> listar() {

        ArrayList<Paquete> lista = new ArrayList<>();

        String sql = "SELECT * FROM paquete ORDER BY idPaquete";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Paquete p = new Paquete();

                p.setIdPaquete(rs.getInt("idPaquete"));
                p.setDescripcion(rs.getString("Descripcion"));
                p.setDestinatario(rs.getString("Destinatario"));
                p.setDireccionEnvio(rs.getString("DireccionEnvio"));
                p.setIdCamionero(rs.getInt("idCamionero"));
                p.setIdCiudad(rs.getInt("idCiudad"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }
}
