package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {
		if (partita.getGiocatore().getBorsa().getNumeroAttrezzi() == 0)
			this.getIo().mostraMessaggio("Non ci sono attrezzi nella borsa");
		else
			if (partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 4)
				this.getIo().mostraMessaggio("Non c'è spazio nella stanza per posare l'attrezzo (MAX 4 Attrezzi)");
			else if (this.getParametro() == null)
				this.getIo().mostraMessaggio("Che attrezzo vuoi posare " + partita.getGiocatore().getNome() + "?");
			else {
				Attrezzo daPosare = partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
				if (daPosare == null)
					this.getIo().mostraMessaggio("Attrezzo inesistente");
				else
					if(partita.getLabirinto().getStanzaCorrente().addAttrezzo(daPosare))
						this.getIo().mostraMessaggio("Attrezzo posato correttamente!");
					else
						this.getIo().mostraMessaggio("OPS ... Qualcosa è andato storto!");
			}
		
	}

}
