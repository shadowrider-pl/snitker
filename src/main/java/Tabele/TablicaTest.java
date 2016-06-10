package Tabele;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TablicaTest {

	public static double[][] dane;
	public static ArrayList<ArrayList<String>> daneAL = new ArrayList<ArrayList<String>>();
	// Nie może być ArrayList<Double> bo line = br.readLine() czyta Stringi
	private static int wiersz;
	private static String dane_str[][];
	private static String line = "";
	public static ArrayList<String> tytulAL;
	private static BufferedReader br = null;
	private static String csvFile;
	private static ArrayList<String> wierszCSV;
	private static String separatorCSV=",";
	public static int iloscKolumn;
	public static String[] tytuly;
	public static String[] daneWiersz;
	private static ArrayList<String> wierszTemp[];

	public void pobierz_naglowki(){

		csvFile = Okna.Okno.plik;
	}
	
	public static void main(String[] args) {
		czytaj();

	}

	public static void czytaj() {

//		if (Okna.Okno.plik != null) {
//			csvFile = "/home/norbert/java/snitker/src/test/java/Tabele/punkty_lojalnościowe.csv";
//		} else {
//			csvFile = Okna.Okno.plik;
//		}
		csvFile = "/home/norbert/Wiek Nabywców Filmów DVD.csv";
		

		try {
			wiersz = 0;
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
//				System.out.println(line);
//				wierszCSV=  Arrays.asList(line.split(separatorCSV));
				wierszCSV = new  ArrayList<String>(Arrays.asList(line.split(separatorCSV)));
				daneAL.add(wierszCSV);
				wiersz++;
			}

			tytulAL= daneAL.get(0);
			iloscKolumn=tytulAL.size();			
			tytuly=	 tytulAL.toArray(new String[iloscKolumn]);
			for(String xxx:tytuly){
				System.out.println(xxx);}

			dane_str = new String[wiersz][iloscKolumn];
//			dane_str = daneAL.toArray(dane_str);
			
			dane = new double[wiersz][iloscKolumn];

			// System.out.println("Rozmiar dane: " + dane.length);
			for (int i = 1; i < wiersz; i++) { //w zerowym wierszu tytuł
				daneWiersz=daneAL.get(i).toArray(new String[iloscKolumn]);
				for(int j=0;j<iloscKolumn;j++){
				dane[i][j] = Double.parseDouble(daneWiersz[j]);	
				System.out.print(dane[i][j]+" ");
				}
				System.out.println();
			}
			
System.out.println(dane.length);
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
}
