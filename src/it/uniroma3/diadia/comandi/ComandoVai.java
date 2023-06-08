package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{
	
	@Override
	public void esegui (Partita partita) {
		if (this.getParametro() == null)
			this.getIo().mostraMessaggio("Dove vuoi andare "+ partita.getGiocatore().getNome() + "?");
		else {
			Stanza prossimaStanza = null;
			try {
				prossimaStanza = partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(Direzione.valueOf(this.getParametro()));
			} catch (IllegalArgumentException e) {
				this.getIo().mostraMessaggio("Direzione inesistente");
				return;
			}
			if (prossimaStanza == null)
				this.getIo().mostraMessaggio("Direzione inesistente");
			else if (prossimaStanza == partita.getLabirinto().getStanzaCorrente())
				this.getIo().mostraMessaggio("La direzione richiesta è bloccata!");
			else {
				partita.getLabirinto().setStanzaCorrente(prossimaStanza);
				int cfu = partita.getGiocatore().getCfu() - 1;
				partita.getGiocatore().setCfu(cfu);
				this.getIo().mostraMessaggio("\n|CFU - 1| ... Cfu correnti = " + partita.getGiocatore().getCfu());
			}
			this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}
		
	}

}
