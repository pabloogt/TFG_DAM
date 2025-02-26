package com.tfg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tfg.model.Almacen;

public interface IAlmacenService {
	void guardar(Almacen admin);
	List<Almacen> buscarTodas();
	Almacen buscarPorId(Integer idAdmin);		
	void eliminar(Integer idAdmin);
	Page<Almacen> buscarTodas(Pageable page);	
}
