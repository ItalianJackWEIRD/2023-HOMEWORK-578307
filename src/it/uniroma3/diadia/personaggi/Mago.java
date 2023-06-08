package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	private Attrezzo attrezzo;

	public Mago (String nome, String descrizione, Attrezzo att) {
		super(nome, descrizione);
		this.attrezzo= att;
	}

	@Override
	public String esegui(Partita partita) {
		if (attrezzo!=null) {
			partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
			this.attrezzo= null;
			return new String("Oggi mi sento così generoso da regalarti un attrezzo. Tieni!\n");
		}
		else
			return new String("Mi spiace ma non ho più niente da darti ...\n");
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		Attrezzo nuovo = attrezzo;
		if (attrezzo.getPeso()!=1)
			nuovo.setPeso(attrezzo.getPeso()/2);
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(nuovo);
		return new String("Ho dimezzato il peso dell'attrezzo per te e l'ho poggiato nella stanza. Non serve che mi ringrazi!");
	}

}
