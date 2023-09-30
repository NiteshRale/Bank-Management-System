package com.dao;

import java.util.List;

import com.pojo.Account;
import com.pojo.Transaction;

public interface AtmDao 
{
	boolean searchAccountByAccountNumber(int accountNumber, String Passsword);
	int checkBalanceByAccountNumber(int accountNumber);
	void transactionDeposit(int ac, int amount);
	void transactionWithdrawal(int ac, int amount);
	List<Transaction>ByAccountNumber(int accountNumber);
}
