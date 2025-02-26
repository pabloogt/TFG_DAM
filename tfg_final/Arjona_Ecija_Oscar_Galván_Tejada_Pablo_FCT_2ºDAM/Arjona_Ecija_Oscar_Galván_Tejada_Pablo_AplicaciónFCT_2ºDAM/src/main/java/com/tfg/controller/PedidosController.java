package com.tfg.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfg.model.Pedidos;
import com.tfg.service.IPedidosService;

@Controller
@RequestMapping(value="/pedidos")
public class PedidosController {
	@Autowired
    //@Qualifier("pedidosServiceJpa")
    private IPedidosService servicePedidos;

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String mostrarIndex(Model model) {
        List<Pedidos> lista = servicePedidos.buscarTodas();
        model.addAttribute("pedidos", lista);
        return "pedidos/listPedidos";
    }

    @GetMapping("/indexPaginate")
    public String mostrarIndexPaginado(Model model, Pageable page) {
        Page<Pedidos> lista = servicePedidos.buscarTodas(page);
        model.addAttribute("pedidos", lista);
        return "pedidos/listPedidos";
    }

    @RequestMapping(value="/create", method=RequestMethod.GET)
    public String crear(Pedidos pedidos) {
        return "pedidos/formPedidos";
    }

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String guardar(Pedidos pedidos, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()){       
            System.out.println("Existieron errores");
            return "pedidos/formPedidos";
        }   
        // Guadamos el objeto pedidos en la bd
        servicePedidos.guardar(pedidos);
        attributes.addFlashAttribute("msg", "Los datos de la categoría fueron guardados!");      
        return "redirect:/pedidos/indexPaginate";
    }

    @GetMapping("/edit/{id}")
    public String editar(@PathVariable("id") int idpedidos, Model model) {       
        Pedidos pedidos = servicePedidos.buscarPorId(idpedidos);           
        model.addAttribute("pedidos", pedidos);
        return "pedidos/formPedidos";
    }

    @GetMapping("/delete/{id}")
    public String eliminar(@PathVariable("id") int idpedidos, RedirectAttributes attributes) {      
        // Eliminamos la pedidos.
        servicePedidos.eliminar(idpedidos);           
        attributes.addFlashAttribute("msg", "La categoría fue eliminada!.");
        return "redirect:/pedidos/indexPaginate";
    }
    @InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
