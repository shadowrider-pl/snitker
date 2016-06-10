package Funkcje;

import java.util.Arrays;


public class Mediana {

	int rozmiar;
	public double[] kol=new double[rozmiar];

	public double mediana (double [][] dane,int kolumna) {
		
		boolean parzysta;
		int srodek;
		rozmiar = dane.length;
		kol=new double[rozmiar];
		
			if(rozmiar%2==0) {
				parzysta=true;
			} else {
				parzysta=false;
			}
			
			for(int i=0;i<rozmiar;i++){
				kol[i]=dane[i][kolumna];
			}
		Arrays.sort(kol);
		if(parzysta){
			srodek=((rozmiar/2)+((rozmiar+2)/2))/2;
		}else {
			srodek=(rozmiar+1)/2;
		}
		
		return dane[srodek][kolumna];
		
	}
	
	public double[] getKol() {
		return kol;
	}
}
