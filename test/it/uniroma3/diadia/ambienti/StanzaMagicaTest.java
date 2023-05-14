package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private Stanza s;
	private String nome;
	private Attrezzo a;
	
	@BeforeEach
	public void setUp () {
		this.s= new StanzaMagica("stanza");
		this.nome= "spada";
		this.a= new Attrezzo(nome, 5);
	}

	@Test
	void testAddAttrezzo() {		//non magica soglia default
		this.s.addAttrezzo(a);
		assertEquals(a, s.getAttrezzo(nome));;
	}
	
	@Test
	void testAddAttrezzo2() {		// magica soglia diversa
		this.s= new StanzaMagica("stanza", 0);
		this.s.addAttrezzo(a);
		Attrezzo nuovo= new Attrezzo("adaps", 10);
		assertEquals(nuovo.getNome(), this.s.getAttrezzo("adaps").getNome());
		assertEquals(10, this.s.getAttrezzo("adaps").getPeso());
	}
	
}
