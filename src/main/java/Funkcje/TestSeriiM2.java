package Funkcje;

import java.util.ArrayList;

import Okna.TestSeriiM2JFrame;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import snitker.PierwszeFunkcje;

public class TestSeriiM2 {

    private double srednia;
    private double wariancja;
    private List<ArrayList<String>> ciagV = new ArrayList<ArrayList<String>>();

    private int intn1 = 0;
    private int intn2 = 0;
    private double n1;
    private double n2;
    private int k = 0;
    final double alfapol = 1.96;
    double statZ;
    private String opis;
    private String wynik_testu;
    private String aktualnaSeria = "";

    public TestSeriiM2(double[][] tablica) {

        for (int j = 0; j < 2; j++) {
            for (int i = 1; i < tablica.length; i++) {
                ArrayList<String> wiersz = new ArrayList<String>();
                wiersz.add(String.valueOf(tablica[i][j]));
                wiersz.add(String.valueOf(j));

                ciagV.add(wiersz);

            }
        }

        // sortowanie
        Collections.sort(ciagV, new Comparator<ArrayList<String>>() {
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o1.get(0).compareTo(o2.get(0));
            }
        });
        
        for (int i = 0; i < ciagV.size(); i++) {
            ArrayList<String> wiersz = new ArrayList<String>();
            wiersz=ciagV.get(i);
            
            if (wiersz.get(1).equals("0")) {
            	intn1++;
            } else if (wiersz.get(1).equals("1")) {
            	intn2++;
            }
          
            if (!wiersz.get(1).equals(aktualnaSeria)) {
                k++;
                aktualnaSeria = wiersz.get(1);
            }
        }

        n1=(double) intn1;
        n2=(double) intn2;
//        System.out.println("k="+k);
        srednia = (2 * n1 * n2) / (n1 + n2) + 1;
        wariancja = (2 * n1 * n2)*((2 * n1 * n2) - (n1 + n2)) / ((n1 + n2 - 1) * Math.pow((n1 + n2), 2));
    
        
        statZ = (k - srednia) / wariancja;

        if (Math.abs(statZ) >= alfapol) {
            wynik_testu = "Należy odrzucić hipotezę H0, że dwie populacje się różnią";
        } else {
            wynik_testu = "Brak podstaw do odrzucenia hipotezy H0, <br>że dwie populacje się różnią";
        }

        TestSeriiM2JFrame tsm2jf = new TestSeriiM2JFrame();
//                opis="<html>n1: "+n1+", n2: "+n2+", mediana: "+mediana+", srednia: "+srednia+", wariancja: "+wariancja
//                        + "<br>Statystyka Z="+statZ+"<br>Z alfa pół="+alfapol+"</html>";
        opis = "<html><center><h1>Test serii [Model 2]</h1><br>Bada, czy dwie populacje się różnią.<br>"
                + "Poziom istotności &#945;=0,05.</center><br><br><br>"
                + "Statystyka Z=" + statZ + "<br>"
                + "Z &#945;/2=" + alfapol + "<br><br>Wynik:<br>"
                + wynik_testu + "<br></html>";
        tsm2jf.ustawjLabel1(opis);
        tsm2jf.setVisible(true);
    }

    public double getStatZ() {
        return statZ;
    }

}
