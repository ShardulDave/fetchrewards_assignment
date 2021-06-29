package com.shardul.fetchrewards_assignment.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Balance {
	@Id
	String payer;
	int points;
	
	public Balance() {
		
	}
	public Balance(String payer, int points) {
		this.payer = payer;
		this.points = points;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	@Override
	public String toString() {
		return this.payer + ": " + this.points; 
	}
	
}
