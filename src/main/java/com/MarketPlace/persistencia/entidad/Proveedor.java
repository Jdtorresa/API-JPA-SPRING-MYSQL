package com.MarketPlace.persistencia.entidad;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "proveedores")
public class Proveedor {

    @Id
    private String nit;
    @Column(nullable = false, length = 50)
    private String razonSocial;
    @Column(nullable = false, length = 80)
    private String direccion;


    public Proveedor(String nit, String razonSocial, String direccion) {
        this.nit = nit;
        this.razonSocial = razonSocial;
        this.direccion = direccion;
    }

    public Proveedor(String nit) {
        this.nit = nit;

    }
    public Proveedor() {
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
