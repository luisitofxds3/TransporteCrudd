
package com.mycompany.transportecrudd.dao;

import com.mycompany.transportecrudd.conexion.CONEXION;
import com.mycompany.transportecrudd.modelo.Camion;
import java.sql.*;
import java.util.ArrayList;

public class CamionDAO {

    // Guardar camion
    public boolean guardar(Camion camion) {
        String sql = "INSERT INTO camion "
                + "(NoPlaca, Modelo, Tipo, Potencia) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection con = CONEXION.conectar()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, camion.getNoPlaca());
                ps.setString(2, camion.getModelo());
                ps.setString(3, camion.getTipo());

                if (camion.getPotencia() == null) {
                    ps.setNull(4, Types.INTEGER);
                } else {
                    ps.setInt(4, camion.getPotencia());
                }

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    // Buscar camion por ID
    public Camion buscar(int id) {
        String sql = "SELECT * FROM camion WHERE idCamion = ?";

        try (Connection con = CONEXION.conectar()) {
            if (con == null) return null;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Camion c = new Camion();
                        c.setIdCamion(rs.getInt("idCamion"));
                        c.setNoPlaca(rs.getString("NoPlaca"));
                        c.setModelo(rs.getString("Modelo"));
                        c.setTipo(rs.getString("Tipo"));

                        int potencia = rs.getInt("Potencia");
                        c.setPotencia(rs.wasNull() ? null : potencia);

                        return c;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return null;
    }

    // Modificar camion
    public boolean modificar(Camion camion) {
        String sql = "UPDATE camion SET NoPlaca=?, "
                + "Modelo=?, Tipo=?, Potencia=? "
                + "WHERE idCamion=?";

        try (Connection con = CONEXION.conectar()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, camion.getNoPlaca());
                ps.setString(2, camion.getModelo());
                ps.setString(3, camion.getTipo());

                if (camion.getPotencia() == null) {
                    ps.setNull(4, Types.INTEGER);
                } else {
                    ps.setInt(4, camion.getPotencia());
                }

                ps.setInt(5, camion.getIdCamion());

                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    // Eliminar camion
    public boolean eliminar(int id) {
        String sql = "DELETE FROM camion WHERE idCamion=?";

        try (Connection con = CONEXION.conectar()) {
            if (con == null) return false;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                return ps.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    // Mostrar todos los camiones
    public ArrayList<Camion> listar() {
        ArrayList<Camion> lista = new ArrayList<>();

        String sql = "SELECT * FROM camion ORDER BY idCamion";

        try (Connection con = CONEXION.conectar()) {
            if (con == null) return lista;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Camion c = new Camion();

                    c.setIdCamion(rs.getInt("idCamion"));
                    c.setNoPlaca(rs.getString("NoPlaca"));
                    c.setModelo(rs.getString("Modelo"));
                    c.setTipo(rs.getString("Tipo"));

                    int potencia = rs.getInt("Potencia");
                    c.setPotencia(rs.wasNull() ? null : potencia);

                    lista.add(c);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }
}
