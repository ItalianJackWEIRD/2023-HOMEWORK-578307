package it.uniroma3.diadia.fixture;

import java.util.List;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Fixture {
	public static IOSimulator creaSimulazionePartitaEGiocaEasy(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanza("Atrio")
				.setStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenze("Atrio", "Biblioteca", "nord")
				.addAdiacenze("Biblioteca", "Atrio", "sud")
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, "caso", labirinto);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaEGiocaHard(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto labirinto = new LabirintoBuilder()
				.addStanza("Atrio")
				.setStanzaIniziale("Atrio")
				.addAttrezzo("martello", 3)
				.addStanzaVincente("Biblioteca")
				.addAdiacenze("Atrio", "Biblioteca", "nord")
				.addAdiacenze("Biblioteca", "Atrio", "sud")
				.addStanza("Bagno")
				.addAdiacenze("Bagno", "Atrio", "sud")
				.addAdiacenze("Atrio", "Bagno", "nord")
				.addStanza("Studio")
				.addAdiacenze("Studio", "Atrio", "est")
				.addAdiacenze("Atrio", "Studio", "ovest")
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, "caso", labirinto);
		gioco.gioca();
		return io;
	}

	public static IOSimulator creaSimulazionePartitaEGiocaMonolocale(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto monolocale = new LabirintoBuilder()
				.addStanza("salotto")
				.setStanzaIniziale("salotto") 
				.addStanzaVincente("salotto") 
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, "caso", monolocale);
		gioco.gioca();
		return io;
	}
	
	
	public static IOSimulator creaSimulazionePartitaEGiocaBilocale(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto bilocale = new LabirintoBuilder()
				.addStanza("salotto")
				.setStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto",10) // dove? fa riferimento all’ultima stanza aggiunta
				.addAdiacenze("salotto", "camera", "nord") // camera si trova a nord di salotto
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, "caso", bilocale);
		gioco.gioca();
		return io;
	}
	
	public static IOSimulator creaSimulazionePartitaEGiocaTrilocale(List<String> comandiDaLeggere) throws Exception {
		IOSimulator io = new IOSimulator(comandiDaLeggere);
		Labirinto trilocale = new LabirintoBuilder()
				.addStanza("salotto")
				.setStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola",1) // dove? fa riferimento all’ultima stanza aggiunta
				.addStanzaVincente("camera")
				.addAdiacenze("salotto", "cucina", "nord")
				.addAdiacenze("cucina", "camera", "est")
				.getLabirinto();
		DiaDia gioco = new DiaDia(io, "caso", trilocale);
		gioco.gioca();
		return io;
	}

	public static Attrezzo creaAttrezzoEAggiugniAStanza(Stanza stanzaDaRiempire, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		stanzaDaRiempire.addAttrezzo(attrezzo);
		return attrezzo;
	}

}
