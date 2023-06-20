package com.MarketPlace.controlador;

import com.MarketPlace.persistencia.entidad.Producto;
import com.MarketPlace.persistencia.entidad.Proveedor;
import com.MarketPlace.servicios.ProductoServicio;
import com.MarketPlace.servicios.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoControlador {
    private ProductoServicio productoServicio;
    private ProveedorServicio proveedorServicio;
    @Autowired
    public ProductoControlador(ProductoServicio productoServicio, ProveedorServicio proveedorServicio) {
        this.productoServicio = productoServicio;
        this.proveedorServicio = proveedorServicio;
    }


    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> listarProductos(){
        return new ResponseEntity<>(productoServicio.listaProductos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Producto> productoPorId(@PathVariable Integer id){
        if(productoServicio.existeProducto(id)){
            return new ResponseEntity<>(productoServicio.productoPorId(id).get(),HttpStatus.OK );
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/insertar")
    public ResponseEntity<Producto> insertarProducto(@RequestBody Producto producto){
        if(proveedorServicio.existeProveedor(producto.getProveedor().getNit())){
            Optional<Proveedor> proveedor = proveedorServicio.proveedorPorNit(producto.getProveedor().getNit());
            producto.setProveedor(proveedor.get());
            return new ResponseEntity<>(productoServicio.insertarProducto(producto), HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public  ResponseEntity<Void> eliminarProducto(@PathVariable Integer id){
        if (productoServicio.existeProducto(id)){
            productoServicio.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Producto> actualizarProducto(@RequestBody Producto producto){
        if(productoServicio.existeProducto(producto.getIdProducto())){
            Optional<Proveedor> proveedor=proveedorServicio.proveedorPorNit(producto.getProveedor().getNit());
            if(proveedor.isPresent()){
                producto.setProveedor(proveedor.get());
                return new ResponseEntity<>(productoServicio.actualizarProducto(producto), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/consulta")
    public ResponseEntity<List<Producto>> consultaProducto(@RequestParam int precioMin, @RequestParam int precioMax){
        return new ResponseEntity<>(productoServicio.consultaProducto(precioMin,precioMax), HttpStatus.OK);
    }


}
