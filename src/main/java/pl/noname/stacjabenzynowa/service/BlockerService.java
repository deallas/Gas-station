package pl.noname.stacjabenzynowa.service;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;
import pl.noname.stacjabenzynowa.service.dao.BlockerAttemptDao;

public interface BlockerService 
{
	public BlockerAttemptDao getBlockerAttemptDao();
	public void setBlockerAttemptDao(BlockerAttemptDao blockerAttemptDao);
	public DateTimeService getDateTimeService();
	public void setDateTimeService(DateTimeService dateTimeService);
	public Integer getNumberOfMaxAttempts();
	public void setNumberOfMaxAttempts(Integer numberOfMaxAttempts);
	public Integer getNumberOfBannedSeconds();

	public void setNumberOfBannedSeconds(Integer numberOfBannedSeconds);
	
	public BlockerAttempt addAttempt(String ip, AclResgroup resgroup);
	public void incrementAttempt(BlockerAttempt blockerAttempt);
	public void setAttemptExpired(BlockerAttempt blockerAttempt);
	public BlockerAttempt getActiveAttempt(String ip, AclResgroup resgroup);
	public Integer getCountAttempts();
}
