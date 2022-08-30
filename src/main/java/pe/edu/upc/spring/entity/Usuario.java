package pe.edu.upc.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="Usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@Column(name = "nombreUsuario", nullable = false, length = 60, unique = true)
	@Size(min = 3, max = 9, message = "El usuario debe tener como minimo 3 caracteres y maximo 9.")
	@NotEmpty(message = "El usuario no puede estar vacío.")
	private String nombreUsuario;
	
	@Column(name = "contraseniaUsuario", nullable = false, length = 60, unique = true)
	@NotEmpty(message = "La contraseña no puede estar vacía.")
	@Pattern(regexp = "^((?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])){4,10}$", 
				message = "La contraseña debe contener al menos una letra minúscula, mayúscula, "
						+ "un carácter especial y un número")
	private String contraseniaUsuario;
	
	private Boolean enabled;
	

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Rol> roles;

	public Usuario() {
		super();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContraseniaUsuario() {
		return contraseniaUsuario;
	}

	public void setContraseniaUsuario(String contraseniaUsuario) {
		this.contraseniaUsuario = contraseniaUsuario;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}


	
}
