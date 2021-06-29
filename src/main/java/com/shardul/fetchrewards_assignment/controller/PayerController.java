package com.shardul.fetchrewards_assignment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shardul.fetchrewards_assignment.model.Balance;
import com.shardul.fetchrewards_assignment.model.Spend;
import com.shardul.fetchrewards_assignment.model.Transaction;
import com.shardul.fetchrewards_assignment.skeleton.PointsTransactionSkeleton;



@RestController
public class PayerController {
	@Autowired
	private PointsTransactionSkeleton pointsTransactionSkeleton;

	@PostMapping("/saveTransaction")
	public ResponseEntity<String> savePayerTransaction(@RequestBody Transaction transaction) {
		Transaction t = pointsTransactionSkeleton.addTransaction(transaction);
		if(t == null) {
			return ResponseEntity.badRequest().body("Error Entring the Transaction");
		}
		return ResponseEntity.ok("Transaction Addedd");
	}

	@GetMapping("/getAllTransactions")
	public ResponseEntity<String> getAll() {
		List<Balance> balStatus = pointsTransactionSkeleton.getAllBalanceStatus();
		List<String> result = new ArrayList<>();
		for(Balance bal: balStatus) {
			result.add(bal.toString());
		}
		return ResponseEntity.ok(balStatus.toString());
	}

	@PutMapping("/spendPoints")
	public ResponseEntity<String> spendPoints(@RequestBody Spend spend) {
		if(!pointsTransactionSkeleton.validateSpentPoints(spend.getPoints()))
			return ResponseEntity.badRequest().body("Spending too much than what is present");
		HashMap<String, Integer> spendResultMap = pointsTransactionSkeleton.spendPoints(spend.getPoints());
		List<String> bal = new ArrayList<>();
		for(String payerKey: spendResultMap.keySet()) {
			bal.add((new Balance(payerKey, spendResultMap.get(payerKey)).toString()));
		}
		return ResponseEntity.ok(bal.toString());
	}
	

}
