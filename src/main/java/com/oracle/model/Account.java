package com.oracle.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.persistence.*;

@Component("account1")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "myaccount1")
public class Account {
	@Id
	@Column(name = "acc_id")
	private int accountId;
	@Column(name = "balance")
	private Double balance;

	public Account() {
		System.out.println("Account object created");
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + "]";
	}

}