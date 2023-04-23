package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	public Labirinto () {
		this.creaStanze();
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo ascia = new Attrezzo("ascia", 5);
		Attrezzo chiave = new Attrezzo("chiave", 1);	//Stanza Bloccata
		Attrezzo lanterna = new Attrezzo("lanterna", 3);	//Stanza Buia
		Attrezzo osso = new Attrezzo("osso", 2);

		/* crea stanze del labirinto */
		Stanza atrio = new StanzaBloccata("Atrio", chiave, "nord");
		Stanza aulaN11 = new StanzaBuia("Aula N11", lanterna);
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new StanzaMagica("Laboratorio Campus", 5);
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		laboratorio.addAttrezzo(chiave);
		aulaN11.addAttrezzo(ascia);

		// il gioco comincia nell'atrio
		stanzaCorrente = atrio;
		stanzaVincente = biblioteca;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

}
