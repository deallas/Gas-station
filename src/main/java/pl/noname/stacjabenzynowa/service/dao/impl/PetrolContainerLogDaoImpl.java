package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.PetrolContainerLog;
import pl.noname.stacjabenzynowa.service.dao.PetrolContainerLogDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("petrolContainerLogDao")
public class PetrolContainerLogDaoImpl extends GenericDaoImpl<PetrolContainerLog, Integer> implements PetrolContainerLogDao {

	public List<PetrolContainerLog> getPetrolContainerLogs() {
		return findAll();
	}

	public HibernatePaginator<PetrolContainerLog> getPaginatorPetrolContainerLogs(
			AbstractOptions options) {

		Criteria criteria = getSession().createCriteria(PetrolContainerLog.class);
		
		HibernatePaginator<PetrolContainerLog> hp = new HibernatePaginator<PetrolContainerLog>(criteria,options);
		
		return hp;
	}

	public PetrolContainerLog getPetrolContainerLogById(Integer id) {
		return findById(id);
	}

	public void createPetrolContainerLog(PetrolContainerLog log) {
		saveOrUpdate(log);
	}

	public void updatePetrolContainerLog(PetrolContainerLog log) {
		saveOrUpdate(log);
	}

	public void deletePetrolContainerLog(PetrolContainerLog log) {
		delete(log);
	}

}
