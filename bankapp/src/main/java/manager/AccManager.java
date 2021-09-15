package manager;

import java.sql.SQLException;
import java.util.List;

import commons.AModel;
import commons.AccountModel;
import doa.AccountDOA;

public class AccManager {
	float balance;
	float transaction;
	String name;
	String customer;
	
	private AccountDOA adoa= new AccountDOA();
	private List<Float> transA;
	
	public List<AccountModel> findAll(){
		return adoa.findAll();
	}
	
	public List<AccountModel> findAcc(String name){
		
		if(name == null || name.isEmpty()) {
;
			throw new IllegalArgumentException("Enter valid username");
		}
		
		return adoa.findAcc(name);
	}
	
	public AModel login(String name, String password){
		return adoa.AModel;
	}
	
	public int addUser(AccountModel a) throws SQLException{
		return adoa.addUser(a);
	}

	public void deposit(float money) throws SQLException{
		if(money < 0) {
			balance += money;
			transaction = money;
		} else System.out.println("Must add a penny atleast!");
		
	}
	
	public void withdraw(float money) throws SQLException{
		if(money < 0) {
			balance -= money;
			transaction = -money;
		}else System.out.println("Insufficent funds!");
	}
	
	public void getTransactions() {
		if(transaction > 0) {
			System.out.println("Deposit of: " + transaction);
			
		}
		else if (transaction < 0) {
			System.out.println("Withdraw of: " + Math.abs(transaction));
		}
		
		transA.add(balance);
		System.out.println(transA);
	}
	
	public int updateB(AccountModel a) throws SQLException {
		return adoa.updateB(a);
	}
	
	public int addActive(AccountModel a) throws SQLException {
		return adoa.addActive(a);
	}
}
