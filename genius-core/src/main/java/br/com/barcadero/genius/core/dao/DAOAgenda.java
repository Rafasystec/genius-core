package br.com.barcadero.genius.core.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import br.com.barcadero.genius.persistence.model.Agenda;



@Repository
public class DAOAgenda extends SuperClassDAO<Agenda> {

	public DAOAgenda() {
	}
	@Override
	public Agenda find(long codigo) {
		try{
			return getManager().find(Agenda.class, codigo);
		}catch(NoResultException e){
			return null;
		}
	}
	
	public void exempleHowToManuallyRemoveInstancesFromPersistenceContext() {
		Agenda exemplo = find(1l);
		//You don't have to wait for the persistence context to close.
		//You can evict entity instances manually
		getManager().detach(exemplo);
		if(getManager().contains(exemplo)){
			//It will never enter here because the entity was removed
		}
		//Any changes has no effect
	}
	
	public List<Agenda> listAllClientAgenda(long idClient) {
		try {
			return getManager().createQuery("FROM Agenda WHERE client.id = :idClient", Agenda.class)
					.setParameter("idClient",idClient)
					.getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

}
