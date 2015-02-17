package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;

public class PetrolContainerAddForm {

	private int id;
	
	@NotBlank
	@AvailableValues({"E95", "E98", "ON", "LPG"})
	protected String type;
	
	/* ----------------------------------------------------------- */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/* ----------------------------------------------------------- */
	

	public PetrolContainer toPetrolContainer() {
		
		PetrolContainer container = new PetrolContainer();

		container.setType(PetrolContainer.Type.valueOf(type));
		
		return container;
	}

}
