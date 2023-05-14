package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String direzioneBloccata;
	private String attrezzoSbloccante;
	
	public StanzaBloccata (String nome, String sbloccante, String direzioneBloccata) {
		super(nome);
		this.direzioneBloccata= direzioneBloccata;
		this.attrezzoSbloccante= sbloccante;
	}
	
	@Override
	public Stanza getStanzaAdiacente (String direzione) {
		if ( direzioneBloccata.equals(direzione) && !this.hasAttrezzo(attrezzoSbloccante))
			return this;
		else
			return super.getStanzaAdiacente(direzione);
	}
	
	@Override
	public String getDescrizione () {
		StringBuilder descr= new StringBuilder();
		if (!this.hasAttrezzo(attrezzoSbloccante)) {
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
