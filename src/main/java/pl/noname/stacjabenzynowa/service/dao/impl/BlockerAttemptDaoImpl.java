package pl.noname.stacjabenzynowa.service.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;
import pl.noname.stacjabenzynowa.persistance.BlockerAttempt.Status;
import pl.noname.stacjabenzynowa.service.dao.BlockerAttemptDao;

@Repository("blockerAttemptDao")
public class BlockerAttemptDaoImpl extends GenericDaoImpl<BlockerAttempt,Integer> implements BlockerAttemptDao 
{
	private Logger logger = LoggerFactory.getLogger(BlockerAttemptDaoImpl.class);
	
	@Override
	public BlockerAttempt addAttempt(String ip, AclResgroup resgroup, Integer numberOfBannedSeconds, Date currentDate) {
		BlockerAttempt blockerAttempt = new BlockerAttempt();
		blockerAttempt.setIp(ip);
		blockerAttempt.setAttempts(1);
		blockerAttempt.setStatus(BlockerAttempt.Status.ACTIVE);
		blockerAttempt.setResgroup(resgroup);

		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.SECOND, numberOfBannedSeconds);
		blockerAttempt.setDateExpired(cal.getTime());
		logger.debug("Add attempt (date: " + cal.toString() + ")");
		
		saveOrUpdate(blockerAttempt);
		
		return blockerAttempt;
	}
	
	@Override
	public void incrementAttempt(BlockerAttempt blockerAttempt, Integer numberOfBannedSeconds, Date currentDate) {
		blockerAttempt.setAttempts(blockerAttempt.getAttempts()+1);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(currentDate);
		cal.add(Calendar.SECOND, numberOfBannedSeconds);
		blockerAttempt.setDateExpired(cal.getTime());
		logger.debug("Increment attempt (date: " + cal.toString() + ", trial: " + blockerAttempt.getAttempts() + ")");
		
		saveOrUpdate(blockerAttempt);
	}

	@Override
	public void setAttemptExpired(BlockerAttempt blockerAttempt) {
		blockerAttempt.setStatus(Status.EXPIRED);
		logger.debug("Attempts set expired");
		
		saveOrUpdate(blockerAttempt);
	}

	@Override
	public BlockerAttempt getActiveAttempt(String ip, AclResgroup resgroup, Date currentDate) {
		Criterion crit = Restrictions.and(
			Restrictions.eq("ip", ip),
			Restrictions.eq("status", BlockerAttempt.Status.ACTIVE),
			Restrictions.eq("resgroup", resgroup),
			Restrictions.ge("dateExpired", currentDate)	
		);
		List<BlockerAttempt> attempts = findByCriteria(crit);
		if(!attempts.isEmpty()){
			return attempts.get(0);
		}
		return null;
	}
}
