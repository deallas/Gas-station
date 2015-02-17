package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.Refueling;
import pl.noname.stacjabenzynowa.service.dao.RefuelingDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("refuelingDao")
public class RefuelingDaoImpl extends GenericDaoImpl<Refueling,Integer> implements RefuelingDao
{

	public List<Refueling> getRefuelings() {
		return findAll();
	}

	public HibernatePaginator<Refueling> getPaginatorRefuelings(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(Refueling.class);
		
		HibernatePaginator<Refueling> hp = new HibernatePaginator<Refueling>(criteria,options);
		
		return hp;
	}

	public Refueling getRefuelingById(Integer id) {
		return findById(id);
	}

	public void createRefueling(Refueling refueling) {
		saveOrUpdate(refueling);
	}

	public void updateRefueling(Refueling refueling) {
		saveOrUpdate(refueling);
	}

	public void deleteRefueling(Refueling refueling) {
		delete(refueling);
	}
	
}
