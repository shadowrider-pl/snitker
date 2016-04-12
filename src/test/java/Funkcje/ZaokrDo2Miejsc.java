package Funkcje;

public class ZaokrDo2Miejsc {
public double zaokraglij(double liczba){
	liczba=Math.round(liczba*100);
	liczba/=100;
	return liczba;
}
}
