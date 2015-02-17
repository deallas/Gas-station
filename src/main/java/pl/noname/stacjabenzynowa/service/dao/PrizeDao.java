package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.Prize;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PrizeDao extends GenericDao<Prize, Integer> {

	public List<Prize> getPrizes();
	public HibernatePaginator<Prize> getPaginatorPrizes(AbstractOptions options);
	public HibernatePaginator<Prize> getPaginatorPrizesByClientId(AbstractOptions options, Integer id);
	public Prize getPrizeById(Integer id);
	
	public void createPrize(Prize prize);	
	public void updatePrize(Prize prize);
	public void deletePrize(Prize prize);
}
