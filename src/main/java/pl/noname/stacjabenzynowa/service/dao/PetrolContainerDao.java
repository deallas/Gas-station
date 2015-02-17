package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PetrolContainerDao extends GenericDao<PetrolContainer, Integer> {
	
	public PetrolContainer getPetrolContainerById(Integer id);	
	public List<PetrolContainer> getPetrolContainers();
	public HibernatePaginator<PetrolContainer> getPaginatorPetrolContainers(AbstractOptions options);
	
	public void createPetrolContainer(PetrolContainer container);	
	public void updatePetrolContainer(PetrolContainer container);
	public void deletePetrolContainer(PetrolContainer container);
}
