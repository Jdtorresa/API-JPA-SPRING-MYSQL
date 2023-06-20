package com.MarketPlace.servicios;

import com.MarketPlace.persistencia.entidad.Proveedor;
import com.MarketPlace.persistencia.repositorio.ProveedorCrudRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServicio {

    private ProveedorCrudRepository proveedorCrudRepository;
    @Autowired
    public ProveedorServicio(ProveedorCrudRepository proveedorCrudRepository) {
        this.proveedorCrudRepository = proveedorCrudRepository;
    }

    public List<Proveedor> listaProveedores() {
        return (List<Proveedor>) proveedorCrudRepository.findAll();
    }

    public Optional<Proveedor> proveedorPorNit(String nit) {
        return proveedorCrudRepository.findById(nit);
    }

    public Proveedor insertarProveedor(Proveedor proveedor) {
        return proveedorCrudRepository.save(proveedor);
    }
    public void eliminarProveedor(String nit){
        proveedorCrudRepository.deleteById(nit);
    }
    @Transactional
    public Proveedor actualizarProveedor(Proveedor proveedorActualizado){
        proveedorCrudRepository.actualizarProveedor(proveedorActualizado.getDireccion(), proveedorActualizado.getRazonSocial(), proveedorActualizado.getNit());
        return proveedorActualizado;
    }
    public boolean existeProveedor(String nit){
        return proveedorCrudRepository.existsById(nit);
    }

    public List<Proveedor> consultarProveedoresNativa(){
        return proveedorCrudRepository.consultarProveedoresNativa();
    }

}
