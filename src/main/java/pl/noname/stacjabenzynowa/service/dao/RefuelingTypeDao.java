package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface RefuelingTypeDao extends GenericDao<RefuelingType, Integer>
{
	public List<RefuelingType> getRefuelingTypes();
	public HibernatePaginator<RefuelingType> getPaginatorRefuelingTypes(AbstractOptions options);
	public RefuelingType getRefuelingTypeById(Integer id);
	
	public void createRefuelingType(RefuelingType type);	
	public void updateRefuelingType(RefuelingType type);
	public void deleteRefuelingType(RefuelingType type);
}
