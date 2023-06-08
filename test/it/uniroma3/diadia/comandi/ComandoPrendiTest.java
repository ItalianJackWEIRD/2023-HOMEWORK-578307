package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendiTest {

	private Partita partita;
	private Attrezzo attrezzo;
	private Attrezzo attrezzoPesante;
	private Comando comando;
	private IO io;
	
}
	
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
		attrezzoPesante = new Attrezzo("incudine", 11);
		comando = (Comando)new ComandoPrendi();
		io = new IOConsole();
		comando.setIo(io);
	}


	
	public boolean attrezzoPresente(String s) {
		Collection<Attrezzo> array = partita.getLabirinto().getStanzaCorrente().getAttrezzi();
		for(Attrezzo a : array) {
			if(a != null && s.equals(a.getNome()))
					return true;
		}
		return false;
	}
	
	@Test
	public void testAttrezzoPreso() {
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(attrezzoPresente("martello"));
	}
	@Test
	public void testAttrezzoNonPresente() {
		comando.setParametro("martello");
		comando.esegui(partita);
		assertFalse(attrezzoPresente("martello"));
	}
	
	@Test
	public void testAttrezzoPesante() {
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(attrezzoPesante);
		comando.setParametro("incudine");
		comando.esegui(partita);
		assertTrue(attrezzoPresente("incudine"));
	}
	
}


