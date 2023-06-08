package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {		//NON FUNZIONA DOPO HW 4

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comando;

	@Before
	public void setUp() throws Exception {
		String nome= "G";
		partita = new Partita(nome, new LabirintoBuilder()		
				.addStanzaBloccata("Atrio", "chiave", Direzione.nord)
				.addAttrezzo("ascia", 5)
				.addStanzaVincente("Biblioteca")
				.addAdiacenze("Atrio", "Biblioteca", Direzione.nord)
				.addStanza("N10")
				.addAttrezzo("lanterna", 3)
				.addAdiacenze("Atrio", "N10", Direzione.sud)
				.addAdiacenze("N10", "Atrio", Direzione.nord)
				.addStanzaBuia("N11", "lanterna")
				.addAttrezzo("osso", 2)
				.addAdiacenze("Atrio", "N11", Direzione.est)
				.addAdiacenze("N10", "N11", Direzione.est)
				.addAdiacenze("N11", "Atrio", Direzione.ovest)
				.addStanzaMagica("Laboratorio Campus")
				.addAttrezzo("chiave", 1)
				.addAdiacenze("N11", "Laboratorio Campus", Direzione.est)
				.addAdiacenze("Atrio", "Laboratorio Campus", Direzione.ovest)
				.addAdiacenze("N10", "Laboratorio Campus", Direzione.ovest)
				.addAdiacenze("Laboratorio Campus", "Atrio", Direzione.est)
				.addAdiacenze("Laboratorio Campus", "N11", Direzione.ovest)
				.setStanzaIniziale("Atrio")
				.getLabirinto(), 5, 5);
		attrezzo = new Attrezzo("martello", 2);
		comando = (Comando)new ComandoPosa();
		io = new IOConsole(new Scanner(System.in));
		comando.setIo(io);
	}


	@Test
	public void testAttrezzoPosato() {
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertTrue(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("martello"));
	}

	@Test
	public void testAttrezzoPosatoNull() {
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("martello"));
	}


	public void creatoreAttrezzi() {
		for(int i= 0; i<10;i++) {
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(new Attrezzo("utensile"+i, 1));
		}
	}
	
	@Test
	public void testTroppiAttrezzi() {
		this.creatoreAttrezzi();
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(partita.getLabirinto().getStanzaCorrente().hasAttrezzo("martello"));
	}

}
