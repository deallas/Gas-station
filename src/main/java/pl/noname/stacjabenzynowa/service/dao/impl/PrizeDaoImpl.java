package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.Prize;
import pl.noname.stacjabenzynowa.service.dao.PrizeDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

@Repository("prizeDao")
public class PrizeDaoImpl extends GenericDaoImpl<Prize, Integer> implements PrizeDao {

	@Override
	public List<Prize> getPrizes() {
		return findAll();
	}

	@Override
	public HibernatePaginator<Prize> getPaginatorPrizes(AbstractOptions options) {

		Criteria criteria = getSession().createCriteria(Prize.class);
		
		HibernatePaginator<Prize> hp = new HibernatePaginator<Prize>(criteria,options);
		
		return hp;
	}
	
	@Override
	public HibernatePaginator<Prize> getPaginatorPrizesByClientId(
			AbstractOptions options, Integer id) {
		
		Criteria criteria = getSession().createCriteria(Prize.class);
		criteria.setFetchMode("client", FetchMode.JOIN); 
		criteria.createAlias("client", "c"); 
		criteria.add(Restrictions.eq("c.id", id));
		
		HibernatePaginator<Prize> hp = new HibernatePaginator<Prize>(criteria,options);
		
		return hp;
	}

	@Override
	public Prize getPrizeById(Integer id) {
		return findById(id);
	}

	@Override
	public void createPrize(Prize prize) {
		saveOrUpdate(prize);
	}

	@Override
	public void updatePrize(Prize prize) {
		saveOrUpdate(prize);
	}

	@Override
	public void deletePrize(Prize prize) {
		delete(prize);
	}

}
