package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.entity.Usuario;
import pe.edu.upc.spring.repository.IUsuarioRepository;
import pe.edu.upc.spring.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private IUsuarioRepository dUsuario;
	
	@Override
	@Transactional
	public boolean saveUsuario(Usuario usuario) {
		Usuario objUsuario = dUsuario.save(usuario);
		if(objUsuario == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void deleteUsuario(int idUsuario) {
		dUsuario.deleteById(idUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findByName(String nombreUsuario) {
		return dUsuario.findByName(nombreUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(int idUsuario) {
		return dUsuario.findById(idUsuario);
	}

	@Override
	@Transactional
	public void insertarRol(String authority, int idUsuario) {
		dUsuario.insertarRol(authority, idUsuario);
	}

	@Override
	@Transactional
	public void deleteRol(int idUsuario) {
		dUsuario.deleteRol(idUsuario);
	}

	@Override
	@Transactional
	public void modUsuario(String nombreUsuario, String contraseniaUsuario, int idUsuario) {
		dUsuario.modUsuario(nombreUsuario, contraseniaUsuario, idUsuario);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> listar() {
		return dUsuario.findAll();
	}

}
