package it.uniroma3.diadia.giocatore;

public class Giocatore {

	static final private int CFU_INIZIALI = 20;
	private String nomeUtente;
	private int cfu;
	private Borsa borsa;

	public Giocatore (String nome) {
		this.nomeUtente= nome;
		this.cfu = CFU_INIZIALI;
		this.borsa= new Borsa();
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
