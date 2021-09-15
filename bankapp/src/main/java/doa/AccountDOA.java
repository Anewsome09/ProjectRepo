package doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import commons.AModel;
import commons.AccountModel;
import commons.DButil;


public class AccountDOA {
	
	public int id;
	public static String name;
	public float balance;
	public AModel AModel;
	public static String customer;
	public static String type;
	private static String password;
	public static String emp;
	public static String activate = "Y";
	private static String accTable = "select * from bankapp.customer_acc";
	private static String fullTable = "SELECT * from bankapp.customer\r\n"
			+ "	INNER JOIN bankapp.customer_login\r\n"
			+ "	ON bankapp.customer_login.user_name1 = bankapp.customer.user_name";

	
	// Check to see if name is Available
	private boolean isNameAv(String name) {
		return name != null && !name.isEmpty();
	}
	public boolean isAcActive(String name) {
		return emp == activate;
	}
	
	public List<AccountModel> findAll(){
		List<AccountModel> acc = new ArrayList<AccountModel>();
		
		try {
			Connection con1 = DButil.getInstance().getConnection();
			Statement stmt = con1.createStatement();
			
			ResultSet rs1 = stmt.executeQuery(fullTable);
			
			while(rs1.next()) {
	//			id = rs1.getInt("acc_id");
				name = rs1.getString("user_name");
				customer = rs1.getString("name");
				password = rs1.getString("password");
				type = rs1.getString("Account_type");
				balance = rs1.getFloat("balance");
				AccountModel a = new AccountModel( name, customer, password, type, balance);
				acc.add(a);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return acc;
	}
	
	public List<AccountModel> findAcc(String name){
		List<AccountModel> accSingle = new ArrayList<AccountModel>();
		
		try {
				Connection con2 = DButil.getInstance().getConnection();
				
				String qry = fullTable;
				if (isNameAv(name)) {
					qry += " where user_name = ?";
				}
				PreparedStatement prst = con2.prepareStatement(qry);
				
				if (isNameAv(name)) {
					prst.setString(1, name);
				}
						
				ResultSet rset = prst.executeQuery();
				
				while(rset.next()) {
					emp = rset.getString("employee");
					customer = rset.getString("name");
					name = rset.getString("user_name");
					type = rset.getString("account_type");
					balance = rset.getFloat("balance");
					password = rset.getString("password");
					AccountModel a = new AccountModel( name, customer, password, type, balance);
					accSingle.add(a);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accSingle;
	}
		

	// Adding a New User with an account
	public int addUser(AccountModel a) throws SQLException {
		Connection con = DButil.getInstance().getConnection();

		PreparedStatement prst = con.prepareStatement("insert into bankapp.customer_login (user_name1,password, balance) values(?,?,?)");
		PreparedStatement prst2 = con.prepareStatement("insert into bankapp.customer (user_name, name, account_type) values(?,?,?)\r\n");
		
			// Apply for an account
			prst2.setString(1, a.getCustomer());
			prst2.setString(2, a.getName());
			prst2.setString(3, a.getType());
			// Add a deposit
			prst.setString(2, a.getPassword());
			prst.setFloat(3, a.getBalance());
			prst.setString(1, a.getCustomer());
		
		int insert1 = prst2.executeUpdate();
		int insert2= prst.executeUpdate();
		return insert1+insert2;
		

	}
	
	//Activate account
	public int addActive(AccountModel a) throws SQLException {
		Connection con = DButil.getInstance().getConnection();
		
		PreparedStatement prst = con.prepareStatement("update bankapp.customer_login set active = 'Y' where user_name1 = ?;");
		
		prst.setString(1, a.getCustomer());
		
		int insert = prst.executeUpdate();
		
		return (insert);
	}
	
	public int updateB(AccountModel a) throws SQLException {
		
		
		Connection con = DButil.getInstance().getConnection();
		PreparedStatement prst = con.prepareStatement("update bankapp.customer_login set balance = ? where user_name1 = ?");			
		prst.setFloat(1, balance);
		prst.setString(2, a.getCustomer());
			
		int insert = prst.executeUpdate();
		
		return (insert);
	}
	
	public AModel login(String name, String password){
		AModel ac = new AModel();
		
		try {
			Connection con1 = DButil.getInstance().getConnection();
			Statement stmt = con1.createStatement();
			
			ResultSet rs1 = stmt.executeQuery(fullTable);
			
			while(rs1.next()) {
				id = rs1.getInt("acc_id");
				name = rs1.getString("user_name1");
				password = rs1.getString("password");
				emp = rs1.getString("employee");
				ac = new AModel(id,name,password,emp);
			}
				
		} catch (SQLException e) {
			System.out.println("Username or password is incorrect.");
			e.printStackTrace();
		}
		return ac;
	}

}
