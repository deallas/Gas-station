package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.CarWash;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface CarWashDao extends GenericDao<CarWash,Integer>{
	public CarWash getCarWashById(Integer id);	
	public List<CarWash> getCarWashes();
	public HibernatePaginator<CarWash> getPaginatorCarWashes(AbstractOptions options);
	
	public void createCarWash(CarWash carWash);
	public void updateCarWash(CarWash carWash);
	public void deleteCarWash(CarWash carWash);
}
