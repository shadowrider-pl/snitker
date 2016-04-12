package Tabele;

import java.util.Random;

public class TablicaLosowana {
	public static int rozmiar_tablicy = 37;
	public static int dane[] =new int[rozmiar_tablicy]; //trzeba zadeklarować tablicę z rozmiarem
//	private static int zakres = 10;

	public void zapelnij_tablice(int zakres){
	for(int i = 0;i<rozmiar_tablicy;i++){
		Random losuj =new Random();
		int wylosowane=losuj.nextInt(zakres);
			dane[i] = wylosowane;
//		System.out.println("Pole "+(i+1) +": "+dane[i]);
	}}
}
