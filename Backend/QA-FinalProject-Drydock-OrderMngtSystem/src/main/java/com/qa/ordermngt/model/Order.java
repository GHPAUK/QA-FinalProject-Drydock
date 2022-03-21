package com.qa.ordermngt.model;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {

	private long id;
	private String customer;
	private String vehicleType;
	private int displacement;
	private boolean military;
	private boolean weaponised;
	private int resourcesRequired;
	private float cost;
	@Temporal(TemporalType.DATE)
	private java.util.Date calendarDate;

	public Order(String customer, String vehicleType, int displacement, boolean military, boolean weaponised,
			int resourcesRequired, float cost, Date date) {
		super();
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.displacement = displacement;
		this.military = military;
		this.weaponised = weaponised;
		this.resourcesRequired = resourcesRequired;
	}

	public Order(long id, String customer, String vehicleType, int displacement, boolean military, boolean weaponised,
			int resourcesRequired, float cost, Date date) {
		super();
		this.id = id;
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.displacement = displacement;
		this.military = military;
		this.weaponised = weaponised;
		this.resourcesRequired = resourcesRequired;
	}

	public void calcCost() {
		setCost(Math.round((this.getDisplacement() * this.getResourcesRequired()) / 1.5f));
	}

}
