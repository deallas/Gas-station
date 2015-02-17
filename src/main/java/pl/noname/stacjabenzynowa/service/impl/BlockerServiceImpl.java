package pl.noname.stacjabenzynowa.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;
import pl.noname.stacjabenzynowa.persistance.AclResgroup;
import pl.noname.stacjabenzynowa.service.BlockerService;
import pl.noname.stacjabenzynowa.service.DateTimeService;
import pl.noname.stacjabenzynowa.service.dao.BlockerAttemptDao;

@Service("blockerAttemptService")
public class BlockerServiceImpl implements BlockerService
{
	private Logger logger = LoggerFactory.getLogger(BlockerServiceImpl.class);
	
	@Autowired
	private BlockerAttemptDao blockerAttemptDao;
	
	@Autowired
	private DateTimeService dateTimeService;
	
	@Value("${blocker.maxAttempts}")
	private Integer numberOfMaxAttempts;
	
	@Value("${blocker.banSeconds}")
	private Integer numberOfBannedSeconds;
	
	/* ----------------------------------------------------------- */
	
	@Override
	public BlockerAttemptDao getBlockerAttemptDao() {
		return blockerAttemptDao;
	}

	@Override
	public void setBlockerAttemptDao(BlockerAttemptDao blockerAttemptDao) {
		this.blockerAttemptDao = blockerAttemptDao;
	}
	
	@Override
	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	@Override
	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}
	
	@Override
	public Integer getNumberOfMaxAttempts() {
		return numberOfMaxAttempts;
	}

	@Override
	public void setNumberOfMaxAttempts(Integer numberOfMaxAttempts) {
		this.numberOfMaxAttempts = numberOfMaxAttempts;
	}

	@Override
	public Integer getNumberOfBannedSeconds() {
		return numberOfBannedSeconds;
	}
	
	@Override
	public void setNumberOfBannedSeconds(Integer numberOfBannedSeconds) {
		this.numberOfBannedSeconds = numberOfBannedSeconds;
	}
	
	/* ----------------------------------------------------------- */

	@Override
	@Transactional(readOnly=false)
	public BlockerAttempt addAttempt(String ip, AclResgroup resgroup) {
		Date date = dateTimeService.getCurrentDate();
		logger.debug("Current date: " + date.toString());
		logger.debug("Numer of banned seconds: " + numberOfBannedSeconds);
		
		return blockerAttemptDao.addAttempt(ip, resgroup, numberOfBannedSeconds, date);
	}
	
	@Override
	@Transactional(readOnly=false)
	public void incrementAttempt(BlockerAttempt blockerAttempt) {
		Date date = dateTimeService.getCurrentDate();
		logger.debug("Current date: " + date.toString());
		logger.debug("Numer of banned seconds: " + numberOfBannedSeconds);
		
		blockerAttemptDao.incrementAttempt(blockerAttempt, numberOfBannedSeconds, date);
	}

	@Override
	@Transactional(readOnly=false)
	public void setAttemptExpired(BlockerAttempt blockerAttempt) {
		blockerAttemptDao.setAttemptExpired(blockerAttempt);
	}

	@Override
	@Transactional(readOnly=true)
	public BlockerAttempt getActiveAttempt(String ip, AclResgroup resgroup) {
		Date date = dateTimeService.getCurrentDate();
		logger.debug("Current date: " + date.toString());
		
		return blockerAttemptDao.getActiveAttempt(ip, resgroup, date);
	}

	@Override
	@Transactional(readOnly=true)
	public Integer getCountAttempts() {
		return blockerAttemptDao.findCount();
	}

}
