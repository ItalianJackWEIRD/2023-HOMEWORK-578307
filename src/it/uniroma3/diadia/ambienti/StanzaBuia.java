package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{

	private String attrezzoLucente;

	public StanzaBuia (String nome, String lucente) {
		super(nome);
		this.attrezzoLucente= lucente;
	}

	@Override
	public String getDescrizione () {
		StringBuilder buio= new StringBuilder();
		buio.append("nella stanza c'è un buio pesto ... non si vede niente!");
		if (!this.hasAttrezzo(attrezzoLucente))
			buio.append("\nprova a posare una lanterna per illuminare.\n");
		else {
			buio.append("\nla lanterna illumina la stanza!\n");
			buio.append(super.toString());
		}
		return buio.toString();

	}
}
