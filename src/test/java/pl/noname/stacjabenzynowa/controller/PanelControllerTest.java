package pl.noname.stacjabenzynowa.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@ContextConfiguration(value={"classpath:WEB-INF/spring/contextConfig.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public class PanelControllerTest 
{
	@Autowired
    private RequestMappingHandlerMapping handlerMapping;
	
	@Autowired
    private RequestMappingHandlerAdapter handlerAdapter;
	
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	
	/* ----------------------------------------------------------- */
	
	public RequestMappingHandlerMapping getHandlerMapping() {
		return handlerMapping;
	}

	public void setHandlerMapping(RequestMappingHandlerMapping handlerMapping) {
		this.handlerMapping = handlerMapping;
	}

	public RequestMappingHandlerAdapter getHandlerAdapter() {
		return handlerAdapter;
	}

	public void setHandlerAdapter(RequestMappingHandlerAdapter handlerAdapter) {
		this.handlerAdapter = handlerAdapter;
	}

	/* ----------------------------------------------------------- */
	
	@Before
	public void setup()
	{
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	@Test
	public void testSuccessfulAuthentication() throws Exception
	{
		request.setMethod("GET");
		request.setRequestURI("/login");
		//request.addParameter("j_username", "test@test.pl");
		//request.addParameter("j_password", "12345");
		
        Object handler = handlerMapping.getHandler(request).getHandler();
        handlerAdapter.handle(request, response, handler);
        assertEquals(200, response.getStatus());
	}
}
