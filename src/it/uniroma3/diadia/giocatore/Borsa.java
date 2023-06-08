package it.uniroma3.diadia.giocatore;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import it.uniroma3.diadia.Configuratore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezziPerNome;

/*
import it.uniroma3.diadia.attrezzi.Attrezzo;
 */
public class Borsa {

	public final static int DEFAULT_PESO_MAX_BORSA = Configuratore.getPesoMax();
	private Map<String, Attrezzo> attrezzi;
	//private List<Attrezzo> attrezzi;		//LinkedList
	//private Set<Attrezzo> attrezzi;		//TreeSet
	//private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private int pesoAttuale;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoAttuale = 0;
		this.pesoMax = pesoMax;
		this.attrezzi= new TreeMap<String, Attrezzo>();
		//this.attrezzi= new LinkedList<Attrezzo>();	//LinkedList
		//this.attrezzi= new TreeSet<Attrezzo>();		//TreeSet
		//this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {		//TreeMap
		if ( (this.pesoAttuale + attrezzo.getPeso() > this.getPesoMax()))
			return false;
		this.attrezzi.put(attrezzo.getNome(),attrezzo);
		this.numeroAttrezzi++;
		this.pesoAttuale += attrezzo.getPeso();
		return true;
	}
	/*
	public boolean addAttrezzo(Attrezzo attrezzo) {		//TreeSet | LinkedList
		if ( (this.pesoAttuale + attrezzo.getPeso() > this.getPesoMax()))
			return false;
		this.attrezzi.add(attrezzo);
		this.numeroAttrezzi++;
		this.pesoAttuale += attrezzo.getPeso();
		return true;
	}
	 */

	public int getPesoMax() {
		return pesoMax;
	}

	public int getNumeroAttrezzi () {
		return this.numeroAttrezzi;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {		//TreeMap
		Attrezzo a= null;
		if(nomeAttrezzo!=null)
			a= this.attrezzi.get(nomeAttrezzo);
		return a;
	}

	/*
	public Attrezzo getAttrezzo(String nomeAttrezzo) {		//TreeSet | LinkedList
		Attrezzo a;
		Iterator<Attrezzo> it= this.attrezzi.iterator();
		while(it.hasNext()) {
			a= it.next();
			if(a.getNome().equals(nomeAttrezzo))
				return a;
		}
		a= null;
		return a;
	}
	 */

	public int getPeso() {
		return this.pesoAttuale;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
	 * Restituisce l'attrezzo rimosso per poterlo lasciare nella stanza dove è il giocatore
	 * (ritorna null se non esiste)
	 * 
	 * @param nome dell'attrezzo da rimuovere
	 * @return l'attrezzo che è stato rimosso
	 */
	public Attrezzo removeAttrezzo (String nomeAttrezzo) {		//TreeMap
		if(!this.hasAttrezzo(nomeAttrezzo))
			return null;
		else {
			Attrezzo a= this.attrezzi.remove(nomeAttrezzo);
			this.pesoAttuale -= a.getPeso();
			this.numeroAttrezzi--;
			return a;
		}
	}

	/*
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {		//TreeSet | LinkedList
		Attrezzo a= null;
		if (nomeAttrezzo!=null) {
			a = this.getAttrezzo(nomeAttrezzo);
			if (a!=null) {	
				this.attrezzi.remove(a);
				this.numeroAttrezzi--;
				this.pesoAttuale -= a.getPeso();
			}
			/*
			for (int i=0; i<this.numeroAttrezzi; i++) {		//scorre l'array di attrezzi in cerca del corrispettivo da cancellare
				if (this.attrezzi[i] != null) {
					if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
						a = this.attrezzi[i];		//se lo ha trovato lo copia in "a"
						for (int j= i; j<this.numeroAttrezzi-1; j++)		//riscorre a partire da quell'attrezzo e sposta di una posizione indietro ogni attrezzo dopo di esso per non lasciare spazi vuoti
							this.attrezzi[j]= this.attrezzi[j+1];
						this.attrezzi[this.numeroAttrezzi-1] = null;	//cancello l'ultimo visto che ho spostato tutti di una casella a sx
						this.numeroAttrezzi--;
					}
				}
			}
	 *//*	//non so perchè non mi andava e l'ho rimesso qui il commento
		}
		return a;
	}*/

	public String toString() {		//TreeMap
		StringBuilder s = new StringBuilder();
		List <Attrezzo> ordinatoPeso= this.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it= ordinatoPeso.iterator();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			while(it.hasNext())
				s.append(" | " + it.next().toString());
			s.append(" |");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}

	/*
	public String toString() {		//TreeSet | LinkedList
		StringBuilder s = new StringBuilder();
		Iterator<Attrezzo> it= this.attrezzi.iterator();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			while(it.hasNext())
				s.append(" | " + it.next().toString());
			s.append(" |");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	 */

	List<Attrezzo> getContenutoOrdinatoPerPeso () {
		List<Attrezzo> l= new LinkedList<Attrezzo>();
		ComparatoreAttrezziPerNome comp= new ComparatoreAttrezziPerNome();
		l.addAll(this.attrezzi.values());
		Collections.sort(l, comp);
		return l;
	}

	SortedSet<Attrezzo> getContenutoOrdinatoPerNome () {
		SortedSet<Attrezzo> set= new TreeSet<Attrezzo>(this.attrezzi.values());
		return set;

	}

	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> map = new TreeMap<Integer, Set<Attrezzo>>();
		for (Attrezzo a : this.attrezzi.values())
			if (map.containsKey(a.getPeso())) {
				map.get(a.getPeso()).add(a);
			}
			else {
				Set<Attrezzo> s= new TreeSet<Attrezzo>();
				s.add(a);
				map.put(a.getPeso(), s);
			}
		return map;
	}
	
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso () {
		SortedSet<Attrezzo> set = new TreeSet<Attrezzo>(new ComparatoreAttrezziPerNome());
		set.addAll(this.attrezzi.values());
		return set;
	}
}