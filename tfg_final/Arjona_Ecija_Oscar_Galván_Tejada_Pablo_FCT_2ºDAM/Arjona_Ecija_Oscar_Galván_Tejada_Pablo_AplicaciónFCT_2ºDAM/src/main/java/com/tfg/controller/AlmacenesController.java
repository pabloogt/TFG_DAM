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
import com.tfg.model.Almacen;
import com.tfg.service.IAlmacenService;


@Controller
@RequestMapping(value="/almacenes")
public class AlmacenesController {
	
	@Autowired
	//@Qualifier("almacenesServiceJpa")
   	private IAlmacenService serviceAlmacen;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<Almacen> lista = serviceAlmacen.buscarTodas();
    	model.addAttribute("almacenes", lista);
		return "almacenes/listAlmacenes";		
	}
	
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Almacen> lista = serviceAlmacen.buscarTodas(page);
		model.addAttribute("almacenes", lista);
		return "almacenes/listAlmacenes";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(Almacen almacen) {
		return "almacenes/formAlmacenes";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(Almacen almacen, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores");
			return "almacenes/formAlmacenes";
		}	
		
		// Guadamos el objeto almacen en la bd
		serviceAlmacen.guardar(almacen);
		attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");		
		return "redirect:/almacenes/indexPaginate";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idAlmacen, Model model) {		
		Almacen almacen = serviceAlmacen.buscarPorId(idAlmacen);			
		model.addAttribute("almacen", almacen);
		return "almacenes/formAlmacenes";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idAlmacen, RedirectAttributes attributes) {		
		// Eliminamos la almacen.
		serviceAlmacen.eliminar(idAlmacen);			
		attributes.addFlashAttribute("msg", "La categoría fue eliminada!.");
		return "redirect:/almacenes/indexPaginate";
	}
	
}

