package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;

import pl.noname.stacjabenzynowa.persistance.Bill;
import pl.noname.stacjabenzynowa.service.dao.BillDao;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;


public class BillDaoImpl extends GenericDaoImpl<Bill, Integer> implements BillDao  {

	@Override
	public Bill getBillById(Integer id) {
		return findById(id);
	}

	@Override
	public List<Bill> getBills() {
		return findAll();
	}

	@Override
	public HibernatePaginator<Bill> getPaginatorBills(AbstractOptions options) {
		Criteria criteria = getSession().createCriteria(Bill.class);
		criteria.setFetchMode("client", FetchMode.JOIN);
		criteria.createAlias("client", "cli");
		criteria.setFetchMode("employee", FetchMode.JOIN);
		criteria.createAlias("employee", "emp");
		
		HibernatePaginator<Bill> hp = new HibernatePaginator<Bill>(criteria,options);
		
		return hp;
	}

	@Override
	public void createBill(Bill bill) {
		saveOrUpdate(bill);
	}

	@Override
	public void updateBill(Bill bill) {
		saveOrUpdate(bill);
	}

	@Override
	public void deleteBill(Bill bill) {
		delete(bill);
	}

}
