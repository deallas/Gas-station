package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.service.dao.PetrolContainerDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("petrolContainerDao")
public class PetrolContainerDaoImpl extends GenericDaoImpl<PetrolContainer, Integer> implements PetrolContainerDao {

	public List<PetrolContainer> getPetrolContainers() {
		return findAll();
	}

	public HibernatePaginator<PetrolContainer> getPaginatorPetrolContainers(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(PetrolContainer.class);
		
		HibernatePaginator<PetrolContainer> hp = new HibernatePaginator<PetrolContainer>(criteria,options);
		
		return hp;
	}

	public PetrolContainer getPetrolContainerById(Integer id) {
		return findById(id);
	}

	public void createPetrolContainer(PetrolContainer container) {
		saveOrUpdate(container);
	}

	public void updatePetrolContainer(PetrolContainer container) {
		saveOrUpdate(container);
	}

	public void deletePetrolContainer(PetrolContainer container) {
		delete(container);
	}

}
