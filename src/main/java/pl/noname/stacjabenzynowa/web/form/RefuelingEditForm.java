package pl.noname.stacjabenzynowa.web.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import pl.noname.stacjabenzynowa.persistance.Client;
import pl.noname.stacjabenzynowa.persistance.PetrolContainer;
import pl.noname.stacjabenzynowa.persistance.Refueling;
import pl.noname.stacjabenzynowa.persistance.RefuelingType;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;
import pl.noname.stacjabenzynowa.validator.annotation.BigDecimalRange;

public class RefuelingEditForm {

	private int id;
	
	@NotNull
	@BigDecimalRange(minPrecision=0, maxPrecision=8, scale=2)
	protected BigDecimal cost;
	
	@NotNull
	@BigDecimalRange(minPrecision=0, maxPrecision=8, scale=2)
	protected BigDecimal fuel;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	protected Date refuelingDate;
	
	private PetrolContainer[] containers;
	
	@AvailableValues(valueList={"getId","petrolService","getPetrolContainers"})
	private long containerId;
	
	private PetrolContainer container;
	
	private Client[] clients;
	
	@AvailableValues(valueList={"getId","clientService","getClients"})
	private long clientId;
	
	private Client client;
	
	private RefuelingType[] refuelingTypes;
	
	@AvailableValues(valueList={"getId","refuelingService","getRefuelingTypes"})
	private long refuelingTypeId;
	
	private RefuelingType refuelingType;
	
	/* ----------------------------------------------------------- */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getFuel() {
		return fuel;
	}

	public void setFuel(BigDecimal fuel) {
		this.fuel = fuel;
	}

	public Date getRefuelingDate() {
		return refuelingDate;
	}

	public void setRefuelingDate(Date refuelingDate) {
		this.refuelingDate = refuelingDate;
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

	public Client[] getClients() {
		return clients;
	}

	public void setClients(Client[] clients) {
		this.clients = clients;
	}

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public RefuelingType[] getRefuelingTypes() {
		return refuelingTypes;
	}

	public void setRefuelingTypes(RefuelingType[] refuelingTypes) {
		this.refuelingTypes = refuelingTypes;
	}

	public long getRefuelingTypeId() {
		return refuelingTypeId;
	}

	public void setRefuelingTypeId(long refuelingTypeId) {
		this.refuelingTypeId = refuelingTypeId;
	}

	public RefuelingType getRefuelingType() {
		return refuelingType;
	}

	public void setRefuelingType(RefuelingType refuelingType) {
		this.refuelingType = refuelingType;
	}
	
	/* ----------------------------------------------------------- */
	
	public Refueling toRefueling() {
		Refueling refueling = new Refueling();

		refueling.setId(id);
		refueling.setCost(cost);
		refueling.setFuel(fuel);
		refueling.setRefuelingDate(refuelingDate);
		refueling.setPetrolContainer(container);
		refueling.setClient(client);
		refueling.setRefuelingType(refuelingType);
		
		return refueling;
	}
	
	public void populateByRefueling(Refueling refueling) {
		
		setId(refueling.getId());
		setCost(refueling.getCost());
		setFuel(refueling.getFuel());
		setRefuelingDate(refueling.getRefuelingDate());
		setContainerId(refueling.getPetrolContainer().getId());
		setClientId(refueling.getClient().getId());
		setRefuelingTypeId(refueling.getRefuelingType().getId());
	}
}
