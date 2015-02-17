package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.PetrolDelivery;

public class EnumPetrolDeliveryStatus extends PersistentEnumType<PetrolDelivery.Status>
{
	@Override
	public Class<PetrolDelivery.Status> returnedClass() {
		return PetrolDelivery.Status.class;
	}
}