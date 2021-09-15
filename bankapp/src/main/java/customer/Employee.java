package customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import commons.AModel;
import commons.AccountModel;
import manager.AccManager;

public class Employee {
	String savings = "Savings";
	String checkings = "Checkings";
	boolean quit = false;
//	String searchN;

	
	private AccManager manage = new AccManager();

	public void start() throws SQLException {
		
		Scanner in = new Scanner(System.in);
		
				
		do {
			
			// Present Menu
			viewMenu();
			//login(in);
			System.out.println("Select an option");
			String input = in.next();

			switch (input) {
			case "1":
				viewAccount();
				break;
			case "2":
				searchAccount(in);
				break;
			case "3":
				addUser(in);
				break;
			case "4":
				deposit(in);
				break;
			case "5":
				withdraw(in);
				break;
			case "6":
				quit = true;
				break;
			case "7":
				login(in);
				break;
			default:
				System.out.println("Please select a valid input\n");
				break;
			}
		} while (!quit);
		
		in.close();
		System.out.println("Goodbye!");

//		makeWithdraw();
//		makeDeposit():

	}
	
	private void viewMenu() {
		System.out.println("1. View Accounts");
		System.out.println("2. Search Accounts");
		System.out.println("3. Add a New Account");
		System.out.println("4. Make a Deposit");
		System.out.println("5. Make a Withdraw");
		System.out.println("6. Quit and close");
	}
	
	private void viewMenu2() {
		System.out.println("1. View Your Accounts");
		System.out.println("1. View Your Transactions");
		System.out.println("3. Apply for a New Account");
		System.out.println("4. Make a Deposit");
		System.out.println("5. Make a Withdraw");
		System.out.println("6. Quit and close");
	}
		
	//Option 1
	private void viewAccount() {
		
		List<AccountModel> accs = manage.findAll();
		printAcc(accs);
		
	}
	
	// Option 2
	private void searchAccount(Scanner in) {
		
		System.out.println("Enter the username of the account: ");
		String name = in.next();
		
		List<AccountModel> accs = manage.findAcc(name);
		printAcc(accs);
		
	}
	
	// Option 3
	private void addUser(Scanner in) {
		List<AccountModel> accs = manage.findAll();
		
//		int id = 3;
		String name = getInp("Username:",in);
		String customer = getInp("Your name:", in);
		String type = getInp("Checkings or Savings:",in);
		// Employee adds account
		String password = getInp("Password: ",in); 
		float balance = getDepo("deposit:",in);
		//TODO: handle invalid input (for negative deposit)
		AccountModel a = new AccountModel(customer,name,password,type,balance);
		AccountModel b = new AccountModel(name,customer,type);
		
		if (!accs.contains(name)) {
			try {
				manage.addUser(a);
			} catch (SQLException e) {
				System.out.println("User could not be added.");
				e.printStackTrace();
			}
			System.out.println("Added User Account");
//			id++;
		}	else {
			System.out.println("User Name is already in use. "
					+ "Choose another user name.");
		}
//		while (accs.isAcActive) {
//			manage.addUser(a);
//		}
	}
	
	// Input for the Add Methods
	private String getInp(String newUser, Scanner in) {
		System.out.println(newUser);
		return in.next();
	}
	
	private float getDepo(String newUser, Scanner in) {
		System.out.println(newUser);
		return in.nextFloat();
	}
	
	private void printAcc(List<AccountModel> b) {
		
		for(AccountModel a : b) {
			System.out.println("\n Name : \t"+a.getCustomer() +"\n Account Type : "+a.getType() +
					"\n Balance: \t$"+a.getBalance());
			System.out.println();
			}
	}
	
	//Option 4
	private void deposit(Scanner in) throws SQLException {
		
		System.out.println("Enter the username of the account: ");
		String name = in.next();
		
		System.out.println("Enter the amount to deposit: ");
		float amount = in.nextFloat();
				
		List<AccountModel> accs = manage.findAcc(name);
		for(AccountModel a1 : accs) {
				float nBalance = a1.getBalance()+amount;
				a1.setBalance(nBalance);
//				manage.getTransactions();
				printAcc(accs);
				manage.updateB(a1);
			}
	}
	
	//Option 5
	private void withdraw(Scanner in) throws SQLException {
		System.out.println("Enter the username of the account: ");
		String name = in.next();
		
		System.out.println("Enter the amount to withdraw: ");
		float amount = in.nextFloat();		
		
		List<AccountModel> accs = manage.findAcc(name);
		for(AccountModel a : accs) {
				float nBalance = a.getBalance();
				nBalance -= amount;
				a.setBalance(nBalance);
//				manage.getTransactions();
				manage.updateB(a);
				printAcc(accs);
				
			}
	}
	
	//Login code
	public void login(Scanner in) {
		
		System.out.println("Enter the username of the account: ");
		String sName = in.next();
		
		System.out.println("Enter the password: ");
		String sPassword = in.next();		
		
		AModel ac = manage.login(sName, sPassword);
		
		
			if (sPassword.equals(ac.getPasswordr()) && ac.getEmp() == "Y") {
					viewMenu();
				}
			else if(sPassword.equals(ac.getPasswordr()) && ac.getEmp() == "N") {
					viewMenu2();
				}
			else System.out.println("Invalid password");
		}	
		
}
