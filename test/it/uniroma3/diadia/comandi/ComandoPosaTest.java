package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosaTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private IO io;
	private Comando comando;

	@BeforeEach
	public void setUp() throws Exception {
		String nome= "G";
		partita = new Partita(nome, new LabirintoBuilder()		
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
		attrezzo = new Attrezzo("martello", 2);
		comando = new ComandoPosa();
		io = new IOConsole();
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
