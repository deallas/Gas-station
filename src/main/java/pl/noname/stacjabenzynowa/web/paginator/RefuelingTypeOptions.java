package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class RefuelingTypeOptions extends SessionOptions {

	private static final long serialVersionUID = 5115109269410769770L;

	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public RefuelingTypeOptions() {
		super();
	}
	
	public RefuelingTypeOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	@Override
	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"refuelingType.name",
			"refuelingType.cost",
			"refuelingType.loyalityPoints"
		};
		
		_availableOrders = new ArrayList<String>(Arrays.asList(orders));
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"refuelingType.name",
			"refuelingType.cost",
			"refuelingType.loyalityPoints"
		};
		
		_defaultOrders = new ArrayList<String>(Arrays.asList(orders));
		
		return _defaultOrders;
	}

	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("refuelingType.name", "name");
		_dbOrders.put("refuelingType.cost", "cost");
		_dbOrders.put("refuelingType.loyalityPoints", "loyalityPoints");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "refuelingTypes";
	}

	@Override
	public String getDefaultOrder() {
		return "refuelingType.name";
	}

}
