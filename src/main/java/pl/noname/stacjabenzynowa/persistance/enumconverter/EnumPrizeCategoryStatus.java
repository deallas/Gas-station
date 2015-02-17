package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.PrizeCategory;

public class EnumPrizeCategoryStatus extends PersistentEnumType<PrizeCategory.Status>
{
	@Override
	public Class<PrizeCategory.Status> returnedClass() {
		return PrizeCategory.Status.class;
	}
}