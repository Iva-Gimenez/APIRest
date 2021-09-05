package com.informatorio.storeinformatorio.service.serviceImpl;

import com.informatorio.storeinformatorio.entity.Orden;
import com.informatorio.storeinformatorio.entity.User;
import com.informatorio.storeinformatorio.repository.OrdenRepository;
import com.informatorio.storeinformatorio.repository.UserRepository;
import com.informatorio.storeinformatorio.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenServiceImpl implements OrdenService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdenRepository ordenRepository;

    @Override
    public Orden deleteOrden(Long idOrden) {
        Orden ordenDB = ordenRepository.findById(idOrden).orElse(null);
        if (ordenDB != null){
            ordenDB.setStatus("CANCELADO");
            ordenRepository.save(ordenDB);
        }
        return ordenDB;
    }

    @Override
    public Orden getOrdenById(Long idOrden) {

        return ordenRepository.findById(idOrden).orElse(null);
    }

    @Override
    public List<Orden> ordenList(Long idUsuario) {
        User userDB = userRepository.findById(idUsuario).get();
        return ordenRepository.findByUser(userDB);
    }
}
