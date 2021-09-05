package com.informatorio.storeinformatorio.service;


import com.informatorio.storeinformatorio.entity.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product putProduct(Long id, Product product);

    Product patchProduct(Long id, Map<String, ?> product);

    Product deleteProduct(Long id);

    List<Product> findByKeyword(String keyword);

    List<Product> getAllProductPublished(String bool);
}
