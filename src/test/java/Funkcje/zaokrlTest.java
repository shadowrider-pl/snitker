package Funkcje;

import static java.lang.Math.round;

public class zaokrlTest {

	public static void main(String[] args) {
		double z1=0.76923;
		double z2=(round(z1*100))/100;
		double zaokr=z2/100;
		System.out.println(z1);
		System.out.println(z2);
		System.out.println(zaokr);
	}

}
