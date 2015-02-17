package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class PetrolDeliveryOptions extends SessionOptions {
	
	private static final long serialVersionUID = -7736154368056930490L;
	
	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public PetrolDeliveryOptions() {
		super();
	}
	
	public PetrolDeliveryOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	/* ----------------------------------------------------------- */

	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"petrolDelivery.status",
			"petrolDelivery.deliveryDate",
			"petrolDelivery.quantity",
			"petrolDelivery.cost",
			"petrolDelivery.orderDate",
			"petrolDelivery.petrolProvider.companyName"	
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"petrolDelivery.status",
			"petrolDelivery.deliveryDate",
			"petrolDelivery.quantity",
			"petrolDelivery.cost",
			"petrolDelivery.orderDate",
			"petrolDelivery.petrolProvider.companyName"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}
	
	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("petrolDelivery.status", "status");
		_dbOrders.put("petrolDelivery.deliveryDate", "deliveryDate");
		_dbOrders.put("petrolDelivery.quantity", "quantity");
		_dbOrders.put("petrolDelivery.cost", "cost");
		_dbOrders.put("petrolDelivery.orderDate", "orderDate");
		_dbOrders.put("petrolDelivery.petrolProvider.companyName", "petrolProvider.companyName");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "petrolDeliveries";
	}

	@Override
	public String getDefaultOrder() {
		return "petrolDelivery.status";
	}
}
