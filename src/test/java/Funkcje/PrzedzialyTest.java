package Funkcje;

import static java.lang.Math.round;
import static java.lang.Math.sqrt;

public class PrzedzialyTest {
private static double tablica[]={6,5,3,4,4,2,2,2,1,1,1,6,6,7,7,7,9};

private static int l_przedzialow = (int) round(sqrt(tablica.length));
private static double zakres = 9-1;
private static double min = 1;
private static double szer_przedz = zakres / l_przedzialow;

private static int przedzialy[] = new int[l_przedzialow];

	public static void main(String[] args) {

		for (int i = 0; i < tablica.length; i++) {
			System.out.println("iteracja: "+i);
			System.out.println("-------------------------------");
			System.out.println("-------------------------------");
			for(int j=0;j< przedzialy.length; j++){
				System.out.println(j);
				System.out.println(tablica[i]);
				System.out.println("-------------------------------");
				System.out.println("-------------------------------");
				System.out.println("j*szer_przedz: "+j*szer_przedz+", (j+1)*szer_przedz: "+(j+1)*szer_przedz);
				if(tablica[i]>min+j*szer_przedz  && tablica[i]<=min+(j+1)*szer_przedz){
					przedzialy[j]++;
					System.out.println("wstaw: "+tablica[i]);
					System.out.println("przedzial: "+j);
					System.out.println("-------------------------------");
					break;
				}
			}
		}

System.out.println("l_przedzialow: "+l_przedzialow);
System.out.println("-------------------------------");
System.out.println("szer_przedz: "+szer_przedz);
System.out.println("-------------------------------");
for(int x:przedzialy){
	System.out.println(x);
}
	}

}
