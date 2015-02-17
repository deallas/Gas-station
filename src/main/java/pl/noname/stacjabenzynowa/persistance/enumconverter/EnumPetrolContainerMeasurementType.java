package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;

public class EnumPetrolContainerMeasurementType 
			extends PersistentEnumType<PetrolContainerMeasurement.Type>
{
	@Override
	public Class<PetrolContainerMeasurement.Type> returnedClass() {
		return PetrolContainerMeasurement.Type.class;
	}
}