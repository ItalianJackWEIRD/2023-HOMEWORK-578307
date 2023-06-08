package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configuratore;

public class Giocatore {

	private String nomeUtente;
	private int cfu;
	private Borsa borsa;

	public Giocatore (String nome, int cfu, int pesoMax) {
		this.nomeUtente= nome;
		this.cfu = cfu;
		this.borsa= new Borsa(pesoMax);
	}

	public String getNome() {
		return this.nomeUtente;
	}

	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public Borsa getBorsa () {
		return this.borsa;
	}
}
