package pl.noname.stacjabenzynowa.web.tag;

import java.util.Properties;  

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
  
import net.tanesha.recaptcha.ReCaptcha;
  
public class RecaptchaTag extends RequestContextAwareTag
{  
	private static final long serialVersionUID = 3418067255282545612L;

	private ReCaptcha reCaptcha;
	
    private String themeName = "clean";  
  
	@Override
	protected int doStartTagInternal() throws Exception 
	{
        WebApplicationContext context = getRequestContext().getWebApplicationContext();
        reCaptcha = (ReCaptcha)context.getBean("reCaptcha");
       
        Properties properties = new Properties();  
        properties.put("theme", themeName);
        properties.put("lang", "en");
  
        String captchaHtml = reCaptcha.createRecaptchaHtml(null, properties); 
        pageContext.getOut().print(captchaHtml);

		return SKIP_BODY;
	}
  
    public String getThemeName() {  
        return themeName;  
    }  
  
    public void setThemeName(String themeName) {  
        this.themeName = themeName;  
    }

	public ReCaptcha getReCaptcha() {
		return reCaptcha;
	}

	public void setReCaptcha(ReCaptcha reCaptcha) {
		this.reCaptcha = reCaptcha;
	}
}  
