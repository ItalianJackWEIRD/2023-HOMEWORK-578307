package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{

	private IO console;
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		if (partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 0)
			this.console.mostraMessaggio("Non ci sono attrezzi nella stanza");
		else
			if (nomeAttrezzo == null)
				this.console.mostraMessaggio("Che attrezzo vuoi prendere " + partita.getGiocatore().getNome() + "?");
			else {
				if(partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo)==null)
					this.console.mostraMessaggio("Attrezzo inesistente");
				else
					if (partita.getGiocatore().getBorsa().getPeso() + partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo).getPeso() > partita.getGiocatore().getBorsa().getPesoMax())
						this.console.mostraMessaggio("Borsa piena!");
					else {
						Attrezzo daPrendere = partita.getLabirinto().getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
						if (partita.getGiocatore().getBorsa().addAttrezzo(daPrendere))
							this.console.mostraMessaggio("Attrezzo raccolto correttamente!");
						else
							this.console.mostraMessaggio("OPS ... Qualcosa è andato storto!");
					}

			}

	}

	@Override
	public void setParametro(String nomeAttrezzo) {
		this.nomeAttrezzo= nomeAttrezzo;

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
