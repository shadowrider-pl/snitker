package Funkcje;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestZnakowRangowanychTest {

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
	private static String separatorCSV = ",";
	public static int iloscKolumn;
	public static String[] tytuly;
	public static String[] daneWiersz;
	private static ArrayList<String> wierszTemp;
	private static ArrayList<String> wierszTemp2;
	private static ArrayList<String> wierszTemp3;

	private static ArrayList<Double> ciagR = new ArrayList<Double>();
	static int lPlus = 0;
	static int lMinus = 0;
	static int lWynik = 0;

	private static ArrayList<ArrayList<String>> ciagV = new ArrayList<ArrayList<String>>();
	private static String wartCiaguR;
	private static String znak;
	private static ArrayList<Integer> duplikatyArr;
	private static String duplikat;
	private static String ranga;
	private static Integer licznikSredniej = 0;
	private static Double sredniaRanga = 0.0;
	private static Double statZ = 0.0;
	private static Integer n = 0;
	final static double alfapol = 1.96;
	private static String wynik_testu;

	public static void main(String[] args) {
		czytaj();

		n = dane.length;

		for (int i = 1; i < n; i++) {
			double r = dane[i][0] - dane[i][1];
			ciagR.add(r);
		}

		n = n - 1; // dalsze tablice nie mają nagłówka

		System.out.println("-----------------------");
		System.out.println("n=" + n);
		for (int i = 0; i < n; i++) {
			wierszTemp = new ArrayList<String>();
			// System.out.println(ciagR.get(i));
			if (ciagR.get(i) > 0.0) {
				znak = "+";
			} else if (ciagR.get(i) < 0.0) {
				znak = "-";
			}
			wartCiaguR = Double.toString(Math.abs(ciagR.get(i)));
			wierszTemp.add(wartCiaguR);
			wierszTemp.add(znak);
			wierszTemp.add(Integer.toString(i));
			ciagV.add(wierszTemp);
//			System.out.println("ciagR: " + ciagR.get(i));
//			System.out.println("wierszTemp: " + wierszTemp.get(0));
		}

		// wierszTemp.clear();

		// sortowanie

		// Collections.sort(ciagV, new Comparator<ArrayList<String>>() {
		// public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		// return o1.get(0).compareTo(o2.get(0));
		// }
		// });

		Collections.sort(ciagV, new Comparator<ArrayList<String>>() {
			public int compare(ArrayList<String> o1, ArrayList<String> o2) {
				return o1.get(0).compareTo(o2.get(0));
			}
		});

		// wierszTemp.clear();
		// rangowanie
		for (int i = 0; i < n; i++) {
			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV.get(i);
			wierszTemp.set(2, Integer.toString(i + 1));
			ciagV.set(i, wierszTemp);
		}

		// System.out.println("po wstępnym rangowaniu");

		for (int i = 0; i < n; i++) {
			if (i > 0) {
				wierszTemp = new ArrayList<String>();
				wierszTemp = ciagV.get(i - 1);
				duplikat = wierszTemp.get(2);
			} else {
				duplikat = null;
			}
			wierszTemp2 = new ArrayList<String>();
			wierszTemp2 = ciagV.get(i);
			ranga = wierszTemp2.get(2);
			if (duplikat != null && ranga.equals(duplikat)) {
				duplikatyArr.add(Integer.parseInt(duplikat));
//				System.out.println("duplikat: " + duplikat + ", i:" + i + ", ranga:" + ranga);
			} else if (duplikatyArr != null && duplikatyArr.size() > 0) {
				for (int j = 0; j < duplikatyArr.size(); j++) {
					licznikSredniej = licznikSredniej + duplikatyArr.get(j);
				}
				sredniaRanga = (double) (licznikSredniej / duplikatyArr.size());
				for (int sr = duplikatyArr.get(0); sr < duplikatyArr.size(); sr++) {
					wierszTemp3 = ciagV.get(sr);
					wierszTemp3.set(2, Double.toString(licznikSredniej));
					ciagV.set(sr, wierszTemp3);
				}
				// duplikatyArr.clear();
				// wierszTemp.clear();
				// wierszTemp2.clear();
				// wierszTemp3.clear();
			}

			// System.out.println(i);
			// System.out.println("------------------------");
		}

		for (int i = 0; i < n; i++) {
			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV.get(i);
			if (wierszTemp.get(1).equals("+"))
				lPlus++;
			if (wierszTemp.get(1).equals("-"))
				lMinus++;
		}

		if (lPlus < lMinus) {
			lWynik = lPlus;
		} else {
			lWynik = lMinus;
		}

		statZ = (lWynik - ((n * (n + 1)) / 4)) / Math.sqrt(((n * (n + 1) * (2 * n + 1)) / 24));

		System.out.println("statZ: " + statZ + ", alfapol: " + alfapol + ", lPlus: " + lPlus + ", lMinus: " + lMinus
				+ ", lWynik: " + lWynik);
		if (Math.abs(statZ) >= alfapol) {
			wynik_testu = "Należy odrzucić hipotezę H0, że próba jest losowa";
		} else {
			wynik_testu = "Brak podstaw do odrzucenia hipotezy H0, że próba jest losowa";
		}
		System.out.println(wynik_testu);
	}

	public static void czytaj() {

		// if (Okna.Okno.plik != null) {
		// csvFile =
		// "/home/norbert/java/snitker/src/test/java/Tabele/punkty_lojalnościowe.csv";
		// } else {
		// csvFile = Okna.Okno.plik;
		// }
		csvFile = "/home/norbert/Wiek Nabywców Filmów DVD.csv";

		try {
			wiersz = 0;
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				// wierszCSV= Arrays.asList(line.split(separatorCSV));
				wierszCSV = new ArrayList<String>(Arrays.asList(line.split(separatorCSV)));
				daneAL.add(wierszCSV);
				wiersz++;
			}

			tytulAL = daneAL.get(0);
			iloscKolumn = tytulAL.size();
			tytuly = tytulAL.toArray(new String[iloscKolumn]);
			for (String xxx : tytuly) {
				System.out.println(xxx);
			}

			dane_str = new String[wiersz][iloscKolumn];
			// dane_str = daneAL.toArray(dane_str);

			dane = new double[wiersz][iloscKolumn];

			// System.out.println("Rozmiar dane: " + n);
			for (int i = 1; i < wiersz; i++) { // w zerowym wierszu tytuł
				daneWiersz = daneAL.get(i).toArray(new String[iloscKolumn]);
				for (int j = 0; j < iloscKolumn; j++) {
					dane[i][j] = Double.parseDouble(daneWiersz[j]);
					System.out.print(dane[i][j] + " ");
				}
				System.out.println();
			}

			// System.out.println(n);
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
