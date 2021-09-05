package com.informatorio.storeinformatorio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.Valid;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class CarritoItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCarrito;


    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "carrito_item_product")
    private ProductModel productModel;


    private Double subTotal;

    private Double quantity;

    private Double  price;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity > 0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }




}
