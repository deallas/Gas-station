package pl.noname.stacjabenzynowa.service.dao.impl;

import org.springframework.stereotype.Repository;
import java.util.List;

import org.hibernate.Criteria;

import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.service.dao.CarWashTypeDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("carWashTypeDao")
public class CarWashTypeDaoImpl extends GenericDaoImpl<CarWashType, Integer> implements CarWashTypeDao {
	
	@Override
	public List<CarWashType> getCarWashTypes() {
		return findAll();
	}

	@Override
	public HibernatePaginator<CarWashType> getPaginatorCarWashTypes(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(CarWashType.class);
		
		HibernatePaginator<CarWashType> hp = new HibernatePaginator<CarWashType>(criteria,options);
		
		return hp;
	}

	@Override
	public CarWashType getCarWashTypeById(Integer id) {
		return findById(id);
	}

	@Override
	public void updateCarWashType(CarWashType carWashType) {
		saveOrUpdate(carWashType);
	}

	@Override
	public void deleteCarWashType(CarWashType carWashType) {
		delete(carWashType);
	}

	@Override
	public void createCarWashType(CarWashType carWashType) {
		saveOrUpdate(carWashType);
	}
}
