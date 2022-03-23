package com.qa.ordermngt.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Getter
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, length = 80)
	private String customer;
	@Column(nullable = false, length = 80)
	private String vehicleType;
	@Column(nullable = false)
	private int displacement;
	@Column(nullable = false)
	private boolean military;
	@Column(nullable = false)
	private boolean weaponised;
	@Column(nullable = false)
	private int resourcesRequired;
	@Column(nullable = false)
	private float cost;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public OrderEntity(String customer, String vehicleType, int displacement, boolean military, boolean weaponised,
			int resourcesRequired) {
		super();
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.displacement = displacement;
		this.military = military;
		this.weaponised = weaponised;
		this.resourcesRequired = resourcesRequired;
	}
	
	public OrderEntity(String customer, String vehicleType, int displacement, boolean military, boolean weaponised,
			int resourcesRequired, float cost, Date date) {
		super();
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.displacement = displacement;
		this.military = military;
		this.weaponised = weaponised;
		this.resourcesRequired = resourcesRequired;
		this.date = date;
	}
	
	public OrderEntity(long id, String customer, String vehicleType, int displacement, boolean military, boolean weaponised,
			int resourcesRequired, float cost, Date date) {
		super();
		this.id = id;
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.displacement = displacement;
		this.military = military;
		this.weaponised = weaponised;
		this.resourcesRequired = resourcesRequired;
		this.date = date;
	}

	public void setDate() {
		this.date = Calendar.getInstance().getTime();
	}
	
	public void setCost() {
		this.cost = Math.round((this.getDisplacement() * this.getResourcesRequired()) / 1.5f);
	}
}
