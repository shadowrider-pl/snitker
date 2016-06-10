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
	private static ArrayList<ArrayList<Double>> ciagV2 = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<String>> ciagV3 = new ArrayList<ArrayList<String>>();
	private static ArrayList<ArrayList<String>> ciagV4 = new ArrayList<ArrayList<String>>();
	private static double wartCiaguV2;
	private static String wartCiaguR;
	private static String znak;
	private static ArrayList<Double> duplikatyArr = new ArrayList<Double>();
	private static String duplikat;
	private static String obecny;
	private static double ranga;
	private static double pierwszaRanga;
	private static double kolejnaRanga;
	private static double licznikSredniej = 0.0;
	private static Double sredniaRanga = 0.0;
	private static Double statZ = 0.0;
	private static Integer n = 0;
	final static double alfapol = 1.96;
	private static String wynik_testu;
	private static double liczba;
	private static double rangaD;

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
			wierszTemp.add(Integer.toString(i + 1));
			ciagV.add(wierszTemp);
			// System.out.println("ciagR: " + ciagR.get(i));
			// System.out.println("wierszTemp: " + wierszTemp.get(0));
		}

		// zamiana na double żeby przesortować liczby a nie łańcuchy
		for (int i = 0; i < n; i++) {
			ArrayList<Double> wierszTempD = new ArrayList<Double>();
			wierszTemp = new ArrayList<String>();
			wierszTemp = (ciagV.get(i));
			liczba = Double.parseDouble(wierszTemp.get(0));
			rangaD = Double.parseDouble(wierszTemp.get(2));
			wierszTempD.add(liczba);
			wierszTempD.add(rangaD);
			ciagV2.add(wierszTempD);
		}

		// wierszTemp.clear();

		// sortowanie

		Collections.sort(ciagV2, new Comparator<ArrayList<Double>>() {
			public int compare(ArrayList<Double> o1, ArrayList<Double> o2) {
				return o1.get(0).compareTo(o2.get(0));
			}
		});

		// zamiana z powrotem na string
		for (int i = 0; i < n; i++) {
			ArrayList<Double> wierszTempD = new ArrayList<Double>();
			wierszTempD = ciagV2.get(i);
			wartCiaguV2 = wierszTempD.get(0);
			rangaD = wierszTempD.get(1);
			wierszTemp = new ArrayList<String>();
			wierszTemp.add(Double.toString(wartCiaguV2));
			wierszTemp.add(Double.toString(rangaD));
			ciagV3.add(wierszTemp);
		}

		// test
		// String a="107";
		// double b=107;
		// System.out.println("a:"+a+", b:"+b);
		// if(a.equals(b)){ System.out.println("OK");
		// }else{
		// System.out.println("nie OK");
		// }
		// System.out.println("====");
		//// double c=Double.valueOf(a);
		// double c=new Double(a).doubleValue();
		// System.out.println("c:"+c);
		//// if(b.equals(c)){ System.out.println("OK2");
		// if(b==c){ System.out.println("OK2");
		// }else{
		// System.out.println("nie OK2");
		// }

		// dodawanie znaku do V3
		for (int i = 0; i < n; i++) {
			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV3.get(i);
			for (int j = 0; j < n; j++) {
				wierszTemp2 = new ArrayList<String>();
				wierszTemp2 = ciagV.get(j);
				// System.out.println("wierszTemp2.get(2):"+Double.parseDouble(wierszTemp2.get(2))+",
				// wierszTemp.get(1):"+wierszTemp.get(1));

				if (Double.parseDouble(wierszTemp.get(1)) == (Double.parseDouble(wierszTemp2.get(2)))) {
					wierszTemp.add(wierszTemp2.get(1));
					ciagV4.add(wierszTemp);
					System.out.println("====");
					break;
				}
				// else{
				// System.out.println("dupa");
				// }

			}
		}

		// wierszTemp.clear();
		// rangowanie
		for (int i = 0; i < ciagV4.size(); i++) {
			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV4.get(i);
			wierszTemp.set(1, Integer.toString(i + 1));
			ciagV4.set(i, wierszTemp);
		}

		for (ArrayList<String> x : ciagV4) {
			wierszTemp = new ArrayList<String>();
			wierszTemp = x;
			System.out.println("wierszTemp.get(0):" + wierszTemp.get(0) + "wierszTemp.get(1):" + wierszTemp.get(1)
					+ "wierszTemp.get(2):" + wierszTemp.get(2));
		}
		// System.out.println("po wstępnym rangowaniu");

		for (int i = 0; i < ciagV4.size(); i++) {

			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV4.get(i);
			obecny = wierszTemp.get(0);
			ranga = Double.parseDouble(wierszTemp.get(1));
			if (i < ciagV4.size() - 1) {
				wierszTemp2 = new ArrayList<String>();
				wierszTemp2 = ciagV4.get(i + 1);
				duplikat = wierszTemp2.get(0);
				System.out.println("duplikat: " + duplikat + ", i:" + i + ", ranga:" + ranga);
			} else {
				duplikat = "koniec";
			}

			if (obecny.equals(duplikat)) {

				duplikatyArr.add(ranga);

			} else if ((duplikat == "koniec" || !obecny.equals(duplikat)) && duplikatyArr.size() > 0) {
				
				duplikatyArr.add(ranga);
				
				for (int j = 0; j < duplikatyArr.size(); j++) {
					kolejnaRanga=duplikatyArr.get(j);
					licznikSredniej = licznikSredniej + kolejnaRanga;
				}
				
				sredniaRanga = licznikSredniej / duplikatyArr.size();
				pierwszaRanga = duplikatyArr.get(0);

				for (int sr = (int) pierwszaRanga; sr < duplikatyArr.size(); sr++) {
					wierszTemp3 = ciagV4.get(sr);
					wierszTemp3.set(1, Double.toString(sredniaRanga));
					ciagV4.set(sr, wierszTemp3);
				}
				duplikat="";
				// duplikatyArr.clear();
				// wierszTemp.clear();
				// wierszTemp2.clear();
				// wierszTemp3.clear();
			}

			// System.out.println(i);
			// System.out.println("------------------------");
		}

		for (int i = 0; i < ciagV4.size(); i++) {
			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV4.get(i);
			if (wierszTemp.get(2).equals("+"))
				lPlus++;
			else if (wierszTemp.get(2).equals("-"))
				lMinus++;
			else
				System.out.println("!!!!!!!");
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
