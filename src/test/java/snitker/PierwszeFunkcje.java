package snitker;

import static java.lang.Math.sqrt;

import java.util.ArrayList;

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
import Tabele.Tablica1KolZcsv;
import Tabele.TablicaZcsv;

public class PierwszeFunkcje {

	// int zakres = 100;
	public double srednia = 0;
	public double wariancja;
	double srednia_zaokraglona = 0;
	double odchylenie_standardowe = 0;
	public String wynik1 = "<html>";
	public static double mediana = 0;
	public static double[] dominanta;
	public static String dominanta_str;
	public static double minimum = 0;
	public static double maximum = 0;
	public double kurtoza = 0;
	public static int liczba_obserwacji;
	public static double tablica[];
	public static String[] tytul;
	public int iloscKolumn;
	public static double [][] pelnaTabela;

	public static ArrayList<HistogramPrzedzialowy> histpAL = new ArrayList<HistogramPrzedzialowy>();
	public HistogramPrzedzialowy histp;
	public static ArrayList<Histogram> histAL = new ArrayList<Histogram>();
	public Histogram hist;

	public PierwszeFunkcje() {
		// TablicaLosowana tabela = new TablicaLosowana();
		// tabela.zapelnij_tablice(zakres);

		// Tablica1KolZcsv tabela = new Tablica1KolZcsv();
		TablicaZcsv tabela = new TablicaZcsv();
		// liczba_obserwacji=tabela.dane.length;
		liczba_obserwacji = tabela.getWiersz();
		iloscKolumn = tabela.iloscKolumn;
		pelnaTabela=tabela.dane;
		wynik1 += "<table border='1' CELLPADDING='8'><tr>";
		for (int i = 0; i < iloscKolumn; i++) {
			dominanta_str = "";
			
			Srednia obj_srednia = new Srednia();
			srednia = obj_srednia.wyliczona_srednia(pelnaTabela, i);

			ZaokrDo2Miejsc obj_zaokraglony = new ZaokrDo2Miejsc();
			srednia_zaokraglona = obj_zaokraglony.zaokraglij(srednia);

			Wariancja obj_wariancja = new Wariancja();
			wariancja = obj_zaokraglony.zaokraglij(obj_wariancja.wariancja(srednia, pelnaTabela, i));

			OdchylenieStandardowe obj_odchylenie = new OdchylenieStandardowe();
			odchylenie_standardowe = obj_zaokraglony
					.zaokraglij(obj_odchylenie.wylicz_odchylenie(srednia, pelnaTabela, i));

			Mediana obj_mediana = new Mediana();
			mediana = obj_mediana.mediana(pelnaTabela, i);

			Dominanta obj_dominanta = new Dominanta();
			dominanta = obj_dominanta.dominanta(pelnaTabela, i);
				for(double ds:dominanta){
					dominanta_str+=ds+"; ";
				}

			MinMax obj_minmax = new MinMax();
			obj_minmax.minmax(tabela.dane, i);
			minimum = obj_minmax.getMinimum();
			maximum = obj_minmax.getMaximum();
			liczba_obserwacji = obj_minmax.getLiczba_obserwacji();
			tablica = obj_mediana.getKol();
			tytul = tabela.getTytuly();

			Kurtoza obj_kurtoza = new Kurtoza();
			kurtoza = obj_zaokraglony.zaokraglij(obj_kurtoza.kurtoza(srednia, wariancja, pelnaTabela, i));

			wynik1 = wynik1 + "<td><h3><u>" + tytul[i] + "</u></h3>Średnia:&nbsp;" + srednia_zaokraglona + "<BR>"
					+ "Wariancja:&nbsp;" + wariancja + "<BR>" + "Odchylenie&nbsp;standardowe:&nbsp;"
					+ obj_zaokraglony.zaokraglij(sqrt(wariancja)) + "<BR>" + "Mediana:&nbsp;" + mediana + "<BR>"
					+ "Dominanta:&nbsp;" + dominanta_str + "<BR>" + "Minimum:&nbsp;" + minimum + "<BR>" + "Maximum:&nbsp;"
					+ maximum + "<BR>" + "Kurtoza:&nbsp;" + kurtoza + "<BR>" + "Liczba&nbsp;rekordów:&nbsp;"
					+ liczba_obserwacji + "<BR>";

			
			hist = new Histogram();
			histAL.add(hist);
			histp = new HistogramPrzedzialowy();
			histpAL.add(histp);
		}
		wynik1 += "</tr></table></html>";
	}

	public static double[][] getPelnaTabela() {
		return pelnaTabela;
	}

	public static double getMediana() {
		return mediana;
	}

	public static ArrayList<Histogram> getHistAL() {
		return histAL;
	}

	public static ArrayList<HistogramPrzedzialowy> getHistpAL() {
		return histpAL;
	}

	public static String[] getTytul() {
		return tytul;
	}

	public int getIloscKolumn() {
		return iloscKolumn;
	}

	public static double[] getTablica() {
		return tablica;
	}

	public static double[] getDominanta() {
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
