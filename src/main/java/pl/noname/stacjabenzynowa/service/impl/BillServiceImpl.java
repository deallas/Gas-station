package pl.noname.stacjabenzynowa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.Bill;
import pl.noname.stacjabenzynowa.persistance.BillElement;
import pl.noname.stacjabenzynowa.service.BillService;
import pl.noname.stacjabenzynowa.service.dao.BillDao;
import pl.noname.stacjabenzynowa.service.dao.BillElementDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.BillOptions;

@Service("billService")
@Transactional(propagation=Propagation.REQUIRED)
public class BillServiceImpl implements BillService{
	
	@Autowired
	private BillDao billDao;
	
	@Autowired
	private BillElementDao billElementDao;
	
	@Autowired
	private BillOptions billPaginatorOptions;

	@Override
	public BillDao getBillDao() {
		return billDao;
	}

	@Override
	public BillElementDao getBillElementDao() {
		return billElementDao;
	}

	@Override
	public BillOptions getBillPaginatorOptions() {
		return billPaginatorOptions;
	}

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	public void setBillElementDao(BillElementDao billElementDao) {
		this.billElementDao = billElementDao;
	}

	public void setBillPaginatorOptions(BillOptions billPaginatorOptions) {
		this.billPaginatorOptions = billPaginatorOptions;
	}

	@Override
	@Transactional(readOnly=true)
	public Bill getBillById(Integer id) {
		return billDao.getBillById(id);
	}

	@Override
	@Transactional
	public void createBill(Bill bill) {
		billDao.createBill(bill);
	}

	@Override
	@Transactional
	public void updateBill(Bill bill) {
		billDao.updateBill(bill);
	}

	@Override
	@Transactional
	public void deleteBill(Bill bill) {
		billDao.deleteBill(bill);
	}

	@Override
	@Transactional(readOnly=true)
	public HibernatePaginator<Bill> getBillPaginator(Integer pageNumber,
			String order, Boolean ascing) {

		AbstractOptions options = billPaginatorOptions.getPaginatorOptions();
		
		if(pageNumber != null) {
			options.setPageNumber(pageNumber);
		}
		if(order != null) {
			options.setOrder(order);
		}
		if(ascing != null) {
			options.setAscing(ascing);
		}
		
		options = billPaginatorOptions.setPaginatorOptions(options);
		HibernatePaginator<Bill> paginator = billDao.getPaginatorBills(options);
		
		return paginator;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Bill> getBills() {
		return billDao.getBills();
	}

	@Override
	public BillElement getBillElementById(Integer id) {
		return billElementDao.getBillElementById(id);
	}

	@Override
	public void createBillElement(BillElement billElement) {
		billElementDao.createBillElement(billElement);
	}

	@Override
	public void updateBillElement(BillElement billElement) {
		billElementDao.updateBillElement(billElement);
	}

	@Override
	public void deleteBillElement(BillElement billElement) {
		billElementDao.deleteBillElement(billElement);
	}

	@Override
	public List<BillElement> getBillElements() {
		return billElementDao.getBillElement();
	}

	@Override
	public List<BillElement> getBillElementsByBillId(Integer id) {
		return billElementDao.getBillElementsByBillId(id);
	}

}
