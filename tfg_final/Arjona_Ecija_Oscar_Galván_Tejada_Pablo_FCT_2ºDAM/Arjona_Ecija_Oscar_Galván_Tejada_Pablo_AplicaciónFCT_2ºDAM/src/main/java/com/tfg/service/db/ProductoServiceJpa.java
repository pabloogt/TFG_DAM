package com.tfg.service.db;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.model.Producto;
import com.tfg.repository.ProductoRepository;
import com.tfg.service.IProductosService;


@Service
@Primary
public class ProductoServiceJpa implements IProductosService {
	
	@Autowired
	private ProductoRepository productosRepo;

	public List<Producto> buscarTodas() {		
		return productosRepo.findAll();
	}

	public Producto buscarPorId(Integer idProductos) {
		Optional<Producto> optional = productosRepo.findById(idProductos);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public void guardar(Producto productos) {
		productosRepo.save(productos);
	}

	public void eliminar(Integer idProductos) {
		productosRepo.deleteById(idProductos);		
	}

	@Override
	public List<Producto> buscarByExample(Example<Producto> example) {
		return productosRepo.findAll(example);
	}

	@Override
	public Page<Producto> buscarTodas(Pageable page) {		
		return productosRepo.findAll(page);
	}


}

