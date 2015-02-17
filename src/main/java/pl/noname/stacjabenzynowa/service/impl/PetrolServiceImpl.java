package pl.noname.stacjabenzynowa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerLog;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.persistance.PetrolContainersDelivery;
import pl.noname.stacjabenzynowa.persistance.PetrolDelivery;
import pl.noname.stacjabenzynowa.persistance.PetrolProvider;
import pl.noname.stacjabenzynowa.service.PetrolService;
import pl.noname.stacjabenzynowa.service.dao.PetrolContainerDao;
import pl.noname.stacjabenzynowa.service.dao.PetrolContainerLogDao;
import pl.noname.stacjabenzynowa.service.dao.PetrolContainerMeasurementDao;
import pl.noname.stacjabenzynowa.service.dao.PetrolContainersDeliveryDao;
import pl.noname.stacjabenzynowa.service.dao.PetrolDeliveryDao;
import pl.noname.stacjabenzynowa.service.dao.PetrolProviderDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.PetrolContainerLogOptions;
import pl.noname.stacjabenzynowa.web.paginator.PetrolContainerMeasurementOptions;
import pl.noname.stacjabenzynowa.web.paginator.PetrolContainerOptions;
import pl.noname.stacjabenzynowa.web.paginator.PetrolDeliveryOptions;
import pl.noname.stacjabenzynowa.web.paginator.PetrolProviderOptions;

@Service("petrolService")
@Transactional(propagation=Propagation.REQUIRED)
public class PetrolServiceImpl implements PetrolService {

	@Autowired
	private PetrolContainerDao petrolContainerDao;
	
	@Autowired
	private PetrolContainerLogDao petrolContainerLogDao;
	
	@Autowired
	private PetrolContainerMeasurementDao petrolContainerMeasurementDao;
	
	@Autowired
	private PetrolContainersDeliveryDao petrolContainersDeliveryDao;
	
	@Autowired
	private PetrolDeliveryDao petrolDeliveryDao;
	
	@Autowired
	private PetrolProviderDao petrolProviderDao;
	
	@Autowired
	private PetrolContainerOptions petrolContainerPaginator;
	
	@Autowired
	private PetrolContainerLogOptions petrolContainerLogPaginator;
	
	@Autowired
	private PetrolContainerMeasurementOptions petrolContainerMeasurementPaginator;
	
	@Autowired
	private PetrolDeliveryOptions petrolDeliveryPaginator;
	
	@Autowired
	private PetrolProviderOptions petrolProviderPaginator;
	
	/* ----------------------------------------------------------- */

	public PetrolContainerDao getPetrolContainerDao() {
		return petrolContainerDao;
	}

	public void setPetrolContainerDao(PetrolContainerDao petrolContainerDao) {
		this.petrolContainerDao = petrolContainerDao;
	}

	public PetrolContainerLogDao getPetrolContainerLogDao() {
		return petrolContainerLogDao;
	}

	public void setPetrolContainerLogDao(PetrolContainerLogDao petrolContainerLogDao) {
		this.petrolContainerLogDao = petrolContainerLogDao;
	}

	public PetrolContainerMeasurementDao getPetrolContainerMeasurementDao() {
		return petrolContainerMeasurementDao;
	}

	public void setPetrolContainerMeasurementDao(
			PetrolContainerMeasurementDao petrolContainerMeasurementDao) {
		this.petrolContainerMeasurementDao = petrolContainerMeasurementDao;
	}

	public PetrolContainersDeliveryDao getPetrolContainersDeliveryDao() {
		return petrolContainersDeliveryDao;
	}

	public void setPetrolContainersDeliveryDao(
			PetrolContainersDeliveryDao petrolContainersDeliveryDao) {
		this.petrolContainersDeliveryDao = petrolContainersDeliveryDao;
	}

	public PetrolDeliveryDao getPetrolDeliveryDao() {
		return petrolDeliveryDao;
	}

	public void setPetrolDeliveryDao(PetrolDeliveryDao petrolDeliveryDao) {
		this.petrolDeliveryDao = petrolDeliveryDao;
	}

	public PetrolProviderDao getPetrolProviderDao() {
		return petrolProviderDao;
	}

	public void setPetrolProviderDao(PetrolProviderDao petrolProviderDao) {
		this.petrolProviderDao = petrolProviderDao;
	}
	
	public PetrolContainerOptions getPetrolContainerPaginator() {
		return petrolContainerPaginator;
	}

	public void setPetrolContainerPaginator(
			PetrolContainerOptions petrolContainerPaginator) {
		this.petrolContainerPaginator = petrolContainerPaginator;
	}
	
	public PetrolContainerLogOptions getPetrolContainerLogPaginator() {
		return petrolContainerLogPaginator;
	}

	public void setPetrolContainerLogPaginator(
			PetrolContainerLogOptions petrolContainerLogPaginator) {
		this.petrolContainerLogPaginator = petrolContainerLogPaginator;
	}

	public PetrolContainerMeasurementOptions getPetrolContainerMeasurementPaginator() {
		return petrolContainerMeasurementPaginator;
	}

	public void setPetrolContainerMeasurementPaginator(
			PetrolContainerMeasurementOptions petrolContainerMeasurementPaginator) {
		this.petrolContainerMeasurementPaginator = petrolContainerMeasurementPaginator;
	}

	public PetrolDeliveryOptions getPetrolDeliveryPaginator() {
		return petrolDeliveryPaginator;
	}

	public void setPetrolDeliveryPaginator(
			PetrolDeliveryOptions petrolDeliveryPaginator) {
		this.petrolDeliveryPaginator = petrolDeliveryPaginator;
	}

	public PetrolProviderOptions getPetrolProviderPaginator() {
		return petrolProviderPaginator;
	}

	public void setPetrolProviderPaginator(
			PetrolProviderOptions petrolProviderPaginator) {
		this.petrolProviderPaginator = petrolProviderPaginator;
	}
	
	/* ----------------------------------------------------------- */


	@Override
	@Transactional(readOnly=true)
	public List<PetrolContainer> getPetrolContainers() {
		return petrolContainerDao.getPetrolContainers();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PetrolContainerLog> getPetrolContainerLogs() {
		return petrolContainerLogDao.getPetrolContainerLogs();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PetrolContainerMeasurement> getPetrolContainerMeasurements() {
		return petrolContainerMeasurementDao.getPetrolContainerMeasurements();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PetrolContainersDelivery> getPetrolContainersDeliveries() {
		return petrolContainersDeliveryDao.getPetrolContainersDeliveries();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PetrolDelivery> getPetrolDeliveries() {
		return petrolDeliveryDao.getPetrolDeliveries();
	}

	@Override
	@Transactional(readOnly=true)
	public List<PetrolProvider> getPetrolProviders() {
		return petrolProviderDao.getPetrolProviders();
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<PetrolContainer> getPaginatorPetrolContainers(
			Integer pageNumber, String order, Boolean ascing) {
		AbstractOptions options = getPetrolContainerPaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPetrolContainerPaginator().setPaginatorOptions(options);
		
		HibernatePaginator<PetrolContainer> paginator = petrolContainerDao.getPaginatorPetrolContainers(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<PetrolContainerLog> getPaginatorPetrolContainerLogs(
			Integer pageNumber, String order, Boolean ascing) {
		AbstractOptions options = getPetrolContainerLogPaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPetrolContainerLogPaginator().setPaginatorOptions(options);
		
		HibernatePaginator<PetrolContainerLog> paginator = petrolContainerLogDao.getPaginatorPetrolContainerLogs(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<PetrolContainerMeasurement> getPaginatorPetrolContainerMeasurements(
			Integer pageNumber, String order, Boolean ascing) {
		AbstractOptions options = getPetrolContainerMeasurementPaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPetrolContainerMeasurementPaginator().setPaginatorOptions(options);
		
		HibernatePaginator<PetrolContainerMeasurement> paginator = petrolContainerMeasurementDao.getPaginatorPetrolContainerMeasurements(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<PetrolContainersDelivery> getPaginatorPetrolContainersDeliveries(
			Integer pageNumber, String order, Boolean ascing) {
		/*AbstractOptions options = getPetrolContainersDeliveryPaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPetrolContainersDeliveryPaginator().setPaginatorOptions(options);
		
		HibernatePaginator<PetrolContainersDelivery> paginator = petrolContainersDeliveryDao.getPaginatorPetrolContainersDeliveries(options);
		
		return paginator;*/
		return null;
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<PetrolDelivery> getPaginatorPetrolDeliveries(
			Integer pageNumber, String order, Boolean ascing) {
		AbstractOptions options = getPetrolDeliveryPaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPetrolDeliveryPaginator().setPaginatorOptions(options);
		
		HibernatePaginator<PetrolDelivery> paginator = petrolDeliveryDao.getPaginatorPetrolDeliveries(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<PetrolProvider> getPaginatorPetrolProviders(
			Integer pageNumber, String order, Boolean ascing) {
		AbstractOptions options = getPetrolProviderPaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPetrolProviderPaginator().setPaginatorOptions(options);
		
		HibernatePaginator<PetrolProvider> paginator = petrolProviderDao.getPaginatorPetrolProviders(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public PetrolContainer getPetrolContainerById(Integer id) {
		return petrolContainerDao.getPetrolContainerById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public PetrolContainerLog getPetrolContainerLogById(Integer id) {
		return petrolContainerLogDao.getPetrolContainerLogById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public PetrolContainerMeasurement getPetrolContainerMeasurementById(
			Integer id) {
		return petrolContainerMeasurementDao.getPetrolContainerMeasurementById(id);
	}
	

	@Override
	@Transactional(readOnly=true)
	public PetrolContainerMeasurement getPetrolContainerMeasurementByContainerId(
			Integer id, PetrolContainerMeasurement.Type type) {
		return petrolContainerMeasurementDao.getPetrolContainerMeasurementByContainerId(id, type);
	}

	@Override
	@Transactional(readOnly=true)
	public PetrolContainersDelivery getPetrolContainersDeliveryById(Integer id) {
		return petrolContainersDeliveryDao.getPetrolContainersDeliveryById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public PetrolDelivery getPetrolDeliveryById(Integer id) {
		return petrolDeliveryDao.getPetrolDeliveryById(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public PetrolProvider getPetrolProviderById(Integer id) {
		return petrolProviderDao.getPetrolProviderById(id);
	}

	@Override
	public void createPetrolProvider(PetrolProvider provider) {
		petrolProviderDao.createPetrolProvider(provider);
	}

	@Override
	public void updatePetrolProvider(PetrolProvider provider) {
		petrolProviderDao.updatePetrolProvider(provider);
	}

	@Override
	public void deletePetrolProvider(PetrolProvider provider) {
		petrolProviderDao.deletePetrolProvider(provider);
	}

	@Override
	public void createPetrolContainer(PetrolContainer container) {
		petrolContainerDao.createPetrolContainer(container);
	}

	@Override
	public void updatePetrolContainer(PetrolContainer container) {
		petrolContainerDao.updatePetrolContainer(container);
	}

	@Override
	public void deletePetrolContainer(PetrolContainer container) {
		petrolContainerDao.deletePetrolContainer(container);
	}

	@Override
	public void createPetrolContainerLog(PetrolContainerLog log) {
		petrolContainerLogDao.createPetrolContainerLog(log);
	}

	@Override
	public void updatePetrolContainerLog(PetrolContainerLog log) {
		petrolContainerLogDao.updatePetrolContainerLog(log);
	}

	@Override
	public void deletePetrolContainerLog(PetrolContainerLog log) {
		petrolContainerLogDao.deletePetrolContainerLog(log);
	}

	@Override
	public void createPetrolContainerMeasurement(
			PetrolContainerMeasurement measurement) {
		petrolContainerMeasurementDao.createPetrolContainerMeasurement(measurement);
	}

	@Override
	public void updatePetrolContainerMeasurement(
			PetrolContainerMeasurement measurement) {
		petrolContainerMeasurementDao.updatePetrolContainerMeasurement(measurement);
	}

	@Override
	public void deletePetrolContainerMeasurement(
			PetrolContainerMeasurement measurement) {
		petrolContainerMeasurementDao.deletePetrolContainerMeasurement(measurement);
	}

	@Override
	public void createPetrolContainersDelivery(PetrolContainersDelivery delivery) {
		petrolContainersDeliveryDao.createPetrolContainersDelivery(delivery);
	}

	@Override
	public void updatePetrolContainersDelivery(PetrolContainersDelivery delivery) {
		petrolContainersDeliveryDao.updatePetrolContainersDelivery(delivery);
	}

	@Override
	public void deletePetrolContainersDelivery(PetrolContainersDelivery delivery) {
		petrolContainersDeliveryDao.deletePetrolContainersDelivery(delivery);
	}

	@Override
	public void createPetrolDelivery(PetrolDelivery delivery) {
		petrolDeliveryDao.createPetrolDelivery(delivery);
	}

	@Override
	public void updatePetrolDelivery(PetrolDelivery delivery) {
		petrolDeliveryDao.updatePetrolDelivery(delivery);
	}

	@Override
	public void deletePetrolDelivery(PetrolDelivery delivery) {
		petrolDeliveryDao.deletePetrolDelivery(delivery);
	}

	
}
