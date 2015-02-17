package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class EmployeeOptions extends SessionOptions
{
	private static final long serialVersionUID = -5639787417730035483L;
	
	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public EmployeeOptions() {
		super();
	}
	
	public EmployeeOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	/* ----------------------------------------------------------- */

	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"employee.email",
			"employee.status",
			"employee.name", 
			"employee.surname",
			"role.name",
			"employee.pesel",
			"employee.nip"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"employee.email",
			"employee.status",
			"employee.name", 
			"employee.surname",
			"role.name"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}
	
	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("employee.email", "email");
		_dbOrders.put("employee.status", "status");
		_dbOrders.put("employee.name", "name");
		_dbOrders.put("employee.surname", "surname");
		_dbOrders.put("role.name", "r.name");
		_dbOrders.put("employee.pesel", "pesel");
		_dbOrders.put("employee.nip", "nip");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "employees";
	}

	@Override
	public String getDefaultOrder() {
		return "employee.email";
	}
}
