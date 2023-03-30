package it.uniroma3.diadia;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	Stanza s1= new Stanza("stanza1");
	Stanza s2= new Stanza("stanza2");
	Attrezzo martello= new Attrezzo("martello", 10);
	Attrezzo spada= new Attrezzo("spada", 5);

	@Test //caso base
	void testGetStanzaAdiacente () {
		assertNull(s1.getStanzaAdiacente("nord"));
	}

	@Test //caso non null
	void testGetStanzaAdiacente2 () {
		s1.impostaStanzaAdiacente("sud", s2);
		assertEquals(s2, s1.getStanzaAdiacente("sud"));
	}

	@Test	//caso una sola stanza
	void testImpostaStanzaAdiacente () {
		s1.impostaStanzaAdiacente("nord", s2);
		assertEquals(s2, s1.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void testAddAttrezzo() {
		assertTrue(s1.addAttrezzo(spada));
	}

	@Test 	//caso null
	void testGetAttrezzo () {
		assertNull(s1.getAttrezzo("spada"));
	}
	
	@Test 	//caso con un attrezzo
	void testGetAttrezzo2 () {
		s1.addAttrezzo(martello);
		assertEquals(martello, s1.getAttrezzo("martello"));
	}
	
	@Test 	//caso con due attrezzo
	void testGetAttrezzo3 () {
		s1.addAttrezzo(martello);
		s1.addAttrezzo(spada);
		assertEquals(spada, s1.getAttrezzo("spada"));
	}
	
	@Test 	//caso con due attrezzo e rimanda null
	void testGetAttrezzo4 () {
		s1.addAttrezzo(martello);
		s1.addAttrezzo(spada);
		assertNull(s1.getAttrezzo("lancia"));
	}
}
