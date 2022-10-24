package mx.com.hotel.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation {

	private Integer id; 
	private Date dateIn; 
	private Date dateOut; 
	private Double value; 
	private String paymentMethod;
	private List<Guest> guests;
	 
	public Reservation(Date dateIn, Date dateOut, Double value, String paymentMethod) {
		this.setDateIn(dateIn); 
		this.setDateOut(dateOut); 
		this.setValue(value); 
		this.setPaymentMethod(paymentMethod); 
		
	}
	
	public Reservation(Integer id, Date dateIn, Date dateOut, Double value, String paymentMethod) {
		this.setId(id);
		this.setDateIn(dateIn); 
		this.setDateOut(dateOut); 
		this.setValue(value); 
		this.setPaymentMethod(paymentMethod); 	}

	public static Double calcValue(long days) {
		Double value = days*345.5;
		return value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public void addGuest(Guest guest) {
		if (this.guests == null) {
			this.guests = new ArrayList<>();
		}
		this.guests.add(guest);
	}
	
	public void cleanGuests() {
		this.guests.clear();
	}
	
	public List<Guest> getGuests(){
		return this.guests;
	}
	
}
