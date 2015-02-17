package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.Session;

public class EnumSessionStatus extends PersistentEnumType<Session.Status>
{
	@Override
	public Class<Session.Status> returnedClass() {
		return Session.Status.class;
	}
}