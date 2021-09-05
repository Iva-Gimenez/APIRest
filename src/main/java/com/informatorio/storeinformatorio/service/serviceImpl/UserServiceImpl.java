package com.informatorio.storeinformatorio.service.serviceImpl;

import com.informatorio.storeinformatorio.entity.User;
import com.informatorio.storeinformatorio.repository.UserRepository;
import com.informatorio.storeinformatorio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;


import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    private User user;


    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User postUser(User user) {
        User userDB = userRepository.findByEmail(user.getEmail());
        if (userDB != null){
            return userDB;
        }
        user.setFechaAlta(LocalDate.now());
        user.setStatus("CREATED");
        return userRepository.save(user);
    }

    @Override
    public User putUser(Long id, User user) {
        User userDB = userRepository.findById(id).orElse(null);
        if (userDB != null){
            userDB.setName(user.getName());
            userDB.setLastName(user.getLastName());
            userDB.setPassword(user.getPassword());
            userDB.setCityName(user.getCityName());
            userDB.setStateName(user.getStateName());
            userDB.setCountry(user.getCountry());
            userRepository.save(userDB);
        }
        return userDB;
    }

    @Override
    public User deleteUser(Long id) {
        User userDB = userRepository.findById(id).orElse(null);
        if (userDB != null){
            userDB.setStatus("DELETED");
            userRepository.save(userDB);
        }
        return userDB;
    }

    @Override
    public User patchUser(Long id, Map<String, String> user) {
        User userDB = userRepository.findById(id).orElse(null);
        if (userDB != null){
            user.forEach((k, v) -> {
                Field field = ReflectionUtils.findField(User.class, k);
                field.setAccessible(true);
                ReflectionUtils.setField(field, userDB, v);
                userRepository.save(userDB);
            });

        }
        return userDB;
    }

    @Override
    public List<User> findAllUsersResistencia() {
        return userRepository.findByCityName("Resistencia");
    }

    @Override
    public List<User> findAllUsersByFechaAlta(LocalDate date) {
        return userRepository.findByFechaAltaAfter(date);
    }


}
