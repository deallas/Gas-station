package pl.noname.stacjabenzynowa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.CarWash;
import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.service.CarWashService;
import pl.noname.stacjabenzynowa.service.dao.CarWashDao;
import pl.noname.stacjabenzynowa.service.dao.CarWashTypeDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.CarWashOptions;
import pl.noname.stacjabenzynowa.web.paginator.CarWashTypeOptions;

@Service("carWashService")
@Transactional(propagation=Propagation.REQUIRED)
public class CarWashServiceImpl implements CarWashService
{
	@Autowired
	private CarWashDao carWashDao;
	
	@Autowired
	private CarWashTypeDao carWashTypeDao;
	
	@Autowired
	private CarWashOptions carWashPaginatorOptions;
	
	@Autowired
	private CarWashTypeOptions carWashTypePaginatorOptions;

	/* ----------------------------------------------------------- */
	
	@Override
	public CarWashDao getCarWashDao() {
		return carWashDao;
	}
	
	@Override
	public CarWashTypeDao getCarWashTypeDao() {
		return carWashTypeDao;
	}
	
	public void setCarWashDao(CarWashDao carWashDao) {
		this.carWashDao = carWashDao;
	}

	public void setCarWashTypeDao(CarWashTypeDao carWashTypeDao) {
		this.carWashTypeDao = carWashTypeDao;
	}
	
	/* ----------------------------------------------------------- */
	
	public CarWashOptions getCarWashPaginatorOptions() {
		return carWashPaginatorOptions;
	}

	public void setCarWashPaginatorOptions(CarWashOptions carWashPaginatorOptions) {
		this.carWashPaginatorOptions = carWashPaginatorOptions;
	}

	public CarWashTypeOptions getCarWashTypePaginatorOptions() {
		return carWashTypePaginatorOptions;
	}

	public void setCarWashTypePaginatorOptions(CarWashTypeOptions carWashTypePaginatorOptions) {
		this.carWashTypePaginatorOptions = carWashTypePaginatorOptions;
	}
	
	/* ----------------------------------------------------------- */

	@Override
	@Transactional(readOnly=true)
	public CarWashType getCarWashTypeById(Integer id) {
		return carWashTypeDao.getCarWashTypeById(id);
	}
	
	@Override
	@Transactional
	public void createCarWashType(CarWashType carWashType) {
		carWashTypeDao.saveOrUpdate(carWashType);
	}

	@Override
	@Transactional
	public void updateCarWashType(CarWashType carWashType) {
		carWashTypeDao.updateCarWashType(carWashType);
	}

	@Override
	@Transactional
	public void deleteCarWashType(CarWashType carWashType) {
		carWashTypeDao.deleteCarWashType(carWashType);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<CarWashType> getCarWashTypes() {
		return carWashTypeDao.getCarWashTypes();
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<CarWashType> getCarWashTypePaginator(
			Integer pageNumber, String order, Boolean ascing) {
		
		AbstractOptions options = carWashTypePaginatorOptions.getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = carWashTypePaginatorOptions.setPaginatorOptions(options);
		HibernatePaginator<CarWashType> paginator = carWashTypeDao.getPaginatorCarWashTypes(options);
		
		return paginator;
	}
	
	/* ----------------------------------------------------------- */
	
	@Override
	@Transactional(readOnly=true)
	public CarWash getCarWashById(Integer id) {
		return carWashDao.getCarWashById(id);
	}
	
	@Override
	public void createCarWash(CarWash carWash) {
		carWashDao.saveOrUpdate(carWash);
	}
	
	@Override
	public void updateCarWash(CarWash carWash) {
		carWashDao.updateCarWash(carWash);
	}

	@Override
	public void deleteCarWash(CarWash carWash) {
		carWashDao.deleteCarWash(carWash);
	}
	
	@Override
	public List<CarWash> getCarWash() {
		return carWashDao.getCarWashes();
	}
	
	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<CarWash> getCarWashPaginator(
			Integer pageNumber, String order, Boolean ascing) {
		AbstractOptions options = carWashPaginatorOptions.getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = carWashPaginatorOptions.setPaginatorOptions(options);
		
		HibernatePaginator<CarWash> paginator = carWashDao.getPaginatorCarWashes(options);
		
		return paginator;
	}
}
