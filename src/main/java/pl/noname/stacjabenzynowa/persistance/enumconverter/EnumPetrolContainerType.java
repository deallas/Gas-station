package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;

public class EnumPetrolContainerType extends PersistentEnumType<PetrolContainer.Type>
{
	@Override
	public Class<PetrolContainer.Type> returnedClass() {
		return PetrolContainer.Type.class;
	}
}