package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.PetrolDelivery;
import pl.noname.stacjabenzynowa.service.dao.PetrolDeliveryDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("petrolDeliveryDao")
public class PetrolDeliveryDaoImpl extends GenericDaoImpl<PetrolDelivery, Integer> implements
		PetrolDeliveryDao {

	public List<PetrolDelivery> getPetrolDeliveries() {
		return findAll();
	}

	public HibernatePaginator<PetrolDelivery> getPaginatorPetrolDeliveries(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(PetrolDelivery.class);
		
		HibernatePaginator<PetrolDelivery> hp = new HibernatePaginator<PetrolDelivery>(criteria,options);
		
		return hp;
	}

	public PetrolDelivery getPetrolDeliveryById(Integer id) {
		return findById(id);
	}

	public void createPetrolDelivery(PetrolDelivery delivery) {
		saveOrUpdate(delivery);
	}

	public void updatePetrolDelivery(PetrolDelivery delivery) {
		saveOrUpdate(delivery);
	}

	public void deletePetrolDelivery(PetrolDelivery delivery) {
		delete(delivery);
	}

}
