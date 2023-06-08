package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

public class FabbricaDiComandiRiflessivaTest {

	private FabbricaDiComandiRiflessiva fabbrica;
	private IO io;
	private AbstractComando expected;
	
	@Before
	public void setUp() throws Exception {
		io = new IOConsole(new Scanner(System.in));
		fabbrica = new FabbricaDiComandiRiflessiva(io);
	}

	@Test
	public void testComandoNonValido() {
		expected = new ComandoNullo();
		assertEquals( expected.getClass().getSimpleName(), fabbrica.costruisciComando("pippo").getClass().getSimpleName());
	}
	
	@Test
	public void testComandoConParametro() {
		expected = new ComandoVai();
		expected.setParametro("nord");
		AbstractComando current = fabbrica.costruisciComando("vai nord");
		assertEquals( expected.getClass().getSimpleName(), current.getClass().getSimpleName());
	}
	
	@Test
	public void testComandoSenzaParametro() {
		expected = new ComandoFine();
		assertEquals( expected.getClass().getSimpleName(), fabbrica.costruisciComando("fine").getClass().getSimpleName());
	}

}
