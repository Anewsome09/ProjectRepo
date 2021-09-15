import java.sql.SQLException;

import customer.Employee;

public class MainApp {
	
	public static void main(String[] args) {
		Employee client = new Employee();
		try {
			client.start();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
