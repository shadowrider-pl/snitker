package Funkcje;

import java.util.Arrays;


public class Mediana {

		
	public double mediana (double [] dane) {
		
		boolean parzysta;
		double mediana = 0;
		int srodek;
		int rozmiar = dane.length;
			if(rozmiar%2==0) {
				parzysta=true;
			} else {
				parzysta=false;
			}
		Arrays.sort(dane);
		if(parzysta){
			srodek=((rozmiar/2)+((rozmiar+2)/2))/2;
		}else {
			srodek=(rozmiar+1)/2;
		}
		
		return dane[srodek];
		
	}
}
