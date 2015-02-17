package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.PrizeCategory;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PrizeCategoryDao extends GenericDao<PrizeCategory, Integer> {
	
	public List<PrizeCategory> getPrizeCategories();
	public HibernatePaginator<PrizeCategory> getPaginatorPrizeCategories(AbstractOptions options);
	public PrizeCategory getPrizeCategoryById(Integer id);
	
	public void createPrizeCategory(PrizeCategory category);	
	public void updatePrizeCategory(PrizeCategory category);
	public void deletePrizeCategory(PrizeCategory category);
}
