package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.service.dao.RefuelingTypeDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("refuelingTypeDao")
public class RefuelingTypeDaoImpl extends GenericDaoImpl<RefuelingType, Integer> implements RefuelingTypeDao
{
	public List<RefuelingType> getRefuelingTypes() {
		return findAll();
	}

	public HibernatePaginator<RefuelingType> getPaginatorRefuelingTypes(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(RefuelingType.class);
		
		HibernatePaginator<RefuelingType> hp = new HibernatePaginator<RefuelingType>(criteria,options);
		
		return hp;
	}

	public RefuelingType getRefuelingTypeById(Integer id) {
		return findById(id);
	}

	public void createRefuelingType(RefuelingType type) {
		saveOrUpdate(type);
	}

	public void updateRefuelingType(RefuelingType type) {
		saveOrUpdate(type);
	}

	public void deleteRefuelingType(RefuelingType type) {
		delete(type);
	}	
}
