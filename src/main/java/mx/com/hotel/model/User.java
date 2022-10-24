package mx.com.hotel.model;

public class User {
	private Integer id; 
	private String user; 
	private String password;
	
	public User(int id) {
		this.id = id; 
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
}
