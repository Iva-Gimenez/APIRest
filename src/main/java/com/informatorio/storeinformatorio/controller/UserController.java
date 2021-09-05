package com.informatorio.storeinformatorio.controller;

import com.informatorio.storeinformatorio.controller.exceptions.BadRequestException;
import com.informatorio.storeinformatorio.controller.exceptions.FormatError;
import com.informatorio.storeinformatorio.controller.exceptions.NotFoundException;
import com.informatorio.storeinformatorio.entity.User;
import com.informatorio.storeinformatorio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> postUser(@Valid @RequestBody User user,  BindingResult result){
        if (result.hasErrors()){
            throw new BadRequestException(FormatError.formatMessage(result));
        }
        return new ResponseEntity<>(userService.postUser(user), HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<?> putUser(@RequestParam(name = "id") Long id,
                                     @RequestBody @Valid User user){

        User userDB = userService.putUser(id, user);

        if (userDB == null){
            throw new NotFoundException("Usuario no encontrado");
        }
        return new ResponseEntity<>(userDB, HttpStatus.OK);
    }

    @PatchMapping
    // Modifica al usuario parcialmente
    public ResponseEntity<?> patchUser(@RequestParam(name = "id") Long id,
                                       @RequestBody Map<String, String> user){

        User userDB = userService.patchUser(id, user);

        if (userDB == null){
            throw new NotFoundException("Usuario no encontrado");
        }
        return new ResponseEntity<>(userDB, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam (name = "id") Long id){
        User userDeleted = userService.deleteUser(id);
        if (userDeleted == null){
            throw new NotFoundException("Usuario no encontrado");
        }
        return new ResponseEntity<>(userDeleted, HttpStatus.OK);
    }

    @GetMapping("/resistencia")
    public ResponseEntity<List<User>> findUsersCityName(){

        return new ResponseEntity<>(userService.findAllUsersResistencia(), HttpStatus.OK);
    }

    @GetMapping("/fecha")
    public ResponseEntity<?> findAllUsers(@RequestParam (name = "alta") String fecha){
        LocalDate date = LocalDate.parse(fecha);
        return new ResponseEntity<>(userService.findAllUsersByFechaAlta(date), HttpStatus.OK);
    }

}
