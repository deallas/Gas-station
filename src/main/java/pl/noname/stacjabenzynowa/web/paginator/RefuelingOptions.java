package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class RefuelingOptions extends SessionOptions {

	private static final long serialVersionUID = 4788361833282206288L;

	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public RefuelingOptions() {
		super();
	}
	
	public RefuelingOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	@Override
	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"refueling.cost",
			"refueling.fuel",
			"refueling.refuelingDate",
			"refueling.petrolContainer.id",
			"refueling.petrolContainer.type",
			"refueling.client.email",
			"refueling.refuelingType.name"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"refueling.cost",
			"refueling.fuel",
			"refueling.refuelingDate",
			"refueling.petrolContainer.id",
			"refueling.petrolContainer.type",
			"refueling.client.email",
			"refueling.refuelingType.name"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}

	@Override
	public Map<String, String> getDbOrders() {
if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("refueling.cost", "cost");
		_dbOrders.put("refueling.fuel", "fuel");
		_dbOrders.put("refueling.refuelingDate", "refuelingDate");
		_dbOrders.put("refueling.petrolContainer.id", "petrolContainer.id");
		_dbOrders.put("refueling.petrolContainer.type", "petrolContainer.type");
		_dbOrders.put("refueling.client.email", "client.email");
		_dbOrders.put("refueling.refuelingType.name", "refuelingType.name");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "refuelings";
	}

	@Override
	public String getDefaultOrder() {
		return "refueling.refuelingDate";
	}

}
