package com.shardul.fetchrewards_assignment.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"id"})
public class Transaction implements Comparable<Transaction> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String payer;
	private int points;
	private Timestamp timestamp;
	private Boolean visited=false;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public Boolean getVisited() {
		return visited;
	}
	public void setVisited(Boolean visited) {
		this.visited = visited;
	}
	@Override
	public int compareTo(Transaction transaction) {
		return this.timestamp.compareTo(transaction.getTimestamp());
	}
	
}
