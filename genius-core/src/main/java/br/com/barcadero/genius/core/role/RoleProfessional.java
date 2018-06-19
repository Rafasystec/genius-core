package br.com.barcadero.genius.core.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.barcadero.genius.core.dao.DAOProfessional;
//import br.com.barcadero.genius.core.exceptions.ValidationException;
import br.com.barcadero.genius.persistence.model.Professional;
import br.com.barcadero.genius.persistence.objects.Filter;
import br.com.barcadero.genius.persistence.response.ProfessionalResponse;
import br.com.idoctor.commons.exception.ValidationException;
import br.com.idoctor.commons.util.HelperNumberFormat;


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
	
	public List<Professional> listProByFilter(Filter filter) throws Exception {
		try{
			return daoProfessional.listProByFilter(filter);
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public List<ProfessionalResponse> listProResponseByFilter(Filter filter) {
		List<ProfessionalResponse> list = new ArrayList<>();
		try {
			//TODO get it with the real query
			for (Professional pro : daoProfessional.listProByFilter(filter)) {
				ProfessionalResponse proResp = new ProfessionalResponse();
				proResp.setDistance("2,5 km");
				proResp.setId(pro.getId());
				proResp.setIsFavorite(false);
				proResp.setLatitude(pro.getLocationPro().getLatitude());
				proResp.setLongitude(pro.getLocationPro().getLongitude());
				proResp.setName(pro.getPerson().getNickName());
				proResp.setProfessionalArea(pro.getArea());
				proResp.setRate(pro.getRateResume().intValue());
				proResp.setRateResume(pro.getRateResume());
				proResp.setUrlPhoto(pro.getPerson().getUrlProfilePhoto());
				proResp.setValue(HelperNumberFormat.currencyFormat(pro.getValue()) + pro.getPriceType().getShortDescription());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
