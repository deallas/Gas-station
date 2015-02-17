package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.ClientPeople;

public class EnumClientPeopleGender extends PersistentEnumType<ClientPeople.Gender>
{
	@Override
	public Class<ClientPeople.Gender> returnedClass() {
		return ClientPeople.Gender.class;
	}
}