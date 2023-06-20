package com.MarketPlace.persistencia.entidad;


import jakarta.persistence.*;
@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_producto", nullable = false)
    private  Integer idProducto;
    @Column(nullable = false, length = 100)
    private String referencia;
    @Column(nullable = false)
    private Integer precio;
    @ManyToOne
    @JoinColumn(name = "nit_proveedor",referencedColumnName = "nit")
    private Proveedor proveedor;

    public Producto(String referencia, Integer precio, Proveedor proveedor) {
        this.referencia = referencia;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    public Producto() {
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
