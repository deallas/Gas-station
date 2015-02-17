package pl.noname.stacjabenzynowa.web.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.validator.annotation.BigDecimalRange;

public class CarWashTypeEditForm {
	
	private int id;
	
	@NotBlank
	@Length(min=1, max=50, message="{form.name.Length}")
	private String name;
	
	@NotNull
	@BigDecimalRange(minPrecision=0,maxPrecision=5,scale=2)
	private BigDecimal cost;
	
	@NotNull
	private Integer loyalityPoints;
	
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
	
	public CarWashType toCarWashType() {
		CarWashType c = new CarWashType();
		
		c.setId(id);
		c.setName(name);
		c.setCost(cost);
		c.setLoyalityPoints(loyalityPoints);
		
		return c;
	}
	
	public void populateByCarWashType(CarWashType carWashType){
		setId(carWashType.getId());
		setName(carWashType.getName());
		setCost(carWashType.getCost());
		setLoyalityPoints(carWashType.getLoyalityPoints());
		
	}
}
