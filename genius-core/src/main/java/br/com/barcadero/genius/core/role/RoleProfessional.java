package br.com.barcadero.genius.core.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barcadero.genius.core.dao.DAOProfessional;
import br.com.barcadero.genius.core.exceptions.ValidationException;
import br.com.barcadero.genius.persistence.model.Professional;


@Service
public class RoleProfessional extends RoleSuperClass<Professional> {

	
	public RoleProfessional() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private DAOProfessional daoProfessional;
	@Override
	public Professional insert(Professional entidade) throws ValidationException {
		return daoProfessional.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		daoProfessional.delete(codigo);
	}

	@Override
	public Professional update(Professional entidade) throws ValidationException {
		return daoProfessional.update(entidade);
	}

	@Override
	public Professional find(long codigo) throws ValidationException {
		return daoProfessional.find(codigo);
	}

}
