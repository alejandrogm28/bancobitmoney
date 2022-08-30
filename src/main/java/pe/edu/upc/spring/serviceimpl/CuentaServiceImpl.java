package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.spring.entity.Cuenta;
import pe.edu.upc.spring.repository.ICuentaRepository;
import pe.edu.upc.spring.service.ICuentaService;


@Service
public class CuentaServiceImpl implements ICuentaService {
	@Autowired
	private ICuentaRepository mD;

	@Override
	@Transactional
	public boolean insertar(Cuenta cuenta) {
		Cuenta objCat = mD.save(cuenta);
		if (objCat == null)
			return false;
		else
			return true;	
	}

	@Override
	public List<Cuenta> listar() {
		return mD.findAll();
	}

	@Override
	@Transactional
	public void eliminar(int idCuenta) {
			mD.deleteById(idCuenta);
	}
	
	@Override
	public List<Cuenta> buscarNombreCuenta(String nombreCuenta) {
		return mD.buscarNombreCuenta(nombreCuenta);
	}
	
	@Override
	public boolean modificar(Cuenta cuenta) {
		boolean flag = false;
		try {
			mD.save(cuenta);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Ocurrio un error");
		}
		return flag;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cuenta> findById(int idCuenta) {
		return mD.findById(idCuenta);
	}


}
