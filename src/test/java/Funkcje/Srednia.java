package Funkcje;

public class Srednia {

	public double wyliczona_srednia(double [] dane) {
		double suma = 0;
		double srednia = 0;
		int rozmiar = dane.length;
		for (int i = 0; i < rozmiar; i++) {
			suma += dane[i];
			// System.out.println("Suma="+suma+", dane="+dane[i]);
		}

		srednia = (double) suma / rozmiar; // lub suma double zamiast int
		return srednia;

	}

}
