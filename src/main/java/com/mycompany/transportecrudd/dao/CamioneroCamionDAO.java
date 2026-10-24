
package com.mycompany.transportecrudd.dao;

import com.mycompany.transportecrudd.conexion.CONEXION;
import com.mycompany.transportecrudd.modelo.CamioneroCamion;
import java.sql.*;
import java.util.ArrayList;

public class CamioneroCamionDAO {

    public boolean guardar(CamioneroCamion cc) {
        String sql = "INSERT INTO camionerocamion (idCamionero, idCamion) VALUES (?, ?)";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cc.getIdCamionero());
            ps.setInt(2, cc.getIdCamion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    public CamioneroCamion buscar(int id) {
        String sql = "SELECT * FROM camionerocamion WHERE idCamioneroCamion = ?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                CamioneroCamion cc = new CamioneroCamion();
                cc.setIdCamioneroCamion(rs.getInt("idCamioneroCamion"));
                cc.setIdCamionero(rs.getInt("idCamionero"));
                cc.setIdCamion(rs.getInt("idCamion"));
                return cc;
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return null;
    }

    public boolean modificar(CamioneroCamion cc) {
        String sql = "UPDATE camionerocamion SET idCamionero = ?, idCamion = ? WHERE idCamioneroCamion = ?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, cc.getIdCamionero());
            ps.setInt(2, cc.getIdCamion());
            ps.setInt(3, cc.getIdCamioneroCamion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM camionerocamion WHERE idCamioneroCamion = ?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public ArrayList<CamioneroCamion> listar() {
        ArrayList<CamioneroCamion> lista = new ArrayList<>();

        String sql = "SELECT * FROM camionerocamion ORDER BY idCamioneroCamion";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CamioneroCamion cc = new CamioneroCamion();

                cc.setIdCamioneroCamion(rs.getInt("idCamioneroCamion"));
                cc.setIdCamionero(rs.getInt("idCamionero"));
                cc.setIdCamion(rs.getInt("idCamion"));

                lista.add(cc);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }
}
