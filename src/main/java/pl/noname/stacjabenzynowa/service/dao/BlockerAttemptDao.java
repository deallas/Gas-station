package pl.noname.stacjabenzynowa.service.dao;

import java.util.Date;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;

public interface BlockerAttemptDao extends GenericDao<BlockerAttempt,Integer> 
{
	public BlockerAttempt addAttempt(String ip, AclResgroup resgroup, Integer numberOfBannedSeconds, Date currentDate);
	public void incrementAttempt(BlockerAttempt blockerAttempt, Integer numberOfBannedSeconds, Date currentDate);
	public void setAttemptExpired(BlockerAttempt blockerAttempt);
	public BlockerAttempt getActiveAttempt(String ip, AclResgroup resgroup, Date currentDate);
}
