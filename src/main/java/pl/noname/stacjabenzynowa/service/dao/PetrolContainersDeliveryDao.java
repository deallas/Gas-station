package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.PetrolContainersDelivery;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PetrolContainersDeliveryDao extends GenericDao<PetrolContainersDelivery, Integer> {
	public List<PetrolContainersDelivery> getPetrolContainersDeliveries();
	public HibernatePaginator<PetrolContainersDelivery> getPaginatorPetrolContainersDeliveries(AbstractOptions options);
	public PetrolContainersDelivery getPetrolContainersDeliveryById(Integer id);
	
	public void createPetrolContainersDelivery(PetrolContainersDelivery delivery);	
	public void updatePetrolContainersDelivery(PetrolContainersDelivery delivery);
	public void deletePetrolContainersDelivery(PetrolContainersDelivery delivery);
}
