package com.shardul.fetchrewards_assignment.skeleton;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shardul.fetchrewards_assignment.model.Balance;
import com.shardul.fetchrewards_assignment.model.Transaction;
import com.shardul.fetchrewards_assignment.resource.PointHandling;
import com.shardul.fetchrewards_assignment.resource.TransactionHandling;

@Service
public class PointsTransactionSkeleton {
	
	@Autowired
	PointHandling pointsHandling;
	@Autowired
	TransactionHandling transactionHandling;
	
	public Transaction addTransaction(Transaction transaction) {
		return transactionHandling.addTransaction(transaction);
	}
	
	public boolean validateSpentPoints(int points) {
		return pointsHandling.validateSpentPoints(points);
	}
	
	public List<Balance> getAllBalanceStatus(){
		return pointsHandling.getAllBalanceStatus();
	}
	
	public HashMap<String, Integer> spendPoints(int points){
		return pointsHandling.spendPoints(points);
	}
	
}
