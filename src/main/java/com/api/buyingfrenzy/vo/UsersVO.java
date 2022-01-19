package com.api.buyingfrenzy.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.api.buyingfrenzy.entities.PurchaseHistory;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class UsersVO {
	
	@JsonProperty(value = "cashBalance")
	@NotNull(message = "cashBalance field is required")
	private double cashBalance;
	
	@JsonProperty(value = "id")
	@NotNull(message = "id field is required")
	private long id;
	
	@JsonProperty(value = "name")
	@NotNull(message = "name field is required")
	private String name;
	
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
