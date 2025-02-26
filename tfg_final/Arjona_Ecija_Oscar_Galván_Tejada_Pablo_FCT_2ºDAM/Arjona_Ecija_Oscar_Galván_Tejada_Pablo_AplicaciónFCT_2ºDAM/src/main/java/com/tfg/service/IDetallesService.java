package com.tfg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tfg.model.Detalle_pedidos;

public interface IDetallesService {
	
	void guardar(Detalle_pedidos detalle_pedidos);
	List<Detalle_pedidos> buscarTodas();
	Detalle_pedidos buscarPorId(Integer idDetalle_pedidos);		
	void eliminar(Integer idDetalle_pedidos);
	Page<Detalle_pedidos> buscarTodas(Pageable page);
}
