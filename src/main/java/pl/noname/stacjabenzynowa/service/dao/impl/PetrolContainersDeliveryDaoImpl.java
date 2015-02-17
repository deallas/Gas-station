package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.PetrolContainersDelivery;
import pl.noname.stacjabenzynowa.service.dao.PetrolContainersDeliveryDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("petrolContainersDeliveryDao")
public class PetrolContainersDeliveryDaoImpl extends GenericDaoImpl<PetrolContainersDelivery, Integer> implements PetrolContainersDeliveryDao {

	public List<PetrolContainersDelivery> getPetrolContainersDeliveries() {
		return findAll();
	}

	public HibernatePaginator<PetrolContainersDelivery> getPaginatorPetrolContainersDeliveries(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(PetrolContainersDelivery.class);
		
		HibernatePaginator<PetrolContainersDelivery> hp = new HibernatePaginator<PetrolContainersDelivery>(criteria,options);
		
		return hp;
	}

	public PetrolContainersDelivery getPetrolContainersDeliveryById(Integer id) {
		return findById(id);
	}

	public void createPetrolContainersDelivery(PetrolContainersDelivery delivery) {
		saveOrUpdate(delivery);
	}

	public void updatePetrolContainersDelivery(PetrolContainersDelivery delivery) {
		saveOrUpdate(delivery);
	}

	public void deletePetrolContainersDelivery(PetrolContainersDelivery delivery) {
		delete(delivery);
	}

}
