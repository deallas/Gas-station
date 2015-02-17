package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.Bill;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface BillDao extends GenericDao<Bill,Integer>{
	public Bill getBillById(Integer id);	
	public List<Bill> getBills();
	public HibernatePaginator<Bill> getPaginatorBills(AbstractOptions options);
	
	public void createBill(Bill bill);
	public void updateBill(Bill bill);
	public void deleteBill(Bill bill);
}
