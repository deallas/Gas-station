package pl.noname.stacjabenzynowa.util;

import org.hibernate.cfg.DefaultNamingStrategy;

public class CustomNamingStrategy extends DefaultNamingStrategy
{
	private static final long serialVersionUID = -6439587726772795542L;
	
	@Override
	public String columnName(String columnName){
		return addQuotes(super.columnName(columnName));
	}
	
    /**
     * Adds backticks before and after the name.
     * 
     * @param input
     *          the input to quote
     * @return the quoted input
     */
    private static String addQuotes(String input)
    {
    	return new StringBuffer().append('`')
                         .append(input)
                         .append('`').toString();
    }
}
