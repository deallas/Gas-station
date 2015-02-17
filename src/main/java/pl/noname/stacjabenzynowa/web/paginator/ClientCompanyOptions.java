package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class ClientCompanyOptions extends SessionOptions
{
	private static final long serialVersionUID = 7706176367285086793L;
	
	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public ClientCompanyOptions() {
		super();
	}
	
	public ClientCompanyOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	/* ----------------------------------------------------------- */

	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"client.email",
			"client.status",
			"clientCompany.companyName", 
			"clientCompany.regon",
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
			"clientCompany.companyName",
			"clientCompany.regon",
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
		_dbOrders.put("clientCompany.companyName", "companyName");
		_dbOrders.put("clientCompany.regon", "regon");
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
		return "clientCompany";
	}

	@Override
	public String getDefaultOrder() {
		return "client.email";
	}
}
