package com.tfg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tfg.model.Ubicacion;

public interface IUbicacionesService {
	void guardar(Ubicacion ubicacion);
	List<Ubicacion> buscarTodas();
	Ubicacion buscarPorId(Integer idUbicacion);		
	void eliminar(Integer idUbicacion);
	Page<Ubicacion> buscarTodas(Pageable page);	
}

