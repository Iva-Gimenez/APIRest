package com.informatorio.storeinformatorio.repository;

import com.informatorio.storeinformatorio.entity.Orden;
import com.informatorio.storeinformatorio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Orden findByIdCarrito(Long id);
    List<Orden> findByUser(User user);
}
