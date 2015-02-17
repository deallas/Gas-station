package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class BillOptions extends SessionOptions  {

	private static final long serialVersionUID = 7077511355949398784L;

	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public BillOptions(){
		super();
	}
	
	public BillOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	@Override
	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"bill.invoiceDate",
			"bill.amount",
			"bill.vat",
			"bill.total",
			"client.email",
			"employee.email"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
				"bill.invoiceDate",
				"bill.amount",
				"bill.vat",
				"bill.total",
				"client.email",
				"employee.email"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}

	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("bill.invoiceDate", "invoiceDate");
		_dbOrders.put("bill.amount", "amount");
		_dbOrders.put("bill.vat", "vat");
		_dbOrders.put("bill.total", "total");
		_dbOrders.put("client.email", "cli.email");
		_dbOrders.put("employee.email", "emp.email");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "bills";
	}

	@Override
	public String getDefaultOrder() {
		return "bill.invoiceDate";
	}

}
