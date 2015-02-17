package pl.noname.stacjabenzynowa.service.impl;

import java.util.Date;
import org.springframework.stereotype.Service;

import pl.noname.stacjabenzynowa.service.DateTimeService;

@Service("dateTimeService")
public class DateTimeServiceImpl implements DateTimeService
{
	@Override
	public Date getCurrentDate() {
		return new Date();
	}
}
