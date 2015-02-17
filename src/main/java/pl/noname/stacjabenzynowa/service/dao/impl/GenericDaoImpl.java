package pl.noname.stacjabenzynowa.service.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

import pl.noname.stacjabenzynowa.service.dao.GenericDao;

public abstract class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

    @SuppressWarnings("rawtypes")
	private Class domainClass = (Class) ((ParameterizedType) getClass()
            .getGenericSuperclass()).getActualTypeArguments()[0];

    @Autowired
    protected SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
	public T findById(PK id) {
        return (T) getSession().get(domainClass, id);
    }

    public List<T> findAll() {
        return findByCriteria();
    }

    public T saveOrUpdate(T obj) {
    	getSession().clear();
        getSession().saveOrUpdate(obj);
        return obj;
    }

    public void delete(T obj) {
        getSession().delete(obj);
    }

    public List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(domainClass);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        @SuppressWarnings("unchecked")
		List<T> list = crit.list();
        return list;
    }
    
	public Integer findCount(Criterion... criterion){
		Criteria crit = getSession().createCriteria(domainClass).setProjection(
					Projections.projectionList().add(Projections.rowCount())
					);
		if(criterion !=null) for (Criterion c: criterion) crit.add(c);
		return ((Long)crit.uniqueResult()).intValue();
	}

	public Session getSession() {
  		Session sess = null;       
   	    try {         
   	    	sess = sessionFactory.getCurrentSession();  
   	    } catch (org.hibernate.HibernateException he) {  
   	    	sess = sessionFactory.openSession();     
   	    }             
   	    return sess;
	}
}