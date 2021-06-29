package com.shardul.fetchrewards_assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shardul.fetchrewards_assignment.model.Balance;

public interface BalanceRepository extends JpaRepository<Balance, String> {

	Balance findByPayer(String payer);

}

