package com.api.buyingfrenzy.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class Users {
	
	@Column(name = "cash_balance", nullable = false)
	private double cashBalance;
	
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	@JoinColumn(
			name = "users_id"
			, nullable = false
		)
	private List<PurchaseHistory> purchaseHistory;

	public double getCashBalance() {
		return cashBalance;
	}

	public void setCashBalance(double cashBalance) {
		this.cashBalance = cashBalance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PurchaseHistory> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(List<PurchaseHistory> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}


	
}
