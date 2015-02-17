package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.CarWash;
import pl.noname.stacjabenzynowa.service.dao.CarWashDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("carWashDao")
public class CarWashDaoImpl extends GenericDaoImpl<CarWash, Integer> implements CarWashDao  {

	@Override
	public HibernatePaginator<CarWash> getPaginatorCarWashes(AbstractOptions options){
		
		Criteria criteria = getSession().createCriteria(CarWash.class);
		criteria.setFetchMode("carWashType", FetchMode.JOIN);
		criteria.createAlias("carWashType", "cwt");
		
		
		HibernatePaginator<CarWash> hp = new HibernatePaginator<CarWash>(criteria,options);
		
		return hp;
	}
	
	@Override
	public CarWash getCarWashById(Integer id) {
		return findById(id);
	}

	@Override
	public List<CarWash> getCarWashes() {
		return findAll();
	}

	@Override
	public void updateCarWash(CarWash carWash) {
		saveOrUpdate(carWash);
	}

	@Override
	public void deleteCarWash(CarWash carWash) {
		delete(carWash);
	}

	@Override
	public void createCarWash(CarWash carWash) {
		saveOrUpdate(carWash);
	}

}
