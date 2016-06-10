package Tabele;

import java.util.Scanner;

public class Zakres {
	public int ustaw_zakres() {
		System.out.println("Podaj zakres liczb do losowania");
		Scanner pobierz_zakres = new Scanner(System.in);
		int zakres=pobierz_zakres.nextInt();
		return zakres;
	}
}
