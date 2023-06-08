package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	
	private String nome;
	private String descrizione;
	private boolean saluto;
	
	public AbstractPersonaggio(String n, String d) {
		this.nome= n;
		this.descrizione= d;
		this.saluto= false;
	}
	
	public String saluta () {
		StringBuilder s = new StringBuilder();
		s.append("Ciao straniero, \nio mi chiamo ");
		s.append(nome);
		s.append(". ");
		if (!this.saluto) {
			s.append(descrizione);
			this.saluto= true;
		}
		else
			s.append("Ci siamo già presentati!");
		return s.toString();	
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public boolean haSalutato () {
		return this.saluto;
	}
	
	public abstract String esegui (Partita partita);
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
}
