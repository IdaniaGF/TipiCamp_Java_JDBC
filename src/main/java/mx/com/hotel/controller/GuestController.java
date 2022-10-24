package mx.com.hotel.controller;

import java.util.List;

import mx.com.hotel.dao.GuestDAO;
import mx.com.hotel.factory.ConnectionFactory;
import mx.com.hotel.model.Guest;

public class GuestController {
	
	private GuestDAO guestDAO; 
	
	public GuestController() {
		guestDAO = new GuestDAO(new ConnectionFactory().getConnection());
	}
	
	public void save(List<Guest> Guests) {
		guestDAO.save(Guests); 
	}

	public List<Guest> search(String input) {
		return guestDAO.search(input);
	}

	public void update(Guest guest) {
		guestDAO.update(guest);
	}

	public void delete(Integer id) {
		guestDAO.delete(id);
	}

}
