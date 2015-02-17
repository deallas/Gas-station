package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.PetrolProvider;
import pl.noname.stacjabenzynowa.service.dao.PetrolProviderDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("petrolProviderDao")
public class PetrolProviderDaoImpl extends GenericDaoImpl<PetrolProvider, Integer> implements
		PetrolProviderDao {

	public List<PetrolProvider> getPetrolProviders() {
		return findAll();
	}

	public HibernatePaginator<PetrolProvider> getPaginatorPetrolProviders(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(PetrolProvider.class);
		
		HibernatePaginator<PetrolProvider> hp = new HibernatePaginator<PetrolProvider>(criteria,options);
		
		return hp;
	}

	public PetrolProvider getPetrolProviderById(Integer id) {
		return findById(id);
	}

	public void createPetrolProvider(PetrolProvider provider) {
		saveOrUpdate(provider);	
	}

	public void updatePetrolProvider(PetrolProvider provider) {
		saveOrUpdate(provider);
	}

	public void deletePetrolProvider(PetrolProvider provider) {
		delete(provider);	
	}

}
