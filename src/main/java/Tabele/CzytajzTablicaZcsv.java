/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tabele;

import Funkcje.Srednia;

/**
 *
 * @author norbert
 */
public class CzytajzTablicaZcsv {

	public static String[] tab_str;

	public static void main(String[] args) {

		//
		// ReadCVS2 obj = new ReadCVS2();
		// obj.run();
		// tab_str=obj.getCountry();

		Tablica1KolZcsv tabelazcsv = new Tablica1KolZcsv();
		tabelazcsv.czytaj();
		tab_str = tabelazcsv.getDane_str();

		//
		// Srednia obj_srednia=new Srednia();
		// double srednia = obj_srednia.wyliczona_srednia(tabela.dane);

		// System.out.println("Country [code= " + tab_str[0]
		// + " , name=" + tab_str[0] + "]");
		if (tab_str != null) {
			System.out.println("Tabele.CzytajzTablicaZcsv.main(): " + tab_str[88]);
		} else {
			System.out.println("puste");
		}
	}
}
