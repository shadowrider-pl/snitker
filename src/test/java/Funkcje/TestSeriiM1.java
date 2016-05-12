package Funkcje;

import java.util.ArrayList;

import snitker.PierwszeFunkcje;

public class TestSeriiM1 {

	private double tablica[] = PierwszeFunkcje.getTablica();
	private double mediana = PierwszeFunkcje.getMediana();
	private ArrayList<String> ciagS = new ArrayList<String>();
	private int n1 = 0;
	private int n2 = 0;
	private int k = 0;
	private String aktualnaSeria = "";
	final double alfapol=0.59871;
	

	// public TestSeriiM1(double[] tablica) {
	public TestSeriiM1() {
		for (int i = 0; i < tablica.length; i++) {
			if (tablica[i] < mediana) {
				ciagS.add("m");
			} else if (tablica[i] > mediana) {
				ciagS.add("w");
			}
		}

		
		for (int i = 0; i < ciagS.size(); i++) {
			if (ciagS.get(i) == "m") {
				n1++;
			} else if (ciagS.get(i) == "w") {
				n2++;
			}

			if (aktualnaSeria != ciagS.get(i)) {
				k++;
				aktualnaSeria = ciagS.get(i);
			}
		}

//		return null;

	}
}
