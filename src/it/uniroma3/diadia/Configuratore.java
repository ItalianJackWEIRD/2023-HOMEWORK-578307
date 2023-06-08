package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Configuratore {

	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static Properties prop = null;
	
	public static int getCFU() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU));
	}
	
	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}

	private static void carica() {
		prop = new Properties();
		try {
			//InputStream input = Configuratore.class.getClassLoader().getResourceAsStream("diadia.properties");
			FileInputStream input= new FileInputStream("diadia.properties");
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}