package pl.noname.stacjabenzynowa.service.dao;

import java.util.List;

import pl.noname.stacjabenzynowa.persistance.PetrolContainerLog;
import pl.noname.stacjabenzynowa.util.paginator.AbstractOptions;
import pl.noname.stacjabenzynowa.util.paginator.HibernatePaginator;

public interface PetrolContainerLogDao extends GenericDao<PetrolContainerLog, Integer> {
	
	public List<PetrolContainerLog> getPetrolContainerLogs();
	public HibernatePaginator<PetrolContainerLog> getPaginatorPetrolContainerLogs(AbstractOptions options);
	public PetrolContainerLog getPetrolContainerLogById(Integer id);	
	
	public void createPetrolContainerLog(PetrolContainerLog log);	
	public void updatePetrolContainerLog(PetrolContainerLog log);
	public void deletePetrolContainerLog(PetrolContainerLog log);
}
