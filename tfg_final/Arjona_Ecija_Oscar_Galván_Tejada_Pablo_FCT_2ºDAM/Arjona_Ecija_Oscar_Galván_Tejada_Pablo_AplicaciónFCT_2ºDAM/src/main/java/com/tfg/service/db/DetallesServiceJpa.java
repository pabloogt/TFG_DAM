package com.tfg.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.model.Detalle_pedidos;
import com.tfg.repository.*;
import com.tfg.service.IDetallesService;

@Service
public class DetallesServiceJpa implements IDetallesService {
	
	 @Autowired
	    private DetallesRepository detallesRepo;

	    public void guardar(Detalle_pedidos Detalle_pedidos) {
	       detallesRepo.save(Detalle_pedidos);
	    }

	    public List<Detalle_pedidos> buscarTodas() {    
	       return detallesRepo.findAll();
	    }

	    public Detalle_pedidos buscarPorId(Integer idDetalle_pedidos) {
	       Optional<Detalle_pedidos> optional = detallesRepo.findById(idDetalle_pedidos);
	       if (optional.isPresent()) {
	          return optional.get();
	       }
	       return null;  
	    }

	    public void eliminar(Integer idDetalle_pedidos) {
	       detallesRepo.deleteById(idDetalle_pedidos);
	    }
	    
	    @Override
		public Page<Detalle_pedidos> buscarTodas(Pageable page) {
			return detallesRepo.findAll(page);	
		}
}
