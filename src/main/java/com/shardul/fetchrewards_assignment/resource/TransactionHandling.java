package com.shardul.fetchrewards_assignment.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shardul.fetchrewards_assignment.dao.BalanceRepository;
import com.shardul.fetchrewards_assignment.dao.TransactionRepository;
import com.shardul.fetchrewards_assignment.model.Balance;
import com.shardul.fetchrewards_assignment.model.Transaction;

@Service
public class TransactionHandling {
	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	BalanceRepository balanceRepository;
	
	public Transaction addTransaction(Transaction transaction) {
		
		Balance payerProfile= balanceRepository.findByPayer(transaction.getPayer());
		if(payerProfile == null) {
			payerProfile = new Balance(transaction.getPayer(), 0);
		}
		int futurePoints=payerProfile.getPoints()+transaction.getPoints();
		if(futurePoints<0) return null;
		payerProfile.setPoints(futurePoints);
		balanceRepository.save(payerProfile);
		return transactionRepository.save(transaction);
		
	}
}
