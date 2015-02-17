package pl.noname.stacjabenzynowa.util.paginator;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;

public abstract class AbstractOptions implements Serializable
{
	private static final long serialVersionUID = -6448401210396860659L;
	
	protected List<String> _orders;
	protected List<String> _diffOrders = null;
	protected String _order;
	protected Integer _items;
	protected Boolean _ascing;
	protected Integer _pageNumber;
	
	/* ----------------------------------------------------------- */
	
	public AbstractOptions()
	{
		_setupDefaultPaginatorOptions();
	}
	
	public AbstractOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		_orders = orders;
		_order = order;
		_items = items;
		_ascing = ascing;
		_pageNumber = pageNumber;
	}
	
	/* ----------------------------------------------------------- */
	
    public void mergePaginatorOptions(AbstractOptions customOptions)
    {
    	customOptions = _filterPaginatorOptions(customOptions);
    	
    	String order = customOptions.getOrder();
        if(order != null) {
            setOrder(order);
        }
        
        Integer page = customOptions.getPageNumber();
        if(page != null) {
            setPageNumber(page);
        }
        
        Integer items = customOptions.getItems();
        if(items != null) {
            setItems(items);
        }
        
        Boolean ascing = customOptions.isAscing();
        if(ascing != null) {
            setAscing(ascing);
        }
    }
    
    public Integer getDefaultItems()
    {
        return 10;
    }
    
    public Integer getDefaultPage()
    {
        return 1;
    }
    
    public boolean getDefaultAscing()
    {
        return false;
    }
    
    public List<String> getDiffOrders()
    {
    	if(_diffOrders != null) return _diffOrders;
    	
    	LinkedList<String> diffOrders = new LinkedList<String>(getAvailableOrders());
    	Iterator<String> it = diffOrders.iterator();
    	while(it.hasNext())
    	{
    		if(_orders.contains(it.next())) {
    			it.remove();
    		}
    	}
    	
    	_diffOrders = diffOrders;
    	
    	return _diffOrders;
    }
    
    public String getDbOrder()
    {
    	return getDbOrders().get(_order);
    }
	
	/* ----------------------------------------------------------- */
	
	public List<String> getOrders() {
		return _orders;
	}
	
	public void setOrders(List<String> orders) {
		_diffOrders = null;
		_orders = orders;
	}
	
	public String getOrder() {
		return _order;
	}
	
	public void setOrder(String order) {
		_order = order;
	}
	
	public Integer getItems() {
		return _items;
	}
	public void setItems(Integer items) {
		_items = items;
	}
	
	public Boolean isAscing() {
		return _ascing;
	}
	
	public void setAscing(boolean ascing) {
		_ascing = ascing;
	}

	public Integer getPageNumber() {
		return _pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		_pageNumber = pageNumber;
	}
	
	public String toString()
	{
		return "Order: " + _order + ", " +
				"Items: " + _items + ", " +
				"Ascing: " + _ascing + ", " +
				"Page: " + _pageNumber + ", " +
				"Orders: " + Arrays.toString(_orders.toArray(new String[0]));
	}
	
	/* ----------------------------------------------------------- */
	
    protected AbstractOptions _filterPaginatorOptions(AbstractOptions options)
    {
        if(!getAvailableOrders().contains(options.getOrder())) {
            options.setOrder(getDefaultOrder());
        }
        
        if(!getAvailableItems().contains(options.getItems())) {
        	options.setItems(getDefaultItems());
        }
        
        List<String> orders = options.getOrders();
        if(orders != null) {
        	orders.retainAll(getAvailableOrders());
            options.setOrders(orders);
        } else {
            options.setOrders(getDefaultOrders());
        }

        Boolean ascing = options.isAscing();
        if(ascing == null) {
        	options.setAscing(getDefaultAscing());
        }
        
        Integer pageNumber = options.getPageNumber();
        if(pageNumber == null) {
        	options.setPageNumber(getDefaultPage());
        }
        
        return options;
    }
    
    protected void _setupDefaultPaginatorOptions()
    {
    	_orders = getDefaultOrders(); 
		_order = getDefaultOrder(); 
		_items = getDefaultItems(); 
		_ascing = getDefaultAscing(); 
		_pageNumber = getDefaultPage();
    }
	
	/* ----------------------------------------------------------- */
	
    abstract public List<String> getAvailableOrders();
    abstract public List<String> getDefaultOrders();
    abstract public Map<String, String> getDbOrders();
    abstract public List<Integer> getAvailableItems();
    abstract public String getOptionsPaginatorName();
    abstract public String getDefaultOrder();
}