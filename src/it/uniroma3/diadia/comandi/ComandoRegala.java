package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if (partita.getGiocatore().getBorsa().getNumeroAttrezzi() == 0)
			this.getIo().mostraMessaggio("Non ci sono attrezzi nella borsa");
		else
			if (partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 4 && partita.getLabirinto().getStanzaCorrente().getPersonaggio().getClass().getSimpleName().equals("Mago"))
				this.getIo().mostraMessaggio("Non c'è spazio nella stanza per regalare l'attrezzo (MAX 4 Attrezzi)");
			else if (this.getParametro() == null)
				this.getIo().mostraMessaggio("Che attrezzo vuoi regalare " + partita.getGiocatore().getNome() + "?");
			else if (this.getParametro().equals("chiave") || this.getParametro().equals("lanterna"))
				this.getIo().mostraMessaggio("Non puoi regalare questo attrezzo, ti serve per andare avanti nel gioco!");				
			else {
				Attrezzo daRegalare = partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				if (daRegalare == null)
					this.getIo().mostraMessaggio("Attrezzo inesistente");
				else
					this.getIo().mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getPersonaggio().riceviRegalo(daRegalare, partita));
			}
		
	}

}
