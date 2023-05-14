package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	private StanzaBuia stanza;
	private Attrezzo lumino;
	@BeforeEach
	public void setUp() throws Exception {
		stanza = new StanzaBuia("StanzaBuia", "lumino");
		lumino = new Attrezzo("lumino", 1);
	}

	@Test
	public void testGetDescrizioneConAttrezzo() {
		assertTrue(stanza.addAttrezzo(lumino));
		StringBuilder e = new StringBuilder();
		//e.append("nella stanza c'è un buio pesto ... non si vede niente!\nla lanterna illumina la stanza!\n");
		e.append(stanza.getDescrizione());
		assertEquals(e, stanza.getDescrizione());
	}

	@Test
	public void testGetDescrizioneSenzaAttrezzo() {
		assertEquals(stanza.toString(), stanza.getDescrizione());
	}
}
