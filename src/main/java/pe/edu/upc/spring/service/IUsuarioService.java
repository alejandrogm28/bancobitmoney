package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.entity.Usuario;

public interface IUsuarioService {
	public boolean saveUsuario(Usuario usuario);
	public void deleteUsuario(int idUsuario);
	public List<Usuario> findByName(String nombreUsuario);
	public Optional<Usuario> findById(int idUsuario);
	public void insertarRol(String authority, int idUsuario);
	public void deleteRol(int idUsuario);
	public void modUsuario(String nombreUsuario, String contraseniaUsuario, int idUsuario);
	public List<Usuario> listar();
}
