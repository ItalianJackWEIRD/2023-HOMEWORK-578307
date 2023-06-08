package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.FormatoFileNonValidoException;

class LabirintoTest {

	Labirinto l;
	Stanza biblioteca;
	Stanza DS1;

	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		l = new Labirinto("labirinto5.txt");
		biblioteca = new Stanza("Biblioteca");
		DS1 = new Stanza("DS1");
	}


	@Test
	public void testGetStanzaVincente() {
		assertEquals("Biblioteca", l.getStanzaVincente().getNome());
	}


	@Test
	public void testSetStanzaCorrente() {
		l.setStanzaCorrente(DS1);
		assertEquals(DS1, l.getStanzaCorrente());
	}
	@Test
	public void testGetStanzaCorrente() {
		assertEquals("Atrio", l.getStanzaCorrente().getNome());
	}

}
