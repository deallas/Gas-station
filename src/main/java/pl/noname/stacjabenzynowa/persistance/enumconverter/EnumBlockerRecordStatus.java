package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.BlockerRecord;

public class EnumBlockerRecordStatus extends PersistentEnumType<BlockerRecord.Status>
{
	@Override
	public Class<BlockerRecord.Status> returnedClass() {
		return BlockerRecord.Status.class;
	}
}