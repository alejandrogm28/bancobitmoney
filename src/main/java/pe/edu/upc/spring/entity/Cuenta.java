package pe.edu.upc.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "cuenta")
public class Cuenta implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCuenta;
	
	@NotBlank(message= "No puede quedar vacio")
	@Column(name="nombreCuenta", nullable = false, length = 50)
	private String nombreCuenta;
	
	@NotBlank(message= "No puede quedar vacio")
	@Column(name="descripcionCuenta", nullable = false, length = 500)
	private String descripcionCuenta;
	
	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public String getNombreCuenta() {
		return nombreCuenta;
	}

	public void setNombreCuenta(String nombreCuenta) {
		this.nombreCuenta = nombreCuenta;
	}

	public String getDescripcionCuenta() {
		return descripcionCuenta;
	}

	public void setDescripcionCuenta(String descripcionCuenta) {
		this.descripcionCuenta = descripcionCuenta;
	}
	
}
