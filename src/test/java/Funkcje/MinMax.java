package Funkcje;

public class MinMax {
	private double minimum;
	private double maximum;
	private int liczba_obserwacji;
	private double[] tablica=new double[liczba_obserwacji];

	public void minmax(double[] dane) {

		liczba_obserwacji = dane.length;
		minimum = dane[0];
		maximum = dane[0];

		for (int i = 0; i < liczba_obserwacji; i++) {
			if (dane[i] > maximum) {
				maximum = dane[i];
			} else if (dane[i] < minimum) {
				minimum = dane[i];
			}
		}
	}

	public double getMinimum() {
		return minimum;
	}

	public double getMaximum() {
		return maximum;
	}

	public int getLiczba_obserwacji() {
		return liczba_obserwacji;
	}

	public double[] getTablica() {
		return tablica;
	}

}
