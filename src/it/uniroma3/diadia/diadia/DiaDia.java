package it.uniroma3.diadia.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;

	public DiaDia(String nomeUtente) {
		this.partita = new Partita(nomeUtente);
	}

	public void gioca() {
		String istruzione;
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);
		do
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

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
			System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		}
		else if(this.partita.isFinita()) {
			System.out.println("Hai esaurito i cfu!");
			return true;
		}
		else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		System.out.println("\nNome utente:\t" + this.partita.getGiocatore().getNome());
		System.out.println("Cfu correnti:\t" + this.partita.getGiocatore().getCfu());
		System.out.println("Inventario:\t" + this.partita.getGiocatore().getBorsa().toString());
		System.out.println("Stanza corrente:\t" + this.partita.getLabirinto().getStanzaCorrente().getDescrizione());
		for (int i = 0; i < elencoComandi.length; i++)
			System.out.print(elencoComandi[i] + " ");
		System.out.println("\n");

	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			System.out.println("Dove vuoi andare "+ this.partita.getGiocatore().getNome() + "?");
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null)
				System.out.println("Direzione inesistente");
			else {
				this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu() - 1;
				this.partita.getGiocatore().setCfu(cfu);
				System.out.println("\n|CFU - 1| ... Cfu correnti = " + this.partita.getGiocatore().getCfu());
			}
			System.out.println(partita.getLabirinto().getStanzaCorrente().getDescrizione());
		}
	}

	/**
	 * Prende il nome dell'attrezzo da prendere, lo rimuove dalla stanza e lo mette nella
	 * borsa.
	 * @param nome dell'attrezzo
	 */
	private void prendi(String attrezzo) {
		if (this.partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 0)
			System.out.println("Non ci sono attrezzi nella stanza");
		else
			if (attrezzo == null)
				System.out.println("Che attrezzo vuoi prendere " + this.partita.getGiocatore().getNome() + "?");
			else {
				Attrezzo daPrendere = this.partita.getLabirinto().getStanzaCorrente().removeAttrezzo(attrezzo);
				if (daPrendere == null)
					System.out.println("Attrezzo inesistente");
				else
					if (this.partita.getGiocatore().getBorsa().addAttrezzo(daPrendere))
						System.out.println("Attrezzo raccolto correttamente!");
					else
						System.out.println("OPS ... Qualcosa è andato storto!");

			}
	}

	private void posa(String attrezzo) {
		if (this.partita.getGiocatore().getBorsa().getNumeroAttrezzi() == 0)
			System.out.println("Non ci sono attrezzi nella borsa");
		else
			if (this.partita.getLabirinto().getStanzaCorrente().getNumeroAttrezzi() == 4)
				System.out.println("Non c'è spazio nella stanza per posare l'attrezzo (MAX 4 Attrezzi)");
			else if (attrezzo == null)
				System.out.println("Che attrezzo vuoi posare " + this.partita.getGiocatore().getNome() + "?");
			else {
				Attrezzo daPosare = this.partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo);
				if (daPosare == null)
					System.out.println("Attrezzo inesistente");
				else
					if(this.partita.getLabirinto().getStanzaCorrente().addAttrezzo(daPosare))
						System.out.println("Attrezzo posato correttamente!");
					else
						System.out.println("OPS ... Qualcosa è andato storto!");
			}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!"); // si desidera smettere
	}

	public static void main(String[] argc) {
		String nomeUtente;
		Scanner scannerNomeUtente;
		scannerNomeUtente= new Scanner(System.in);
		System.out.println("Benvenuto\nInserisci il tuo nome utente:\t");
		nomeUtente= scannerNomeUtente.nextLine();
		System.out.println("\n");
		DiaDia gioco = new DiaDia(nomeUtente);
		gioco.gioca();
	}
}