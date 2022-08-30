package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.Cuenta;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Integer> {
	
	@Query("from Cuenta c where c.nombreCuenta like %:nombreCuenta")
	List<Cuenta> buscarNombreCuenta(@Param("nombreCuenta") String nombreCuenta);
	
}
