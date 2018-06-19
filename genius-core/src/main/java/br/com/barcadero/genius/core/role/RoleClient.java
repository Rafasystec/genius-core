package br.com.barcadero.genius.core.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barcadero.genius.core.dao.DAOClient;
import br.com.barcadero.genius.core.exceptions.ValidationException;
import br.com.barcadero.genius.persistence.model.Client;


@Service
public class RoleClient extends RoleSuperClass<Client> {

	
	public RoleClient() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private DAOClient daoClient;
	@Override
	public Client insert(Client entidade) throws ValidationException {
		return daoClient.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		daoClient.delete(codigo);
	}

	@Override
	public Client update(Client entidade) throws ValidationException {
		return daoClient.update(entidade);
	}

	@Override
	public Client find(long codigo) throws ValidationException {
		return daoClient.find(codigo);
	}

}
