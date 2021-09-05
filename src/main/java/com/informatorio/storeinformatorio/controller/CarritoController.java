package com.informatorio.storeinformatorio.controller;

import com.informatorio.storeinformatorio.entity.CarritoItem;
import com.informatorio.storeinformatorio.model.Obervaciones;
import com.informatorio.storeinformatorio.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/carrito")
public class CarritoController {

    

    @Autowired
    private CarritoService carritoService;

    @GetMapping
    public ResponseEntity<?> getAllCarritos(){
        return ResponseEntity.ok(carritoService.getAllCarrito());
    }

    @PostMapping("{idUser}")
    public ResponseEntity<?> addItemCarrito(@PathVariable Long idUser,
                                            @RequestParam (name = "id_producto") Long idProducto){

        return new ResponseEntity<>(carritoService.addCarritoItem(idUser, idProducto), HttpStatus.OK);
    }
    @DeleteMapping("{idCarrito}")
    public ResponseEntity<?> putItemCarrito(@PathVariable Long idCarrito){
        return new ResponseEntity<>(carritoService.deleteCarrito(idCarrito), HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkoutCarrito(@RequestParam (name = "idCarrito") Long idCarrito,
                                             @RequestBody Obervaciones observaciones){

        return ResponseEntity.ok(carritoService.checkOut(idCarrito, observaciones));
    }


}
