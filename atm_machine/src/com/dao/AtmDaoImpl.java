package com.dao;

import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pojo.Account;
import com.pojo.Transaction;
import com.utility.DataConnect;

public class AtmDaoImpl implements AtmDao
{
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Account account = null;
	Transaction transaction = null;
	
	public boolean searchAccountByAccountNumber(int accountNumber, String Passsword) 
	{
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("Select * from Account where accountNumber = ? And Password = ?");
			ps.setInt(1, accountNumber);
			ps.setString(2, Passsword);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				return true;
			}
		}
		catch(Exception e)
		{
			
		}
		return false;
	}
	
	public int checkBalanceByAccountNumber(int accountNumber) 
	{
		try
		{
			con = DataConnect.getConnect();
			
			ps = con.prepareStatement("select balance from Account where accountNumber = ?");
			ps.setInt(1, accountNumber);
			rs = ps.executeQuery();
			
			
			while(rs.next())
			{	
				int currentBalance = rs.getInt("balance");
				return currentBalance;
			}
		}
		catch(Exception e)
		{
			
		}
		
		return 0;
	}

	public void transactionDeposit(int ac, int amount) 
	{
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("insert into Transaction (accountNumber, amount, transaction_date, transaction_type) values(?,?,now(),'Deposit')");
			ps.setInt(1, ac);
			ps.setInt(2, amount);
			ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			
		}
	}
	public void transactionWithdrawal(int ac, int amount) 
	{
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("insert into Transaction (accountNumber, amount, transaction_date, transaction_type) values(?,?,now(),'Withdrawal')");
			ps.setInt(1, ac);
			ps.setInt(2, amount);
			ps.executeUpdate();
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	public List<Transaction>ByAccountNumber(int accountNumber) 
	{
		List<Transaction> t1 = new ArrayList<Transaction>();
		
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("Select * from Transaction where accountNumber = ? order by transaction_date desc limit 5");
			ps.setInt(1, accountNumber);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				transaction = new Transaction();
				
				transaction.setTransaction_id(rs.getInt("transaction_id"));
				transaction.setAccountNumber(rs.getInt("accountNumber"));
				transaction.setTransaction_date(rs.getString("transaction_date"));
				transaction.setAmount(rs.getInt("amount"));
				transaction.setTransaction_type(rs.getString("Transaction_type"));	
				
				t1.add(transaction);
			}
		}
		catch(Exception e)
		{
			
		}
		return t1;
	}
	
			
}
