package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoNullo extends AbstractComando{
	
	@Override
	public void esegui(Partita partita) {
		this.getIo().mostraMessaggio("Comando non valido");
	}

}


