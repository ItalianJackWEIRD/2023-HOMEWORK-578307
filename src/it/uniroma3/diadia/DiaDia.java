package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;


/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano far comodo a qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private Partita partita;
	private IO console;

	public DiaDia(IO console, String nomeUtente, Labirinto labirinto, int cfu, int pesoMax) {
		this.console= console;
		this.partita = new Partita(nomeUtente, labirinto, cfu, pesoMax);		
	}

	public void gioca() throws Exception {
		String istruzione;
		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandiRiflessiva fisarmonica= new FabbricaDiComandiRiflessiva(this.console);
		comandoDaEseguire= fisarmonica.costruisciComando(istruzione);
		if (comandoDaEseguire==null)
			this.console.mostraMessaggio("Comando Sconosciuto");
		comandoDaEseguire.esegui(this.partita);
		
		/*

		// COMANDO FISARMONICA ( ELENCO DI COMANDI DA ESEGUIRE "FINE" "VAI")
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		*/
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		}
		else if(this.partita.isFinita() && this.partita.getGiocatore().getCfu()==0) {
			this.console.mostraMessaggio("Hai esaurito i cfu!");
			return true;
		}
		else if(this.partita.isFinita())
			return true;
		else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	/*
	private void aiuto() {
		this.console.mostraMessaggio("\nNome utente:\t" + this.partita.getGiocatore().getNome());
		this.console.mostraMessaggio("Cfu correnti:\t" + this.partita.getGiocatore().getCfu());
		this.console.mostraMessaggio("Inventario:\t" + this.partita.getGiocatore().getBorsa().toString());
		this.console.mostraMessaggio("Stanza corrente:\t" + this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		for (int i = 0; i < elencoComandi.length; i++)
			this.console.mostraMessaggio(elencoComandi[i] + " ");
		this.console.mostraMessaggio("\n");

	}
*/

	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	/*
	private void vai(String direzione) {
		if (direzione == null)
			this.console.mostraMessaggio("Dove vuoi andare "+ this.partita.getGiocatore().getNome() + "?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				this.console.mostraMessaggio("Direzione inesistente");
			else {
				this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu() - 1;
				this.partita.getGiocatore().setCfu(cfu);
				this.console.mostraMessaggio("\n|CFU - 1| ... Cfu correnti = " + this.partita.getGiocatore().getCfu());
			}
			this.console.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}
	}
	*/

	/**
	 * Prende il nome dell'attrezzo da prendere, lo rimuove dalla stanza e lo mette nella
	 * borsa.
	 * @param nome dell'attrezzo
	 */
	/*
	private void prendi(String attrezzo) {
		if (this.partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 0)
			this.console.mostraMessaggio("Non ci sono attrezzi nella stanza");
		else
			if (attrezzo == null)
				this.console.mostraMessaggio("Che attrezzo vuoi prendere " + this.partita.getGiocatore().getNome() + "?");
			else {
				Attrezzo daPrendere = this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);
				if (daPrendere == null)
					this.console.mostraMessaggio("Attrezzo inesistente");
				else
					if (this.partita.getGiocatore().getBorsa().addAttrezzo(daPrendere))
						this.console.mostraMessaggio("Attrezzo raccolto correttamente!");
					else
						this.console.mostraMessaggio("OPS ... Qualcosa è andato storto!");

			}
	}
	*/
	/*
	private void posa(String attrezzo) {
		if (this.partita.getGiocatore().getBorsa().getNumeroAttrezzi() == 0)
			this.console.mostraMessaggio("Non ci sono attrezzi nella borsa");
		else
			if (this.partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 4)
				this.console.mostraMessaggio("Non c'è spazio nella stanza per posare l'attrezzo (MAX 4 Attrezzi)");
			else if (attrezzo == null)
				this.console.mostraMessaggio("Che attrezzo vuoi posare " + this.partita.getGiocatore().getNome() + "?");
			else {
				Attrezzo daPosare = this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
				if (daPosare == null)
					this.console.mostraMessaggio("Attrezzo inesistente");
				else
					if(this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(daPosare))
						this.console.mostraMessaggio("Attrezzo posato correttamente!");
					else
						this.console.mostraMessaggio("OPS ... Qualcosa è andato storto!");
			}
	}
*/

	/**
	 * Comando "Fine".
	 * @throws Exception 
	 */
	/*
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}
	*/

	public static void main(String[] argc) throws Exception {
		Scanner scanner= new Scanner(System.in);
		IO console= new IOConsole(scanner);
		String nomeUtente;
		console.mostraMessaggio("Benvenuto\nInserisci il tuo nome utente:\t");
		nomeUtente= console.leggiRiga();
		
		Labirinto labirinto = new Labirinto("labirinto5.txt");
				
//				new LabirintoBuilder()		//manca la stanza iniziale
//								.addStanzaBloccata("Atrio", "chiave", "nord")
//								.addAttrezzo("ascia", 5)
//								.addMago("Merlino", "Sono il mago dell'ingegneria, e posso donarti uno ed un solo attrezzo!", "osso", 2)
//								.addStanzaVincente("Biblioteca")
//								.addAdiacenze("Atrio", "Biblioteca", "nord")
//								.addStanza("N10")
//								.addAttrezzo("lanterna", 3)
//								.addCane("Fuffy il Cane", "BAU BAU.", "bacchetta", 2)
//								.addAdiacenze("Atrio", "N10", "sud")
//								.addAdiacenze("N10", "Atrio", "nord")
//								.addStanzaBuia("N11", "lanterna")
//								.addAttrezzo("pugnale", 3)
//								.addAdiacenze("Atrio", "N11", "est")
//								.addAdiacenze("N10", "N11", "est")
//								.addAdiacenze("N11", "Atrio", "ovest")
//								.addStanzaMagica("Laboratorio Campus")
//								.addAttrezzo("chiave", 1)
//								.addStrega("Morgana", "Sono una strega della Vasca Navale e posso teletrasportarti in una stanza vicina!")
//								.addAdiacenze("N11", "Laboratorio Campus", "est")
//								.addAdiacenze("Atrio", "Laboratorio Campus", "ovest")
//								.addAdiacenze("N10", "Laboratorio Campus", "ovest")
//								.addAdiacenze("Laboratorio Campus", "Atrio", "est")
//								.addAdiacenze("Laboratorio Campus", "N11", "ovest")
//								.setStanzaIniziale("Atrio")
//								.getLabirinto();
		
		//Configuratore conf = new Configuratore();
		DiaDia gioco = new DiaDia(console, nomeUtente, labirinto, Configuratore.getCFU(), Configuratore.getPesoMax());
		gioco.gioca();
		scanner.close();
	}
}