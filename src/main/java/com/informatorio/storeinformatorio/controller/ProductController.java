package com.informatorio.storeinformatorio.controller;

import com.informatorio.storeinformatorio.controller.exceptions.BadRequestException;
import com.informatorio.storeinformatorio.controller.exceptions.FormatError;
import com.informatorio.storeinformatorio.entity.Product;
import com.informatorio.storeinformatorio.service.ProductService;
import javassist.compiler.ast.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

   
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(@RequestParam (required = false, name = "published") String bool,
                                            @RequestParam (required = false, name = "title") String keyword) {

        if ( bool != null ){
            return new ResponseEntity<>(productService.getAllProductPublished(bool), HttpStatus.OK);
        }
        else if ( keyword != null){
            return new ResponseEntity<>(productService.findByKeyword(keyword), HttpStatus.OK);
        }
        return new ResponseEntity<>( productService.getAllProducts(), HttpStatus.OK );
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@Valid @RequestBody Product product, BindingResult result){
        if (result.hasErrors()){
            throw new BadRequestException(FormatError.formatMessage(result));
        }
        return new ResponseEntity<>( productService.createProduct(product), HttpStatus.CREATED );
    }

    @PutMapping
    public ResponseEntity<?> putProduct(@RequestParam(name = "id") Long id,
                                     @RequestBody @Valid Product product){

        Product productDB = productService.putProduct(id, product);

        return (productDB != null) ?
                ResponseEntity.ok(productDB) : ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<?> patchProduct(@RequestParam (name = "id") Long id,
                                          @RequestBody Map<String, ?> product){
        Product productDB = productService.patchProduct(id, product);
        return (productDB != null) ?
                ResponseEntity.ok(productDB) : ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteProduct(@RequestParam (name = "id") Long id){
        Product productDeleted = productService.deleteProduct(id);
        return (productDeleted != null) ?
                new ResponseEntity<>(HttpStatus.OK) : ResponseEntity.notFound().build();
    }

}
