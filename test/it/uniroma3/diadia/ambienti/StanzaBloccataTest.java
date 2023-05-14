package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {

	private StanzaBloccata stanzaBloccata;
	private Stanza stanza;
	private Attrezzo grimaldello;

	@BeforeEach
	public void setUp() {
		stanza = new Stanza("Stanzetta");
		grimaldello = new Attrezzo("grimaldello", 1);
		stanzaBloccata = new StanzaBloccata("StanzaBloccata", "grimaldello", "ovest");
		stanzaBloccata.impostaStanzaAdiacente("ovest", stanza);

	}


	@Test
	public void testGetStanzaAdiacenteDirezioneBloccata() {
		assertEquals(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("ovest"));
	}

	@Test
	public void testGetStanzaAdiacenteDirezioneSbloccata() {
		stanzaBloccata.addAttrezzo(grimaldello);
		assertEquals(stanza, stanzaBloccata.getStanzaAdiacente("ovest"));

	}


}
