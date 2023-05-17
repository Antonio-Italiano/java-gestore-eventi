package org.java.evento.abs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Evento {

	private String title;
	private LocalDate date;
	private int maxPlaces;
	private int orderPlaces;
	
	public Evento(String title, LocalDate date, int maxPlaces) throws Exception {
		
		if (date.isBefore(LocalDate.now())) {
			throw new Exception("Questa data è già passata");
		}
		if(maxPlaces <= 0) {
			throw new Exception("I posti totali devono essere maggiori di zero");
		}
		
		setTitle(title);
		setDate(date);
		setMaxPlaces(maxPlaces);
		this.orderPlaces = 0;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getMaxPlaces() {
		return maxPlaces;
	}

	public void setMaxPlaces(int maxPlaces) {
		this.maxPlaces = maxPlaces;
	}

	public int getOrderPlaces() {
		return orderPlaces;
	}

	public void setOrderPlaces(int orderPlaces) {
		this.orderPlaces = orderPlaces;
	}
	
	public void reserve() throws Exception{
		if(LocalDate.now().isAfter(date)) {
			throw new Exception("Non è possibile prenotare un evento passato");
		}
		if(orderPlaces >= maxPlaces) {
			throw new Exception("Posti terminati");
		}
		orderPlaces++;
	}
	
	public void cancel() throws Exception{
		if(LocalDate.now().isAfter(date)) {
			throw new Exception("Non è possibile disdire un evento passato");
		}
		if(orderPlaces <= 0 ) {
			throw new Exception("Non risulta nessuna prenotazione per questo evento");
		}
		orderPlaces--;
	}
	
	//METODI AGGIUNTIVI
	public int getplacesLeft() {
		return maxPlaces - orderPlaces;
	}
	
	private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
	
	protected String getEventoString() {
		return formatDate(date) 
				+ " - " + getTitle()
				+ "\nPosti disponibili: " + getplacesLeft();
	}
	
	@Override
    public String toString() {
        return "\n" + getEventoString() + "\n";
    }
}
