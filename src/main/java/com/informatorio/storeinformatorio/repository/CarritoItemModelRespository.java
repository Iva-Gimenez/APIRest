package com.informatorio.storeinformatorio.repository;

import com.informatorio.storeinformatorio.model.CarritoItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoItemModelRespository extends JpaRepository<CarritoItemModel, Long> {

    List<CarritoItemModel> findByIdCarrito(Long id);

}
