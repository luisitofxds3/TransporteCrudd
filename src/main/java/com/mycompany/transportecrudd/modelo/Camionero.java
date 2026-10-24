
package com.mycompany.transportecrudd.modelo;

import java.math.BigDecimal;

public class Camionero {

    private int idCamionero;
    private String nombre;
    private String telefono;
    private String direccion;
    private BigDecimal salario;
    private String poblacion;

    public Camionero() {
    }

    public int getIdCamionero() {
        return idCamionero;
    }

    public void setIdCamionero(int idCamionero) {
        this.idCamionero = idCamionero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }
}
