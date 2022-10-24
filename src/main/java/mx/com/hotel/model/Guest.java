package mx.com.hotel.model;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Locale;

public class Guest {
	
	private Integer id; 
	private String firstName; 
	private String lastName;
	private Date birth; 
	private String nationality; 
	private Long phoneNumber; 
	private Integer idReservation; 
	
	public Guest(String firstName, String lastName, Date birth, String nationality, Long phoneNumber, Integer idReservation) {
		this.setFirstName(firstName); 
		this.setLastName(lastName); 
		this.setBirth(birth); 
		this.setNationality(nationality); 
		this.setPhoneNumber(phoneNumber); 
		this.setIdReservation(idReservation); 
	}
	
	public Guest(int id, String firstName, String lastName, Date birth, String nationality, long phoneNumber, int idReservation) {
		this.setId(id);
		this.setFirstName(firstName); 
		this.setLastName(lastName); 
		this.setBirth(birth); 
		this.setNationality(nationality); 
		this.setPhoneNumber(phoneNumber); 
		this.setIdReservation(idReservation); 	}

	public Guest(String firstName, String lastName) {
		this.setFirstName(firstName); 
		this.setLastName(lastName); 
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth; 
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer reservationId) {
		this.idReservation = reservationId;
	}

}
