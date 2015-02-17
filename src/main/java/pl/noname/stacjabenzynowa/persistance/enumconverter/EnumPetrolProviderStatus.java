package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.PetrolProvider;

public class EnumPetrolProviderStatus extends PersistentEnumType<PetrolProvider.Status>
{
	@Override
	public Class<PetrolProvider.Status> returnedClass() {
		return PetrolProvider.Status.class;
	}
}