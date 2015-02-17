package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class PetrolContainerLogOptions extends SessionOptions {
	
	private static final long serialVersionUID = 587977086830028788L;
	
	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public PetrolContainerLogOptions() {
		super();
	}
	
	public PetrolContainerLogOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	/* ----------------------------------------------------------- */

	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"petrolContainerLog.message",
			"petrolContainerLog.petrolContainer.id",
			"petrolContainerLog.petrolContainer.type"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"petrolContainerLog.message",
			"petrolContainerLog.petrolContainer.id",
			"petrolContainerLog.petrolContainer.type"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}
	
	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("petrolContainerLog.message", "message");
		_dbOrders.put("petrolContainerLog.petrolContainer.id", "petrolContainer.id");
		_dbOrders.put("petrolContainerLog.petrolContainer.type", "petrolContainer.type");
		
		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "petrolContainerLogs";
	}

	@Override
	public String getDefaultOrder() {
		return "petrolContainerLog.petrolContainer.id";
	}
}
