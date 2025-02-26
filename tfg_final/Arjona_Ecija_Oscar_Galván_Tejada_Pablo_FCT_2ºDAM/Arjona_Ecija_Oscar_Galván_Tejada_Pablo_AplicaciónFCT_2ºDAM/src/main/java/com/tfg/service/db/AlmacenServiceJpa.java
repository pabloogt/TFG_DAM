package com.tfg.service.db;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.model.Almacen;
import com.tfg.repository.AlmacenRepository;
import com.tfg.service.IAlmacenService;


@Service
@Primary
public class AlmacenServiceJpa implements IAlmacenService {
	
	@Autowired
	private AlmacenRepository almacenRepo;

	public void guardar(Almacen almacen) {
		almacenRepo.save(almacen);
	}

	public List<Almacen> buscarTodas() {		
		return almacenRepo.findAll();
	}

	public Almacen buscarPorId(Integer idAlmacen) {
		Optional<Almacen> optional = almacenRepo.findById(idAlmacen);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;  
	}

	public void eliminar(Integer idAlmacen) {
		almacenRepo.deleteById(idAlmacen);
	}

	@Override
	public Page<Almacen> buscarTodas(Pageable page) {
		return almacenRepo.findAll(page);	
	}

}


