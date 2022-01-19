package com.api.buyingfrenzy.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="Restaurants")
public class Restaurants {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "restaurant_name", nullable = false)
	private String restaurantName;
	
	@Column(name = "cash_balance", nullable = false)
	private double cashBalance;
	
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@JoinColumn(
			name = "rest_id"
			, nullable = false
		)
	private List<Menu> menu = new ArrayList<>();
	
	@Column(name = "opening_hours", nullable = false)
	private String openingHours;
	
	@OneToMany(
	        orphanRemoval = true,
	        targetEntity = PurchaseHistory.class,
	        mappedBy = "restaurants"
	    )
	@JsonManagedReference
	private List<PurchaseHistory> purchaseHistory;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public List<Menu> getMenus() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	
	public List<Menu> getMenu() {
		return menu;
	}

	public String getOpeningHours() {
		return openingHours;
	}

	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}

	public List<PurchaseHistory> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(List<PurchaseHistory> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}


}
