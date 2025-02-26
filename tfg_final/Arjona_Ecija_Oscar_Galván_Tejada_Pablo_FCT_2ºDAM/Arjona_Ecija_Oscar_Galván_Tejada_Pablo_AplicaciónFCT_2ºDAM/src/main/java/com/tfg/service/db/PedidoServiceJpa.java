package com.tfg.service.db;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tfg.model.Pedidos;
import com.tfg.model.Usuario;
import com.tfg.repository.*;
import com.tfg.service.IPedidosService;

@Service
public class PedidoServiceJpa implements IPedidosService{

	 @Autowired
	    private PedidosRepository PedidosRepo;

	    public void guardar(Pedidos Pedidos) {
	       PedidosRepo.save(Pedidos);
	    }

	    public List<Pedidos> buscarTodas() {    
	       return PedidosRepo.findAll();
	    }

	    public Pedidos buscarPorId(Integer idPedidos) {
	       Optional<Pedidos> optional = PedidosRepo.findById(idPedidos);
	       if (optional.isPresent()) {
	          return optional.get();
	       }
	       return null;  
	    }

	    public void eliminar(Integer idPedidos) {
	       PedidosRepo.deleteById(idPedidos);
	    }
	    
	    @Override
		public Page<Pedidos> buscarTodas(Pageable page) {
			return PedidosRepo.findAll(page);	
		}

		
		@Override
		public List<Pedidos> buscarPorEstado(String estadoPedidos) {
			return PedidosRepo.findByEstado(estadoPedidos);
		}

		@Override
		public int pendiente(int idPedidos) {
			int rows = PedidosRepo.pendiente(idPedidos);
			return rows;
		}

		@Override
		public int entregado(int idPedidos) {
			int rows = PedidosRepo.entregado(idPedidos);
			return rows;
		}
}
