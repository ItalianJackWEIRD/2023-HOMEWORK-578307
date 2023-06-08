package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;

	public Labirinto (String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c= new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaCorrente= c.getStanzaIniziale();
		this.stanzaVincente= c.getStanzaVincente();	
	}
	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */

	//	private void creaStanze() {
	//
	//		/* crea gli attrezzi */
	//		Attrezzo ascia = new Attrezzo("ascia", 5);
	//		Attrezzo chiave = new Attrezzo("chiave", 1);	//Stanza Bloccata
	//		Attrezzo lanterna = new Attrezzo("lanterna", 3);	//Stanza Buia
	//		Attrezzo osso = new Attrezzo("osso", 2);

	//		/* crea stanze del labirinto */
	//		Stanza atrio = new StanzaBloccata("Atrio", chiave, "nord");
	//		Stanza aulaN11 = new StanzaBuia("Aula N11", lanterna);
	//		Stanza aulaN10 = new Stanza("Aula N10");
	//		Stanza laboratorio = new StanzaMagica("Laboratorio Campus", 5);
	//		Stanza biblioteca = new Stanza("Biblioteca");

	//		/* collega le stanze */
	//		atrio.impostaStanzaAdiacente("nord", biblioteca);
	//		atrio.impostaStanzaAdiacente("est", aulaN11);
	//		atrio.impostaStanzaAdiacente("sud", aulaN10);
	//		atrio.impostaStanzaAdiacente("ovest", laboratorio);
	//		aulaN11.impostaStanzaAdiacente("est", laboratorio);
	//		aulaN11.impostaStanzaAdiacente("ovest", atrio);
	//		aulaN10.impostaStanzaAdiacente("nord", atrio);
	//		aulaN10.impostaStanzaAdiacente("est", aulaN11);
	//		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
	//		laboratorio.impostaStanzaAdiacente("est", atrio);
	//		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
	//		biblioteca.impostaStanzaAdiacente("sud", atrio);

	/* pone gli attrezzi nelle stanze */
	//		aulaN10.addAttrezzo(lanterna);
	//		atrio.addAttrezzo(osso);
	//		laboratorio.addAttrezzo(chiave);
	//		aulaN11.addAttrezzo(ascia);

	// il gioco comincia nell'atrio
	//		stanzaCorrente = atrio;
	//		stanzaVincente = biblioteca;
	//	}


	public void setStanzaVincente (Stanza s) {
		this.stanzaVincente= s;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	public static LabirintoBuilder newBuilder () {
		return new LabirintoBuilder();
	}

	// NESTED CLASS
	public static class LabirintoBuilder {

		private Labirinto labirinto;
		private Stanza ultimaStanza;
		private Map<String, Stanza> nomiStanze;

		public LabirintoBuilder () {
			this.nomiStanze = new TreeMap<String, Stanza>();
		}

		public void setUltimaStanza (Stanza s) {		//aggiorna anche la map
			this.ultimaStanza = s;
			this.nomiStanze.put(s.getNome(), s);
		}

		public Map<String, Stanza> getStanze () {
			return this.nomiStanze;
		}

		public Labirinto getLabirinto() {
			return this.labirinto;
		}

		public LabirintoBuilder setStanzaIniziale (String s) {
			Stanza room = nomiStanze.get(s);
			this.labirinto.setStanzaCorrente(room);
			return this;		
		}

		public LabirintoBuilder setStanzaVincente (String s) {
			Stanza room= nomiStanze.get(s);
			this.labirinto.setStanzaVincente(room);
			return this;
		}

		public LabirintoBuilder addStanzaVincente (String s) {
			Stanza room = new Stanza(s);
			this.labirinto.setStanzaVincente(room);
			this.setUltimaStanza(room);
			return this;
		}

		public LabirintoBuilder addStanza (String s) {
			Stanza room = new Stanza(s);
			this.setUltimaStanza(room);
			return this;
		}

		public LabirintoBuilder addAttrezzo (String nome, int peso) {
			Attrezzo a = new Attrezzo(nome, peso);
			if (this.ultimaStanza==null)
				return this;
			this.ultimaStanza.addAttrezzo(a);
			return this;
		}

		public LabirintoBuilder addAdiacenze (String stanzaCorrente, String stanzaAdiacente, Direzione direzione) {
			Stanza c = this.nomiStanze.get(stanzaCorrente);
			Stanza a = this.nomiStanze.get(stanzaAdiacente);
			c.impostaStanzaAdiacente(direzione, a);
			return this;
		}

		public LabirintoBuilder addStanzaMagica (String s) {
			Stanza stanza = new StanzaMagica(s);
			this.setUltimaStanza(stanza);
			return this;		
		}

		public LabirintoBuilder addStanzaBuia (String s, String attrezzoPerVedere) {
			Stanza stanza = new StanzaBuia(s, attrezzoPerVedere);
			this.setUltimaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addStanzaBloccata (String s, String attrezzoSbloccante, Direzione direzioneBloccata) {
			Stanza stanza = new StanzaBloccata(s, attrezzoSbloccante, direzioneBloccata);
			this.setUltimaStanza(stanza);
			return this;
		}

		public LabirintoBuilder addMago (String nome, String descrizione, String attrezzo, int peso) {
			Attrezzo a = new Attrezzo(attrezzo, peso);
			AbstractPersonaggio p = new Mago(nome,descrizione,a);
			if (this.ultimaStanza==null)
				return this;
			else {
				this.ultimaStanza.addPersonaggio(p);
				return this;
			}
		}

		public LabirintoBuilder addCane (String nome, String descrizione, String nomeAtt, int peso) {
			AbstractPersonaggio p = new Cane(nome,descrizione, nomeAtt, peso);
			if (this.ultimaStanza==null)
				return this;
			else {
				this.ultimaStanza.addPersonaggio(p);
				return this;
			}
		}

		public LabirintoBuilder addStrega (String nome, String descrizione) {
			AbstractPersonaggio p = new Strega(nome,descrizione);
			if (this.ultimaStanza==null)
				return this;
			else {
				this.ultimaStanza.addPersonaggio(p);
				return this;
			}
		}

		public Labirinto newLabirinto(Map<String, Stanza> stanze, Stanza iniziale, Stanza vincente) {
			LabirintoBuilder l= new LabirintoBuilder();
			l.getStanze().putAll(stanze);
			return l.setStanzaIniziale(iniziale.getNome())
					.setStanzaVincente(vincente.getNome())
					.getLabirinto();
		}


	}
}
