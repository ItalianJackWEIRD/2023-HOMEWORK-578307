package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.jupiter.api.BeforeEach;

class PartitaTest {
	private Partita partita;
	
	@BeforeEach
	public void setUp () throws FileNotFoundException, FormatoFileNonValidoException {
		this.partita= new Partita ("gia", new Labirinto("labirinto5.txt"), 20, 10);
	}
	
	
	@Test //getCfu
	void testGetCfu () {
		assertEquals(20, this.partita.getGiocatore().getCfu());
	}

	@Test //setCfu		-setto a 0-
	void testSetCfu () {
		this.partita.getGiocatore().setCfu(0);
		assertEquals(0, this.partita.getGiocatore().getCfu());
	}
	
	@Test //setCfu		-setto a variabile (17)-
	void testSetCfu2 () {
		int j= 17;
		this.partita.getGiocatore().setCfu(j);
		assertEquals(17, this.partita.getGiocatore().getCfu());
	}
	
	
	@Test //vinta 	// stanza corrente: Atrio (FALSE)
	void testVinta () {
		assertFalse(this.partita.vinta());
	}
	
	@Test //vinta	// stanza corrente: Biblioteca (TRUE)
	void testVinta2 () {
		Stanza prossimaStanza= this.partita.getLabirinto().getStanzaVincente();
		this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		assertTrue(this.partita.vinta());
	}
	
	@Test //isFinita	//false
	void testIsFinita () {
		assertFalse(this.partita.isFinita());
	}
	
	@Test //isFinita	//finita= true
	void testIsFinita2 () {
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test //isFinita	// cfu= 0
	void testIsFinita3 () {
		this.partita.getGiocatore().setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test //isFinita	//vinta--->true
	void testIsFinita4 () {
		Stanza prossimaStanza= this.partita.getLabirinto().getStanzaVincente();
		this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
		assertTrue(this.partita.vinta());
	}
	
	@Test
	public void testSetStanzaCorrente() {
		Stanza s= new Stanza("stanza");
		partita.getLabirinto().setStanzaCorrente(s);
		assertEquals(s, partita.getLabirinto().getStanzaCorrente());
	}
}
