package Funkcje;

import static java.lang.Math.pow;

public class Wariancja {
	public double wariancja(double srednia, double[][] dane, int kolumna) {

		double roznica = 0;
		double wariancja;
		int rozmiar = dane.length;
		for (int i = 0; i < rozmiar; i++) {
			roznica += pow((dane[i][kolumna] - srednia), 2);
		}
		wariancja = roznica / rozmiar;
		return wariancja;
	}
}
