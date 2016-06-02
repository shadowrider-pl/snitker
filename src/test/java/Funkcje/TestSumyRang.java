package Funkcje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Okna.TestSeriiM1JFrame;
import Okna.TestSumyRangJFrame;
import Okna.TestZnakowRangowanychJFrame;

public class TestSumyRang {
	private static ArrayList<Double> wierszTemp;
	private static ArrayList<Double> wierszTemp2;
	private static ArrayList<Double> wierszTemp3;

	private static ArrayList<Double> ciagR = new ArrayList<Double>();
	static int lPlus = 0;
	static int lMinus = 0;
	static int lWynik = 0;

	private static ArrayList<ArrayList<Double>> ciagV = new ArrayList<ArrayList<Double>>();
	final static double alfapol = 1.96;
	private static String wynik_testu;
	private static ArrayList<Double> duplikatyArr = new ArrayList<Double>();
	private static double duplikat;
	private static double ranga = 0.0;
	private static double licznikSredniej = 0.0;
	private static double sredniaRanga = 0.0;
	private static double statZ = 0.0;
	private static int n = 0;

	public TestSumyRang(double[][] tablica) {

		n = tablica.length;

		for (int k = 0; k < 2; k++) {
			for (int i = 1; i < n; i++) {
				ranga = ranga + 1.0;
				ciagR = new ArrayList<Double>();
				ciagR.add(tablica[i][k]);
				ciagR.add((double) k);
				ciagV.add(ciagR);
			}
		}

		n = ciagV.size();

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


		for (int i = 0; i < n; i++) {
			if (i > 0) {
				wierszTemp = new ArrayList<Double>();
				wierszTemp = ciagV.get(i - 1);
				duplikat = wierszTemp.get(0);
			} 

			wierszTemp2 = new ArrayList<Double>();
			wierszTemp2 = ciagV.get(i);
			ranga = wierszTemp2.get(2);
			if (wierszTemp2.get(0).equals(duplikat)) {
				duplikatyArr.add(ranga);
			} else if (duplikatyArr.size() > 0) {
				for (int j = 0; j < duplikatyArr.size(); j++) {
					licznikSredniej = licznikSredniej + duplikatyArr.get(j);
				}
				sredniaRanga = ((double) licznikSredniej / (double) duplikatyArr.size());

				for (int sr = 0; sr < duplikatyArr.size(); sr++) {
					wierszTemp3 = new ArrayList<Double>();
					wierszTemp3 = ciagV.get(duplikatyArr.get(sr).intValue());
					wierszTemp3.set(2, sredniaRanga);
					ciagV.set(duplikatyArr.get(sr).intValue(), wierszTemp3);
				}
				sredniaRanga = 0;
				licznikSredniej = 0;
				duplikatyArr.clear();
			}

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

		if (Math.abs(statZ) >= alfapol) {
			wynik_testu = "Należy odrzucić hipotezę H0, że dane populacje się różnią";
		} else {
			wynik_testu = "Brak podstaw do odrzucenia hipotezy H0, że dane populacje się różnią";
		}

		TestSumyRangJFrame tzrjf = new TestSumyRangJFrame();
		String opis = "<html><center><h1>Test sumy rang Wilcoxona</h1><br>Bada, czy dwie dane populacje się różnią.<br>Poziom istotności &#945;=0,05. </center><br><br><br>"
				+ "Statystyka Z=" + statZ + "<br>" + "Z alfa pół=" + alfapol + "<br><br>Wynik:<br>" + wynik_testu
				+ "<br></html>";
		tzrjf.ustawjLabel1(opis);
		tzrjf.setVisible(true);
	}
}
