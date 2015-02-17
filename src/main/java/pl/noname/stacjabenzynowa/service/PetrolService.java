package pl.noname.stacjabenzynowa.service;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerLog;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.persistance.PetrolContainersDelivery;
import pl.noname.stacjabenzynowa.persistance.PetrolDelivery;
import pl.noname.stacjabenzynowa.persistance.PetrolProvider;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.PetrolContainerLogOptions;
import pl.noname.stacjabenzynowa.web.paginator.PetrolContainerMeasurementOptions;
import pl.noname.stacjabenzynowa.web.paginator.PetrolContainerOptions;
import pl.noname.stacjabenzynowa.web.paginator.PetrolDeliveryOptions;
import pl.noname.stacjabenzynowa.web.paginator.PetrolProviderOptions;

public interface PetrolService {
	public List<PetrolContainer> getPetrolContainers();
	public List<PetrolContainerLog> getPetrolContainerLogs();
	public List<PetrolContainerMeasurement> getPetrolContainerMeasurements();
	public List<PetrolContainersDelivery> getPetrolContainersDeliveries();
	public List<PetrolDelivery> getPetrolDeliveries();
	public List<PetrolProvider> getPetrolProviders();
	public HibernatePaginator<PetrolContainer> getPaginatorPetrolContainers(Integer pageNumber, String order, Boolean ascing);
	public HibernatePaginator<PetrolContainerLog> getPaginatorPetrolContainerLogs(Integer pageNumber, String order, Boolean ascing);
	public HibernatePaginator<PetrolContainerMeasurement> getPaginatorPetrolContainerMeasurements(Integer pageNumber, String order, Boolean ascing);
	public HibernatePaginator<PetrolContainersDelivery> getPaginatorPetrolContainersDeliveries(Integer pageNumber, String order, Boolean ascing);
	public HibernatePaginator<PetrolDelivery> getPaginatorPetrolDeliveries(Integer pageNumber, String order, Boolean ascing);
	public HibernatePaginator<PetrolProvider> getPaginatorPetrolProviders(Integer pageNumber, String order, Boolean ascing);
	
	public PetrolContainer getPetrolContainerById(Integer id);	
	public PetrolContainerLog getPetrolContainerLogById(Integer id);	
	public PetrolContainerMeasurement getPetrolContainerMeasurementById(Integer id);	
	public PetrolContainersDelivery getPetrolContainersDeliveryById(Integer id);	
	public PetrolDelivery getPetrolDeliveryById(Integer id);
	public PetrolProvider getPetrolProviderById(Integer id);
	
	public void createPetrolContainer(PetrolContainer container);	
	public void updatePetrolContainer(PetrolContainer container);
	public void deletePetrolContainer(PetrolContainer container);
	
	public void createPetrolContainerLog(PetrolContainerLog log);	
	public void updatePetrolContainerLog(PetrolContainerLog log);
	public void deletePetrolContainerLog(PetrolContainerLog log);
	
	public void createPetrolContainerMeasurement(PetrolContainerMeasurement measurement);	
	public void updatePetrolContainerMeasurement(PetrolContainerMeasurement measurement);
	public void deletePetrolContainerMeasurement(PetrolContainerMeasurement measurement);
	
	public void createPetrolContainersDelivery(PetrolContainersDelivery delivery);	
	public void updatePetrolContainersDelivery(PetrolContainersDelivery delivery);
	public void deletePetrolContainersDelivery(PetrolContainersDelivery delivery);
	
	public void createPetrolDelivery(PetrolDelivery delivery);	
	public void updatePetrolDelivery(PetrolDelivery delivery);
	public void deletePetrolDelivery(PetrolDelivery delivery);
	
	public void createPetrolProvider(PetrolProvider provider);	
	public void updatePetrolProvider(PetrolProvider provider);
	public void deletePetrolProvider(PetrolProvider provider);
	
	public PetrolContainerOptions getPetrolContainerPaginator();
	public PetrolContainerLogOptions getPetrolContainerLogPaginator();
	public PetrolContainerMeasurementOptions getPetrolContainerMeasurementPaginator();
	public PetrolContainerMeasurement getPetrolContainerMeasurementByContainerId(Integer id, PetrolContainerMeasurement.Type type);
	public PetrolDeliveryOptions getPetrolDeliveryPaginator();
	public PetrolProviderOptions getPetrolProviderPaginator();
}
