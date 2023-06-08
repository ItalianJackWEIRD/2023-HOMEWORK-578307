package it.uniroma3.diadia;
import java.util.Scanner;

public class IOConsole implements IO{
		
		private Scanner scannerDiLinee;
		
		public IOConsole (Scanner sca) {
			this.scannerDiLinee= sca;
		}
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	public String leggiRiga() {
		//Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		return riga;
	}
}
