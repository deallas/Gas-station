package pl.noname.stacjabenzynowa.util.paginator;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

public class HibernatePaginator<E>
{
	private Criteria _criteria;
	private AbstractOptions _options;
	
	private Integer _currentItemCount = null;
	private Integer _pageCount = null;
	private Integer _pageRange;
	private List<E> _currentItems;
	
    protected static int _defaultItemCountPerPage = 10;
    protected static int _defaultPageRange = 10;
	
	/* ----------------------------------------------------------- */
	
	public HibernatePaginator(Criteria criteria)
	{
		_criteria = criteria;
	}
	
	public HibernatePaginator(Criteria criteria, AbstractOptions options)
	{
		_criteria = criteria;
		_options = options;
	}
	
	/* ----------------------------------------------------------- */
	
	public AbstractOptions getOptions() {
		return _options;
	}

	public void setOptions(AbstractOptions options) {
		_options = options;
	}

	
	public List<E> getItemsByPage(int pageNumber)
	{
		int offset = (pageNumber - 1) * getItemCountPerPage();
		
		return _getItems(offset, getItemCountPerPage());
	}
	
	public int getCurrentItemCount()
	{
        if (_currentItemCount == null) {
        	_currentItemCount = getCurrentItems().size();
        }
        
        return _currentItemCount;
	}
	
	public int getCurrentPageNumber()
	{
		return normalizePageNumber(_options.getPageNumber());
	}
	
	public void setCurrentPageNumber(Integer pageNumber)
	{
		if(pageNumber == null) pageNumber = 1;
		
		_options.setPageNumber(pageNumber);
		_currentItems = null;
		_currentItemCount = 0;
	}
	
	public List<E> getCurrentItems()
	{
		if(_currentItems == null) {
            _currentItems = getItemsByPage(getCurrentPageNumber());
        }

        return _currentItems;
	}
	
	public int getItemCountPerPage()
	{
		Integer itemCountPerPage = _options.getItems();
		if (itemCountPerPage == null) {
			itemCountPerPage = getDefaultItemCountPerPage();
        }

        return itemCountPerPage;
	}
	
    public void setItemCountPerPage(Integer itemCountPerPage)
    {
    	if(itemCountPerPage == null) itemCountPerPage = getDefaultItemCountPerPage();
    	
        Integer items = itemCountPerPage;
        if (items < 1) {
            items = _getTotalItemCount();
        }
        _options.setItems(items);
        
        _pageCount = _calculatePageCount();
        _currentItems = null;
        _currentItemCount = null;
    }
	
	public int normalizePageNumber(int pageNumber)
    {
        if (pageNumber < 1) {
            pageNumber = 1;
        }

        int pageCount = getPageCount();

        if (pageCount > 0 && pageNumber > pageCount) {
            pageNumber = pageCount;
        }

        return pageNumber;
    }

	public int getPageCount()
    {
        if (_pageCount == null) {
            _pageCount = _calculatePageCount();
        }

        return _pageCount;
    }
	
	public Integer getPreviousPageNumber()
	{
		int currentPageNumber = getCurrentPageNumber();
		
		if (currentPageNumber - 1 > 0) {
            return currentPageNumber - 1;
        }
		
		return null;
	}
	
	public Integer getNextPageNumber()
	{
		int pageCount = getPageCount();
		int currentPageNumber = getCurrentPageNumber();
		
		if (currentPageNumber + 1 <= pageCount) {
            return currentPageNumber + 1;
        }
		
		return null;
	}
	
	public List<Integer> getPages()
	{
		return getPages(null);
	}
	
	public List<Integer> getPages(Integer pageRange)
    {
        if (pageRange == null) {
            pageRange = getPageRange();
        }

        int pageNumber = getCurrentPageNumber();
        int pageCount  = getPageCount();

        if (pageRange > pageCount) {
            pageRange = pageCount;
        }

        int delta = (int)Math.ceil(pageRange / 2);
        int lowerBound;
        int upperBound;
        
        if (pageNumber - delta > pageCount - pageRange) {
            lowerBound = pageCount - pageRange + 1;
            upperBound = pageCount;
        } else {
            if (pageNumber - delta < 0) {
                delta = pageNumber;
            }

            int offset = pageNumber - delta;
            lowerBound = offset + 1;
            upperBound = offset + pageRange;
        }

        return getPagesInRange(lowerBound, upperBound);
    }
	
	public List<Integer> getPagesInRange(int lowerBound, int upperBound)
	{
        lowerBound = normalizePageNumber(lowerBound);
        upperBound = normalizePageNumber(upperBound);

        List<Integer> pages = new ArrayList<Integer>();

        for (int pageNumber = lowerBound; pageNumber <= upperBound; pageNumber++) {
            pages.add(pageNumber);
        }

        return pages;
	}
	
    public int getPageRange()
    {
        if (null == _pageRange) {
            _pageRange = getDefaultPageRange();
        }

        return _pageRange;
    }

    public void setPageRange(int pageRange)
    {
        _pageRange = pageRange;
    }
    
    public int getCurrentCounter()
    {
    	return (getCurrentPageNumber() - 1) * getOptions().getItems() + 1;
    }
    
    public static int getDefaultPageRange()
    {
        return _defaultPageRange;
    }
	
    public static int getDefaultItemCountPerPage()
    {
        return _defaultItemCountPerPage;
    }

    public static void setDefaultItemCountPerPage(int count)
    {
        _defaultItemCountPerPage = count;
    }
	
	@SuppressWarnings("unchecked")
	protected List<E> _getItems(Integer offset, Integer itemCountPerPage) 
	{	
		_criteria.setProjection(null);
		_criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		_criteria.setFirstResult(offset);
		_criteria.setMaxResults(itemCountPerPage);
		
		String order = _options.getDbOrder();
		if(_options.isAscing()) {
			_criteria.addOrder(Order.asc(order));
		} else {
			_criteria.addOrder(Order.desc(order));
		}
		
		return (List<E>)_criteria.list();
	}
	
	protected int _getTotalItemCount()
	{
		return ((Number)_criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
    
	protected int _calculatePageCount()
    {
        return (int)Math.ceil(_getTotalItemCount() / (double)getItemCountPerPage());
    }
}