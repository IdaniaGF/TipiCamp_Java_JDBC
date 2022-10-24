package mx.com.hotel.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import mx.com.hotel.model.Guest;
import mx.com.hotel.model.Reservation;

public class ReservationDAO {
	
	final private Connection con; 
	
	public ReservationDAO(Connection con) {
		this.con = con; 
	}

	public void save(Reservation reservation) {
		try {
			final PreparedStatement statement = con.prepareStatement("INSERT INTO RESERVATION (date_in, date_out, value, payment_method)"
					+"VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			
			try(statement){
				executeStatement(reservation, statement); 
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void executeStatement(Reservation reservation, PreparedStatement statement) throws SQLException {
		statement.setDate(1, new Date(reservation.getDateIn().getTime()));
		statement.setDate(2, new Date (reservation.getDateOut().getTime()));
		statement.setDouble(3, reservation.getValue());
		statement.setString(4, reservation.getPaymentMethod());
		
		statement.execute();
		
		final ResultSet resultSet = statement.getGeneratedKeys();
		
		
		try(resultSet){
			while(resultSet.next()) {
				reservation.setId(resultSet.getInt(1));
			}
		}
	}

	public List<Reservation> search(String text) {	
		Integer id;
		try {
			id = Integer.valueOf(text);
		} catch (NumberFormatException e) {
			id = null; 
			e.printStackTrace();
		}
		try {
			final PreparedStatement statement = con.prepareStatement(
					"SELECT R.ID, R.DATE_IN, R.DATE_OUT, R.VALUE, R.PAYMENT_METHOD, G.FIRST_NAME, G.LAST_NAME"
					+ " FROM RESERVATION R INNER JOIN GUEST G ON G.ID_RESERVATION = R.ID"
					+ " WHERE R.ID = " + id
					+ " OR G.FIRST_NAME = '" + text + "'"
					+ " OR G.LAST_NAME = '" + text + "'"
					+ " OR G.ID = " + id
					);
			try(statement){
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet){
					List<Reservation> result = new ArrayList<>();
					 while(resultSet.next()) {
						 Integer idReservation = resultSet.getInt("R.ID"); 
						 java.util.Date dateIn = new java.util.Date(resultSet.getDate("R.DATE_IN").getTime());
						 java.util.Date dateOut = new java.util.Date(resultSet.getDate("R.DATE_OUT").getTime());
						 Double value = resultSet.getDouble("R.VALUE");
						 String paymentMethod = resultSet.getString("R.PAYMENT_METHOD");
						 
						 Reservation reservation = result.stream()
								 .filter(res -> res.getId().equals(idReservation))
								 .findAny()
								 .orElseGet(()->{
									 Reservation res = new Reservation(
											 idReservation, 
											 dateIn,
											 dateOut,
											 value,
											 paymentMethod
											 );
									 result.add(res); 
									 return res; 		
								 });
						
						 Guest guest = new Guest(
								 resultSet.getString("G.FIRST_NAME"),
								 resultSet.getString("G.LAST_NAME")
								 );
						 reservation.addGuest(guest);
					 }
					 return result; 
				}

			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Reservation reservation) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVATION SET"
					+ " DATE_IN = ?,"
					+ " DATE_OUT = ?,"
					+ " VALUE = ?,"
					+ " PAYMENT_METHOD = ?"
					+ " WHERE ID = ?"
					);
			try(statement){
				statement.setDate(1, new java.sql.Date(reservation.getDateIn().getTime()));
				statement.setDate(2, new java.sql.Date(reservation.getDateOut().getTime()));
				statement.setDouble(3, reservation.getValue());
				statement.setString(3, reservation.getPaymentMethod());
				statement.setInt(4, reservation.getId());
				statement.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Integer id) {
		try {
			con.setAutoCommit(false);
			PreparedStatement guestStatement = con.prepareStatement("DELETE FROM GUEST WHERE ID_RESERVATION = ?");
			guestStatement.setInt(1, id);
				
			PreparedStatement reservationStatement = con.prepareStatement("DELETE FROM RESERVATION WHERE ID = ?");
			reservationStatement.setInt(1, id);

			try {
				guestStatement.execute();
				reservationStatement.executeUpdate();
				con.commit();
			} catch (Exception e) {
				con.rollback();
			}
			
			guestStatement.close();
			reservationStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(); 
		}
		
	}
	
}
