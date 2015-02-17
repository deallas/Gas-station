package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import pl.noname.stacjabenzynowa.persistance.ClientCompany;
import pl.noname.stacjabenzynowa.validator.annotation.Regon;

public abstract class AbstractClientCompanyForm extends AbstractClientForm
{
	@NotBlank
	@Length(min=1, max=50, message="{form.name.Length}")
	protected String companyName;
	
	@NotBlank
	@Regon
	protected String regon;

	/* ----------------------------------------------------------- */
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getRegon(){
		return regon;
	}
	
	public void setRegon(String regon){
		this.regon = regon;
	}

	/* ----------------------------------------------------------- */
	
	public ClientCompany toClientCompany() {
		ClientCompany cc = new ClientCompany();
		cc.setCompanyName(companyName);
		cc.setRegon(regon);
		
		return cc;
	}
}
