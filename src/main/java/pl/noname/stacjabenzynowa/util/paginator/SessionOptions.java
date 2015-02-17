package pl.noname.stacjabenzynowa.util.paginator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class SessionOptions extends AbstractOptions
{
	private static final long serialVersionUID = -4425757948135206943L;

	private static final Logger logger = LoggerFactory.getLogger(SessionOptions.class);
	
	@Autowired
	@Qualifier("userOptions")
	private Map<String, Object> userOptions;

	public SessionOptions() {
		super();
	}
	
	public SessionOptions(List<String> orders, String order, Integer items,
			boolean ascing, Integer pageNumber) {
		super(orders, order, items, ascing, pageNumber);
	}
	
    public AbstractOptions getPaginatorOptions()
    {
        AbstractOptions options = (AbstractOptions)userOptions.get(getOptionsPaginatorName());
        if(options == null) {
        	logger.debug("Setup default paginator settings");
            _setupDefaultPaginatorOptions();
        } else {
        	logger.debug("Merge paginator options:" + options);
        	mergePaginatorOptions(options);
        }
        
        return (AbstractOptions)this;
    }
    
    public AbstractOptions setPaginatorOptions(AbstractOptions options)
    {
        options = _filterPaginatorOptions(options);
        userOptions.put(getOptionsPaginatorName(), options);
        
        return (AbstractOptions)this;
    }
    
	public AbstractOptions setPaginatorOptions(String[] orders, String order, Integer items, Boolean ascing)
    {
		_pageNumber = getDefaultPage();
        _orders = Arrays.asList(orders);
        _order = order;
        _items = items;
        _ascing = ascing;
        
        logger.debug("Set paginator options:" + this);
        
        return (AbstractOptions)this;
    }
    
    public AbstractOptions clearPaginatorOptions()
    {
    	userOptions.remove(getOptionsPaginatorName());
    	_setupDefaultPaginatorOptions();
    	
    	return (AbstractOptions)this;
    }
}
