package pl.noname.stacjabenzynowa.service.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public interface GenericDao<T, PK extends Serializable> {
    public T findById(PK id);
    public List<T> findAll();
    public T saveOrUpdate(T obj);
    public void delete(T obj);
    public List<T> findByCriteria(Criterion... criterion);
    public Integer findCount(Criterion... criterion);
    public Session getSession();
}
