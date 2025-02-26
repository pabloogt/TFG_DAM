package com.tfg.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfg.service.IProductosService;
import com.tfg.util.Utileria;
import com.tfg.model.Producto;
import com.tfg.service.IAlmacenService;


@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Value("${empleosapp.ruta.imagenes}")
	private String ruta;
	
	@Autowired
	private IProductosService serviceProductos;
	
	@Autowired
	//@Qualifier("categoriasServiceJpa")
	private IAlmacenService serviceAlmacen; 
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Producto> lista = serviceProductos.buscarTodas();
    	model.addAttribute("productos", lista);
		return "productos/listProductos";
	}
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Producto>lista = serviceProductos.buscarTodas(page);
		model.addAttribute("productos", lista);
		return "productos/listProductos";
	}
	
	@GetMapping("/create")
	public String crear(Model model) {
	    model.addAttribute("producto", new Producto()); // Agregar el objeto producto al modelo
	    return "productos/formProductos";
	}
	@PostMapping("/save")
	public String guardar(@Valid Producto producto, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart, Model model) {
	    
	    if (result.hasErrors()) {
	        for (ObjectError error : result.getAllErrors()) {
	            System.out.println("Ocurrió un error: " + error.getDefaultMessage());
	        }
	        model.addAttribute("producto", producto); // Agregar el objeto producto al modelo
	        model.addAttribute("almacenes", serviceAlmacen.buscarTodas()); // También asegúrate de agregar los almacenes al modelo si los necesitas en el formulario
	        return "productos/formProductos";
	    }
		
		if (!multiPart.isEmpty()) {
			//String ruta = "/empleos/img-productos/"; // Linux/MAC
			//String ruta = "c:/empleos/img-productos/"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){ // La imagen si se subio
				// Procesamos la variable nombreImagen
				producto.setImagen(nombreImagen);
			}
		}
		
		serviceProductos.guardar(producto);
		attributes.addFlashAttribute("msg", "Registro Guardado");		
		System.out.println("producto: " + producto);		
		return "redirect:/productos/indexPaginate"; 
	}
		
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idProductos, RedirectAttributes attributes) {
		System.out.println("Borrando producto con id: " + idProductos);
		serviceProductos.eliminar(idProductos);
		attributes.addFlashAttribute("msg","La producto fue eliminada!");
		return "redirect:/productos/index";
	}
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int idProductos, Model model) {
		Producto producto = serviceProductos.buscarPorId(idProductos);
		model.addAttribute("producto", producto);
		return "productos/formProductos";
	}
	
	
	@GetMapping("/view/{id}")
	public String verDetalle(@PathVariable("id") int idProductos, Model model) {		
		Producto producto = serviceProductos.buscarPorId(idProductos);	
		System.out.println("producto: " + producto);
		model.addAttribute("producto", producto);
		
		// Buscar los detalles de la producto en la BD...		
		return "detalle";
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("almacenes", serviceAlmacen.buscarTodas() );
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}

