package it.uniroma3.diadia.personaggi;

import java.util.List;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{
	
	public Strega (String nome, String descrizione) {
		super(nome, descrizione);
	}

	@Override
	public String esegui(Partita partita) {
		List<Stanza> stanze= partita.getLabirinto().getStanzaCorrente().getStanzeAdiacenti();
		
		if (this.haSalutato()) {		//trasferisco nella stanza con più attrezzi
			Stanza max= stanze.get(0);
			for (Stanza s : stanze)
				if (s.getNumeroAttrezzi()>max.getNumeroAttrezzi())
					max = s;
			
			partita.getLabirinto().setStanzaCorrente(max);
			
			return new String("Visto che sei stato così carino da salutarmi ti trasporterò alla stanza vicina con più attrezzi!\n");
		}
		else {
			Stanza min= stanze.get(0);
			for (Stanza s : stanze)
				if (s.getNumeroAttrezzi()<min.getNumeroAttrezzi())
					min = s;
			
			partita.getLabirinto().setStanzaCorrente(min);
			
			return new String("Visto che non mi hai salutato ti trasporterò alla stanza vicina con meno attrezzi!\n");
		}
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return new String("Ahahahah ... che sciocco che sei, ora è mio!");
	}
	
	

}
