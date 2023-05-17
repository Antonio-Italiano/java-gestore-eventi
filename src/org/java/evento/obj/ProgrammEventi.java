package org.java.evento.obj;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

import org.java.evento.abs.Evento;

public class ProgrammEventi{

	private String titolo;
    private List<Evento> eventi;
    
    public ProgrammEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }
	
    public void addEvento(Evento evento) {
        eventi.add(evento);
    }

    public List<Evento> getEventiDate(LocalDate date) {
        return eventi.stream()
                .filter(e -> e.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public int numberEventi() {
        return eventi.size();
    }

    public void empty() {
        eventi.clear();
    }
    
	public String getString() {
    List<Evento> eventiOrdinati = eventi.stream()
            .sorted(Comparator.comparing(Evento::getDate))
            .collect(Collectors.toList());

    StringBuilder sb = new StringBuilder();
    sb.append(titolo).append("\n");
    for (Evento e : eventiOrdinati) {
        sb.append(e.getDate().toString()).append(" - ").append(e.getTitle()).append("\n");
    }
    return sb.toString();
    }
    
}
