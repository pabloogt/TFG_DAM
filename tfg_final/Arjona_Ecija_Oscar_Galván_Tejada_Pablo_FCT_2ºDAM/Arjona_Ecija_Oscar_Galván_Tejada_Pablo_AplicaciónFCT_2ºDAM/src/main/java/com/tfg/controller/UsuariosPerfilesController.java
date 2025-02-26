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

import com.tfg.model.UsuarioPerfil;
import com.tfg.service.IUsuariosPerfilesService;



@Controller
@RequestMapping(value="/usuarioPerfil")
public class UsuariosPerfilesController {
	
	@Autowired
	//@Qualifier("usuarioPerfilServiceJpa")
   	private IUsuariosPerfilesService serviceUsuarioPerfil;
	
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		List<UsuarioPerfil> lista = serviceUsuarioPerfil.buscarTodas();
    	model.addAttribute("usuarioPerfil", lista);
		return "usuarioPerfil/listUsuariosPerfiles";		
	}
	
	@GetMapping("/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<UsuarioPerfil> lista = serviceUsuarioPerfil.buscarTodas(page);
		model.addAttribute("usuarioPerfil", lista);
		return "usuarioPerfil/listUsuariosPerfiles";
	}
	
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String crear(UsuarioPerfil usuarioPerfil) {
		return "usuarioPerfil/formUsuariosPerfiles";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String guardar(UsuarioPerfil usuarioPerfil, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()){		
			System.out.println("Existieron errores");
			return "usuarioPerfil/formUsuariosPerfiles";
		}	
		
		// Guadamos el objeto usuarioPerfil en la bd
		serviceUsuarioPerfil.guardar(usuarioPerfil);
		attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");		
		return "redirect:/usuarioPerfil/indexPaginate";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idUsuarioPerfil, Model model) {		
		UsuarioPerfil usuarioPerfil = serviceUsuarioPerfil.buscarPorId(idUsuarioPerfil);			
		model.addAttribute("usuarioPerfil", usuarioPerfil);
		return "usuarioPerfil/formUsuariosPerfiles";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuarioPerfil, RedirectAttributes attributes) {		
		// Eliminamos la usuarioPerfil.
		serviceUsuarioPerfil.eliminar(idUsuarioPerfil);			
		attributes.addFlashAttribute("msg", "La categoría fue eliminada!.");
		return "redirect:/usuarioPerfil/indexPaginate";
	}
	
}

