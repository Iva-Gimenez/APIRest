package com.informatorio.storeinformatorio.repository;

import com.informatorio.storeinformatorio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findByCityName(String city);
    public User findByEmail(String email);
    public List<User> findByFechaAltaAfter(LocalDate alta);
}
