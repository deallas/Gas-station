package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.BlockerAttempt;

public class EnumBlockerAttemptStatus extends PersistentEnumType<BlockerAttempt.Status>
{
	@Override
	public Class<BlockerAttempt.Status> returnedClass() {
		return BlockerAttempt.Status.class;
	}
}