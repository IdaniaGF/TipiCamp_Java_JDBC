package mx.com.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.com.hotel.model.User;

public class LoginDAO {
	private Connection con; 
	
	public LoginDAO(Connection con) {
		this.con = con;
	}

	public boolean login(String password, String user) {
		ArrayList<User> result = new ArrayList<>();
		try {
			PreparedStatement statement = con.prepareStatement("SELECT ID FROM USER WHERE USER = ? AND PASSWORD = ?");
			try(statement){
				statement.setString(1, user);
				statement.setString(2, password);
				ResultSet resultSet = statement.executeQuery();
				while(resultSet.next()) {
					User userId = new User (resultSet.getInt("ID"));
					result.add(userId);
				}
			}
			if (result.isEmpty()) {
				return false; 
			}else {
				return true; 
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
