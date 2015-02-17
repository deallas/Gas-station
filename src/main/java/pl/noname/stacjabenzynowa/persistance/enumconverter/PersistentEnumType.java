package pl.noname.stacjabenzynowa.persistance.enumconverter;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@SuppressWarnings("rawtypes")
public abstract class PersistentEnumType<T extends Enum> implements UserType  {
 
	private static final int[] SQL_TYPES = new int[]{Types.OTHER};

	public int[] sqlTypes() {
		return SQL_TYPES;
	}
	
    @Override
    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }
 
    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }
 
    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable)value;
    }
 
    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return x == y;
    }
 
    @Override
    public int hashCode(Object x) throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }
 
    @Override
    public boolean isMutable() {
        return false;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor si, Object owner)
            throws HibernateException, SQLException {
        String s = rs.getString(names[0]);
        if(rs.wasNull()) {
            return null;
        }
        return Enum.valueOf(returnedClass(), s);
    }
 
    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor si)
            throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, Types.OTHER);
        } else {
        	st.setString(index,((Enum) value).name());
            st.setObject(index,((Enum) value), Types.OTHER);
        }
    }
 
    @Override
    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }
 
    @Override
    public abstract Class<T> returnedClass();
}
