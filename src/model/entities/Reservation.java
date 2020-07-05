package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
	
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation() {
	}
	
	public Reservation(Integer roomNumber, Date checkin, Date checkout) {
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumbe() {
		return roomNumber;
	}

	public void setRoomNumbe(Integer roomNumbe) {
		this.roomNumber = roomNumbe;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	public String updateDates(Date checkin, Date checkout) {
		Date now = new Date();
		if(checkin.before(now) || checkout.before(now)) {
			return "Error in reservation: Reservation dates for update be future dates";
		}
		if(!checkout.after(checkin)) {
			return "Error in reservation: Check-out must be after check-in date";
		}
		this.checkin = checkin;
		this.checkout = checkout;
		return null;
	}
	
	@Override
	public String toString() {
		return "Room"
				+ roomNumber
				+ ", checkin"
				+  sdf.format(checkin)
				+ ", checkout"
				+ sdf.format(checkout)
				+ ", "
				+ duration()
				+ " nights";
	}
	
}
