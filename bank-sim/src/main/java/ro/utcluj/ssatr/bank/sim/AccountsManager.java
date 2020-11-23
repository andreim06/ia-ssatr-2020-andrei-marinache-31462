/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.ssatr.bank.sim;

/**
 *
 * @author Asus
 */
public class AccountsManager {
    
    BankAccount[] accounts = new BankAccount[10];
    
    public void addAccount(BankAccount a){
    for(int i=0;i<accounts.length;i++){
           if(accounts[i]==null){
               accounts[i] = a;
               System.out.println("New account added on account manager");
               return;
           }           
       }
       System.out.println("No empty position found on account manager.");
    }
    
    int getTotalBalance(){
      int sum =0;
        for (BankAccount account : accounts) {
            if (account != null) {
                sum += account.balance;
                System.out.println("sum: " +sum);
            } 
        }
       return sum;
    }
    
    boolean transfer(String fromOwner, String toOwner, int amount){
    
        for (BankAccount account : accounts) {
            BankAccount a = account.getAccountByOwner(fromOwner);
            System.out.println("Account a: " + a.owner + a.balance );
            BankAccount b = account.getAccountByOwner(toOwner);
            System.out.println("Account b: " + b.owner + b.balance );
            if(a.owner != null && b.owner != null){
                a.balance -= amount;
                b.balance += amount;
                System.out.println("Found accounts.");
                return true;
            }
        }
       return false;
   }
}

