package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.PrizeCategory;
import pl.noname.stacjabenzynowa.service.dao.PrizeCategoryDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("prizeCategoryDao")
public class PrizeCategoryDaoImpl extends GenericDaoImpl<PrizeCategory, Integer> implements PrizeCategoryDao {

	@Override
	public List<PrizeCategory> getPrizeCategories() {
		return findAll();
	}

	@Override
	public HibernatePaginator<PrizeCategory> getPaginatorPrizeCategories(
			AbstractOptions options) {
		
		Criteria criteria = getSession().createCriteria(PrizeCategory.class);
		
		HibernatePaginator<PrizeCategory> hp = new HibernatePaginator<PrizeCategory>(criteria,options);
		
		return hp;
	}

	@Override
	public PrizeCategory getPrizeCategoryById(Integer id) {
		return findById(id);
	}

	@Override
	public void createPrizeCategory(PrizeCategory category) {
		saveOrUpdate(category);
	}

	@Override
	public void updatePrizeCategory(PrizeCategory category) {
		saveOrUpdate(category);
	}

	@Override
	public void deletePrizeCategory(PrizeCategory category) {
		delete(category);
	}

}
