package pl.noname.stacjabenzynowa.persistance.enumconverter;

import pl.noname.stacjabenzynowa.persistance.News;

public class EnumNewsStatus extends PersistentEnumType<News.Status>
{
	@Override
	public Class<News.Status> returnedClass() {
		return News.Status.class;
	}
}