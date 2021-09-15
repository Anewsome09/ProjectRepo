package customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import commons.AccountModel;
import manager.AccManager;

public class CustomerL {
	
	private AccManager customer = new AccManager();

	public void start() throws SQLException {
		boolean quit = false;
		
		Scanner in = new Scanner(System.in);
		
		do {
			// Present Menu
			viewMenu();
			System.out.println("Select an option");
			String input = in.next();

			switch (input) {
			case "1":
				searchAccount(in);
				break;
			case "2":
//				searchAccount(in);
				break;
			case "3":
//				addUser(in);
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
			default:
				System.out.println("Please select a valid input\n");
				break;
			}
		} while (!quit);
		
		in.close();
		System.out.println("Goodbye!");

}
	
	protected void viewMenu() {
		System.out.println("1. View your Accounts");
		System.out.println("2. Apply for a New Account");
		System.out.println("3. Transfer to another Account");
		System.out.println("4. Make a Deposit");
		System.out.println("5. Make a Withdraw");
		System.out.println("6. Make a Withdraw");
		System.out.println("7. Quit and close");
	}
	
	private void searchAccount(Scanner in) {
		
		System.out.println("Enter the username of the account: ");
		String name = in.next();
		
		List<AccountModel> accs = customer.findAcc(name);
		printAcc(accs);
		
	}

	//Option 1
	private void viewAccount() {
		
		
		List<AccountModel> accs = customer.findAll();
		printAcc(accs);
		
	}
		
	private void printAcc(List<AccountModel> b) {
		
		for(AccountModel a : b) {
			System.out.println("\n Name : \t"+a.getCustomer() +"\n Account Type : "+a.getType() +
					"\n Balance: \t$"+a.getBalance());
			System.out.println();
			}
	}
	
	private void deposit(Scanner in) throws SQLException {
		
		System.out.println("Enter the username of the account: ");
		String name = in.next();
		
		System.out.println("Enter the amount to deposit: ");
		float amount = in.nextFloat();
				
		List<AccountModel> accs = customer.findAcc(name);
		for(AccountModel a1 : accs) {
				float balance = a1.getBalance()+amount;
				a1.setBalance(balance);
				printAcc(accs);
				customer.updateB(a1);
			}
	}
	
	//Option 5
	private void withdraw(Scanner in) throws SQLException {
		System.out.println("Enter the username of the account: ");
		String name = in.next();
		
		System.out.println("Enter the amount to withdraw: ");
		float amount = in.nextFloat();		
		
		List<AccountModel> accs = customer.findAcc(name);
		for(AccountModel a : accs) {
				float nbalance = (float) (a.getBalance()-amount);
				a.setBalance(nbalance);
				customer.updateB(a);
				printAcc(accs);
				
			}
	}
}