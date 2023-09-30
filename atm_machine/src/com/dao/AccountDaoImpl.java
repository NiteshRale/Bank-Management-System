package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Account;
import com.utility.DataConnect;

public class AccountDaoImpl implements AccountDao
{

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Account account = null;
	
	@Override
	public boolean addAccount(Account account)
	{
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("insert into Account values(?,?,?,?,?,?)");
			ps.setString(1, account.getName());
			ps.setString(2, account.getAddress());
			ps.setInt(3, account.getAccountNumber());
			ps.setInt(4,account.getMobileNo());
			ps.setString(5, account.getPassword());
			ps.setInt(6, account.getBalance());
			int row = ps.executeUpdate();
			
			if(row>0)
			{
				return true;
				
			}
			
		}
		catch(Exception e)
		{
			
		}
		return false;
	}

	@Override
	public boolean updateAccountByAccountNumber(Account account) 
	{
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("Update Account set name = ?, address = ?, mobileNo = ?, Password = ? where accountNumber = ? ");
			ps.setString(1, account.getName());
			ps.setString(2, account.getAddress());
			ps.setInt(3, account.getMobileNo());
			ps.setString(4, account.getPassword());
			ps.setInt(5, account.getAccountNumber());
			
			int row = ps.executeUpdate();
			
			if(row > 0)
			{
				return true;
			}
			
		}
		catch(Exception e)
		{
			
		}
		return false;
	}

	@Override
	public boolean deleteAccountByAccountNumber(int accountNumber) 
	{
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("delete from Account where accountNumber = ?");
			ps.setInt(1, accountNumber);
			int row = ps.executeUpdate();
		
			if(row > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
			
		}
		catch(Exception e)
		{
			
		}
		return false;
	}

	@Override
	public List<Account> getAllAccount() 
	{
		List<Account> a1 = new ArrayList<Account>();
		
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("Select * from Account");
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				account = new Account();
				
				account.setName(rs.getString("name"));
				account.setAddress(rs.getString("address"));
				account.setAccountNumber(rs.getInt("accountNumber"));
				account.setMobileNo(rs.getInt("mobileNo"));
				account.setPassword(rs.getString("Password"));
				account.setBalance(rs.getInt("balance"));
				
				a1.add(account);
			}
		}
		catch(Exception e)
		{
			
		}
		
		return a1;
	}

	@Override
	public Account searchAccountByAccountNumber(int accountNumber) 
	{
		try
		{
			con = DataConnect.getConnect();
			ps = con.prepareStatement("Select * from Account where accountNumber = ?");
			ps.setInt(1, accountNumber);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				account = new Account();
				
			    account.setName(rs.getString("name"));
				account.setAddress(rs.getString("address"));
				account.setAccountNumber(rs.getInt("accountNumber"));
				account.setMobileNo(rs.getInt("mobileNo"));
				account.setPassword(rs.getString("Password"));	
				account.setBalance(rs.getInt("balance"));
			}
		}
		catch(Exception e)
		{
			
		}
		return account;
	}

	@Override
	public boolean setAccountBalanceByAccountNumber(int amount,int accountNumber) 
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
				int balance = amount + currentBalance;
				
				ps = con.prepareStatement("update Account set balance = ? where accountNumber = ?");
				ps.setInt(1, balance);
				ps.setInt(2, accountNumber);
				int row = ps.executeUpdate();
			
				if(row > 0)
				{
					return true;
				}
				
			}
		}//try
		catch(Exception e)	
		{
			
		}
		return false;
	}//method

	@Override
	public boolean withdrawlByAccountNumber(int amount, int accountNumber) 
	{
		try
		{
			con = DataConnect.getConnect();
			
			ps = con.prepareStatement("select balance from Account where accountNumber = ?");
			ps.setInt(1, accountNumber);
			rs = ps.executeQuery();
			
			//if(rs!=null)
			while(rs.next())
			{	
				int currentBalance = rs.getInt("balance");
				if(currentBalance>amount)
				{
					int balance = currentBalance-amount;
					ps = con.prepareStatement("update Account set balance = ? where accountNumber = ?");
					ps.setInt(1, balance);
					ps.setInt(2, accountNumber);
					int row = ps.executeUpdate();
				
					if(row > 0)
					{
						return true;
					}
				}
				else
				{
					return false;
				}
				
			}
		}//try
		catch(Exception e)	
		{
			
		}
		return false;
	}

	@Override
	public boolean transferByAccountNumber(int amount, int accountNumber1, int accountNumber2) 
	{
		AtmDaoImpl atmObj = new AtmDaoImpl();
		
		int i=0;
		try
		{
			
			con = DataConnect.getConnect();
			
			ps = con.prepareStatement("select balance from Account where accountNumber = ?");
			ps.setInt(1, accountNumber1);
			rs = ps.executeQuery();
			
			while(rs.next())
			{	
				int currentBalance = rs.getInt("balance");
				if(currentBalance>amount)
				{
					int balance = currentBalance-amount;
					ps = con.prepareStatement("update Account set balance = ? where accountNumber = ?");
					ps.setInt(1, balance);
					ps.setInt(2, accountNumber1);
					int row = ps.executeUpdate();
					
					if(row > 0)
					{
						ps = con.prepareStatement("select balance from Account where accountNumber = ?");
						ps.setInt(1, accountNumber2);
						rs = ps.executeQuery();
							
							while(rs.next())
							{	
								int currentBalance2 = rs.getInt("balance");
								int balance2 = amount + currentBalance2;
								
								ps = con.prepareStatement("update Account set balance = ? where accountNumber = ?");
								ps.setInt(1, balance2);
								ps.setInt(2, accountNumber2);
								int row2 = ps.executeUpdate();
								
								i = row2;
								///////////////////////////////////////////////////////////////
								if(row > 0 && row2 > 0)
								{
									atmObj.transactionDeposit(accountNumber2,amount);
									atmObj.transactionWithdrawal(accountNumber1, amount);
								}
								//////////////////////////////////////////////////////////////
								if(row > 0 && row2 > 0)
								{
									return true;
								}
								
							}//while
					}//if end
				}//if end
			}//while
			
			if(i < 1)
			{
				ps = con.prepareStatement("select balance from Account where accountNumber = ?");
				ps.setInt(1, accountNumber1);
				rs = ps.executeQuery();
				
				while(rs.next())
				{	
					int currentBalance3 = rs.getInt("balance");
					if(currentBalance3>amount)
					{
						int balance3 = currentBalance3 + amount;
						ps = con.prepareStatement("update Account set balance = ? where accountNumber = ?");
						ps.setInt(1, balance3);
						ps.setInt(2, accountNumber1);
						ps.executeUpdate();	
					}
				}//while end
				
			}//if end
			
			
		}//try
		
		catch(Exception e)	
		{
			
		}
		return false;

	}//method
		
}//class



