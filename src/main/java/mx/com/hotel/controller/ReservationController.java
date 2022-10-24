package mx.com.hotel.controller;

import java.util.List;

import mx.com.hotel.dao.ReservationDAO;
import mx.com.hotel.factory.ConnectionFactory;
import mx.com.hotel.model.Reservation;

public class ReservationController {
	
	private ReservationDAO reservationDAO; 
	
	public ReservationController() {
		this.reservationDAO = new ReservationDAO(new ConnectionFactory().getConnection());
	}
	
	public void save(Reservation reservation) {
		reservationDAO.save(reservation); 
	}

	public List<Reservation> search(String text) {
		return reservationDAO.search(text);
	}

	public void update(Reservation reservation) {
		reservationDAO.update(reservation);
	}

	public void delete(Integer id) {
		reservationDAO.delete(id);
		
	}

}