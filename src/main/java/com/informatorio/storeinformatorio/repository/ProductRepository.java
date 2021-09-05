package com.informatorio.storeinformatorio.repository;

import com.informatorio.storeinformatorio.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContainingIgnoreCase(String keyword);
    List<Product> findByPublishedTrue();
    List<Product> findByPublishedFalse();

}
