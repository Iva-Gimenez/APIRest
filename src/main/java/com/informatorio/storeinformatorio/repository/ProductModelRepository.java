package com.informatorio.storeinformatorio.repository;

import com.informatorio.storeinformatorio.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductModelRepository extends JpaRepository<ProductModel, Long> {
    ProductModel findByReferenceItemModel(Long id);
}
