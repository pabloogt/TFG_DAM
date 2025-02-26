package com.tfg.service.db;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.model.UsuarioPerfil;
import com.tfg.repository.UsuariosPerfilesRepository;
import com.tfg.service.IUsuariosPerfilesService;


@Service
@Primary
public class UsuariosPerfilesServiceJpa implements IUsuariosPerfilesService {
	
	@Autowired
	private UsuariosPerfilesRepository usuarioPerfilRepo;

	public void guardar(UsuarioPerfil usuarioPerfil) {
		usuarioPerfilRepo.save(usuarioPerfil);
	}

	public List<UsuarioPerfil> buscarTodas() {		
		return usuarioPerfilRepo.findAll();
	}

	public UsuarioPerfil buscarPorId(Integer idUsuarioPerfil) {
		Optional<UsuarioPerfil> optional = usuarioPerfilRepo.findById(idUsuarioPerfil);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;  
	}

	public void eliminar(Integer idUsuarioPerfil) {
		usuarioPerfilRepo.deleteById(idUsuarioPerfil);
	}

	@Override
	public Page<UsuarioPerfil> buscarTodas(Pageable page) {
		return usuarioPerfilRepo.findAll(page);	
	}

}


