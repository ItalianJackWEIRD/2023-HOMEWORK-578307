package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	
	private String nomeAttrezzo;
	private IO console;

	@Override
	public void esegui(Partita partita) {
		if (partita.getGiocatore().getBorsa().getNumeroAttrezzi() == 0)
			this.console.mostraMessaggio("Non ci sono attrezzi nella borsa");
		else
			if (partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 4)
				this.console.mostraMessaggio("Non c'è spazio nella stanza per posare l'attrezzo (MAX 4 Attrezzi)");
			else if (nomeAttrezzo == null)
				this.console.mostraMessaggio("Che attrezzo vuoi posare " + partita.getGiocatore().getNome() + "?");
			else {
				Attrezzo daPosare = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
				if (daPosare == null)
					this.console.mostraMessaggio("Attrezzo inesistente");
				else
					if(partita.getLabirinto().getStanzaCorrente().addAttrezzo(daPosare))
						this.console.mostraMessaggio("Attrezzo posato correttamente!");
					else
						this.console.mostraMessaggio("OPS ... Qualcosa è andato storto!");
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
