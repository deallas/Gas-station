package pl.noname.stacjabenzynowa.web.form;

import org.hibernate.validator.constraints.NotBlank;

import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.PetrolContainer.Type;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;

public class PetrolContainerEditForm {
	
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

		container.setId(id);
		container.setType(PetrolContainer.Type.valueOf(type));
		
		return container;
	}
	
	public void populateByPetrolContainer(PetrolContainer container) {
		
		setId(container.getId());
		Type t = container.getType();
		if(t == null) {
			setType(Type.E95.toString());
		} else {
			setType(container.getType().toString());
		}
	}
	
}
