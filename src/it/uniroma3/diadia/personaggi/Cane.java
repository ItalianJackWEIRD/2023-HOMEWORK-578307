package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	private Attrezzo nuovoAttrezzo;
	
	public Cane (String nome, String descrizione, String att, int peso) {
		super(nome, descrizione);
		nuovoAttrezzo= new Attrezzo(att, peso);
	}

	@Override
	public String esegui(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return new String("Il cane morde! Hai perso 1 CFU ...\n");
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder r = new StringBuilder();
		if (attrezzo.getNome().equals("osso")) {
			r.append("Il mio cibo preferito! Tieni questo come regalo ... BAU BAU!");
			r.append("\nIl Cane ha lasciato un oggetto.");
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(nuovoAttrezzo);
		}
		else {
			r.append("Non è il mio cibo preferito! Bleh ... BAU BAU!");
			r.append("Il Cane morde il giocatore ... - 1 CFU");
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		}
		return r.toString();
	}

}
