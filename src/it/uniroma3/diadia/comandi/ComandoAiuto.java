package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = { "vai", "aiuto", "guarda", "prendi", "posa", "fine", "saluta", "interagisci", "regala" };

	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("\nNome utente:\t" + partita.getGiocatore().getNome());
		this.getIo().mostraMessaggio("Cfu correnti:\t" + partita.getGiocatore().getCfu());
		this.getIo().mostraMessaggio("Inventario:\t" + partita.getGiocatore().getBorsa().toString());
		this.getIo().mostraMessaggio("Stanza corrente:\t" + partita.getLabirinto().getStanzaCorrente().getDescrizione());
		for (int i = 0; i < elencoComandi.length; i++)
			this.getIo().mostraMessaggio(elencoComandi[i] + " ");
		this.getIo().mostraMessaggio("\n");
		
	}
}
