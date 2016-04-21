package Funkcje;

public class Dominanta {
	public double dominanta(double[] dane) {

		int rozmiar = dane.length;

		double wartosc[] = new double[rozmiar];
		int czestosc[] = new int[rozmiar];
		int index_dominanty = 0;
		double wartosc_dominanty = 0;
		double rekord = 0;
		int maxJ = 0;

		for (int i = 0; i < rozmiar; i++) {
			rekord = dane[i];
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
			if (wartosc_dominanty < czestosc[i]) {
				index_dominanty = i;
				wartosc_dominanty = czestosc[i];
//				System.out.println("wartosc_dominanty: " + wartosc_dominanty);
			}}
		return wartosc[index_dominanty];

	}
}
