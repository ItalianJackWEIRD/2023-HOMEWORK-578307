package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.TreeMap;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	private Labirinto labirinto;
	private Stanza ultimaStanza;
	private Map<String, Stanza> nomiStanze;
	
	public LabirintoBuilder () {
		this.labirinto = new Labirinto();
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
	
	public LabirintoBuilder addAdiacenze (String stanzaCorrente, String stanzaAdiacente, String direzione) {
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
	
	public LabirintoBuilder addStanzaBloccata (String s, String attrezzoSbloccante, String direzioneBloccata) {
		Stanza stanza = new StanzaBloccata(s, attrezzoSbloccante, direzioneBloccata);
		this.setUltimaStanza(stanza);
		return this;
	}
	
	
}
