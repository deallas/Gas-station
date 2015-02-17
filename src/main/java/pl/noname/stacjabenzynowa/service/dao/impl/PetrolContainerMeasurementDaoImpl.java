package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.service.dao.PetrolContainerMeasurementDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("petrolContainerMeasurementDao")
public class PetrolContainerMeasurementDaoImpl extends GenericDaoImpl<PetrolContainerMeasurement, Integer> implements
		PetrolContainerMeasurementDao {

	public List<PetrolContainerMeasurement> getPetrolContainerMeasurements() {
		return findAll();
	}

	public HibernatePaginator<PetrolContainerMeasurement> getPaginatorPetrolContainerMeasurements(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(PetrolContainerMeasurement.class);
		
		HibernatePaginator<PetrolContainerMeasurement> hp = new HibernatePaginator<PetrolContainerMeasurement>(criteria,options);
		
		return hp;
	}

	public PetrolContainerMeasurement getPetrolContainerMeasurementById(
			Integer id) {
		return findById(id);
	}
	
	@Override
	public PetrolContainerMeasurement getPetrolContainerMeasurementByContainerId(
			Integer id, PetrolContainerMeasurement.Type type) {
		
		Criteria crit = getSession().createCriteria(PetrolContainerMeasurement.class)
									.add(Restrictions.eq("petrolContainer.id", id))
									.add(Restrictions.eq("type", type))
									.addOrder(Order.desc("measurementDate"));
		crit.setMaxResults(1);
		List<Criteria> list = crit.list();
		if(!list.isEmpty())
			return (PetrolContainerMeasurement)list.get(0);
		else
			return null;
	}

	public void createPetrolContainerMeasurement(
			PetrolContainerMeasurement measurement) {
		saveOrUpdate(measurement);
	}

	public void updatePetrolContainerMeasurement(
			PetrolContainerMeasurement measurement) {
		saveOrUpdate(measurement);
	}

	public void deletePetrolContainerMeasurement(
			PetrolContainerMeasurement measurement) {
		delete(measurement);
	}

}
