package pl.noname.stacjabenzynowa.web.form;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import pl.noname.stacjabenzynowa.persistance.PetrolDelivery;
import pl.noname.stacjabenzynowa.persistance.PetrolProvider;
import pl.noname.stacjabenzynowa.validator.annotation.AvailableValues;
import pl.noname.stacjabenzynowa.validator.annotation.BigDecimalRange;

public class PetrolDeliveryEditForm {
	
	private int id;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	protected Date deliveryDate;
	
	@NotNull
	protected Integer quantity;
	
	@NotNull
	@BigDecimalRange(minPrecision=0, maxPrecision=8, scale=2)
	protected BigDecimal cost;
	
	@NotNull
	@DateTimeFormat(pattern="dd-MM-yyyy")
	protected Date orderDate;
	
	@NotBlank
	@AvailableValues({"WAITING", "COMPLETED"})
	protected String status;
	
	private PetrolProvider[] providers;
	
	@AvailableValues(valueList={"getId","petrolService","getPetrolProviders"})
	private long providerId;
	
	private PetrolProvider provider;
	
	/* ----------------------------------------------------------- */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public PetrolProvider[] getProviders() {
		return providers;
	}

	public void setProviders(PetrolProvider[] providers) {
		this.providers = providers;
	}

	public long getProviderId() {
		return providerId;
	}

	public void setProviderId(long providerId) {
		this.providerId = providerId;
	}

	public PetrolProvider getProvider() {
		return provider;
	}

	public void setProvider(PetrolProvider provider) {
		this.provider = provider;
	}
	
	/* ----------------------------------------------------------- */
	

	public PetrolDelivery toPetrolDelivery() {
		PetrolDelivery delivery = new PetrolDelivery();

		delivery.setId(id);
		delivery.setDeliveryDate(deliveryDate);
		delivery.setQuantity(quantity);
		delivery.setCost(cost);
		delivery.setOrderDate(orderDate);
		delivery.setStatus(PetrolDelivery.Status.valueOf(status));
		delivery.setPetrolProvider(provider);
		
		return delivery;
	}
	
	public void populateByPetrolDelivery(PetrolDelivery delivery) {
		
		setId(delivery.getId());
		setDeliveryDate(delivery.getDeliveryDate());
		setQuantity(delivery.getQuantity());
		setCost(delivery.getCost());
		setOrderDate(delivery.getOrderDate());
		setStatus(delivery.getStatus().toString());
		setProviderId(delivery.getPetrolProvider().getId());
	}
}
