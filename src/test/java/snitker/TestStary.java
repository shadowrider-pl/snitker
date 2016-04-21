package snitker;

import Funkcje.OdchylenieStandardowe;
import Funkcje.Srednia;
import Funkcje.ZaokrDo2Miejsc;
import Tabele.TablicaLosowana;
import Tabele.Zakres;

public class TestStary {

	public static void main(String[] args) {
		
		Zakres zakresowanie=new Zakres();
		int zakres=zakresowanie.ustaw_zakres();

		System.out.println();
		System.out.println("----------------------------------");
		System.out.println();
		
		TablicaLosowana tabela = new TablicaLosowana();
		tabela.zapelnij_tablice(zakres);		
		
		for (double element : tabela.dane) {
			System.out.println("Pole z Testu " + element);

		}



		System.out.println();
		System.out.println("----------------------------------");
		System.out.println();
		
		Srednia obj_srednia=new Srednia();
		double srednia = obj_srednia.wyliczona_srednia(tabela.dane);

		System.out.println("Średnia: " + srednia);

		ZaokrDo2Miejsc obj_zaokraglony=new ZaokrDo2Miejsc();
		double srednia_zaokraglona=obj_zaokraglony.zaokraglij(srednia);
		System.out.println("Średnia zaokrąglona: " + srednia_zaokraglona);
		

		System.out.println();
		System.out.println("----------------------------------");
		System.out.println();
		
		OdchylenieStandardowe obj_odchylenie=new OdchylenieStandardowe();
		double odchylenie_standardowe = obj_odchylenie.wylicz_odchylenie(srednia, tabela.dane);
		System.out.println("Odchylenie standardowe: " + odchylenie_standardowe);
		System.out.println("Odchylenie standardowe za: " + obj_odchylenie.getOdchylenie_zaokr());
	}


}
