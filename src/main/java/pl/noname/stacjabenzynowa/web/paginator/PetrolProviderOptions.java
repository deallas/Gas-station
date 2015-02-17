package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class PetrolProviderOptions extends SessionOptions {
	
	private static final long serialVersionUID = -159205782981663276L;
	
	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public PetrolProviderOptions() {
		super();
	}
	
	public PetrolProviderOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	/* ----------------------------------------------------------- */

	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
				
			"petrolProvider.companyName",
			"petrolProvider.status",
			"petrolProvider.nip",
			"petrolProvider.regon",
			"petrolProvider.email",
			"petrolProvider.address",
			"petrolProvider.phoneNumber"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"petrolProvider.companyName",
			"petrolProvider.status",
			"petrolProvider.nip",
			"petrolProvider.regon",
			"petrolProvider.email",
			"petrolProvider.address",
			"petrolProvider.phoneNumber"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}
	
	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("petrolProvider.companyName", "companyName");
		_dbOrders.put("petrolProvider.status", "status");
		_dbOrders.put("petrolProvider.nip", "nip");
		_dbOrders.put("petrolProvider.regon", "regon");
		_dbOrders.put("petrolProvider.email", "email");
		_dbOrders.put("petrolProvider.address", "address");
		_dbOrders.put("petrolProvider.phoneNumber", "phoneNumber");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "petrolProviders";
	}

	@Override
	public String getDefaultOrder() {
		return "petrolProvider.companyName";
	}
}
