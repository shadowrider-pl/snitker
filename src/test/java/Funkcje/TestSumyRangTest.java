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

public class TestSumyRangTest {

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
	private static ArrayList<Double> wierszTemp;
	private static ArrayList<Double> wierszTemp2;
	private static ArrayList<Double> wierszTemp3;

	private static ArrayList<Double> ciagR;
	static int lPlus = 0;
	static int lMinus = 0;
	static int lWynik = 0;

	private static ArrayList<ArrayList<Double>> ciagV = new ArrayList<ArrayList<Double>>();
	private static String wartCiaguR;
	private static String znak;
	private static ArrayList<Double> duplikatyArr = new ArrayList<Double>();
	private static double duplikat;
	private static double ranga = 0.0;
	private static double licznikSredniej = 0.0;
	private static double sredniaRanga = 0.0;
	private static double statZ = 0.0;
	private static int n = 0;
	final static double alfapol = 1.96;
	private static String wynik_testu;

	public static void main(String[] args) {
		czytaj();

		n = dane.length;

		for (int k = 0; k < 2; k++) {
			for (int i = 1; i < n; i++) {
				// double r = dane[i][0] - dane[i][1];
				ranga = ranga + 1.0;
				ciagR = new ArrayList<Double>();
				ciagR.add(dane[i][k]);
				ciagR.add((double) k);
				// ciagR.add(ranga);
				ciagV.add(ciagR);
				System.out.println("ranga:" + ranga);
			}
		}

		n = ciagV.size();

		System.out.println("-----------------------");
		System.out.println("n=" + n);

		Collections.sort(ciagV, new Comparator<ArrayList<Double>>() {
			public int compare(ArrayList<Double> o1, ArrayList<Double> o2) {
				return o1.get(0).compareTo(o2.get(0));
			}
		});

		// rangowanie
		for (int i = 0; i < n; i++) {
			wierszTemp = new ArrayList<Double>();
			wierszTemp = ciagV.get(i);
			wierszTemp.add((double) i);
		}

		// System.out.println("po wstępnym rangowaniu");

		for (int i = 0; i < n; i++) {
			if (i > 0) {
				wierszTemp = new ArrayList<Double>();
				wierszTemp = ciagV.get(i - 1);
				duplikat = wierszTemp.get(0);
				System.out.println("duplikat:" + duplikat);
			} else {
				// duplikat = null;
			}
			wierszTemp2 = new ArrayList<Double>();
			wierszTemp2 = ciagV.get(i);
			System.out.println("wierszTemp2-0: " + wierszTemp2.get(0) + ", wierszTemp2-1: " + wierszTemp2.get(1)
					+ ", wierszTemp2-2: " + wierszTemp2.get(2));
			ranga = wierszTemp2.get(2);
			if (wierszTemp2.get(0).equals(duplikat)) {
				duplikatyArr.add(ranga);
				System.out.println("duplikat: " + duplikat + ", i:" + i + ", ranga:" + ranga);
			} else if (duplikatyArr.size() > 0) {
				for (int j = 0; j < duplikatyArr.size(); j++) {
					licznikSredniej = licznikSredniej + duplikatyArr.get(j);
				}
				sredniaRanga = ((double) licznikSredniej / (double) duplikatyArr.size());
				System.out.println("sredniaRanga:" + sredniaRanga + "licznikSredniej:" + licznikSredniej
						+ "duplikatyArr.size():" + duplikatyArr.size() + " duplikatyArr.get(0).intValue():"
						+ duplikatyArr.get(0).intValue());

				for (int sr = 0; sr < duplikatyArr.size(); sr++) {
					wierszTemp3 = new ArrayList<Double>();
					wierszTemp3 = ciagV.get(duplikatyArr.get(sr).intValue());
					wierszTemp3.set(2, sredniaRanga);
					ciagV.set(duplikatyArr.get(sr).intValue(), wierszTemp3);

					System.out.println("wierszTemp3-0: " + wierszTemp3.get(0) + ", wierszTemp3-1: " + wierszTemp3.get(1)
							+ ", wierszTemp3-2: " + wierszTemp3.get(2));
				}
				sredniaRanga = 0;
				licznikSredniej = 0;
				duplikatyArr.clear();
			}

			// System.out.println(i);
			// System.out.println("------------------------");
		}

		double tA = 0.0;
		double tB = 0.0;

		for (int i = 0; i < n; i++) {
			wierszTemp = new ArrayList<Double>();
			wierszTemp = ciagV.get(i);
			if (wierszTemp.get(1).equals(0.0))
				tA = tA + wierszTemp.get(2);
			if (wierszTemp.get(1).equals(1.0))
				tB = tB + wierszTemp.get(2);
		}

		double tWynik;
		if (tA < tB) {
			tWynik = tA;
		} else {
			tWynik = tB;
		}

		int mi = (n * (2 * n + 1)) / 2;
		double sigma = Math.sqrt((n * n * (2 * n + 1)) / 12);
		statZ = (tWynik - mi) / sigma;

		System.out.println(
				"statZ: " + statZ + ", alfapol: " + alfapol + ", tA: " + tA + ", tB: " + tB + ", tWynik: " + tWynik);
		if (Math.abs(statZ) >= alfapol) {
			wynik_testu = "Należy odrzucić hipotezę H0, że dane populacje się różnią";
		} else {
			wynik_testu = "Brak podstaw do odrzucenia hipotezy H0, że dane populacje się różnią";
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
