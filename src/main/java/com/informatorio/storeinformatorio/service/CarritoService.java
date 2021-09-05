package com.informatorio.storeinformatorio.service;

import com.informatorio.storeinformatorio.entity.Carrito;
import com.informatorio.storeinformatorio.entity.CarritoItem;
import com.informatorio.storeinformatorio.entity.Orden;
import com.informatorio.storeinformatorio.entity.Product;
import com.informatorio.storeinformatorio.model.CarritoItemModel;
import com.informatorio.storeinformatorio.model.Obervaciones;
import com.informatorio.storeinformatorio.model.ProductModel;

import java.util.List;
import java.util.Map;

public interface CarritoService {
    List<Carrito> getAllCarrito();
    Carrito addCarritoItem(Long idUser, Long idProduct);
    Carrito deleteCarrito(Long idCarrito);
    Orden checkOut(Long idCarrito, Obervaciones observaciones);

}
