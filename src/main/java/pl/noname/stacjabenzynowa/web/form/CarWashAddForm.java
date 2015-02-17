package pl.noname.stacjabenzynowa.web.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import pl.noname.stacjabenzynowa.persistance.CarWash;
import pl.noname.stacjabenzynowa.persistance.CarWashType;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;

public class CarWashAddForm {

	private int id;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	protected Date dateBeginWash;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	protected Date dateEndWash;
	
	private CarWashType[] carWashTypes;
	
	@AvailableValues(valueList={"getId","carWashService","getCarWashTypes"})
	private long carWashTypeId;
	
	private CarWashType carWashType;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateBeginWash() {
		return dateBeginWash;
	}

	public void setDateBeginWash(Date dateBeginWash) {
		this.dateBeginWash = dateBeginWash;
	}

	public Date getDateEndWash() {
		return dateEndWash;
	}

	public void setDateEndWash(Date dateEndWash) {
		this.dateEndWash = dateEndWash;
	}

	public CarWashType[] getCarWashTypes() {
		return carWashTypes;
	}

	public void setCarWashTypes(CarWashType[] carWashTypes) {
		this.carWashTypes = carWashTypes;
	}

	public long getCarWashTypeId() {
		return carWashTypeId;
	}

	public void setCarWashTypeId(long carWashTypeId) {
		this.carWashTypeId = carWashTypeId;
	}

	public CarWashType getCarWashType() {
		return carWashType;
	}

	public void setCarWashType(CarWashType carWashType) {
		this.carWashType = carWashType;
	}

	public CarWash toCarWash() {
		CarWash carWash = new CarWash();

		carWash.setDateBeginWash(dateBeginWash);
		carWash.setDateEndWash(dateEndWash);
		carWash.setCarWashType(carWashType);
		
		return carWash;
	}
}
