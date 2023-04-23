package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando{
	
	private String direzione;
	private IO console;
	
	@Override
	public void esegui (Partita partita) {
		if (direzione == null)
			this.console.mostraMessaggio("Dove vuoi andare "+ partita.getGiocatore().getNome() + "?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				this.console.mostraMessaggio("Direzione inesistente");
			else if (prossimaStanza == partita.getLabirinto().getStanzaCorrente())
				this.console.mostraMessaggio("La direzione richiesta è bloccata!");
			else {
				partita.getLabirinto().setStanzaCorrente(prossimaStanza);
				int cfu = partita.getGiocatore().getCfu() - 1;
				partita.getGiocatore().setCfu(cfu);
				console.mostraMessaggio("\n|CFU - 1| ... Cfu correnti = " + partita.getGiocatore().getCfu());
			}
			this.console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}
		
	}
	
	@Override
	public void setParametro (String direzione) {
		this.direzione= direzione;
	}
	
	@Override
	public void setIo (IO console) {
		this.console= console;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}
}
