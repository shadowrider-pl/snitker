package Funkcje;

import static java.lang.Math.*;

public class OdchylenieStandardowe implements InterfZaokraglijDo2Miejsc {

	double odchylenie_zaokr = 0;


	public double wylicz_odchylenie(double srednia, double[] dane) {
		double odchylenie = 0;
		double roznica = 0;
		int rozmiar = dane.length;
		for (int i = 0; i < rozmiar; i++) {
			roznica = pow((dane[i] - srednia), 2);

			// System.out.println("Suma="+suma+", dane="+dane[i]);
		}
		double podzielone = roznica / rozmiar;
		odchylenie = sqrt(podzielone);

		odchylenie_zaokr = zaokraglij(odchylenie);

		return odchylenie;

	}

	public double getOdchylenie_zaokr() {
		return odchylenie_zaokr;
	}

	public double zaokraglij(double liczba) {
		liczba = Math.round(liczba * 100);
		liczba /= 100;
		return liczba;
	}
}
