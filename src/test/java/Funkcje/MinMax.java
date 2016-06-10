package Funkcje;

public class MinMax {
	private double minimum;
	private double maximum;
	private int liczba_obserwacji;
//	private double[] tablica=new double[liczba_obserwacji];

	public void minmax(double[][] dane, int kolumna) {

		liczba_obserwacji = dane.length;
		minimum = dane[1][kolumna]; // w pierwszym wierszu jest tytuł
		maximum = dane[1][kolumna];
//		System.out.println("minimum start: "+minimum);
//		System.out.println("maximum start: "+maximum);

		for (int i = 1; i < liczba_obserwacji; i++) {
			if (dane[i][kolumna] > maximum) {
				maximum = dane[i][kolumna];
//				System.out.println("maximum: "+maximum);
			} else if (dane[i][kolumna] < minimum) {
				minimum = dane[i][kolumna];
//				System.out.println("minimum: "+minimum);
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
		return liczba_obserwacji-1;
	}

//	public double[] getTablica() {
//		return tablica;
//	}

}
