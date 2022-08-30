package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.spring.entity.Usuario;
import pe.edu.upc.spring.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@RequestMapping("/")
	public String irUsuario(Map<String, Object> model) {
		model.put("listaUsuarios", usuarioService.listar());
		return "listaUsuarios";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "usuario";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Usuario objUsuario, BindingResult binRes, Model model) 
		throws ParseException {
		if(binRes.hasErrors())
			return "usuario";
		else {
			boolean flag = usuarioService.saveUsuario(objUsuario);
			
			if(flag)
				return "redirect:/usuario/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/usuario/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Usuario> objUsuario = usuarioService.findById(id);
		if(objUsuario == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/usuario/listar";
		} else {
			model.addAttribute("usuario", objUsuario);
			return "usuario";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id != null && id > 0) {
				usuarioService.deleteUsuario(id);
				model.put("listaUsuarios", usuarioService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "No se pudo eliminar al usuario.");
			model.put("listaUsuarios", usuarioService.listar());
		}
		return "listaUsuarios";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaUsuarios", usuarioService.listar());
		return "listaUsuarios";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Usuario usuario)
		throws ParseException{
		List<Usuario> listaUsuarios;
		
		listaUsuarios = usuarioService.findByName(usuario.getNombreUsuario());
		
		if(listaUsuarios.isEmpty()) {
			model.put("mensaje", "No se encontro al usuario.");
		}
		
		model.put("listaUsuarios", listaUsuarios);
		return "listaUsuarios";
	}
}
