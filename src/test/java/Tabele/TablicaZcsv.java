/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author norbert
 */
public class TablicaZcsv {

	public double [] dane;
	public ArrayList<String> daneAL = new ArrayList<String>(); 
// Nie może być ArrayList<Double> bo line = br.readLine() czyta Stringi
	private static int wiersz;
	private static String dane_str[];

	private BufferedReader br = null;

	// public static void main(String[] args) {
	public TablicaZcsv() {

		czytaj();

	}

	public void czytaj() {

		 String csvFile =
		 "/home/norbert/java/snitker/src/test/java/Tabele/punkty_lojalnościowe.csv";
//		String csvFile = Okna.Okno.plik;
		BufferedReader br = null;
		String line = "";


		try {
			wiersz = 0;
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				daneAL.add(line);
				// System.out.println("Wiersz: " + wiersz + " Punkty: [" +
				// daneAL.get(wiersz) + "]");
				wiersz++;
			}
//			System.out.println("Rozmiar daneAL: " + daneAL.size());

			dane_str = new String[wiersz];
			dane_str = daneAL.toArray(dane_str);

			dane = new double [wiersz];

//			System.out.println("Rozmiar dane: " + dane.length);
			for (int i = 0; i < wiersz; i++) {
				dane[i] = Double.parseDouble(dane_str[i]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<String> getDaneAL() {
		return daneAL;
	}

	public static String[] getDane_str() {
		return dane_str;
	}

	// public String[] getDane_str() {
	// if (dane_str != null) {
	// return dane_str;
	// } else {
	// return pusty;
	// }
	// }

}
