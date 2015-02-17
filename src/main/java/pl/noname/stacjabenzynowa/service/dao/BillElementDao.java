package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.BillElement;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface BillElementDao extends GenericDao<BillElement,Integer> {
	public BillElement getBillElementById(Integer id);	
	public List<BillElement> getBillElement();
	public List<BillElement> getBillElementsByBillId(Integer id);
	public HibernatePaginator<BillElement> getPaginatorBillElements(AbstractOptions options);
	
	public void createBillElement(BillElement billElement);
	public void updateBillElement(BillElement billElement);
	public void deleteBillElement(BillElement billElement);
}
