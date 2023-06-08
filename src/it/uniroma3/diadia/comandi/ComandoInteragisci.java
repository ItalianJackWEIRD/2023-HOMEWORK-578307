package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.Partita;

public class ComandoInteragisci extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio p= partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (p!=null) 
			this.getIo().mostraMessaggio(p.esegui(partita));
		else
			this.getIo().mostraMessaggio("Non c'è nessuno con cui interagire!");
		
		
	}

}
