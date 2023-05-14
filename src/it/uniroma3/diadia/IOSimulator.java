package it.uniroma3.diadia;

import java.util.ArrayList;
import java.util.List;

public class IOSimulator implements IO{
	
	//private String[] righeDaScrivere;
	private List<String> righeDaScrivere;
	private int indiceRigheDaScrivere;
	
	//private String[] messaggiDalComputer;
	private List<String> messaggiDalComputer;
	private int indiceMessaggiProdottiDalComputer;
	private int indiceMessaggiMostratiDalComputer;
	
	public IOSimulator (List<String> messaggiDaImpartire) {
		this.righeDaScrivere= messaggiDaImpartire;
		this.indiceMessaggiMostratiDalComputer= 0;
		this.indiceMessaggiProdottiDalComputer= 0;
		this.messaggiDalComputer= new ArrayList<String>();
	}
	
	public List<String> getMessaggidDalComputer () {
		return this.messaggiDalComputer;
	}
	
	public void setMessaggiProdotti(List<String> messaggiProdotti) {
		this.messaggiDalComputer = messaggiProdotti;
	}

	@Override
	public void mostraMessaggio(String msg) {
		this.messaggiDalComputer.add(indiceMessaggiMostratiDalComputer, msg);;
		this.indiceMessaggiMostratiDalComputer++;
		
	}

	@Override
	public String leggiRiga() {
		String riga= null;
		riga= this.righeDaScrivere.get(indiceRigheDaScrivere);
		this.indiceRigheDaScrivere++;
		return riga;
	}
	
	public String mostraNextMessaggio() {
		String next= this.messaggiDalComputer.get(indiceMessaggiMostratiDalComputer);
		this.indiceMessaggiMostratiDalComputer++;
		return next;
	}
	
	public boolean hasNext () {
		return this.indiceMessaggiMostratiDalComputer<this.indiceMessaggiProdottiDalComputer;
	}

}
