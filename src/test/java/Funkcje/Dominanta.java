package Funkcje;

import java.util.Arrays;

public class Dominanta {
	public double[] dominanta(double[][] dane, int kolumna) {

		int rozmiar = dane.length;

		double wartosc[] = new double[rozmiar];
		int czestosc[] = new int[rozmiar];
		int index_dominanty[];
		double liczebnosc_dominanty = 0;
		double dominanta[];
		double rekord = 0;
		int maxJ = 0;
		int moda = 0;
		int id = 0;

		for (int i = 0; i < rozmiar; i++) {
			rekord = dane[i][kolumna];
			for (int j = 0; j <= i; j++) {
				if (j >= maxJ) {
					wartosc[j] = rekord;
					czestosc[j] = 1;
					maxJ++;
				} else if (wartosc[j] == rekord) {
					czestosc[j]++;
				}
			}
		}
		for (int i = 0; i < rozmiar; i++) {
			if (czestosc[i] > liczebnosc_dominanty) {
				liczebnosc_dominanty = czestosc[i];
				moda = 1;
			} else if (czestosc[i] == liczebnosc_dominanty) {
				moda++;
			}
		}
		
		dominanta = new double[moda];
		index_dominanty= new int[moda];
		for (int i = 0; i < rozmiar; i++) { //szuka kolejnych dominant
			if (czestosc[i] == liczebnosc_dominanty) {
			index_dominanty[id] = i;
			dominanta[id]=wartosc[index_dominanty[id]];
			id++;
		}}
		
		Arrays.sort(dominanta);
		return dominanta;

	}
}
