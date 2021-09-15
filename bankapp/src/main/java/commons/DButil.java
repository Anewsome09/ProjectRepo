package commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	
	private static DButil _instance;
	private Connection con = null;
	
	private DButil() throws SQLException{

	}
		
	public static DButil getInstance() throws SQLException {
		if(_instance == null) {
			_instance = new DButil();
		}		
		return _instance;
	}
	
	public Connection getConnection() throws SQLException{
		if(this.con == null) {
			this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","4Dapost");
		}
		return this.con;
	}
}

