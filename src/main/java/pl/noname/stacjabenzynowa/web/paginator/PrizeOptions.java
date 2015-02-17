package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class PrizeOptions extends SessionOptions {

	private static final long serialVersionUID = 2659682891564321084L;
	
	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public PrizeOptions() {
		super();
	}
	
	public PrizeOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	/* ----------------------------------------------------------- */

	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
				
			"prize.dateOfSelection",
			"prize.status",
			"prize.client.email",
			"prize.category.name"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"prize.dateOfSelection",
			"prize.status",
			"prize.client.email",
			"prize.category.name"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}
	
	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("prize.dateOfSelection", "dateOfSelection");
		_dbOrders.put("prize.status", "status");
		_dbOrders.put("prize.client.email", "client.email");
		_dbOrders.put("prize.category.name", "category.name");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "prizes";
	}

	@Override
	public String getDefaultOrder() {
		return "prize.status";
	}

}
