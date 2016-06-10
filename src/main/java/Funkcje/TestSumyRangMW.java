package Funkcje;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Okna.TestSeriiM1JFrame;
import Okna.TestSumyRangJFrame;
import Okna.TestSumyRangMWJFrame;
import Okna.TestZnakowRangowanychJFrame;

public class TestSumyRangMW {
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
	

	public TestSumyRangMW(double[][] tablica) {

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
				rA = rA + wierszTemp.get(2);
			
//				System.out.println("!!!"+"wierszTemp.get(1)"+wierszTemp.get(1)+", wierszTemp.get(2)"+ wierszTemp.get(2));
		}

		
		n=n-1;
		nDouble=(double)n;
		u=nDouble*nDouble+(nDouble*(nDouble+1))/2-rA;
		mi = nDouble*nDouble / 2;
		sigma = Math.sqrt((nDouble * nDouble * (2 * nDouble + 1)) / 12);
		statZ = (u - mi) / sigma;
//		System.out.println(
//				"statZ: " + statZ + ", alfapol: " + alfapol + ", rA: " + rA + ", u: " + u+ ", mi: " 
//		+ mi+ ", sigma: " + sigma+  ", n: " + n);
		
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
		n=0;
		nDouble=0;
		rA=0;

		TestSumyRangMWJFrame tzrmwjf = new TestSumyRangMWJFrame();
		String opis = "<html><center><h1>Test sumy rang Manna-Whitney'a</h1><br>Bada, czy dwie dane populacje się różnią.<br>Poziom istotności &#945;=0,05. </center><br><br><br>"
				+ "Statystyka Z=" + statZ + "<br>" + "Z alfa pół=" + alfapol + "<br><br>Wynik:<br>" + wynik_testu
				+ "<br></html>";
		tzrmwjf.ustawjLabel1(opis);
		tzrmwjf.setVisible(true);
	}
}
