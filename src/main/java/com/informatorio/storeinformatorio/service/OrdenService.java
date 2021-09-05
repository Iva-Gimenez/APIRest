package com.informatorio.storeinformatorio.service;

import com.informatorio.storeinformatorio.entity.Orden;

import java.util.List;

public interface OrdenService {
    Orden deleteOrden(Long idOrden);
    Orden getOrdenById(Long idOrden);
    List<Orden> ordenList(Long idUsuario);
}
