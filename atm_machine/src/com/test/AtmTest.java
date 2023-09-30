package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.dao.AccountDaoImpl;
import com.dao.AtmDaoImpl;
import com.pojo.Account;
import com.pojo.Transaction;

public class AtmTest 
{
	
	public static void main(String[] args) throws SQLException 
	{
		
		PreparedStatement ps;
		ResultSet rs;
		Account account = null;
		Transaction transaction = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("************** Welcome to UNBHK Atm **************\n");
		System.out.println("Enter the Account no : ");
		int ac = sc.nextInt();
		
		System.out.println("Enter the Password : ");
		String pwd = sc.next(); 
		
		AtmDaoImpl atmObj = new AtmDaoImpl();
		AccountDaoImpl accountObj = new AccountDaoImpl();
		
		
		boolean acCheck = atmObj.searchAccountByAccountNumber(ac, pwd);
		
		while(acCheck)
		{
			System.out.println("************************************");
			System.out.println("\n1.Withdraw\n2.Deposit\n3.Check Balance\n4.Mini Statement\n5.Exit\n************************************");
			System.out.println("\nEnter the choice : ");
			int choice = sc.nextInt();
			System.out.println("************************************");
			
			boolean b;
			
			switch(choice)
			{
			case 1 :
				
				System.out.println("\nWithdrawl Money");
				
				System.out.println("************************************");
				
				System.out.println("Enter the Amount : ");
				int awithdraw = sc.nextInt();
				
					
				b = accountObj.withdrawlByAccountNumber(awithdraw,ac);
				

				if(b)
				{
					System.out.println("Successfully");
				}
				else
				{
					System.out.println("Insufficient balance");
				}
				if(b)
				{
					atmObj.transactionWithdrawal(ac, awithdraw);
				}
				
				break;
				
			case 2 :
				
				System.out.println("\nDeposit Money");
				
				System.out.println("************************************");
				
				System.out.println("Enter the Amount : ");
				int amount = sc.nextInt();
				
				b = accountObj.setAccountBalanceByAccountNumber(amount,ac);
				
				String s = "Deposit";
				
				if(b)
				{
					System.out.println("Successfully");
				}
				else
				{
					System.out.println("Failed");
				}
				
				if(b)
				{
					atmObj.transactionDeposit(ac,amount);
				}
				
				break;
				
			case 3 :
				
				System.out.println("\nCheck Balance");
				
				System.out.println("************************************");
				
				int cuurentBalance = atmObj.checkBalanceByAccountNumber(ac);
				
				System.out.println("Current Balance : "+cuurentBalance);
				
				break;
				
			case 4 :
				
				System.out.println("\nMini Statement");
				
				System.out.println("************************************");
				
				transaction = new Transaction();
				
				List<Transaction> t1 = new ArrayList<Transaction>();
				
				t1 = atmObj.ByAccountNumber(ac);
				
				Iterator<Transaction> it = t1.iterator();                                                    
				
				//here i want to add "No data found" if set is empty
				
				while(it.hasNext())
				{
					System.out.println(it.next());
				}
				
				break;
				
			case 5 :
				
				System.exit(0);
			
			}//switch
		}//while
		

	}//main

}//class
