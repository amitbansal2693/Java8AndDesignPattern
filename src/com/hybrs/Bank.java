package com.hybrs;

//create 2 variables amount, balance and 2 methods deposit and withdraw. Create a constructor to initialize the balance. Create a main method to test your code.

public class Bank {
//write your code here
    private double balance;
//constructor to initialize the balance and no arg constructor
public Bank(double balance) {
    this.balance = balance;
}
//o arg constructor
public Bank() {
    this.balance = 0.0;
}

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    //add a method to check balance
    public double getBalance() {
        return balance;
    }

    //add a method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);

        } else {
            System.out.println("Invalid withdraw amount or insufficient balance.");
        }
    }
}
