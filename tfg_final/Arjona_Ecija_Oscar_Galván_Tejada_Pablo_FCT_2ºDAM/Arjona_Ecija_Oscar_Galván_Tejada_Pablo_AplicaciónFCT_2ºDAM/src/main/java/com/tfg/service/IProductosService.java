package com.tfg.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tfg.model.Producto;


public interface IProductosService {
	List<Producto> buscarTodas();
	Producto buscarPorId(Integer idProductos);
	void guardar(Producto productos);
	void eliminar(Integer idProductos);
	List<Producto> buscarByExample(Example<Producto> example);
	Page<Producto>buscarTodas(Pageable page);
}
