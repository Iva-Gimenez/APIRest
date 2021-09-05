package com.informatorio.storeinformatorio.repository;

import com.informatorio.storeinformatorio.entity.Carrito;
import com.informatorio.storeinformatorio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Carrito findByUserIdAndStatusTrue(User user);
    Carrito findByIdAndStatusTrue(Long id);
}
