package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private Attrezzo attrezzoSbloccante;
	
	public StanzaBloccata (String nome, Attrezzo sbloccante, String direzioneBloccata) {
		super(nome);
		this.direzioneBloccata= direzioneBloccata;
		this.attrezzoSbloccante= sbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente (String direzione) {
		if ( direzioneBloccata.equals(direzione) && !this.hasAttrezzo(attrezzoSbloccante.getNome()))
			return this;
		else
			return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione () {
		StringBuilder descr= new StringBuilder();
		if (!this.hasAttrezzo(attrezzoSbloccante.getNome())) {
			descr.append(super.getDescrizione());
			descr.append("La stanza ha la direzione " + direzioneBloccata.toUpperCase() + " bloccata. Per accedervi posare la chiave...\n");
			return descr.toString();
		}
		else {
			descr.append(super.getDescrizione());
			descr.append("La chiave ha sbloccato la direzione " + direzioneBloccata + "!");
			return descr.toString();
		}
	}

}
