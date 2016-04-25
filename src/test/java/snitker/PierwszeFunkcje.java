package snitker;

import static java.lang.Math.sqrt;

import Funkcje.Dominanta;
import Funkcje.Kurtoza;
import Funkcje.Mediana;
import Funkcje.MinMax;
import Funkcje.OdchylenieStandardowe;
import Funkcje.Srednia;
import Funkcje.Wariancja;
import Funkcje.ZaokrDo2Miejsc;
import Okna.Histogram;
import Okna.HistogramPrzedzialowy;
//import Tabele.TablicaLosowana;
import Tabele.TablicaZcsv;

public class PierwszeFunkcje {

	// int zakres = 100;
	public double srednia = 0;
	public double wariancja;
	double srednia_zaokraglona = 0;
	double odchylenie_standardowe = 0;
	public String wynik1 = "";
	public double mediana = 0;
	public static double dominanta = 0;
	public static double minimum = 0;
	public static double maximum = 0;
	public double kurtoza = 0;
	public static int liczba_obserwacji;
	public static double tablica[];

	public PierwszeFunkcje() {
		// TablicaLosowana tabela = new TablicaLosowana();
		// tabela.zapelnij_tablice(zakres);

		TablicaZcsv tabela = new TablicaZcsv();
		liczba_obserwacji=tabela.dane.length;
		Srednia obj_srednia = new Srednia();
		srednia = obj_srednia.wyliczona_srednia(tabela.dane);

		ZaokrDo2Miejsc obj_zaokraglony = new ZaokrDo2Miejsc();
		srednia_zaokraglona = obj_zaokraglony.zaokraglij(srednia);

		Wariancja obj_wariancja = new Wariancja();
		wariancja = obj_zaokraglony.zaokraglij(obj_wariancja.wariancja(srednia, tabela.dane));

		OdchylenieStandardowe obj_odchylenie = new OdchylenieStandardowe();
		odchylenie_standardowe = obj_zaokraglony.zaokraglij(obj_odchylenie.wylicz_odchylenie(srednia, tabela.dane));

		Mediana obj_mediana = new Mediana();
		mediana = obj_mediana.mediana(tabela.dane);

		Dominanta obj_dominanta = new Dominanta();
		dominanta = obj_dominanta.dominanta(tabela.dane);

		MinMax obj_minmax = new MinMax();
		obj_minmax.minmax(tabela.dane);
		minimum = obj_minmax.getMinimum();
		maximum = obj_minmax.getMaximum();
		liczba_obserwacji = obj_minmax.getLiczba_obserwacji();
		tablica= tabela.dane;

		Kurtoza obj_kurtoza = new Kurtoza();
		kurtoza = obj_zaokraglony.zaokraglij(obj_kurtoza.kurtoza(srednia, wariancja, tabela.dane));

		wynik1 = "<html>Średnia: " + srednia_zaokraglona + "<BR>" + "Wariancja: " + wariancja + "<BR>"
				+ "Odchylenie standardowe: " + obj_zaokraglony.zaokraglij(sqrt(wariancja)) + "<BR>" + "Mediana: "
				+ mediana + "<BR>" + "Dominanta: " + dominanta + "<BR>" + "Minimum: " + minimum + "<BR>" + "Maximum: "
				+ maximum + "<BR>" + "Kurtoza: " + kurtoza + "<BR>" + "Liczba rekordów: " + liczba_obserwacji + "<BR>" + "</html>";

		HistogramPrzedzialowy histp = new HistogramPrzedzialowy();
		Histogram hist = new Histogram();
		
	}

	public static double[] getTablica() {
		return tablica;
	}

	public static double getDominanta() {
		return dominanta;
	}

	public static double getMinimum() {
		return minimum;
	}

	public static double getMaximum() {
		return maximum;
	}

	public static int getLiczba_obserwacji() {
		return liczba_obserwacji;
	}

	public String getWynik1() {
		return wynik1;
	}

}
