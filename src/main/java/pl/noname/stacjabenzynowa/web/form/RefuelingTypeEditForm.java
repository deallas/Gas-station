package pl.noname.stacjabenzynowa.web.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.validator.annotation.BigDecimalRange;

public class RefuelingTypeEditForm {
	
	private int id;
	
	@NotBlank
	@Length(min=1, max=50, message="{form.name.Length}")
	protected String name;
	
	@NotNull
	@BigDecimalRange(minPrecision=0, maxPrecision=5, scale=2)
	protected BigDecimal cost;
	
	@NotNull
	protected Integer loyalityPoints;
	
	/* ----------------------------------------------------------- */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Integer getLoyalityPoints() {
		return loyalityPoints;
	}

	public void setLoyalityPoints(Integer loyalityPoints) {
		this.loyalityPoints = loyalityPoints;
	}
	
	/* ----------------------------------------------------------- */
	
	public RefuelingType toRefuelingType() {
		RefuelingType refuelingType = new RefuelingType();

		refuelingType.setId(id);
		refuelingType.setName(name);
		refuelingType.setCost(cost);
		refuelingType.setLoyalityPoints(loyalityPoints);
		
		return refuelingType;
	}
	
	public void populateByRefuelingType(RefuelingType refuelingType) {
		
		setId(refuelingType.getId());
		setName(refuelingType.getName());
		setCost(refuelingType.getCost());
		setLoyalityPoints(refuelingType.getLoyalityPoints());
	}
}
