package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface CarWashTypeDao extends GenericDao<CarWashType,Integer>
{
	public CarWashType getCarWashTypeById(Integer id);	
	public List<CarWashType> getCarWashTypes();
	public HibernatePaginator<CarWashType> getPaginatorCarWashTypes(AbstractOptions options);
	
	public void createCarWashType(CarWashType carWashType);
	public void updateCarWashType(CarWashType carWashType);
	public void deleteCarWashType(CarWashType carWashType);
}
