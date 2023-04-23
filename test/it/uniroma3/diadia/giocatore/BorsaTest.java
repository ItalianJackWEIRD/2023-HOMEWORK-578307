package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	Borsa b = new Borsa();
	Attrezzo falce;
	Attrezzo sega;

	@BeforeEach
	public void setUp() {
		falce = new Attrezzo("falce", 2);
		sega = new Attrezzo("sega", 16);
	}

	@Test
	public void testAddAttrezzoPesoMinoreDiDieci() {
		assertTrue(b.addAttrezzo(falce));

	}

	@Test
	public void testAddAttrezzoPesoMaggioreDiDieci() {
		assertFalse(b.addAttrezzo(sega));

	}

	@Test
	public void testGetAttrezzo() {
		b.addAttrezzo(falce);
		assertEquals(falce, b.getAttrezzo("falce"));

	}
	
	@Test
	public void testGetPesoAttrezzo() {
		b.addAttrezzo(falce);
		assertEquals(falce.getPeso(), b.getPeso());
	}
	
	@Test
	public void testGetPesoMax() {
		assertEquals(10, b.getPesoMax());
	}
}
