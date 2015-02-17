package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.Prize;

public class EnumPrizeStatus extends PersistentEnumType<Prize.Status>
{
	@Override
	public Class<Prize.Status> returnedClass() {
		return Prize.Status.class;
	}
}