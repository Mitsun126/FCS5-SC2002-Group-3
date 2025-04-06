package code;

public abstract class User {
	private String name;
	private String nric;
	private int age;
	private String marital_status;
	private String password;
	
	public User() {
		name = "";
		nric = "";
		age = 0;
		marital_status = "";
		password = "password";
	}
	
	public User(String name, String NRIC, int age, String marital_status) {
		this.name = name;
		this.nric = NRIC;
		this.age = age;
		this.marital_status = marital_status;
		this.password = "password";
	}
	
	//name get/set
	public String get_Name() {return name;}
    public void set_Name(String name) {this.name = name;}

    //nric get/set
    public String get_Nric() {return nric;}
    public void set_Nric(String nric) {this.nric = nric;}
    
    //age get/set
    public int get_Age() {return age;}
    public void set_Age(int age) {this.age = age;}
    
    //Marital status get/set
    public String get_Marital_status() {return marital_status;}
    public void set_Marital_status(String marital_status) {this.marital_status = marital_status;}
	
    //password get/set
    public String get_Password() {return password;}
    public void set_Password(String password) {this.password = password;}

}
