package pl.noname.stacjabenzynowa.web.paginator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.noname.stacjabenzynowa.util.paginator.SessionOptions;

public class PetrolContainerMeasurementOptions extends SessionOptions {
	
	private static final long serialVersionUID = -5400814544614969396L;
	
	private List<String> _availableOrders;
	private List<String> _defaultOrders;
	private HashMap<String, String> _dbOrders;
	
	public PetrolContainerMeasurementOptions() {
		super();
	}
	
	public PetrolContainerMeasurementOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
	/* ----------------------------------------------------------- */

	public List<String> getAvailableOrders() {
		if(_availableOrders != null) return _availableOrders;
		
		String[] orders = new String[]{ 
			"petrolContainerMeasurement.measurementDate",
			"petrolContainerMeasurement.type",
			"petrolContainerMeasurement.value",
			"petrolContainerMeasurement.petrolContainer.id",
			"petrolContainerMeasurement.petrolContainer.type"
		};
		
		_availableOrders = Arrays.asList(orders);
		
		return _availableOrders;
	}

	@Override
	public List<String> getDefaultOrders() {
		if(_defaultOrders != null) return _defaultOrders;
		
		String[] orders = new String[]{ 
			"petrolContainerMeasurement.measurementDate",
			"petrolContainerMeasurement.type",
			"petrolContainerMeasurement.value",
			"petrolContainerMeasurement.petrolContainer.id",
			"petrolContainerMeasurement.petrolContainer.type"
		};
		
		_defaultOrders = Arrays.asList(orders);
		
		return _defaultOrders;
	}
	
	@Override
	public Map<String, String> getDbOrders() {
		if(_dbOrders != null) return _dbOrders;
		
		_dbOrders = new HashMap<String, String>();
		_dbOrders.put("petrolContainerMeasurement.measurementDate", "measurementDate");
		_dbOrders.put("petrolContainerMeasurement.type", "type");
		_dbOrders.put("petrolContainerMeasurement.value", "value");
		_dbOrders.put("petrolContainerMeasurement.petrolContainer.id", "petrolContainer.id");
		_dbOrders.put("petrolContainerMeasurement.petrolContainer.type", "petrolContainer.type");

		return _dbOrders;
	}

	@Override
	public List<Integer> getAvailableItems() {
		Integer[] items = new Integer[]{ 3,5,10 };
		
		return Arrays.asList(items);
	}

	@Override
	public String getOptionsPaginatorName() {
		return "petrolContainerMeasurements";
	}

	@Override
	public String getDefaultOrder() {
		return "petrolContainerMeasurement.measurementDate";
	}
}
