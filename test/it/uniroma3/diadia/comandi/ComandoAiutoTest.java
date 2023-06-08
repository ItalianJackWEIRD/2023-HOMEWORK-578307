package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class ComandoAiutoTest {
	
	private Scanner scanner;

	@BeforeEach
	public void setUp() throws Exception {
		scanner= new Scanner(System.in);
		IO console= new IOConsole(scanner);
		String nome= "G";
		DiaDia d= new DiaDia(console, nome, new LabirintoBuilder().getLabirinto(), 5, 5);
		d.gioca();
	}

/*
	@Test
	public void testPartitaConComandoAiuto() {
		String righeDaLeggere = "Nome utente:\tG\n"
				+ "Cfu correnti:\t20\n"
				+ "Inventario:\tBorsa vuota\n"
				+ "Stanza corrente:\tAtrio\n"
				+ "Uscite:  nord est sud ovest\n"
				+ "Attrezzi nella stanza:  | osso (2kg) |\n"
				+ "La stanza ha la direzione NORD bloccata. Per accedervi posare la chiave...\n"
				+ "\n"
				+ "vai \n"
				+ "aiuto \n"
				+ "guarda \n"
				+ "prendi \n"
				+ "posa \n"
				+ "fine ";
		assertEquals(righeDaLeggere, d.processaIstruzione("aiuto"));
	}
*/
}
