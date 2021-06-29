package com.shardul.fetchrewards_assignment.resource;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shardul.fetchrewards_assignment.dao.BalanceRepository;
import com.shardul.fetchrewards_assignment.dao.TransactionRepository;
import com.shardul.fetchrewards_assignment.model.Balance;
import com.shardul.fetchrewards_assignment.model.Transaction;
@Service
public class PointHandling {
	
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	BalanceRepository balanceRepository;

	public boolean validateSpentPoints(int points) {
		if(points<0) return false;
		int totalPoints = getTotalPresentPoints();
		
		if(points > totalPoints){
			return false;
		}
		
		return true;
	}
	
	public int getTotalPresentPoints() {
		int total = 0;
		for(Balance b : getAllBalanceStatus()) {
			total += b.getPoints();		
		}
		return total;
	}
	
	private HashMap<String,Balance> getPayerBalanceMap(){
		HashMap<String,Balance> payerBalanceMap = new HashMap<>();
		List<Balance> allBalance = getAllBalanceStatus();
		for(Balance balance : allBalance) {
			payerBalanceMap.put(balance.getPayer(), balance);
		}
		return payerBalanceMap;
	}
	
	public List<Balance> getAllBalanceStatus(){
		return balanceRepository.findAll();
	}
	
	public HashMap<String, Integer> spendPoints(int points){
		List<Transaction> allTransaction=transactionRepository.findAll();
		HashMap<String,Balance> payerBalanceMap = getPayerBalanceMap();
		HashMap<String, Integer> resultMap = new HashMap<>();
		Collections.sort(allTransaction);
		for(Transaction transaction : allTransaction ) {
			
			if(transaction.getVisited()) continue;
			
			//Balance payer = payerBalanceMap.get(transaction.getPayer());
			if(!resultMap.containsKey(transaction.getPayer())) resultMap.put(transaction.getPayer(), 0);
			// If transaction is negative we need to add that point in the spend points because those transaction was already added 
			if(transaction.getPoints()<=0) {
				points += transaction.getPoints()*-1;
				resultMap.put(transaction.getPayer(), resultMap.get(transaction.getPayer())+(transaction.getPoints()*-1));
				transaction.setVisited(true);
			}
			else if(points >= transaction.getPoints()) {
				resultMap.put(transaction.getPayer(), resultMap.get(transaction.getPayer())-transaction.getPoints());
				points -= transaction.getPoints();
				transaction.setVisited(true);
			}
			else if(points < transaction.getPoints()) {
				int diff = transaction.getPoints()-points;
				resultMap.put(transaction.getPayer(), resultMap.get(transaction.getPayer())-points);
				points = 0;
				transaction.setPoints(diff);
			}
			transactionRepository.save(transaction);
			if(points == 0) break;
		}
		for(String payer: resultMap.keySet()) {
			Balance payerBalance = payerBalanceMap.get(payer);
			payerBalance.setPoints(payerBalance.getPoints()+resultMap.get(payer));
			balanceRepository.save(payerBalance);
		}
		return resultMap;
	}
}

