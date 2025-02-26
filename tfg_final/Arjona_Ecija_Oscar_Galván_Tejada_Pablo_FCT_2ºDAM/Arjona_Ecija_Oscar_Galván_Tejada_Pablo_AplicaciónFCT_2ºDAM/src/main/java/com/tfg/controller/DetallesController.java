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

import com.tfg.model.Detalle_pedidos;
import com.tfg.service.IDetallesService;

@Controller
@RequestMapping(value="/detalles")
public class DetallesController {
	@Autowired
    //@Qualifier("Detalle_pedidosServiceJpa")
    private IDetallesService serviceDetalles;

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String mostrarIndex(Model model) {
        List<Detalle_pedidos> lista = serviceDetalles.buscarTodas();
        model.addAttribute("Detalle_pedidos", lista);
        return "detalles/listDetalle_pedidos";
    }

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Detalle_pedidos> lista = serviceDetalles.buscarTodas(page);
        model.addAttribute("Detalle_pedidos", lista);
        return "detalles/listDetalle_pedidos";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String crear(Detalle_pedidos Detalle_pedidos, Model model) {
    	model.addAttribute("Detalle_pedidos", new Detalle_pedidos());
        return "detalles/formDetalle_pedidos";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String guardar(Detalle_pedidos Detalle_pedidos, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()){       
            System.out.println("Existieron errores");
            return "detalles/formDetalle_pedidos";
        }   
        // Guadamos el objeto Detalle_pedidos en la bd
        serviceDetalles.guardar(Detalle_pedidos);
        attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");      
        return "redirect:/detalles/indexPaginate";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idDetalle_pedidos, Model model) {       
        Detalle_pedidos Detalle_pedidos = serviceDetalles.buscarPorId(idDetalle_pedidos);           
        model.addAttribute("Detalle_pedidos", Detalle_pedidos);
        return "detalles/formDetalle_pedidos";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idDetalle_pedidos, RedirectAttributes attributes) {      
        // Eliminamos la Detalle_pedidos.
        serviceDetalles.eliminar(idDetalle_pedidos);           
        attributes.addFlashAttribute("msg", "La categoría fue eliminada!.");
        return "redirect:/detalles/indexPaginate";
    }
}
