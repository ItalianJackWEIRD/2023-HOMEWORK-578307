package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/*
import it.uniroma3.diadia.attrezzi.Attrezzo;
 */
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if ( (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) || this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public int getNumeroAttrezzi () {
		return this.numeroAttrezzi;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];
		return a;
	}

	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
	 * Restituisce l'attrezzo rimosso per poterlo lasciare nella stanza dove è il giocatore
	 * (ritorna null se non esiste)
	 * 
	 * @param nome dell'attrezzo da rimuovere
	 * @return l'attrezzo che è stato rimosso
	*/
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if (nomeAttrezzo!=null) {
			for (int i=0; i<this.numeroAttrezzi; i++) {		//scorre l'array di attrezzi in cerca del corrispettivo da cancellare
				if (this.attrezzi[i] != null) {
					if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
						a = this.attrezzi[i];		//se lo ha trovato lo copia in "a"
						for (int j= i; j<this.numeroAttrezzi-1; j++)		//riscorre a partire da quell'attrezzo e sposta di una posizione indietro ogni attrezzo dopo di esso per non lasciare spazi vuoti
							this.attrezzi[j]= this.attrezzi[j+1];
						this.attrezzi[this.numeroAttrezzi-1] = null;	//cancello l'ultimo visto che ho spostato tutti di una casella a sx
						this.numeroAttrezzi--;
					}
				}
			}
		}
		return a;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(" | " + attrezzi[i].toString());
			s.append(" |");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

}