package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GiocatoreTest {
	
	String nome= "giacomo";
	Giocatore g = new Giocatore(nome, 5, 5);
	
	@Test
	public void testGetCfuDefault() {
		assertEquals(5, g.getCfu());
	}
	
	@Test
	public void testSetCfu() {
		g.setCfu(3);
		assertEquals(3, g.getCfu());
	}

	@Test
	public void testGetBorsaDefault() {
		assertNotNull(g.getBorsa());
	}
}
