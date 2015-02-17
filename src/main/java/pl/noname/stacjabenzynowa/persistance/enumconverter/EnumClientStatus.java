package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.Client;

public class EnumClientStatus extends PersistentEnumType<Client.Status>
{
	@Override
	public Class<Client.Status> returnedClass() {
		return Client.Status.class;
	}
}