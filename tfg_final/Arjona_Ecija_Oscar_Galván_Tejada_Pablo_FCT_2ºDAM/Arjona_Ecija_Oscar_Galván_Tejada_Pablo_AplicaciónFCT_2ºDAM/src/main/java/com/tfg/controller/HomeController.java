package com.tfg.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tfg.model.Detalle_pedidos;
import com.tfg.model.Pedidos;
import com.tfg.model.Perfil;
import com.tfg.model.Producto;
import com.tfg.model.Usuario;
import com.tfg.service.IUsuariosService;
import com.tfg.service.IDetallesService;
import com.tfg.service.IPedidosService;
import com.tfg.service.IProductosService;

@Controller
public class HomeController {
	

	@Autowired
	private IPedidosService servicePedidos;
	
	@Autowired
	private IDetallesService serviceDetalles;
	
	@Autowired
	private IProductosService serviceProductos;
	
	// Inyectamos una instancia desde nuestro ApplicationContext

    @Autowired
   	private IUsuariosService serviceUsuarios;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
  
	@GetMapping("/")
	public String mostrarHome() {
		return "home";
	}
	
	@GetMapping("/compraConfirmada")
	public String mostrarCompra(Model model) {
		return "compraConfirmada";
	}
	
	@GetMapping("/menu")
	public String mostrarHome(Model model) {
		return "menu";
	}
	
	@GetMapping("/tienda")
	public String mostrarTabla(Model model) {
		List<Producto> lista = serviceProductos.buscarTodas();
		model.addAttribute("productos", lista);
		
		return "tienda";
	}
	
	/**
	 * Método que esta mapeado al botón Ingresar en el menú
	 * @param authentication
	 * @param session
	 * @return
	 */
	@GetMapping("/index")
	public String mostrarIndex(Authentication authentication, HttpSession session) {		
		
		// Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
		String username = authentication.getName();		
		
		for(GrantedAuthority rol: authentication.getAuthorities()) {
			System.out.println("ROL: " + rol.getAuthority());
		}
		
		if (session.getAttribute("usuario") == null){
			Usuario usuario = serviceUsuarios.buscarPorUsername(username);	
			//System.out.println("Usuario: " + usuario);
			session.setAttribute("usuario", usuario);
		}
		
		return "redirect:/";
	}
	
	/**
	 * Método que muestra el formulario para que se registren nuevos usuarios.
	 * @param usuario
	 * @return
	 */
	@GetMapping("/signup")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	/**
	 * Método que guarda en la base de datos el usuario registrado
	 * @param usuario
	 * @param attributes
	 * @return
	 */
	@PostMapping("/signup")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		// Recuperamos el password en texto plano
		String pwdPlano = usuario.getPassword();
		// Encriptamos el pwd BCryptPasswordEncoder
		String pwdEncriptado = passwordEncoder.encode(pwdPlano); 
		// Hacemos un set al atributo password (ya viene encriptado)
		usuario.setPassword(pwdEncriptado);	
		usuario.setEstatus(1); // Activado por defecto
		usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor
		
		// Creamos el Perfil que le asignaremos al usuario nuevo
		Perfil perfil = new Perfil();
		perfil.setId(3); // Perfil USUARIO
		usuario.agregar(perfil);
		
		/**
		 * Guardamos el usuario en la base de datos. El Perfil se guarda automaticamente
		 */
		serviceUsuarios.guardar(usuario);
				
		attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");
		
		return "redirect:/login";
	}
	
	/**
	 * Método para realizar búsquedas desde el formulario de búsqueda del HomePage
	 * @param vacante
	 * @param model
	 * @return
	 */
	
	/**
	 * Metodo que muestra la vista de la pagina de Acerca
	 * @return
	 */
	@GetMapping("/about")
	public String mostrarAcerca() {			
		return "acerca";
	}
	
	/**
	 * Método que muestra el formulario de login personalizado.
	 * @return
	 */
	@GetMapping("/login")
	public String mostrarLogin() {
		return "formLogin";
	}
	
	/**
	 * Método personalizado para cerrar la sesión del usuario
	 * @param request
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
		logoutHandler.logout(request, null, null);
		return "redirect:/";
	}
	
	/**
     * Utileria para encriptar texto con el algorito BCrypt
     * @param texto
     * @return
     */
    @GetMapping("/bcrypt/{texto}")
    @ResponseBody
   	public String encriptar(@PathVariable("texto") String texto) {    	
   		return texto + " Encriptado en Bcrypt: " + passwordEncoder.encode(texto);
   	}
	
	/**
	 * Metodo que agrega al modelo datos genéricos para todo el controlador
	 * @param model
	 */

	/**
	 * InitBinder para Strings si los detecta vacios en el Data Binding los settea a NULL
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	@GetMapping("/hacerCompra")
    public String hacerCompra(@RequestParam("productoIds") List<Integer> productoIds,
                              @RequestParam("importe") Double importe,
                              @RequestParam("totalProductos") Integer total,
                              @RequestParam("nombreUsuario")String nombre,
                              Model model) {
        try {
            // Crear un nuevo pedido
            Pedidos pedidos = new Pedidos();
            pedidos.setFecha_pedido(new Date());
            pedidos.setFecha_entrega(new Date()); 
            pedidos.setEstado("pendiente");
            pedidos.setImporte(importe);
            pedidos.setCantidad_productos(total);

            Usuario username = serviceUsuarios.buscarPorUsername(nombre); 
            pedidos.setUsuario(username);

            // Guardar el pedido para obtener su ID
            servicePedidos.guardar(pedidos);

            for (Integer productoId : productoIds) {
                Producto producto = serviceProductos.buscarPorId(productoId);
                Detalle_pedidos detalle = new Detalle_pedidos();
                detalle.setProducto(producto.getNombre());
                detalle.setCantidad(1);  
                detalle.setPrecio(producto.getPrecio());
                detalle.setPedido(pedidos);
                serviceDetalles.guardar(detalle);

                producto.setCantidad_disponible(producto.getCantidad_disponible() - 1);
                serviceProductos.guardar(producto);
            }

            model.addAttribute("mensaje", "Compra realizada con éxito");
            return "compraConfirmada";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("mensaje", "Hubo un error al procesar la compra. Por favor, inténtelo de nuevo.");
            return "error";
        }
    }
	@InitBinder
	public void initBinder2(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
