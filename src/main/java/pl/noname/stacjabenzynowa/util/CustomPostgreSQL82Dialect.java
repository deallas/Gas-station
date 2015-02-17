package pl.noname.stacjabenzynowa.util;

import java.util.Properties;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.PostgreSQL82Dialect;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.Type;

/**
 * Creates a sequence per table instead of the default behavior of one sequence.
 *
 * From <a href='http://www.hibernate.org/296.html'>http://www.hibernate.org/296.html</a>
 * @author Burt
 */
public class CustomPostgreSQL82Dialect extends PostgreSQL82Dialect {

   /**
    * Get the native identifier generator class.
    * @return TableNameSequenceGenerator.
    */
   @Override
   public Class<?> getNativeIdentifierGeneratorClass() {
       return TableNameSequenceGenerator.class;
   }

   /**
    * Creates a sequence per table instead of the default behavior of one sequence.
    */
   public static class TableNameSequenceGenerator extends SequenceGenerator 
   {
       @Override
       public void configure(final Type type,final Properties params,final Dialect dialect) {
    	   if (params.getProperty(SEQUENCE) == null || params.getProperty(SEQUENCE).length() == 0) {
        	   String tableName = params.getProperty(PersistentIdentifierGenerator.TABLE);
               String pk = params.getProperty(PK);
               pk.toUpperCase();
               StringBuilder column = new StringBuilder(pk);
               column.setCharAt(pk.length()-1, 'd');
               column.setCharAt(pk.length()-6, 'k');
               if (tableName != null) {
            	   params.setProperty(SEQUENCE, tableName+"_"+column+"_seq");
               }
           }
           super.configure(type, params, dialect);
       }
   }
}
