package com.informatorio.storeinformatorio.repository;

import com.informatorio.storeinformatorio.entity.Carrito;
import com.informatorio.storeinformatorio.entity.CarritoItem;
import com.informatorio.storeinformatorio.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CarritoItemsRepository extends JpaRepository<CarritoItem, Long> {
    CarritoItem findByCarritoAndProduct(Carrito carrito, Product product);
}
