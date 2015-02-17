package pl.noname.stacjabenzynowa.web.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.validator.annotation.BigDecimalRange;
import pl.noname.stacjabenzynowa.validator.annotation.FieldNoExists;

@FieldNoExists(columnNames={"id"}, tableName=Client.class)

public class CheckoutForm{

	@NotNull
	private Integer id;
	
	private Integer employeeId;
	
	private Integer refuelingType;
	
	private Integer container;
	
	@BigDecimalRange(minPrecision=0,maxPrecision=8,scale=2)
	private BigDecimal fuel;
	
	private Integer washType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Integer getRefuelingType() {
		return refuelingType;
	}

	public void setRefuelingType(Integer refuelingType) {
		this.refuelingType = refuelingType;
	}

	public Integer getContainer() {
		return container;
	}

	public void setContainer(Integer container) {
		this.container = container;
	}

	public BigDecimal getFuel() {
		return fuel;
	}

	public void setFuel(BigDecimal fuel) {
		this.fuel = fuel;
	}

	public Integer getWashType() {
		return washType;
	}

	public void setWashType(Integer washType) {
		this.washType = washType;
	}
	
}
