package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = { "vai", "aiuto", "guarda", "prendi", "posa", "fine" };
	private IO console;

	@Override
	public void esegui(Partita partita) {
		this.console.mostraMessaggio("\nNome utente:\t" + partita.getGiocatore().getNome());
		this.console.mostraMessaggio("Cfu correnti:\t" + partita.getGiocatore().getCfu());
		this.console.mostraMessaggio("Inventario:\t" + partita.getGiocatore().getBorsa().toString());
		this.console.mostraMessaggio("Stanza corrente:\t" + partita.getLabirinto().getStanzaCorrente().getDescrizione());
		for (int i = 0; i < elencoComandi.length; i++)
			this.console.mostraMessaggio(elencoComandi[i] + " ");
		this.console.mostraMessaggio("\n");
		
	}

	@Override
	public void setParametro(String direzione) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIo(IO console) {
		this.console= console;
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

}
