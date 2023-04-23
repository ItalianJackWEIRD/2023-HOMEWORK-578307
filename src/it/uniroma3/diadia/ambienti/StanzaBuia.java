package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuia extends Stanza{

	private Attrezzo attrezzoLucente;

	public StanzaBuia (String nome, Attrezzo lucente) {
		super(nome);
		this.attrezzoLucente= lucente;
	}

	@Override
	public String getDescrizione () {
		StringBuilder buio= new StringBuilder();
		buio.append("nella stanza c'è un buio pesto ... non si vede niente!");
		if (!this.hasAttrezzo(attrezzoLucente.getNome()))
			buio.append("\nprova a posare una lanterna per illuminare.\n");
		else {
			buio.append("\nla lanterna illumina la stanza!\n");
			buio.append(super.toString());
		}
		return buio.toString();

	}
}
