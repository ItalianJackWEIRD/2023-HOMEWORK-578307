package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version base
 */

public class Stanza {

	static final private int NUMERO_MASSIMO_DIREZIONI = 4; // puoi avere max 4 direzioni Nord, Sud, Ovest, Est
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	private Map<String, Attrezzo> attrezzi;
	//private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private Map<Direzione, Stanza> stanzeAdiacenti;
	//private Stanza[] stanzeAdiacenti;
	private int numeroStanzeAdiacenti;
	//private String[] direzioni;
	private AbstractPersonaggio personaggio;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public Stanza(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
		this.stanzeAdiacenti = new TreeMap<>();
		this.attrezzi = new TreeMap<>();
		this.personaggio= null;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
		boolean aggiornato = false;
		if (this.stanzeAdiacenti.containsKey(direzione)) {
			this.stanzeAdiacenti.put(direzione, stanza);
			aggiornato = true;
		}
		if (!aggiornato) {
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.stanzeAdiacenti.put(direzione, stanza);
				this.numeroStanzeAdiacenti++;
			}
		}

		/*
		for (int i = 0; i < this.direzioni.length; i++)
			if (direzione.equals(this.direzioni[i])) {
				this.stanzeAdiacenti[i] = stanza;
				aggiornato = true;
			}
		if (!aggiornato)
			if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
				this.direzioni[numeroStanzeAdiacenti] = direzione;
				this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
				this.numeroStanzeAdiacenti++;
			}
		 */
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public Stanza getStanzaAdiacente(Direzione direzione) {
		Stanza stanza = null;

		if (this.stanzeAdiacenti.containsKey(direzione))
			stanza = this.stanzeAdiacenti.get(direzione);
		/*
		for (int i = 0; i < this.numeroStanzeAdiacenti; i++)
			if (this.direzioni[i].equals(direzione))
				stanza = this.stanzeAdiacenti[i];
		 */
		return stanza;
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Collection<Attrezzo> getAttrezzi() {
		return this.attrezzi.values();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi.put(attrezzo.getNome(), attrezzo);
			this.numeroAttrezzi++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		for (Direzione direzione : this.stanzeAdiacenti.keySet())
			if (direzione != null)
				risultato.append(" " + direzione);
		risultato.append("\nAttrezzi nella stanza: ");
		if (this.numeroAttrezzi==0)
			risultato.append(" nessun attrezzo nella stanza");
		else {
			for (Attrezzo attrezzo : this.attrezzi.values()) 
				if (attrezzo != null)
					risultato.append(" | " + attrezzo.toString());
			risultato.append(" |");
		}
		if (this.personaggio!=null)
			risultato.append("\nE' presente un " + this.personaggio.getClass().getSimpleName() + "!");
			
		risultato.append("\n");
		return risultato.toString();
	}

	public int getNumeroAttrezzi () {
		return this.numeroAttrezzi;
	}
	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		if (this.attrezzi.containsKey(nomeAttrezzo))
			trovato = true;
		return trovato;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		if(this.numeroAttrezzi==0)
			return attrezzoCercato;
		else {
			if (this.attrezzi.containsKey(nomeAttrezzo))
				attrezzoCercato = this.attrezzi.get(nomeAttrezzo);
			return attrezzoCercato;
		}
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo che è stato rimosso (ritorna null se non esiste)
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a= null; //da ritornare
		if(nomeAttrezzo!=null) {
			if (this.attrezzi.containsKey(nomeAttrezzo)) {
				a = this.attrezzi.remove(nomeAttrezzo);
				this.numeroAttrezzi--;
			}			
		}			
		return a;

	}

	public Set<Direzione> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
	}
	
	public List<Stanza> getStanzeAdiacenti() {
		List<Stanza> listaStanzeAdiacenti = new ArrayList<>();
		for (Stanza s : stanzeAdiacenti.values()) {
			listaStanzeAdiacenti.add(s);
		}
		return listaStanzeAdiacenti;
	}
	
	public void addPersonaggio(AbstractPersonaggio p) {
		this.personaggio= p;
	}
	
	public AbstractPersonaggio getPersonaggio() {
		return this.personaggio;
	}

}