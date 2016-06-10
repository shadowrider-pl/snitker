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
	private double mi;
	private double sigma;
	private String duplikatStr;
	private double tA = 0.0;
	private double tB = 0.0;

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
//				System.out.println("dodałem rangę "+ranga+" do duplikatyArr");

			} else if ((duplikatStr == "koniec" || !(obecny==duplikat)) && duplikatyArr.size() > 0) {

//				System.out.println("dodałem rangę " + ranga + " do duplikatyArr");
				duplikatyArr.add(ranga);
				
				for (int j = 0; j < duplikatyArr.size(); j++) {
					kolejnaRanga=duplikatyArr.get(j);
					licznikSredniej = licznikSredniej + kolejnaRanga;
				}
				
				sredniaRanga = licznikSredniej / duplikatyArr.size();
				pierwszaRanga = duplikatyArr.get(0);
//System.out.println("sredniaRanga"+sredniaRanga+", pierwszaRanga "+pierwszaRanga+", duplikatyArr.size():"+duplikatyArr.size());

				for (int sr = (int) pierwszaRanga; sr < pierwszaRanga+duplikatyArr.size(); sr++) {
//					System.out.println("sr: "+sr);
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
				tA = tA + wierszTemp.get(2);
			else if (wierszTemp.get(1)==1.0)
				tB = tB + wierszTemp.get(2);
			else {
			}
//				System.out.println("!!!"+"wierszTemp.get(1)"+wierszTemp.get(1)+", wierszTemp.get(2)"+ wierszTemp.get(2));
		}

		double tWynik;
		if (tA < tB) {
			tWynik = tA;
		} else {
			tWynik = tB;
		}

		n = n-1;
		nDouble=(double)n;
		
//		System.out.println("tA:"+tA+"tB:"+tB);
		mi = (nDouble * (2 * nDouble + 1)) / 2;
		sigma = Math.sqrt((nDouble * nDouble * (2 * nDouble + 1)) / 12);
		statZ = (tWynik - mi) / sigma;
//		System.out.println(
//				"statZ: " + statZ + ", alfapol: " + alfapol + ", tA: " + tA + ", tB: " + tB + ", tWynik: " + tWynik+  ", n: " + n);
		
		if (Math.abs(statZ) >= alfapol) {
			wynik_testu = "Należy odrzucić hipotezę H0, że dane populacje się różnią";
		} else {
			wynik_testu = "Brak podstaw do odrzucenia hipotezy H0, że dane populacje się różnią";
		}
		ciagR.clear();
		ciagV.clear();
		ciagV2.clear();
		duplikatyArr.clear();
		wierszTemp.clear();
		wierszTemp2.clear();
		wierszTemp3.clear();
		
		TestSumyRangJFrame tzrjf = new TestSumyRangJFrame();
		String opis = "<html><center><h1>Test sumy rang Wilcoxona</h1><br>Bada, czy dwie dane populacje się różnią.<br>Poziom istotności &#945;=0,05. </center><br><br><br>"
				+ "Statystyka Z=" + statZ + "<br>" + "Z alfa pół=" + alfapol + "<br><br>Wynik:<br>" + wynik_testu
				+ "<br></html>";
		tzrjf.ustawjLabel1(opis);
		tzrjf.setVisible(true);
	}
}
