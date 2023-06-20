package com.MarketPlace.controlador;
import com.MarketPlace.persistencia.entidad.Proveedor;
import com.MarketPlace.servicios.ProveedorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
public class ProveedorControlador {

    private ProveedorServicio proveedorServicio;
    @Autowired
    public ProveedorControlador(ProveedorServicio proveedorServicio) {
        this.proveedorServicio = proveedorServicio;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Proveedor>> listaProveedores() {
        return new ResponseEntity<>(proveedorServicio.listaProveedores(), HttpStatus.OK);
    }

    @GetMapping("/{nit}")
    public ResponseEntity<Proveedor> proveedorPorNit(@PathVariable String nit) {
        Optional<Proveedor> proveedor = proveedorServicio.proveedorPorNit(nit);
        if (proveedor.isPresent()) {
            return new ResponseEntity<>(proveedor.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/insertar")
    public ResponseEntity<Proveedor> insertarProveedor(@RequestBody Proveedor proveedor) {
        if (proveedorServicio.existeProveedor(proveedor.getNit())){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else{
            return new ResponseEntity<>(proveedorServicio.insertarProveedor(proveedor), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/eliminar/{nit}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable String nit){
        if(proveedorServicio.existeProveedor(nit)){
            proveedorServicio.eliminarProveedor(nit);
            return  new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Proveedor> actualizarProveedor(@RequestBody Proveedor proveedor){
        if(proveedorServicio.existeProveedor(proveedor.getNit())){
            return new ResponseEntity<>(proveedorServicio.actualizarProveedor(proveedor), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/consulta")
    public ResponseEntity<List<Proveedor>> consultaProveedorCantidadProductos(){
        return new ResponseEntity<>(proveedorServicio.consultarProveedoresNativa(), HttpStatus.OK);
    }

}