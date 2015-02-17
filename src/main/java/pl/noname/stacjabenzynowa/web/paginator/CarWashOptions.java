package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class CarWashOptions extends SessionOptions {

	private static final long serialVersionUID = 1463028083281514873L;

	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public CarWashOptions() {
		super();
	}
	
	public CarWashOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	@Override
	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"carwash.datebeginwash",
			"carwash.dateendwash",
			"carwashtype.name"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
				"carwash.datebeginwash",
				"carwash.dateendwash",
				"carwashtype.name"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}

	@Override
	public Map<String, String> getDbOrders() {
if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("carwash.datebeginwash", "dateBeginWash");
		_dbOrders.put("carwash.dateendwash", "dateEndWash");
		_dbOrders.put("carwashtype.name", "cwt.name");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "carwashes";
	}

	@Override
	public String getDefaultOrder() {
		return "carwashtype.name";
	}

}
