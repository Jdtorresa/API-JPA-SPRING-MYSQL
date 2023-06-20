package com.MarketPlace.persistencia.repositorio;

import com.MarketPlace.persistencia.entidad.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
    @Modifying // JQPL
    @Query(value = "UPDATE Producto p SET p.precio = :precio, p.referencia = :referencia, p.proveedor.nit = :nit WHERE p.idProducto = :id")
    void actualizarProducto(@Param("precio") Integer precio, @Param("referencia") String referencia, @Param("nit") String nit, @Param("id") Integer id);

    List<Producto> findByPrecioBetweenOrderByPrecioDesc(int precioMin, int precioMax);
}
