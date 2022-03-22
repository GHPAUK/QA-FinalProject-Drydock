package com.qa.ordermngt.entitydto;

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
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date calendarDate;

	public OrderEntity(String customer, String vehicleType, int displacement, boolean military, boolean weaponised,
			int resourcesRequired, float cost) {
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
		this.calendarDate = date;
	}
	
	public OrderEntity(long id, String customer, String vehicleType, int displacement, boolean military, boolean weaponised,
			int resourcesRequired, float cost) {
		super();
		this.id = id;
		this.customer = customer;
		this.vehicleType = vehicleType;
		this.displacement = displacement;
		this.military = military;
		this.weaponised = weaponised;
		this.resourcesRequired = resourcesRequired;
	}

	public void setDate() {
		this.calendarDate = Calendar.getInstance().getTime();
	}
	
}
