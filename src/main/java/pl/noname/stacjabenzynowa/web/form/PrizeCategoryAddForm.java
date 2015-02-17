package pl.noname.stacjabenzynowa.web.form;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.PrizeCategory;
import pl.noname.stacjabenzynowa.validator.annotation.Alpha;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;

public class PrizeCategoryAddForm {

	@NotBlank
	@Length(min=1, max=50, message="{form.name.Length}")
	@Alpha
	protected String name;
	
	@NotNull
	private Integer minPoints;
	
	@NotBlank
	@AvailableValues({"ACTIVE","INACTIVE"})
	protected String status;

	/* ----------------------------------------------------------- */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMinPoints() {
		return minPoints;
	}

	public void setMinPoints(Integer minPoints) {
		this.minPoints = minPoints;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	/* ----------------------------------------------------------- */
	
	public PrizeCategory toPrizeCategory() {
		PrizeCategory category = new PrizeCategory();

		category.setName(name);
		category.setMinPoints(minPoints);
		category.setStatus(PrizeCategory.Status.valueOf(status));
		
		return category;
	}
}
