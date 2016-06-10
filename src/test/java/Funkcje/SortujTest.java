package Funkcje;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SortujTest {

//	private class wierszTestu {
//		private double modul;
//		private String znak;
//		private int ranga;
//		
//		private wierszTestu(double modul, String znak, int ranga){
//			this.modul=modul;
//			this.znak=znak;
//			this.ranga=ranga;
//		}
//		
//		private void setModul(double modul) {
//			this.modul = modul;
//		}
//		private void setZnak(String znak) {
//			this.znak = znak;
//		}
//		private void setRanga(int ranga) {
//			this.ranga = ranga;
//		}
//	}
	
	
	public static void main(String[] args) {
		
		ArrayList<String> x1= new ArrayList<String>();
		x1.add("-13.0");
		x1.add("+");
		x1.add("5");
		
		ArrayList<String> x2= new ArrayList<String>();
		x2.add("2.0");
		x2.add("-");
		x2.add("3");
		
		ArrayList<String> x3= new ArrayList<String>();
		x3.add("7.0");
		x3.add("-");
		x3.add("2");
		
		ArrayList<ArrayList<String>> namesAndNumbers = new ArrayList<ArrayList<String>>();
//		namesAndNumbers.add(new wierszTestu(-13.0, "+", 5));
//		namesAndNumbers.add(new ArrayList<String>());
//		namesAndNumbers.add(new ArrayList<String>(Arrays.asList("-13.0", "+", "5")));
//		namesAndNumbers.add(new ArrayList<String>(Arrays.asList("2.0", "-", "3")));
//		namesAndNumbers.add(new ArrayList<String>(Arrays.asList("7.0", "-", "2")));
		namesAndNumbers.add(x1);
		namesAndNumbers.add(x2);
		namesAndNumbers.add(x3);
		
		Collections.sort(namesAndNumbers, new Comparator<ArrayList<String>>() {    
		        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		            return o1.get(0).compareTo(o2.get(0));
		        }               
		});
		System.out.println(namesAndNumbers);
	}

}
