package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pl.noname.stacjabenzynowa.persistance.BillElement;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.service.dao.BillElementDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;


public class BillElementDaoImpl extends GenericDaoImpl<BillElement, Integer> implements BillElementDao   {

	@Override
	public BillElement getBillElementById(Integer id) {
		return findById(id);
	}
	
	@Override
	public List<BillElement> getBillElementsByBillId(Integer id) {
		
		Criteria crit = getSession().createCriteria(BillElement.class).add(Restrictions.eq("bill.id", id));
		List<BillElement> list = (List<BillElement>)crit.list();
		if(!list.isEmpty())
			return list;
		else
			return null;
	}

	@Override
	public List<BillElement> getBillElement() {
		return findAll();
	}

	@Override
	public HibernatePaginator<BillElement> getPaginatorBillElements(
			AbstractOptions options) {
		Criteria criteria = getSession().createCriteria(BillElement.class);
		
		HibernatePaginator<BillElement> hp = new HibernatePaginator<BillElement>(criteria,options);
		
		return hp;
	}

	@Override
	public void createBillElement(BillElement billElement) {
		saveOrUpdate(billElement);
	}

	@Override
	public void updateBillElement(BillElement billElement) {
		saveOrUpdate(billElement);
	}

	@Override
	public void deleteBillElement(BillElement billElement) {
		delete(billElement);
	}

}
