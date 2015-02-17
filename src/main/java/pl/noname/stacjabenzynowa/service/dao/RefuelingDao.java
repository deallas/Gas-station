package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.Refueling;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface RefuelingDao extends GenericDao<Refueling,Integer>
{
	public List<Refueling> getRefuelings();
	public HibernatePaginator<Refueling> getPaginatorRefuelings(AbstractOptions options);
	public Refueling getRefuelingById(Integer id);

	public void createRefueling(Refueling refueling);	
	public void updateRefueling(Refueling refueling);
	public void deleteRefueling(Refueling refueling);
}
