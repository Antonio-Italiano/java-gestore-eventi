package org.java.evento;

import org.java.evento.abs.Evento;
import org.java.evento.obj.Concerto;
import org.java.evento.obj.ProgrammEventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Locale;

public class Main {

	public static void main(String[] args) throws Exception {
		
		//CREAZIONE EVENTO
		Scanner sc = new Scanner(System.in);		
		
		while(true) {
			
			System.out.println("Inserisci il titolo dell'evento:");						
			String title  = sc.nextLine();
			
			LocalDate date = null;
			boolean correctDate = false;
			
			while (!correctDate) {
				System.out.println("Inserisci la data dell'evento: (AAAA-MM-GG)");
	            String dateString = sc.nextLine();
	            try {
	            	date = LocalDate.parse(dateString);
	            	if(date.isBefore(LocalDate.now())) {
	            		System.err.println("Errore: la data inserita è già passata.");
	            	} else {            		
	            		correctDate = true;
	            	}
	            } catch(DateTimeParseException e) {
	            	System.err.println("La data inserita non è valida.");
	            }
			}
			
			int maxPlaces = -1;
	        while (maxPlaces < 0) {
	            System.out.println("Inserisci il numero di posti totali disponibili:");
	            try {
	            	maxPlaces = sc.nextInt();
	            	sc.nextLine();
	            } catch (NumberFormatException e) {
	                System.err.println("Inserisci un numero valido.");
	            }
	        }		
	        
	        LocalTime time = null;			
			while (time == null) {
				System.out.println("Inserisci l'ora dell'evento: (\"HH:mm\")");
	            String timeString = sc.nextLine();
	            try {
	            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.ITALIAN);
					time = LocalTime.parse(timeString, formatter);
	            	
	            } catch(DateTimeParseException e) {
	            	System.out.println("L'ora inserita non è valida.");
	            }
			}
			
			String price = "0.00";
	        while (price == "0.00") {
	            System.out.println("Inserisci il prezzo dell'concerto: (\"#.##\")");
	            try {
	            	price = sc.nextLine();
	            	
	            } catch (NumberFormatException e) {
	                System.err.println("Inserisci un numero valido.");
	            }
	        }				
	        
	        BigDecimal priceConverted =  new BigDecimal(price);
	        
			//sc.close();
	        Concerto evento = new Concerto(title, date, maxPlaces, time, priceConverted);
	        System.out.println("\nEvento creato:\n" + evento);  
	       
	        //PRENOTAZIONE/DISDIRE EVENTO
	        while (true) {
	            try {
	            	if(evento.getplacesLeft() == evento.getMaxPlaces()) {
	            		System.out.println("\nPer prenotare (1) per uscire (3)");
	            	} else if(evento.getplacesLeft() == 0){            		
	            		System.out.println("\nPer disdire(2) per uscire (3)");
	            	} else {
	            		System.out.println("\nPer prenotare (1) per disdire(2) per uscire (3)");
	            	}
	            	int choice = sc.nextInt();  
	            	sc.nextLine();
	            	
	            	if(choice < 1 || choice > 2) break;
	            	
	            	if (choice == 1) {                    
	            		System.out.println("\nQuanti posti vuoi prenotare");
	                }
	            	
	            	if (choice == 2) {
	            		System.out.println("\nQuanti posti vuoi disdire");
	            	}
	            	
	            	int numberPlaces = sc.nextInt();
	            	
	            	if(choice == 1) {	            		
	            	System.out.println("\nPrezzo Tot: " + evento.getTotPrice(numberPlaces) + " €");
	            	}
	            	
	            	if(maxPlaces < numberPlaces) {
	            		if (choice == 1) {                    
	            			System.out.println("Hai superato la disponibilità: " + evento.getplacesLeft());
	                    }
	            		if (choice == 2) {
	                		System.out.println("\nNon risulta nessuna prenotazione da poter disdire");
	                	}            		
	            		break;
	            	}
	            	
	            	for (int x = 0; x < numberPlaces; x++) {
	            		if (choice == 1) {                    
	            			evento.reserve();                		
	                    }
	            		if (choice == 2) {                    
	            			evento.cancel();                		
	                    }
	                }
	            	
	            	if (choice == 1) {                    
	            		System.out.println(
	                			"\nPosti prenotati: " + numberPlaces + ".");       		
	                }
	            	
	            	if (choice == 2) {                    
	            		System.out.println(
	                			"\nPosti disdetti: " + numberPlaces + ".");
	                }
	            	System.out.println(
				            	"Posti rimanenti: " 
				            	+ evento.getplacesLeft() + ".");
	            	
	            	continue;                 		
	            	
	            } catch (NumberFormatException e) {
	                System.out.println("Inserisci un numero valido.");
	                break;
	            }
	        }	        
		}
	}
}
