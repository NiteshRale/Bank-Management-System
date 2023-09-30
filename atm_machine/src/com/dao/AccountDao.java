package com.dao;

import java.util.List;

import com.pojo.Account;

public interface AccountDao 
{
	boolean addAccount(Account account);
	boolean updateAccountByAccountNumber(Account account);
	boolean deleteAccountByAccountNumber(int accountNumber);
	boolean setAccountBalanceByAccountNumber(int amount,int accountNumber);
	boolean withdrawlByAccountNumber(int amount,int accountNumber);
	boolean transferByAccountNumber(int amount,int accountNumber1,int accountNumber2);
	List<Account>getAllAccount();
	Account searchAccountByAccountNumber(int accountNumber);
}
