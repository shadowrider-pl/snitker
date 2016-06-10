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
	static double lPlus = 0;
	static double lMinus = 0;
	static double lWynik = 0;

	private static ArrayList<ArrayList<String>> ciagV = new ArrayList<ArrayList<String>>();
	private static ArrayList<ArrayList<Double>> ciagV2 = new ArrayList<ArrayList<Double>>();
	private static ArrayList<ArrayList<String>> ciagV3 = new ArrayList<ArrayList<String>>();
	private static ArrayList<ArrayList<String>> ciagV4 = new ArrayList<ArrayList<String>>();
	private static String wartCiaguR;
	private static double wartCiaguV2;
	private static String znak;
	private static ArrayList<Double> duplikatyArr = new ArrayList<Double>();
	private static String duplikat;
	private static double licznikSredniej = 0.0;
	private static double sredniaRanga = 0.0;
	private static double statZ = 0.0;
	private static Integer n = 0;
	final static double alfapol = 1.96;
	private static String wynik_testu;
	private static double ranga;
	private static double pierwszaRanga;
	private static double kolejnaRanga;
	private static double liczba;
	private static double rangaD;
	private static String obecny;

	public TestZnakowRangowanych(double[][] tablica) {

		n = tablica.length;

		for (int i = 1; i < n; i++) {
			double r = tablica[i][0] - tablica[i][1];
			ciagR.add(r);
		}

		for (int i = 0; i < ciagR.size(); i++) {
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
		}

		// zamiana na double żeby przesortować liczby a nie łańcuchy
		for (int i = 0; i < ciagV.size(); i++) {
			ArrayList<Double> wierszTempD = new ArrayList<Double>();
			wierszTemp = new ArrayList<String>();
			wierszTemp = (ciagV.get(i));
			liczba = Double.parseDouble(wierszTemp.get(0));
			rangaD = Double.parseDouble(wierszTemp.get(2));
			wierszTempD.add(liczba);
			wierszTempD.add(rangaD);
			ciagV2.add(wierszTempD);
		}

		// sortowanie

		Collections.sort(ciagV2, new Comparator<ArrayList<Double>>() {
			public int compare(ArrayList<Double> o1, ArrayList<Double> o2) {
				return o1.get(0).compareTo(o2.get(0));
			}
		});

		// zamiana z powrotem na string
		for (int i = 0; i < ciagV2.size(); i++) {
			ArrayList<Double> wierszTempD = new ArrayList<Double>();
			wierszTempD = ciagV2.get(i);
			wartCiaguV2 = wierszTempD.get(0);
			rangaD = wierszTempD.get(1);
			wierszTemp = new ArrayList<String>();
			wierszTemp.add(Double.toString(wartCiaguV2));
			wierszTemp.add(Double.toString(rangaD));
			ciagV3.add(wierszTemp);
		}

		// dodawanie znaku do V3
		for (int i = 0; i < ciagV3.size(); i++) {
			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV3.get(i);
			for (int j = 0; j < n; j++) {
				wierszTemp2 = new ArrayList<String>();
				wierszTemp2 = ciagV.get(j);

				if (Double.parseDouble(wierszTemp.get(1)) == (Double.parseDouble(wierszTemp2.get(2)))) {
					wierszTemp.add(wierszTemp2.get(1));
					ciagV4.add(wierszTemp);
					// System.out.println("====");
					break;
				}

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

		for (int i = 0; i < ciagV4.size(); i++) {

			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV4.get(i);
			obecny = wierszTemp.get(0);
			ranga = Double.parseDouble(wierszTemp.get(1));
			if (i < ciagV4.size() - 1) {
				wierszTemp2 = new ArrayList<String>();
				wierszTemp2 = ciagV4.get(i + 1);
				duplikat = wierszTemp2.get(0);
				// System.out.println("duplikat: " + duplikat + ", i:" + i + ",
				// ranga:" + ranga);
			} else {
				duplikat = "koniec";
			}

			if (obecny.equals(duplikat)) {

				duplikatyArr.add(ranga);
//				System.out.println("dodałem rangę " + ranga + " do duplikatyArr");

			} else if ((duplikat == "koniec" || !obecny.equals(duplikat)) && duplikatyArr.size() > 0) {

				duplikatyArr.add(ranga);
//				System.out.println("dodałem rangę " + ranga + " do duplikatyArr");

				for (int j = 0; j < duplikatyArr.size(); j++) {
					kolejnaRanga = duplikatyArr.get(j);
					licznikSredniej = licznikSredniej + kolejnaRanga;
				}

				sredniaRanga = licznikSredniej / duplikatyArr.size();
				pierwszaRanga = duplikatyArr.get(0);
//				System.out.println("sredniaRanga" + sredniaRanga + ", pierwszaRanga " + pierwszaRanga
//						+ ", duplikatyArr.size():" + duplikatyArr.size());

				for (int sr = (int) pierwszaRanga; sr < pierwszaRanga + duplikatyArr.size(); sr++) {
//					System.out.println("sr: " + sr);
					wierszTemp3 = ciagV4.get(sr - 1);
					wierszTemp3.set(1, Double.toString(sredniaRanga));
					ciagV4.set(sr - 1, wierszTemp3);
				}
				duplikat = "";
				licznikSredniej = 0;
				duplikatyArr.clear();
				sredniaRanga = 0;
			}
		}

		for (int i = 0; i < ciagV4.size(); i++) {
			wierszTemp = new ArrayList<String>();
			wierszTemp = ciagV4.get(i);
			if (wierszTemp.get(2).equals("+"))
				lPlus = lPlus + Double.parseDouble(wierszTemp.get(1));
			else if (wierszTemp.get(2).equals("-"))
				lMinus = lMinus + Double.parseDouble(wierszTemp.get(1));
		}
//		System.out.println("lPlus:" + lPlus + ", lMinus" + lMinus);

		if (lPlus < lMinus) {
			lWynik = lPlus;
		} else {
			lWynik = lMinus;
		}
		
		n = ciagV4.size();
		
		statZ = (lWynik - ((n * (n + 1)) / 4)) / Math.sqrt(((n * (n + 1) * (2 * n + 1)) / 24));

		if (Math.abs(statZ) >= alfapol) {
			wynik_testu = "Należy odrzucić hipotezę H0, że dane populacje się różnią";
		} else {
			wynik_testu = "Brak podstaw do odrzucenia hipotezy H0, że dane populacje się różnią";
		}
		ciagR.clear();
		ciagV.clear();
		ciagV2.clear();
		ciagV3.clear();
		ciagV4.clear();
		duplikatyArr.clear();
		wierszTemp.clear();
		wierszTemp2.clear();
		wierszTemp3.clear();
		lPlus=0;
		lMinus=0;
		
		
		TestZnakowRangowanychJFrame tzrjf = new TestZnakowRangowanychJFrame();
		String opis = "<html><center><h1>Test znaków rangowanych Wilcoxona</h1><br>Bada, czy dwie dane populacje się różnią.<br>Poziom istotności &#945;=0,05. </center><br><br><br>"
				+ "Statystyka Z=" + statZ + "<br>" + "Z alfa pół=" + alfapol + "<br><br>Wynik:<br>" + wynik_testu
				+ "<br></html>";
		tzrjf.ustawjLabel1(opis);
		tzrjf.setVisible(true);
	}
}
