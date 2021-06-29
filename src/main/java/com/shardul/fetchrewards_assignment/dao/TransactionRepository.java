package com.shardul.fetchrewards_assignment.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.shardul.fetchrewards_assignment.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

}
