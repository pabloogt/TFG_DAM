package com.tfg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tfg.model.Pedidos;

public interface IPedidosService {

	void guardar(Pedidos pedidos);
	List<Pedidos> buscarTodas();
	Pedidos buscarPorId(Integer idPedidos);
	List<Pedidos> buscarPorEstado(String estadoPedidos);		
	void eliminar(Integer idPedidos);
	Page<Pedidos> buscarTodas(Pageable page);
	int pendiente(int idPedidos);
	int entregado(int idPedidos);

}
