package pe.edu.upc.spring.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.upc.spring.entity.Cuenta;
import pe.edu.upc.spring.service.ICuentaService;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {
	
	@Autowired
	private ICuentaService aService;
	
	@RequestMapping("/")
	public String irCuenta(Map<String , Object> model) {
		model.put("listCuentas", aService.listar());
		return "/Cuenta/listaCuentas";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("cuenta", new Cuenta());
		return "/Cuenta/cuenta";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute("cuenta") Cuenta objCuenta, BindingResult binRes, Model model) 
		throws ParseException {
		if(binRes.hasErrors())
			return "/Cuenta/cuenta";
		else {
			boolean flag = aService.insertar(objCuenta);
			if(flag)
				return "redirect:/cuenta/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error.");
				return "redirect:/cuenta/irRegistrar";
			}
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
		throws ParseException {
		Optional<Cuenta> objCuenta = aService.findById(id);
		if(objCuenta == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error.");
			return "redirect:/alumno/listar";
		} else {
			model.addAttribute("cuenta", objCuenta);
			model.addAttribute("listCuentas", aService.listar());
			return "/Cuenta/cuenta";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value = "id") Integer id) {
		try {
			if(id!=null && id>0) {
			aService.eliminar(id);
			//model.put("alumno", new Alumno());
			model.put("listCuentas", aService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "Ocurrió un error.");
			//model.put("alumno", new Alumno());
			model.put("listCuentas", aService.listar());
		}
		return "/Cuenta/listaCuentas";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listCuentas", aService.listar());
		model.put("cuenta", new Cuenta());
		return "/Cuenta/listaCuentas";
	}
	
	
}
