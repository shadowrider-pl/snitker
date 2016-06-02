package Funkcje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Okna.TestSeriiM1JFrame;
import Okna.TestZnakowRangowanychJFrame;

public class TestZnakowRangowanych {
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

	public TestZnakowRangowanych(double[][] tablica) {

		n = tablica.length;

		for (int i = 1; i < n; i++) {
			double r = tablica[i][0] - tablica[i][1];
			ciagR.add(r);
		}

		n = n - 1; // dalsze tablice nie mają nagłówka

		for (int i = 0; i < n; i++) {
			wierszTemp = new ArrayList<String>();
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
		}

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
			} else if (duplikatyArr != null && duplikatyArr.size() > 0) {
				for (int j = 0; j < duplikatyArr.size(); j++) {
					licznikSredniej = licznikSredniej + duplikatyArr.get(j);
				}
				sredniaRanga = (double) (licznikSredniej / duplikatyArr.size());
				for (int sr = duplikatyArr.get(0); sr < duplikatyArr.size(); sr++) {
					wierszTemp3 = ciagV.get(sr);
					wierszTemp3.set(2, Double.toString(sredniaRanga));
					ciagV.set(sr, wierszTemp3);
				}
			}

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

		if (Math.abs(statZ) >= alfapol) {
			wynik_testu = "Należy odrzucić hipotezę H0, że dane populacje się różnią";
		} else {
			wynik_testu = "Brak podstaw do odrzucenia hipotezy H0, że dane populacje się różnią";
		}

		TestZnakowRangowanychJFrame tzrjf = new TestZnakowRangowanychJFrame();
		String opis = "<html><center><h1>Test znaków rangowanych Wilcoxona</h1><br>Bada, czy dwie dane populacje się różnią.<br>Poziom istotności &#945;=0,05. </center><br><br><br>"
				+ "Statystyka Z=" + statZ + "<br>" + "Z alfa pół=" + alfapol + "<br><br>Wynik:<br>" + wynik_testu
				+ "<br></html>";
		tzrjf.ustawjLabel1(opis);
		tzrjf.setVisible(true);
	}
}
