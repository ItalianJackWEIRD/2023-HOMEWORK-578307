package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoBuilderTest {
	
	private LabirintoBuilder lb;
	
	@BeforeEach
	public void setUp() {
		this.lb= new LabirintoBuilder();
	}

	@Test
	public void testGetLabbirinto() {
		assertNotNull(this.lb.getLabirinto());
		assertEquals(Labirinto.class, this.lb.getLabirinto().getClass());
	}
	
	@Test
	public void testAddStanza() {
		this.lb.addStanza("Salotto");
		assertEquals(Stanza.class, lb.getStanze().get("Salotto").getClass());
	}
	
	@Test
	public void testAddAttrezzo () {
		this.lb.setStanzaIniziale("casa").addAttrezzo("martello", 5);
		assertEquals("martello", lb.getStanze().get("casa").getAttrezzo("martello").getNome());
		assertEquals(5, lb.getStanze().get("casa").getAttrezzo("martello").getPeso());
	}
	
	

}
