package pl.noname.stacjabenzynowa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.Prize;
import pl.noname.stacjabenzynowa.persistance.PrizeCategory;
import pl.noname.stacjabenzynowa.service.PrizeService;
import pl.noname.stacjabenzynowa.service.dao.PrizeCategoryDao;
import pl.noname.stacjabenzynowa.service.dao.PrizeDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.PrizeCategoryOptions;
import pl.noname.stacjabenzynowa.web.paginator.PrizeOptions;

@Service("prizeService")
@Transactional(propagation=Propagation.REQUIRED)
public class PrizeServiceImpl implements PrizeService {

	@Autowired
	private PrizeDao prizeDao;
	
	@Autowired
	private PrizeCategoryDao prizeCategoryDao;
	
	@Autowired
	private PrizeOptions prizePaginator;
	
	@Autowired
	private PrizeCategoryOptions prizeCategoryPaginator;
	
	/* ----------------------------------------------------------- */
	
	public PrizeDao getPrizeDao() {
		return prizeDao;
	}

	public void setPrizeDao(PrizeDao prizeDao) {
		this.prizeDao = prizeDao;
	}

	public PrizeCategoryDao getPrizeCategoryDao() {
		return prizeCategoryDao;
	}

	public void setPrizeCategoryDao(PrizeCategoryDao prizeCategoryDao) {
		this.prizeCategoryDao = prizeCategoryDao;
	}
	
	public PrizeOptions getPrizePaginator() {
		return prizePaginator;
	}

	public void setPrizePaginator(PrizeOptions prizePaginator) {
		this.prizePaginator = prizePaginator;
	}

	public PrizeCategoryOptions getPrizeCategoryPaginator() {
		return prizeCategoryPaginator;
	}

	public void setPrizeCategoryPaginator(
			PrizeCategoryOptions prizeCategoryPaginator) {
		this.prizeCategoryPaginator = prizeCategoryPaginator;
	}
	
	/* ----------------------------------------------------------- */

	@Override
	@Transactional(readOnly=true)
	public List<Prize> getPrizes() {
		return prizeDao.getPrizes();
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<Prize> getPaginatorPrizes(Integer pageNumber, String order, Boolean ascing) {
		
		AbstractOptions options = getPrizePaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPrizePaginator().setPaginatorOptions(options);
		
		HibernatePaginator<Prize> paginator = prizeDao.getPaginatorPrizes(options);
		
		return paginator;
	}
	

	@Override
	public HibernatePaginator<Prize> getPaginatorPrizesByClientId(
			Integer pageNumber, String order, Boolean ascing, Integer id) {
		
		AbstractOptions options = getPrizePaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPrizePaginator().setPaginatorOptions(options);
		
		HibernatePaginator<Prize> paginator = prizeDao.getPaginatorPrizesByClientId(options, id);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public Prize getPrizeById(Integer id) {
		return prizeDao.getPrizeById(id);
	}

	@Override
	public void createPrize(Prize prize) {
		prizeDao.createPrize(prize);
	}

	@Override
	public void updatePrize(Prize prize) {
		prizeDao.updatePrize(prize);
	}

	@Override
	public void deletePrize(Prize prize) {
		prizeDao.deletePrize(prize);
	}

	@Override
	@Transactional(readOnly=true)
	public List<PrizeCategory> getPrizeCategories() {
		return prizeCategoryDao.getPrizeCategories();
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<PrizeCategory> getPaginatorPrizeCategories(
			Integer pageNumber, String order, Boolean ascing) {
		
		AbstractOptions options = getPrizeCategoryPaginator().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getPrizeCategoryPaginator().setPaginatorOptions(options);
		
		HibernatePaginator<PrizeCategory> paginator = prizeCategoryDao.getPaginatorPrizeCategories(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public PrizeCategory getPrizeCategoryById(Integer id) {
		return prizeCategoryDao.getPrizeCategoryById(id);
	}

	@Override
	public void createPrizeCategory(PrizeCategory category) {
		prizeCategoryDao.createPrizeCategory(category);
	}

	@Override
	public void updatePrizeCategory(PrizeCategory category) {
		prizeCategoryDao.updatePrizeCategory(category);
	}

	@Override
	public void deletePrizeCategory(PrizeCategory category) {
		prizeCategoryDao.deletePrizeCategory(category);
	}

}
