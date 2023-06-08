package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando {

	private IO console;
	private String parametro;

	public abstract void esegui (Partita partita);

	public void setIo(IO console) {
		this.console= console;

	}

	public IO getIo() {
		return this.console;
	}

	public void setParametro(String parametro) {
		this.parametro= parametro;
	}

	public String getParametro() {
		return this.parametro;
	}


}
