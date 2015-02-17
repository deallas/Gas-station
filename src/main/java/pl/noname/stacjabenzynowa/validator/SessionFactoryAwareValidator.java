package pl.noname.stacjabenzynowa.validator;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class SessionFactoryAwareValidator 
{  
	@Autowired
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
   	public final Session getCurrentSession() {
   		Session sess = null;       
   	    try {         
   	    	sess = sessionFactory.getCurrentSession();  
   	    } catch (org.hibernate.HibernateException he) {  
   	    	sess = sessionFactory.openSession();     
   	    }             
   	    return sess;
   	}
} 