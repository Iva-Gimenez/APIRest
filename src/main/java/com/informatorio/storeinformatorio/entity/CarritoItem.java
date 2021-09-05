package com.informatorio.storeinformatorio.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Entity
@Data
@Table(name = "tbl_carrito_item")
public class CarritoItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "item_carrito_id")
    @JsonIgnore
    private Carrito carrito;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "carrito_item_product_id")
    private Product product;

    @Transient
    private Double subTotal;

    //@Positive(message = "El stock debe ser mayor que cero")
    private Double quantity = 0.0;

    private Double  price;

    public Double getSubTotal(){
        if (this.price >0  && this.quantity > 0 ){
            return this.quantity * this.price;
        }else {
            return (double) 0;
        }
    }
    public CarritoItem(){
        this.quantity=(double) 0;
        this.price=(double) 0;

    }
    public void setQuantity(Double quantity){
        this.quantity = this.quantity + quantity;
    }




}
