package Tabele;

import java.util.ArrayList;
import java.util.Arrays;

public class TablicaTest2 {

	public static void main(String[] args) {
		String commaSeparated = "item1 , item2 , item3";
		ArrayList<String> items = 
		new  ArrayList<String>(Arrays.asList(commaSeparated.split(",")));
for(String xxx:items){
	System.out.println(xxx);
}
	}

}
