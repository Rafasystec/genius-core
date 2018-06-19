package br.com.barcadero.genius.core.dao;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import br.com.barcadero.genius.persistence.model.Client;



@Repository
public class DAOClient extends SuperClassDAO<Client> {

	public DAOClient() {
	}
	@Override
	public Client find(long codigo) {
		try{
			return getManager().find(Client.class, codigo);
		}catch(NoResultException e){
			return null;
		}
	}
	
	public void exempleHowToManuallyRemoveInstancesFromPersistenceContext() {
		Client exemplo = find(1l);
		//You don't have to wait for the persistence context to close.
		//You can evict entity instances manually
		getManager().detach(exemplo);
		if(getManager().contains(exemplo)){
			//It will never enter here because the entity was removed
		}
		//Any changes has no effect
	}

}
