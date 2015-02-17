package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.PetrolProvider;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PetrolProviderDao extends GenericDao<PetrolProvider, Integer> {
	public List<PetrolProvider> getPetrolProviders();
	public HibernatePaginator<PetrolProvider> getPaginatorPetrolProviders(AbstractOptions options);
	public PetrolProvider getPetrolProviderById(Integer id);
	
	public void createPetrolProvider(PetrolProvider provider);	
	public void updatePetrolProvider(PetrolProvider provider);
	public void deletePetrolProvider(PetrolProvider provider);
}
