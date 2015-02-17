package pl.noname.stacjabenzynowa.web.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.PetrolContainerMeasurement;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;
import pl.noname.stacjabenzynowa.validator.annotation.BigDecimalRange;

public class PetrolContainerMeasurementEditForm {

	private int id;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	protected Date measurementDate;
	
	@NotBlank
	@AvailableValues({"PRESSURE", "PETROL_LEVEL"})
	protected String type;
	
	@NotNull
	@BigDecimalRange(minPrecision=0, maxPrecision=10, scale=2)
	protected BigDecimal value;
	
	private PetrolContainer[] containers;
	
	@AvailableValues(valueList={"getId","petrolService","getPetrolContainers"})
	private long containerId;
	
	private PetrolContainer container;

	/* ----------------------------------------------------------- */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getMeasurementDate() {
		return measurementDate;
	}

	public void setMeasurementDate(Date measurementDate) {
		this.measurementDate = measurementDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public PetrolContainer[] getContainers() {
		return containers;
	}

	public void setContainers(PetrolContainer[] containers) {
		this.containers = containers;
	}

	public long getContainerId() {
		return containerId;
	}

	public void setContainerId(long containerId) {
		this.containerId = containerId;
	}

	public PetrolContainer getContainer() {
		return container;
	}

	public void setContainer(PetrolContainer container) {
		this.container = container;
	}
	
	/* ----------------------------------------------------------- */

	public PetrolContainerMeasurement toPetrolContainerMeasurement() {
		
		PetrolContainerMeasurement measurement = new PetrolContainerMeasurement();

		measurement.setId(id);
		measurement.setMeasurementDate(measurementDate);
		measurement.setType(PetrolContainerMeasurement.Type.valueOf(type));
		measurement.setValue(value);
		measurement.setPetrolContainer(container);
		
		return measurement;
	}
	
	public void populateByPetrolContainerMeasurement(PetrolContainerMeasurement measurement) {
		
		setId(measurement.getId());
		setMeasurementDate(measurement.getMeasurementDate());
		setType(measurement.getType().toString());
		setValue(measurement.getValue());
		setContainerId(measurement.getPetrolContainer().getId());
	}
}
