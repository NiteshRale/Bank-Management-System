package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.dao.AccountDaoImpl;
import com.dao.AtmDaoImpl;
import com.pojo.Account;
import com.utility.DataConnect;

public class AccountTest 
{

	public static void main(String[] args) throws IOException, SQLException 
	{
		Scanner sc = new Scanner(System.in);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String name, address, Password;
		int accountNumber, mobileNo, balance;
		int accountNumber1, accountNumber2;
		
		AccountDaoImpl accountObj = new AccountDaoImpl();
		AtmDaoImpl atmObj = new AtmDaoImpl();
		
		String user = "UNBHK";
		String password = "unb123";
		
		System.out.println("Enter the UserId : ");
		String s1 = sc.nextLine();
		
		System.out.println("Enter the Password : ");
		String s2 = sc.nextLine();
		
		if(s1.equalsIgnoreCase(user)&&s2.equals(s2))
		{	
			while(true)
			{
				System.out.println("************************************");
				System.out.println("\n1.Add a new Account\n2.Update Account\n3.Deposit Money\n4.Withdraw Money\n5.Transfer Money\n6.Delete Account\n7.Search an Account\n8.Show all Accounts\n9.Exit\n************************************");
				System.out.println("\nEnter the choice : ");
				int choice = sc.nextInt();
				System.out.println("************************************");
				
				boolean b;
				
				switch(choice)
				{
				case 1 :
					System.out.println("How many Account you want to add : ");
					int num = sc.nextInt();
					
					for(int i=1; i<=num; i++)
					{
						System.out.println("Enter the Name : ");
						name = br.readLine();
						
						System.out.println("Enter the Address : ");
						address = br.readLine();
						
						System.out.println("Enter the Account number : ");
						accountNumber = sc.nextInt();
						
						System.out.println("Enter the Mobile number : ");
						mobileNo = sc.nextInt(); 
						
						System.out.println("Enter the Password : ");
						Password = br.readLine();
						
						System.out.println("Enter the Amount to be Deposited : ");
						balance = sc.nextInt();
						
						b = accountObj.addAccount(new Account(name,address,accountNumber,mobileNo,Password,balance));
						
						if(b)
						{
							System.out.println("Successfully");
						}
						else
						{
							System.out.println("Failed");
						}
					}
					break;
					
				case 2: 
					
					System.out.println("\nUpdate Account");
					
					System.out.println("************************************");
					
					System.out.println("Enter the Name : ");
					name = br.readLine();
					
					System.out.println("Enter the Address : ");
					address = br.readLine();
					
					System.out.println("Enter the Account number : ");
					accountNumber = sc.nextInt();
					
					System.out.println("Enter the Mobile number : ");
					mobileNo = sc.nextInt(); 
					
					System.out.println("Enter the Password : ");
					Password = br.readLine();
					
					b = accountObj.updateAccountByAccountNumber(new Account(name,address,accountNumber,mobileNo,Password));
					
					if(b)
					{
						System.out.println("Successfully");
					}
					else
					{
						System.out.println("Failed");
					}
					break;
			
				case 3 :
					
	//				Connection con;
	//				PreparedStatement ps;
	//				ResultSet rs;
					
					System.out.println("\nDeposit Money");
					
					System.out.println("************************************");
					
					System.out.println("Enter the Account No : ");
					
					accountNumber = sc.nextInt();
					
					System.out.println("Enter the Amount : ");
					int amount = sc.nextInt();
					
				
	//				con = DataConnect.getConnect();
	//				ps = con.prepareStatement("Select * from Account where accountNumber = ?");
	//				ps.setInt(1, accountNumber);
	//				rs = ps.executeQuery();
	//					
	//				int currentbalance = rs.getInt("balance");
	//				balance = currentbalance + amount;
						
					b = accountObj.setAccountBalanceByAccountNumber(amount,accountNumber);
					
				
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
						atmObj.transactionDeposit(accountNumber,amount);
					}
					
					break;
					
				case 4 :
					
					System.out.println("\nWithdrawl Money");
					
					System.out.println("************************************");
					
					System.out.println("Enter the Account No : ");
					
					accountNumber = sc.nextInt();
					
					System.out.println("Enter the Amount : ");
					int awithdraw = sc.nextInt();
					
					
	//				con = DataConnect.getConnect();
	//				ps = con.prepareStatement("Select * from Account where accountNumber = ?");
	//				ps.setInt(1, accountNumber);
	//				rs = ps.executeQuery();
	//					
	//				int currentbalance = rs.getInt("balance");
	//				balance = currentbalance + amount;
						
					b = accountObj.withdrawlByAccountNumber(awithdraw,accountNumber);
					
	
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
						atmObj.transactionWithdrawal(accountNumber, awithdraw);
					}
					
					break;
				
					
				case 5 :
					
					System.out.println("\nTransfer Money");
					
					System.out.println("************************************");
					
					System.out.println("Enter the Sender Account No : ");
					accountNumber1 = sc.nextInt();
					
					System.out.println("Enter the Amount to be Transfer : ");
					amount = sc.nextInt();
					
					System.out.println("Enter the Receiver Account No : ");
					accountNumber2 = sc.nextInt();
					
					b = accountObj.transferByAccountNumber(amount, accountNumber1, accountNumber2);
					
					if(b)
					{
						System.out.println("Successfully");
					}
					else
					{
						System.out.println("Transaction Failed");
						System.out.println("Enter the correct A/C no.");
					}
					
					break;
					
					
				case 6 :
					
					System.out.println("\nDelete Account");
					
					System.out.println("************************************");
					
					System.out.println("Enter the Account No : ");
					
					accountNumber = sc.nextInt();
					
					b = accountObj.deleteAccountByAccountNumber(accountNumber);
					
	
					if(b)
					{
						System.out.println("Successfully");
					}
					else
					{
						System.out.println("Failed");
					}
					
					break;
					
				case 7 :
	 
					System.out.println("\nAccount serach");
					
					System.out.println("************************************");
					
					System.out.println("Enter the Account No for search : ");
					
					accountNumber = sc.nextInt();
					
					Account account = new Account();
					
					account = accountObj.searchAccountByAccountNumber(accountNumber);
					
					if(account!=null && accountNumber==account.getAccountNumber())
					{
						System.out.println(account);
					}
					else
					{
						System.out.println("Not Available");
					}
					break;
					
				case 8 :
					
					System.out.println("\nShow all Data");
					
					System.out.println("************************************");
					
					List<Account> a1 = new ArrayList<Account>();
					
					a1 = accountObj.getAllAccount();
					
					Iterator<Account> it = a1.iterator();                                                    
					
					//here i want to add "No data found" if set is empty
					
					while(it.hasNext())
					{
						System.out.println(it.next());
					}
					
					break;
					
				case 9 :
					System.exit(0);
					
			}//switch
		}//while
	}
	else
	{
		System.out.println("************************************");
		System.out.println("Invalid Credentials");
	}
   }//main
}//class
