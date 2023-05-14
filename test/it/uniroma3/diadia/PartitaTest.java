package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.jupiter.api.BeforeEach;

class PartitaTest {
	private Partita partita;
	
	@BeforeEach
	public void setUp () {
		this.partita= new Partita ("pippo", new LabirintoBuilder()
				.addStanzaBloccata("Atrio", "chiave", "nord")
				.addAttrezzo("ascia", 5)
				.addStanzaVincente("Biblioteca")
				.addAdiacenze("Atrio", "Biblioteca", "nord")
				.addStanza("N10")
				.addAttrezzo("lanterna", 3)
				.addAdiacenze("Atrio", "N10", "sud")
				.addAdiacenze("N10", "Atrio", "nord")
				.addStanzaBuia("N11", "lanterna")
				.addAttrezzo("osso", 2)
				.addAdiacenze("Atrio", "N11", "est")
				.addAdiacenze("N10", "N11", "est")
				.addAdiacenze("N11", "Atrio", "ovest")
				.addStanzaMagica("Laboratorio Campus")
				.addAttrezzo("chiave", 1)
				.addAdiacenze("N11", "Laboratorio Campus", "est")
				.addAdiacenze("Atrio", "Laboratorio Campus", "ovest")
				.addAdiacenze("N10", "Laboratorio Campus", "ovest")
				.addAdiacenze("Laboratorio Campus", "Atrio", "est")
				.addAdiacenze("Laboratorio Campus", "N11", "ovest")
				.setStanzaIniziale("Atrio")
				.getLabirinto());
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
