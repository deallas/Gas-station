package pl.noname.stacjabenzynowa.service;

import java.util.List;

import pl.noname.stacjabenzynowa.web.paginator.CarWashOptions;
import pl.noname.stacjabenzynowa.web.paginator.CarWashTypeOptions;
import pl.noname.stacjabenzynowa.persistance.CarWash;
import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.service.dao.CarWashDao;
import pl.noname.stacjabenzynowa.service.dao.CarWashTypeDao;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface CarWashService 
{	
	public CarWashDao getCarWashDao();
	public CarWashTypeDao getCarWashTypeDao();
	
	/* ----------------------------------------------------------- */
	
	public CarWashOptions getCarWashPaginatorOptions();
	public CarWashTypeOptions getCarWashTypePaginatorOptions();
	
	/* ----------------------------------------------------------- */
	
	public CarWashType getCarWashTypeById(Integer id);	
	public void createCarWashType(CarWashType carWashType);
	public void updateCarWashType(CarWashType carWashType);
	public void deleteCarWashType(CarWashType carWashType);
	public HibernatePaginator<CarWashType> getCarWashTypePaginator(Integer pageNumber, String order, Boolean ascing);
	List<CarWashType> getCarWashTypes();
	
	/* ----------------------------------------------------------- */
	
	public CarWash getCarWashById(Integer id);	
	public void createCarWash(CarWash carWash);
	public void updateCarWash(CarWash carWash);
	public void deleteCarWash(CarWash carWash);
	public HibernatePaginator<CarWash> getCarWashPaginator(Integer pageNumber, String order, Boolean ascing);
	List<CarWash> getCarWash();

}
