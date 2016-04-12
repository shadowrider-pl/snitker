package snitker;

import Funkcje.OdchylenieStandardowe;
import Funkcje.Srednia;
import Funkcje.ZaokrDo2Miejsc;
//import Tabele.TablicaLosowana;
import Tabele.TablicaZcsv;

public class PierwszeFunkcje {

	int zakres=100;
	public double srednia =0;
	double srednia_zaokraglona=0;
	double odchylenie_standardowe = 0;
	public String wynik1="";
	
			
	public PierwszeFunkcje(){
//	TablicaLosowana tabela = new TablicaLosowana();
//	tabela.zapelnij_tablice(zakres);
		TablicaZcsv tabela= new TablicaZcsv();
	

	Srednia obj_srednia=new Srednia();
	srednia = obj_srednia.wyliczona_srednia(tabela.dane);
	

	ZaokrDo2Miejsc obj_zaokraglony=new ZaokrDo2Miejsc();
	srednia_zaokraglona=obj_zaokraglony.zaokraglij(srednia);
	
	
	OdchylenieStandardowe obj_odchylenie=new OdchylenieStandardowe();
	odchylenie_standardowe = obj_odchylenie.wylicz_odchylenie(srednia, tabela.dane);

	wynik1="<html>Średnia z losowej tabeli: "+srednia+"<BR>"
	+"Średnia zaokrąglona: "+srednia_zaokraglona+"</html>";
	}


	public String getWynik1() {
		return wynik1;
	}


	

	
	
	
}
