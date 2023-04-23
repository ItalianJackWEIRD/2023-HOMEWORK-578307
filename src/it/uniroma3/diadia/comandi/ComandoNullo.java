package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoNullo implements Comando{
	
	private IO console;
	private final static String NOME = "non valido";

	@Override
	public void esegui(Partita partita) {
		this.console.mostraMessaggio("Comando non valido");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}



	@Override
	public void setIo(IO console) {
		this.console = console;
	}

	@Override
	public String getNome() {
		return NOME;
	}
}


