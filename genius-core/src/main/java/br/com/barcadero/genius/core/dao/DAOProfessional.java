package br.com.barcadero.genius.core.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import br.com.barcadero.genius.persistence.model.Professional;
import br.com.barcadero.genius.persistence.objects.Filter;



@Repository
public class DAOProfessional extends SuperClassDAO<Professional> {

	public DAOProfessional() {
	}
	@Override
	public Professional find(long codigo) {
		try{
			return getManager().find(Professional.class, codigo);
		}catch(NoResultException e){
			return null;
		}
	}
	
	public void exempleHowToManuallyRemoveInstancesFromPersistenceContext() {
		Professional exemplo = find(1l);
		//You don't have to wait for the persistence context to close.
		//You can evict entity instances manually
		getManager().detach(exemplo);
		if(getManager().contains(exemplo)){
			//It will never enter here because the entity was removed
		}
		//Any changes has no effect
	}
	
	public List<Professional> listProByFilter(Filter filter) throws Exception {
		try {
			//TODO Put filters and finish
			return getManager().createQuery("FROM Professional",Professional.class).getResultList();
		} catch (Exception e) {
			throw e;
		}
	}

}
