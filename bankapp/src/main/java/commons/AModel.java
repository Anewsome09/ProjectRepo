package commons;

public class AModel {
	
	private int id;
	private String name;
	private String password;
	public String emp;
	
	public AModel() {
		super();
	}
	
	public AModel(int id, String name, String password, String emp) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.emp = emp;
	}

	public String getPasswordr() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmp() {
		return emp;
	}

	public void setemp(String emp) {
		this.emp = emp;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}


