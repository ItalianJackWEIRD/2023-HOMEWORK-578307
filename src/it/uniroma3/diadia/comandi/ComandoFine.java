package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine extends AbstractComando{
	
	private IO console;

	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		this.console.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
		
	}


	@Override
	public void setIo(IO console) {
		this.console= console;
		
	}

}
