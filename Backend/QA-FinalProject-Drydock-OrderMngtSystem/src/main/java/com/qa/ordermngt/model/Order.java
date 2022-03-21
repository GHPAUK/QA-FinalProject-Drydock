package com.qa.ordermngt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("unused")
public class Order {

	private long id;
	private String customer;
	private String vehicleType;
	private int displacement;
	private boolean military;
	private boolean weaponised;
	private int resourcesRequired;
	private float cost;

	public Order(String customer, String vehicleType, int displacement, boolean military, boolean weaponised,
			int resourcesRequired, float cost) {
		super();
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.displacement = displacement;
		this.military = military;
		this.weaponised = weaponised;
		this.resourcesRequired = resourcesRequired;
		this.cost = Math.round((displacement * resourcesRequired / 1.5f));
	}

}
