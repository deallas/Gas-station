package pl.noname.stacjabenzynowa.service;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.Prize;
import pl.noname.stacjabenzynowa.persistance.PrizeCategory;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PrizeService {

	//Prize
	public List<Prize> getPrizes();
	public HibernatePaginator<Prize> getPaginatorPrizes(Integer pageNumber, String order, Boolean ascing);
	public HibernatePaginator<Prize> getPaginatorPrizesByClientId(Integer pageNumber, String order, Boolean ascing, Integer id);
	public Prize getPrizeById(Integer id);
	
	public void createPrize(Prize prize);	
	public void updatePrize(Prize prize);
	public void deletePrize(Prize prize);
	
	//Prize Category
	public List<PrizeCategory> getPrizeCategories();
	public HibernatePaginator<PrizeCategory> getPaginatorPrizeCategories(Integer pageNumber, String order, Boolean ascing);
	public PrizeCategory getPrizeCategoryById(Integer id);
	
	public void createPrizeCategory(PrizeCategory category);	
	public void updatePrizeCategory(PrizeCategory category);
	public void deletePrizeCategory(PrizeCategory category);
}
