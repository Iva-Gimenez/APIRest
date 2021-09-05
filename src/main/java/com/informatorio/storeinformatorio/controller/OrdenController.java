package com.informatorio.storeinformatorio.controller;

import com.informatorio.storeinformatorio.entity.Orden;
import com.informatorio.storeinformatorio.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/ordenes")
public class OrdenController {
    
    @Autowired
    private OrdenService ordenService;

    @GetMapping("/{idOrden}")
    public ResponseEntity<?> getOrdenId(@PathVariable Long idOrden){
        Orden ordenDB = ordenService.getOrdenById(idOrden);

        return new ResponseEntity<>(ordenDB, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getOrdenByUser (@RequestParam (name = "idUser") Long idUser){
        List<Orden> ordens = ordenService.ordenList(idUser);
        return new ResponseEntity<>(ordens, HttpStatus.OK);
    }

    @DeleteMapping("/{idOrden}")
    public ResponseEntity<?> deleteOrden(@PathVariable Long idOrden){
        Orden ordenDB = ordenService.deleteOrden(idOrden);

        return new ResponseEntity<>(ordenDB, HttpStatus.OK);
    }
}
