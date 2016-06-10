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
import java.util.Arrays;

/**
 *
 * @author norbert
 */
public class TablicaZcsv {

	public double[][] dane;
	public ArrayList<ArrayList<String>> daneAL = new ArrayList<ArrayList<String>>();
	// Nie może być ArrayList<Double> bo line = br.readLine() czyta Stringi
	private static int wiersz;
	private static String dane_str[][];
	private String line = "";
	public ArrayList<String> tytulAL;
	private BufferedReader br = null;
	private String csvFile;
	private ArrayList<String> wierszCSV;
	private String separatorCSV=",";
	public int iloscKolumn;
	public String[] tytuly;
	public String[] daneWiersz;

	// public static void main(String[] args) {
	public TablicaZcsv() {

		czytaj();

	}

	public void czytaj() {

//		if (Okna.Okno.plik != null) {
//			csvFile = "/home/norbert/java/snitker/src/test/java/Tabele/punkty_lojalnościowe.csv";
//		} else {
//			csvFile = Okna.Okno.plik;
//		}
		csvFile = Okna.Okno.plik;
		

		try {
			wiersz = 0;
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				wierszCSV = new  ArrayList<String>(Arrays.asList(line.split(separatorCSV)));
				daneAL.add(wierszCSV);
				wiersz++;
			}
			
			tytulAL= daneAL.get(0);
			iloscKolumn=tytulAL.size();			
			tytuly=	 tytulAL.toArray(new String[iloscKolumn]);

			dane_str = new String[wiersz][iloscKolumn];
			
			dane = new double[wiersz][iloscKolumn];

			for (int i = 1; i < wiersz; i++) { //w zerowym wierszu tytuł
				daneWiersz=daneAL.get(i).toArray(new String[iloscKolumn]);
				for(int j=0;j<iloscKolumn;j++){
					dane[i][j] = Double.parseDouble(daneWiersz[j]);	
					
				}
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

	public String[] getTytuly() {
		return tytuly;
	}
	public ArrayList<ArrayList<String>> getDaneAL() {
		return daneAL;
	}

	public static String[][] getDane_str() {
		return dane_str;
	}

	public int getWiersz() {
		return wiersz;
	}

	// public String[] getDane_str() {
	// if (dane_str != null) {
	// return dane_str;
	// } else {
	// return pusty;
	// }
	// }

}
