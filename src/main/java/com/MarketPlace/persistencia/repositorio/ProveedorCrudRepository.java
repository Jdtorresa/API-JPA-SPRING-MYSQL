package com.MarketPlace.persistencia.repositorio;

import com.MarketPlace.persistencia.entidad.Proveedor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProveedorCrudRepository extends CrudRepository<Proveedor, String> {
    @Modifying
    @Query(value = "UPDATE proveedores SET direccion = :direccion, razon_social = :razonSocial WHERE nit = :nit", nativeQuery = true)
    void actualizarProveedor(@Param("direccion") String direccion, @Param("razonSocial") String razonSocial, @Param("nit") String nit);

    @Query(value = "SELECT pr.* FROM proveedores pr LEFT JOIN productos p ON p.nit_proveedor = pr.nit  WHERE p.nit_proveedor IS NULL", nativeQuery = true)
    List<Proveedor> consultarProveedoresNativa();



}
