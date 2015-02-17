package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.PetrolDelivery;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PetrolDeliveryDao extends GenericDao<PetrolDelivery, Integer> {
	public List<PetrolDelivery> getPetrolDeliveries();
	public HibernatePaginator<PetrolDelivery> getPaginatorPetrolDeliveries(AbstractOptions options);
	public PetrolDelivery getPetrolDeliveryById(Integer id);
	
	public void createPetrolDelivery(PetrolDelivery delivery);	
	public void updatePetrolDelivery(PetrolDelivery delivery);
	public void deletePetrolDelivery(PetrolDelivery delivery);
}
