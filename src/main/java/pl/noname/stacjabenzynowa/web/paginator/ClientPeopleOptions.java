package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class ClientPeopleOptions extends SessionOptions
{
	
	private static final long serialVersionUID = -8836949541054529119L;
	
	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public ClientPeopleOptions() {
		super();
	}
	
	public ClientPeopleOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	/* ----------------------------------------------------------- */

	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"client.email",
			"client.status",
			"clientPeople.name", 
			"clientPeople.surname",
			"clientPeople.pesel",
			"client.nip"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"client.email",
			"clientPeople.name",
			"clientPeople.surname",
			"clientPeople.pesel",
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}
	
	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("client.email", "c.email");
		_dbOrders.put("client.status", "c.status");
		_dbOrders.put("clientPeople.name", "name");
		_dbOrders.put("clientPeople.surname", "surname");
		_dbOrders.put("clientPeople.pesel", "pesel");
		_dbOrders.put("client.nip", "c.nip");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "clientPeople";
	}

	@Override
	public String getDefaultOrder() {
		return "client.email";
	}
}
