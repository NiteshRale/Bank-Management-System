package com.pojo;

public class Account 
{
	private String name;
	private String address;
	private int accountNumber;
	private int mobileNo;
	private String Password;
	private int balance;
	
	public Account() 
	{
		super();
	}

	public Account(String name, String address, int accountNumber, int mobileNo, String password, int balance) {
		super();
		this.name = name;
		this.address = address;
		this.accountNumber = accountNumber;
		this.mobileNo = mobileNo;
		Password = password;
		this.balance = balance;
	}

	public Account(String name, String address, int accountNumber, int mobileNo, String password) {
		super();
		this.name = name;
		this.address = address;
		this.accountNumber = accountNumber;
		this.mobileNo = mobileNo;
		Password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(int mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", address=" + address + ", accountNumber=" + accountNumber + ", mobileNo="
				+ mobileNo + ", Password=" + Password + ", balance=" + balance + "]";
	}
	
	
	
}
