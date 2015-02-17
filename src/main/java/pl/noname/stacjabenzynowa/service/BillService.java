package pl.noname.stacjabenzynowa.service;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.Bill;
import pl.noname.stacjabenzynowa.persistance.BillElement;
import pl.noname.stacjabenzynowa.service.dao.BillDao;
import pl.noname.stacjabenzynowa.service.dao.BillElementDao;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;
import pl.noname.stacjabenzynowa.web.paginator.BillOptions;

public interface BillService {
	public BillDao getBillDao();
	public BillElementDao getBillElementDao();
	
	public BillOptions getBillPaginatorOptions();
	
	public Bill getBillById(Integer id);	
	public void createBill(Bill bill);
	public void updateBill(Bill bill);
	public void deleteBill(Bill bill);
	public HibernatePaginator<Bill> getBillPaginator(Integer pageNumber, String order, Boolean ascing);
	public List<Bill> getBills();
	
	public BillElement getBillElementById(Integer id);	
	public void createBillElement(BillElement billElement);
	public void updateBillElement(BillElement billElement);
	public void deleteBillElement(BillElement billElement);
	public List<BillElement> getBillElements();
	public List<BillElement> getBillElementsByBillId(Integer id);
}
