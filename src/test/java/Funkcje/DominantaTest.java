package Funkcje;

public class DominantaTest {

	public static void main(String[] args) {
		double dane[] = { 210, 90, 90, 90, 90, 275, 155, 155, 109, 109, 109, 109, 305 };
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
		for (double xxx : dane) {
			System.out.println(xxx);
		}

		System.out.println("czestosc ============+");
		for (double xxx : czestosc) {
			System.out.println(xxx);
		}

		System.out.println("wartosc ============+");
		for (double xxx : wartosc) {
			System.out.println(xxx);
		}

		for (int i = 0; i < rozmiar; i++) {
			if (wartosc_dominanty < czestosc[i]) {
				index_dominanty = i;
				wartosc_dominanty = czestosc[i];
				System.out.println("wartosc_dominanty: " + wartosc_dominanty);
			} else {
				System.out.println("dupa");

			}

		}
		System.out.println("dominanta=" + wartosc[index_dominanty] + ", wartość dominanty=" + wartosc_dominanty);

	}

}
