package com.rabobank.db.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabobank.entity.CustomerStatements;

@Repository
public interface CustomerStatementsRepository extends JpaRepository<CustomerStatements,BigInteger>{

}