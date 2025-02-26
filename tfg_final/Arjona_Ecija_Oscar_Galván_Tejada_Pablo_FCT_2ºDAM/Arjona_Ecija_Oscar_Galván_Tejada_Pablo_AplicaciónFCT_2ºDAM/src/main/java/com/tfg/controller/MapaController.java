package com.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfg.model.Pedidos;
import com.tfg.model.Ubicacion;
import com.tfg.service.IPedidosService;
import com.tfg.service.IUbicacionesService;

import java.util.List;

@Controller
public class MapaController {

	@Autowired
	//@Qualifier("ubicacionesServiceJpa")
   	private IUbicacionesService serviceUbicacion;
	@Autowired
    //@Qualifier("pedidosServiceJpa")
    private IPedidosService servicePedidos;
	
    @GetMapping("/repartidor")
    public String getMap(Model model) {
		List<Ubicacion> lista = serviceUbicacion.buscarTodas();
    	model.addAttribute("ubicaciones", lista);
        return "repartidores/repartidor";
    }
    @RequestMapping(value="repartidor/index", method=RequestMethod.GET)
    public String mostrarIndex(Model model) {
        List<Pedidos> lista = servicePedidos.buscarPorEstado("pendiente");
        model.addAttribute("pedidos", lista);
        return "repartidores/PedidosRe";
    }

    @GetMapping("/repartidor/entregado/{id}")
    public String editar(@PathVariable("id") int idpedidos, Model model, RedirectAttributes attributes) {       
        Pedidos pedidos = servicePedidos.buscarPorId(idpedidos);     
        pedidos.setEstado("entregado");
        servicePedidos.guardar(pedidos);
        attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");
        return "redirect:/repartidor/index";
    }
}