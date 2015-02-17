package pl.noname.stacjabenzynowa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.Refueling;
import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.service.RefuelingService;
import pl.noname.stacjabenzynowa.service.dao.RefuelingDao;
import pl.noname.stacjabenzynowa.service.dao.RefuelingTypeDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.RefuelingOptions;
import pl.noname.stacjabenzynowa.web.paginator.RefuelingTypeOptions;

@Service("refuelingService")
@Transactional(propagation=Propagation.REQUIRED)
public class RefuelingServiceImpl implements RefuelingService
{
	@Autowired
	private RefuelingTypeDao refuelingTypeDao;
	
	@Autowired
	private RefuelingDao refuelingDao;
	
	@Autowired
	private RefuelingTypeOptions refuelingTypesPaginatorOptions;
	
	@Autowired
	private RefuelingOptions refuelingPaginatorOptions;
	
	/* ----------------------------------------------------------- */
	
	public RefuelingTypeOptions getRefuelingTypesPaginatorOptions() {
		return refuelingTypesPaginatorOptions;
	}

	public void setRefuelingTypesPaginatorOptions(
			RefuelingTypeOptions refuelingTypesPaginatorOptions) {
		this.refuelingTypesPaginatorOptions = refuelingTypesPaginatorOptions;
	}
	
	public RefuelingOptions getRefuelingPaginatorOptions() {
		return refuelingPaginatorOptions;
	}

	public void setRefuelingPaginatorOptions(
			RefuelingOptions refuelingPaginatorOptions) {
		this.refuelingPaginatorOptions = refuelingPaginatorOptions;
	}

	public RefuelingTypeDao getRefuelingTypeDao() {
		return refuelingTypeDao;
	}

	public void setRefuelingTypeDao(RefuelingTypeDao refuelingTypeDao) {
		this.refuelingTypeDao = refuelingTypeDao;
	}

	public RefuelingDao getRefuelingDao() {
		return refuelingDao;
	}

	public void setRefuelingDao(RefuelingDao refuelingDao) {
		this.refuelingDao = refuelingDao;
	}
	
	/* ----------------------------------------------------------- */

	@Override
	@Transactional(readOnly=true)
	public List<RefuelingType> getRefuelingTypes() {
		return refuelingTypeDao.getRefuelingTypes();
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<RefuelingType> getPaginatorRefuelingTypes(
			Integer pageNumber, String order, Boolean ascing) {

		AbstractOptions options = getRefuelingTypesPaginatorOptions().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getRefuelingTypesPaginatorOptions().setPaginatorOptions(options);
		
		HibernatePaginator<RefuelingType> paginator = refuelingTypeDao.getPaginatorRefuelingTypes(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Refueling> getRefuelings() {
		return refuelingDao.getRefuelings();
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<Refueling> getPaginatorRefuelings(
			Integer pageNumber, String order, Boolean ascing) {
		AbstractOptions options = getRefuelingPaginatorOptions().getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = getRefuelingPaginatorOptions().setPaginatorOptions(options);
		
		HibernatePaginator<Refueling> paginator = refuelingDao.getPaginatorRefuelings(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public RefuelingType getRefuelingTypeById(Integer id) {
		return refuelingTypeDao.getRefuelingTypeById(id);
	}

	@Override
	@Transactional(readOnly=true)
	public Refueling getRefuelingById(Integer id) {
		return refuelingDao.getRefuelingById(id);
	}

	@Override
	public void createRefuelingType(RefuelingType type) {
		refuelingTypeDao.createRefuelingType(type);
	}

	@Override
	public void updateRefuelingType(RefuelingType type) {
		refuelingTypeDao.updateRefuelingType(type);
	}

	@Override
	public void deleteRefuelingType(RefuelingType type) {
		refuelingTypeDao.deleteRefuelingType(type);
	}

	@Override
	public void createRefueling(Refueling refueling) {
		refuelingDao.createRefueling(refueling);
	}

	@Override
	public void updateRefueling(Refueling refueling) {
		refuelingDao.updateRefueling(refueling);
	}

	@Override
	public void deleteRefueling(Refueling refueling) {
		refuelingDao.deleteRefueling(refueling);
	}

}
