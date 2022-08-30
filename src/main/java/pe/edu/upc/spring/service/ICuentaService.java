package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.entity.Cuenta;

public interface ICuentaService {
	public boolean insertar(Cuenta cuenta);
	public List<Cuenta> listar();
	public void eliminar(int idCuenta);
	public boolean modificar (Cuenta cuenta);
	List<Cuenta> buscarNombreCuenta(String nombreCuenta);
	public Optional<Cuenta> findById(int idCuenta);
}