package com.tfg.service.db;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.model.Ubicacion;
import com.tfg.repository.UbicacionesRepository;
import com.tfg.service.IUbicacionesService;


@Service
@Primary
public class UbicacionesServiceJpa implements IUbicacionesService {
	
	@Autowired
	private UbicacionesRepository ubicacionRepo;

	public void guardar(Ubicacion ubicacion) {
		ubicacionRepo.save(ubicacion);
	}

	public List<Ubicacion> buscarTodas() {		
		return ubicacionRepo.findAll();
	}

	public Ubicacion buscarPorId(Integer idUbicacion) {
		Optional<Ubicacion> optional = ubicacionRepo.findById(idUbicacion);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;  
	}

	public void eliminar(Integer idUbicacion) {
		ubicacionRepo.deleteById(idUbicacion);
	}

	@Override
	public Page<Ubicacion> buscarTodas(Pageable page) {
		return ubicacionRepo.findAll(page);	
	}

}



