package Funkcje;

import java.util.ArrayList;

import Okna.TestSeriiM1JFrame;
import snitker.PierwszeFunkcje;

public class TestSeriiM1 {

    private double mediana;
    private double srednia;
    private double wariancja;
    private ArrayList<String> ciagS = new ArrayList<String>();
    private int n1 = 0;
    private int n2 = 0;
    private int k = 0;
    private String aktualnaSeria = "";
    final double alfapol = 1.96;
    double statZ;
    private String opis;
    private String wynik_testu;

    public TestSeriiM1(double[][] tablica, int kolumna) {
        Mediana obj_mediana = new Mediana();
        mediana = obj_mediana.mediana(tablica, kolumna);

        for (int i = 1; i < tablica.length; i++) {
            if (tablica[i][kolumna] < mediana) {
                ciagS.add("m");
//                                System.out.println("m");
            } else if (tablica[i][kolumna] > mediana) {
                ciagS.add("w");
//                                System.out.println("w");
            }
        }

//		          System.out.println("tablica.length:"+tablica.length);
        for (int i = 0; i < ciagS.size(); i++) {
            if (ciagS.get(i) == "m") {
                n1++;
            } else if (ciagS.get(i) == "w") {
                n2++;
            }

            if (aktualnaSeria != ciagS.get(i)) {
                k++;
                aktualnaSeria = ciagS.get(i);
            }
        }

        srednia = (2 * n1 * n2) / (n1 + n2) + 1;
        wariancja = ((2 * n1 * n2) * (2 * n1 * n2 - (n1 + n2)) / ((n1 + n2 - 1) * (n1 + n2) ^ 2));
//                System.out.println("srednia: "+srednia+", wariancja: "+wariancja);
        statZ = (k - srednia) / wariancja;
        
        if(Math.abs(statZ)>=alfapol){
            wynik_testu="Należy odrzucić hipotezę H0, że próba jest losowa";
        } else{
            wynik_testu="Brak podstaw do odrzucenia hipotezy H0, że próba jest losowa";            
        }

        TestSeriiM1JFrame tsm1jf = new TestSeriiM1JFrame();
//                opis="<html>n1: "+n1+", n2: "+n2+", mediana: "+mediana+", srednia: "+srednia+", wariancja: "+wariancja
//                        + "<br>Statystyka Z="+statZ+"<br>Z alfa pół="+alfapol+"</html>";
        opis = "<html><center><h1>Test serii [Model 1]</h1><br>Bada, czy próba jest losowa.<br>Poziom istotności &#945;=0,05. </center><br><br><br>"
                + "Statystyka Z=" + statZ + "<br>"
                + "Z alfa pół=" + alfapol + "<br><br>Wynik:<br>"
                + wynik_testu+"<br></html>";
        tsm1jf.ustawjLabel1(opis);
        tsm1jf.setVisible(true);
    }

    public double getStatZ() {
        return statZ;
    }
}
