package mx.com.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import mx.com.hotel.model.Guest;

public class GuestDAO {
	
	private Connection con; 
		
	public GuestDAO(Connection con) {
		this.con = con; 
	}

	public void save(List<Guest> guests){
		try {
			con.setAutoCommit(false);
			PreparedStatement statement = con.prepareStatement("INSERT INTO GUEST (first_name, last_name, birth, nationality, phone, id_reservation)"
					+"VALUES (?, ?, ?, ?, ?, ?)");
			guests.forEach(guest -> {
					executeStatement(guest, statement);
			});
			con.commit();
			statement.close();
		}catch (Exception e1) {
			try {
				con.rollback();
			} catch (SQLException e2) {
				throw new RuntimeException(e2);
			}
			throw new RuntimeException(e1);
		}
		
	}

	private void executeStatement(Guest guest, PreparedStatement statement){
		try {
			statement.setString(1, guest.getFirstName());
			statement.setString(2, guest.getLastName());
			statement.setDate(3, new Date(guest.getBirth().getTime()));
			statement.setString(4, guest.getNationality());
			statement.setLong(5, guest.getPhoneNumber());
			statement.setInt(6, guest.getIdReservation());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	
	}

	public List<Guest> search(String input) {
		Long longNumber;
		Integer intNumber;
		try {
			longNumber = Long.valueOf(input);
		} catch (NumberFormatException e){
			longNumber = null; 
			e.printStackTrace();
		}
		
		try {
			intNumber = Integer.valueOf(input);
		} catch (NumberFormatException e) {
			intNumber = null; 
			e.printStackTrace();
		}
		
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT ID, FIRST_NAME, LAST_NAME, BIRTH, NATIONALITY, PHONE, ID_RESERVATION"
					+ " FROM GUEST "
					+ " WHERE ID = " + intNumber
					+ " OR FIRST_NAME = '" + input + "'"
					+ " OR LAST_NAME = '" + input + "'"
					+ " OR PHONE = " + longNumber
					+ " OR ID_RESERVATION = " + intNumber
					);
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet){
					List<Guest> result = new ArrayList<>();
					 while(resultSet.next()) {
						 Guest guest = new Guest(
								 resultSet.getInt("ID"),
								 resultSet.getString("FIRST_NAME"),
								 resultSet.getString("LAST_NAME"),
								 new java.util.Date(resultSet.getDate("BIRTH").getTime()),
								 resultSet.getString("NATIONALITY"),
								 resultSet.getLong("PHONE"),
								 resultSet.getInt("ID_RESERVATION")
								 );

						 result.add(guest);
					 }
					 return result; 
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Guest guest) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE GUEST SET"
					+ " FIRST_NAME = ?,"
					+ " LAST_NAME = ?, "
					+ " BIRTH = ?, "
					+ " NATIONALITY = ?,"
					+ " PHONE = ?, "
					+ " ID_RESERVATION = ?"
					+ " WHERE ID = ?");
			try(statement){
				statement.setString(1, guest.getFirstName());
				statement.setString(2, guest.getLastName());
				statement.setDate(3, new Date (guest.getBirth().getTime()));
				statement.setString(4, guest.getNationality());
				statement.setLong(5, guest.getPhoneNumber());
				statement.setInt(6, guest.getIdReservation());
				statement.setInt(7, guest.getId());
				
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM GUEST WHERE ID = ?");
			try (statement){
				statement.setInt(1, id);
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
