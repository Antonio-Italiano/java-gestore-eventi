package org.java.evento.obj;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

import org.java.evento.abs.Evento;

public class Concerto extends Evento{

	private LocalTime time;
	private BigDecimal price;
	
	public Concerto(String title, LocalDate date, int maxPlaces, LocalTime time, BigDecimal price) throws Exception {
		super(title, date, maxPlaces);

		setTime(time);
		setPrice(price);		
	}
	
	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public BigDecimal getTotPrice(int val) {
		return price.multiply(BigDecimal.valueOf(val));
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getFormattedDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    String timeString = this.time.format(formatter);
	    return getDate() + " ore: " + timeString;
	}

	public String getFormattedPrice() {
	    DecimalFormat formatter = new DecimalFormat("##,##0.00 €");
	    return formatter.format(getPrice());
	}

	protected String getConcertoString() {
		return  
				" - nome: " + getTitle() 
				+ "\n - data: " +getFormattedDateTime()
				+ "\n - prezzo: " + getFormattedPrice()
				+ "\nPosti disponibili: " + getplacesLeft();
	}
	
	@Override
	public String toString() {
	    return getConcertoString();
	}
	
}
