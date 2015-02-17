package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PetrolContainerMeasurementDao extends GenericDao<PetrolContainerMeasurement, Integer> {
	public List<PetrolContainerMeasurement> getPetrolContainerMeasurements();
	public HibernatePaginator<PetrolContainerMeasurement> getPaginatorPetrolContainerMeasurements(AbstractOptions options);
	public PetrolContainerMeasurement getPetrolContainerMeasurementById(Integer id);
	public PetrolContainerMeasurement getPetrolContainerMeasurementByContainerId(Integer id, PetrolContainerMeasurement.Type type);
	
	public void createPetrolContainerMeasurement(PetrolContainerMeasurement measurement);	
	public void updatePetrolContainerMeasurement(PetrolContainerMeasurement measurement);
	public void deletePetrolContainerMeasurement(PetrolContainerMeasurement measurement);
}
