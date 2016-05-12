package Funkcje;

import static java.lang.Math.*;

public class Kurtoza {
	public double kurtoza(double srednia,double wariancja, double[][] dane, int kolumna) {

		int n = dane.length;
		double sigma=0;
		double kurtoza=0;
		for (int i = 0; i < n; i++) {
			sigma += pow((dane[i][kolumna] - srednia)/sqrt(wariancja), 4);
		}
		kurtoza=(n*(n+1))/((n-1)*(n-2)*(n-3))*sigma-((3*pow((n-1),2))/((n-2)*(n-3)));
		return kurtoza;
	}
}
