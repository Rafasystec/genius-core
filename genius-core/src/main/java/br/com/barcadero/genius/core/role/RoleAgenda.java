package br.com.barcadero.genius.core.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barcadero.genius.core.dao.DAOAgenda;
import br.com.barcadero.genius.core.exceptions.ValidationException;
import br.com.barcadero.genius.persistence.model.Agenda;
import br.com.barcadero.genius.persistence.response.ClientAgendaResponse;
import br.com.idoctor.commons.util.HelperDateTime;


@Service
public class RoleAgenda extends RoleSuperClass<Agenda> {

	
	public RoleAgenda() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private DAOAgenda daoAgenda;
	@Override
	public Agenda insert(Agenda entidade) throws ValidationException {
		return daoAgenda.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		daoAgenda.delete(codigo);
	}

	@Override
	public Agenda update(Agenda entidade) throws ValidationException {
		return daoAgenda.update(entidade);
	}

	@Override
	public Agenda find(long codigo) throws ValidationException {
		return daoAgenda.find(codigo);
	}
	
	public List<Agenda> listAllClientAgenda(long idClient) {
		return daoAgenda.listAllClientAgenda(idClient);
	}
	
	public List<ClientAgendaResponse> getAgendaResponseFromClient(long idClient) {
		List<Agenda> list = daoAgenda.listAllClientAgenda(idClient);
		List<ClientAgendaResponse> listResponse = new ArrayList<>();
		if(list != null){
			for (Agenda agenda : list) {
				ClientAgendaResponse agendaCli = new ClientAgendaResponse();
				agendaCli.setAgendaDate(HelperDateTime.formatDateTime(agenda.getRegistedDate(), "dd/MM/yyyy"));
				agendaCli.setClientId(agenda.getClient().getId());
				agendaCli.setClientName(agenda.getClient().getPerson().getNickName());
				agendaCli.setClientPhotoUrl(agenda.getClient().getPerson().getUrlProfilePhoto());
				agendaCli.setConfirmed(agenda.isConfirmed());
				agendaCli.setProfessionalId(agenda.getProfessional().getId());
				agendaCli.setProfessionalName(agenda.getProfessional().getPerson().getNickName());
				agendaCli.setProfessionalPhotoUrl(agenda.getProfessional().getPerson().getUrlProfilePhoto());
				agendaCli.setStatus(agenda.getStatus().getDescription());
				listResponse.add(agendaCli);
			}
		}
		return listResponse;
	}

}
