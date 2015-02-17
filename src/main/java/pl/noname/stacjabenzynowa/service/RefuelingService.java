package pl.noname.stacjabenzynowa.service;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.Refueling;
import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface RefuelingService 
{
	public List<RefuelingType> getRefuelingTypes();
	public List<Refueling> getRefuelings();
	public HibernatePaginator<RefuelingType> getPaginatorRefuelingTypes(Integer pageNumber, String order, Boolean ascing);
	public HibernatePaginator<Refueling> getPaginatorRefuelings(Integer pageNumber, String order, Boolean ascing);
	
	public RefuelingType getRefuelingTypeById(Integer id);
	public Refueling getRefuelingById(Integer id);
	
	public void createRefuelingType(RefuelingType type);	
	public void updateRefuelingType(RefuelingType type);
	public void deleteRefuelingType(RefuelingType type);
	
	public void createRefueling(Refueling refueling);	
	public void updateRefueling(Refueling refueling);
	public void deleteRefueling(Refueling refueling);
}
