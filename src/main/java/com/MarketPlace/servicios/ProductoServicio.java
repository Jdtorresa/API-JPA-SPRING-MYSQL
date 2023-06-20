package com.MarketPlace.servicios;

import com.MarketPlace.persistencia.entidad.Producto;
import com.MarketPlace.persistencia.repositorio.ProductoCrudRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductoServicio {

    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    public ProductoServicio(ProductoCrudRepository productoCrudRepository) {
        this.productoCrudRepository = productoCrudRepository;
    }

    public List<Producto> listaProductos(){
        return (List<Producto>) productoCrudRepository.findAll();
    }
    public Optional<Producto> productoPorId(Integer id){
        return productoCrudRepository.findById(id);
    }
    public Producto insertarProducto(Producto producto){
        return productoCrudRepository.save(producto);
    }
    public void eliminarProducto(Integer id){
        productoCrudRepository.deleteById(id);
    }
    @Transactional
    public Producto actualizarProducto(Producto productoActualizado){
        productoCrudRepository.actualizarProducto(productoActualizado.getPrecio(), productoActualizado.getReferencia(), productoActualizado.getProveedor().getNit(), productoActualizado.getIdProducto());
        return productoActualizado;
    }
    public boolean existeProducto(Integer id){
        return productoCrudRepository.existsById(id);
    }

    public List<Producto> consultaProducto(int precioMin, int precioMax){
        return productoCrudRepository.findByPrecioBetweenOrderByPrecioDesc(precioMin, precioMax);

    }
}
