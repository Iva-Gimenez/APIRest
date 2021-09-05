package com.informatorio.storeinformatorio.service.serviceImpl;

import com.informatorio.storeinformatorio.entity.Product;
import com.informatorio.storeinformatorio.repository.ProductRepository;
import com.informatorio.storeinformatorio.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        product.setFechaAlta(LocalDate.now());
        product.setStatus("CREATED");
        return productRepository.save(product);
    }

    @Override
    public Product putProduct(Long id, Product product) {
        Product productDB = productRepository.findById(id).orElse(null);

        if (null != productDB){
                productDB.setName(product.getName());
                productDB.setDescription(product.getDescription());
                productDB.setPrice(product.getPrice());
                productDB.setContent(product.getContent());
                productDB.setPublished(product.getPublished());
                productRepository.save(productDB);

        }
        return productDB;

    }

    @Override
    public Product patchProduct(Long id, Map<String, ?> product) {
        Product productDB = productRepository.findById(id).orElse(null);
        if (productDB != null){
            product.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(Product.class, k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, productDB, v);

                productRepository.save(productDB);

            });
        }
        return productDB;
    }

    @Override
    public Product deleteProduct(Long id) {
        Product productDB = productRepository.findById(id).orElse(null);
        if (productDB != null){
            productDB.setStatus("DELETED");
            productRepository.save(productDB);
        }
        return productDB;
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        List<Product> results = productRepository.findByNameContainingIgnoreCase(keyword);
        return results;
    }

    @Override
    public List<Product> getAllProductPublished(String bool) {
        String normalizedBool = bool.toLowerCase();
        List<Product> Published = new ArrayList<>();

        if ( normalizedBool.equals("false") ){
            Published = productRepository.findByPublishedFalse();
        }
        else if ( normalizedBool.equals("true") ){
            Published = productRepository.findByPublishedTrue();
        }
        return Published;
    }
}
