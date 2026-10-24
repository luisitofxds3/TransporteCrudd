
package com.mycompany.transportecrudd.dao;

import com.mycompany.transportecrudd.conexion.CONEXION;
import com.mycompany.transportecrudd.modelo.Camionero;
import java.sql.*;
import java.util.ArrayList;

public class CamioneroDAO {

    // Guardar camionero
    public boolean guardar(Camionero c) {

        String sql = "INSERT INTO camionero "
                + "(Nombre, Telefono, Direccion, Salario, Poblacion) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());
            ps.setBigDecimal(4, c.getSalario());
            ps.setString(5, c.getPoblacion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
            return false;
        }
    }

    // Buscar camionero por ID
    public Camionero buscar(int id) {

        String sql = "SELECT * FROM camionero WHERE idCamionero = ?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Camionero c = new Camionero();

                    c.setIdCamionero(rs.getInt("idCamionero"));
                    c.setNombre(rs.getString("Nombre"));
                    c.setTelefono(rs.getString("Telefono"));
                    c.setDireccion(rs.getString("Direccion"));
                    c.setSalario(rs.getBigDecimal("Salario"));
                    c.setPoblacion(rs.getString("Poblacion"));

                    return c;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return null;
    }

    // Modificar camionero
    public boolean modificar(Camionero c) {

        String sql = "UPDATE camionero SET "
                + "Nombre=?, Telefono=?, Direccion=?, "
                + "Salario=?, Poblacion=? "
                + "WHERE idCamionero=?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getDireccion());
            ps.setBigDecimal(4, c.getSalario());
            ps.setString(5, c.getPoblacion());
            ps.setInt(6, c.getIdCamionero());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al modificar: " + e.getMessage());
            return false;
        }
    }

    // Eliminar camionero
    public boolean eliminar(int id) {

        String sql = "DELETE FROM camionero WHERE idCamionero=?";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    // Mostrar todos los camioneros
    public ArrayList<Camionero> listar() {

        ArrayList<Camionero> lista = new ArrayList<>();

        String sql = "SELECT * FROM camionero ORDER BY idCamionero";

        try (Connection con = CONEXION.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Camionero c = new Camionero();

                c.setIdCamionero(rs.getInt("idCamionero"));
                c.setNombre(rs.getString("Nombre"));
                c.setTelefono(rs.getString("Telefono"));
                c.setDireccion(rs.getString("Direccion"));
                c.setSalario(rs.getBigDecimal("Salario"));
                c.setPoblacion(rs.getString("Poblacion"));

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        return lista;
    }
}
