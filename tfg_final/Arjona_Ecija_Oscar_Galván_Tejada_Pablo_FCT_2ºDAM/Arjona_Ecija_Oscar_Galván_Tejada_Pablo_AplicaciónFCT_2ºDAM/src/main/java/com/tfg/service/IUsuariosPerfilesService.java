package com.tfg.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tfg.model.UsuarioPerfil;

public interface IUsuariosPerfilesService {
	void guardar(UsuarioPerfil usuarioPerfil);
	List<UsuarioPerfil> buscarTodas();
	UsuarioPerfil buscarPorId(Integer idUsuarioPerfil);		
	void eliminar(Integer idUsuarioPerfil);
	Page<UsuarioPerfil> buscarTodas(Pageable page);	
}

