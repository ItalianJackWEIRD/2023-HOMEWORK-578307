package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		if (partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 0)
			this.getIo().mostraMessaggio("Non ci sono attrezzi nella stanza");
		else
			if (this.getParametro() == null)
				this.getIo().mostraMessaggio("Che attrezzo vuoi prendere " + partita.getGiocatore().getNome() + "?");
			else {
				if(partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.getParametro())==null)
					this.getIo().mostraMessaggio("Attrezzo inesistente");
				else
					if (partita.getGiocatore().getBorsa().getPeso() + partita.getLabirinto().getStanzaCorrente().getAttrezzo(this.getParametro()).getPeso() > partita.getGiocatore().getBorsa().getPesoMax())
						this.getIo().mostraMessaggio("Borsa piena!");
					else {
						Attrezzo daPrendere = partita.getLabirinto().getStanzaCorrente().removeAttrezzo(this.getParametro());
						if (partita.getGiocatore().getBorsa().addAttrezzo(daPrendere))
							this.getIo().mostraMessaggio("Attrezzo raccolto correttamente!");
						else
							this.getIo().mostraMessaggio("OPS ... Qualcosa è andato storto!");
					}

			}

	}

}
