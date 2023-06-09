package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica fabbrica;
	private IO io;
	private Comando expected;
	
	@BeforeEach
	public void setUp() throws Exception {
		io = new IOConsole();
		fabbrica = new FabbricaDiComandiFisarmonica(io);
	}

	@Test
	public void testComandoNonValido() {
		expected = new ComandoNullo();
		assertEquals( expected.getNome(), fabbrica.costruisciComando("pippo").getNome());
	}
	
	@Test
	public void testComandoConParametro() {
		expected = new ComandoVai();
		expected.setParametro("nord");
		Comando current = fabbrica.costruisciComando("vai nord");
		assertEquals( expected.getNome(), current.getNome());
	}
	
	@Test
	public void testComandoSenzaParametro() {
		expected = new ComandoFine();
		assertEquals( expected.getNome(), fabbrica.costruisciComando("fine").getNome());
	}

}
