package com.informatorio.storeinformatorio.service;

import com.informatorio.storeinformatorio.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public interface UserService {
    public User getUser(Long id);
    public List<User> getAllUsers();
    public User postUser(User user);
    public User putUser(Long id, User user);
    public User deleteUser(Long id);
    public User patchUser(Long id, Map<String, String> user);
    public List<User> findAllUsersResistencia();
    public List<User> findAllUsersByFechaAlta(LocalDate date);
}
