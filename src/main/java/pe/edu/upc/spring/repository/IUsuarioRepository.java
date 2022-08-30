package pe.edu.upc.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.spring.entity.Usuario;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query("from Usuario u where LOWER(u.nombreUsuario) like LOWER(concat('%',:nombreUsuario,'%')) order by u.idUsuario ASC")
	public List<Usuario> findByName(@Param("nombreUsuario") String nombreUsuario);
	
	@Modifying
	@Query(value = "insert into authorities (authority, idUsuario) VALUES (:authority, :idUsuario)", nativeQuery = true)
	public void insertarRol(@Param("authority") String authority, @Param("idUsuario") int idUsuario);
	
	@Modifying
	@Query(value = "delete from authorities where idUsuario = :idUsuario", nativeQuery = true)
	public void deleteRol(@Param("idUsuario") int idUsuario);
	
	@Modifying
	@Query(value = "update Usuario u set u.contraseniaUsuario = :contraseniaUsuario, u.nombreUsuario = :nombreUsuario where u.idUsuario = :idUsuario", nativeQuery = true)
	public void modUsuario(@Param("nombreUsuario") String nombreUsuario, 
			@Param("contraseniaUsuario") String contraseniaUsuario, 
			@Param("idUsuario") int idUsuario);
}
