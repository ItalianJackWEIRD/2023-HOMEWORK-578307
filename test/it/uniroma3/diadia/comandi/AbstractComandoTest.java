package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class AbstractComandoTest {
	
	private AbstractComando com;
	private Scanner scanner;

	@BeforeEach
	void setUp() throws Exception {
		com= new ComandoPosa();
		scanner= new Scanner(System.in);
	}

	@Test
	void testIo() {
		IO console= new IOConsole(scanner);
		com.setIo(console);
		assertEquals(console, com.getIo());
	}
	
	@Test
	void testParametro() {
		String s = new String("ciao");
		com.setParametro(s);
		assertEquals(s, com.getParametro());
	}

}
