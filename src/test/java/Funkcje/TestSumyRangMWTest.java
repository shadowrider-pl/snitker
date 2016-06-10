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

public class TestSumyRangMWTest {

	public static double[][] dane;
	private static String dane_str[][];
	public static ArrayList<ArrayList<String>> daneAL = new ArrayList<ArrayList<String>>();
	// Nie może być ArrayList<Double> bo line = br.readLine() czyta Stringi
	private static int wiersz;
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
	private static ArrayList<ArrayList<Double>> ciagV = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<Double>> ciagV2 = new ArrayList<ArrayList<Double>>();
	final static double alfapol = 1.96;
	private static String wynik_testu;
	private static ArrayList<Double> duplikatyArr = new ArrayList<Double>();
	private static double duplikat;
	private static double obecny;
	private static double ranga = 0.0;
	private static double licznikSredniej = 0.0;
	private static double sredniaRanga = 0.0;
	private static double kolejnaRanga;
	private static double pierwszaRanga;
	private static double statZ = 0.0;
	private static int n = 0;
	private static double nDouble=0;
	private static double mi;
	private static double u;
	private static double sigma;
	private static String duplikatStr;
	private static double rA = 0.0;

	public static void main(String[] args) {
		czytaj();

		n = dane.length;

		for (int k = 0; k < 2; k++) {
			for (int i = 1; i < n; i++) {
				ranga = ranga + 1.0;
				ciagR = new ArrayList<Double>();
				ciagR.add(dane[i][k]);
				ciagR.add((double) k);
				ciagV.add(ciagR);
			}
		}


		Collections.sort(ciagV, new Comparator<ArrayList<Double>>() {
			public int compare(ArrayList<Double> o1, ArrayList<Double> o2) {
				return o1.get(0).compareTo(o2.get(0));
			}
		});

		// rangowanie
		for (int i = 0; i < ciagV.size(); i++) {
			wierszTemp = new ArrayList<Double>();
			wierszTemp = ciagV.get(i);
			wierszTemp.add((double) i+1);
			ciagV2.add(wierszTemp);
		}


		for (int i = 0; i < ciagV2.size(); i++) {
			wierszTemp = new ArrayList<Double>();
			wierszTemp = ciagV2.get(i);
			obecny = wierszTemp.get(0);
			ranga = wierszTemp.get(2);
//			System.out.println("ranga: "+ranga);
			if (i < ciagV2.size()-1) {
				wierszTemp2 = new ArrayList<Double>();
				wierszTemp2 = ciagV2.get(i + 1);
				duplikat = wierszTemp2.get(0);
//				System.out.println("duplikat: " + duplikat + ", i:" + i + ", ranga:" + ranga);
			} else {
				duplikatStr = "koniec";
			}
			
			if (obecny==duplikat) {

				duplikatyArr.add(ranga);
				System.out.println("dodałem rangę "+ranga+" do duplikatyArr");

			} else if ((duplikatStr == "koniec" || !(obecny==duplikat)) && duplikatyArr.size() > 0) {

				System.out.println("dodałem rangę " + ranga + " do duplikatyArr");
				duplikatyArr.add(ranga);
				
				for (int j = 0; j < duplikatyArr.size(); j++) {
					kolejnaRanga=duplikatyArr.get(j);
					licznikSredniej = licznikSredniej + kolejnaRanga;
				}
				
				sredniaRanga = licznikSredniej / duplikatyArr.size();
				pierwszaRanga = duplikatyArr.get(0);
System.out.println("sredniaRanga"+sredniaRanga+", pierwszaRanga "+pierwszaRanga+", duplikatyArr.size():"+duplikatyArr.size());

				for (int sr = (int) pierwszaRanga; sr < pierwszaRanga+duplikatyArr.size(); sr++) {
					System.out.println("sr: "+sr);
					wierszTemp3 = ciagV2.get(sr-1);
					wierszTemp3.set(2,sredniaRanga);
					ciagV2.set(sr-1, wierszTemp3);
				}
				duplikatStr="";
				duplikat=0;
				licznikSredniej=0;
				duplikatyArr.clear();
				sredniaRanga=0;
			}
			
		}


		for (int i = 0; i < ciagV2.size(); i++) {
			wierszTemp = new ArrayList<Double>();
			wierszTemp = ciagV2.get(i);
			if (wierszTemp.get(1)==0.0)
				rA = rA + wierszTemp.get(2);
			
				System.out.println("!!!"+"wierszTemp.get(1)"+wierszTemp.get(1)+", wierszTemp.get(2)"+ wierszTemp.get(2));
		}

		n=n-1;
		nDouble=(double)n;
		u=nDouble*nDouble+(nDouble*(nDouble+1))/2-rA;
		mi = nDouble*nDouble / 2;
		sigma = Math.sqrt((nDouble * nDouble * (2 * nDouble + 1)) / 12);
		double sigmasrodek=(double)(n * n * (2 * n + 1)) / 12;
		double sigmasrodekpierw=Math.sqrt(sigmasrodek);
		statZ = (u - mi) / sigma;

		System.out.println(
				"statZ: " + statZ + ", alfapol: " + alfapol + ", rA: " + rA + ", u: " + u+ ", mi: " 
		+ mi+ ", sigma: " + sigma+ ", sigmasrodek: " + sigmasrodek+ ", sigmasrodekpierw: " 
						+ sigmasrodekpierw+ ", n: " + n);
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
