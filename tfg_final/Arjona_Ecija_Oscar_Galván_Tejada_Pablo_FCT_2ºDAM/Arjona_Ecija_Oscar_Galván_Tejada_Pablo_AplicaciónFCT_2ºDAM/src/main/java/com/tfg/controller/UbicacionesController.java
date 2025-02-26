package com.tfg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.tfg.model.Ubicacion;
import com.tfg.service.IUbicacionesService;


@Controller
@RequestMapping(value="/ubicaciones")
public class UbicacionesController {
	
	@Autowired
	//@Qualifier("ubicacionesServiceJpa")
   	private IUbicacionesService serviceUbicacion;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Ubicacion> lista = serviceUbicacion.buscarTodas();
    	model.addAttribute("ubicaciones", lista);
		return "ubicaciones/listUbicaciones";		
	}
	
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Ubicacion> lista = serviceUbicacion.buscarTodas(page);
		model.addAttribute("ubicaciones", lista);
		return "ubicaciones/listUbicaciones";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Ubicacion ubicacion) {
		return "ubicaciones/formUbicaciones";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Ubicacion ubicacion, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores");
			return "ubicaciones/formUbicaciones";
		}	
		
		// Guadamos el objeto ubicacion en la bd
		serviceUbicacion.guardar(ubicacion);
		attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");		
		return "redirect:/ubicaciones/indexPaginate";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idUbicacion, Model model) {		
		Ubicacion ubicacion = serviceUbicacion.buscarPorId(idUbicacion);			
		model.addAttribute("ubicacion", ubicacion);
		return "ubicaciones/formUbicaciones";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUbicacion, RedirectAttributes attributes) {		
		// Eliminamos la ubicacion.
		serviceUbicacion.eliminar(idUbicacion);			
		attributes.addFlashAttribute("msg", "La categoría fue eliminada!.");
		return "redirect:/ubicaciones/indexPaginate";
	}
	
}

