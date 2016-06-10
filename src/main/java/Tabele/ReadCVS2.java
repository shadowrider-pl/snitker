package Tabele;
//http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadCVS2 {

	public String[] country;
	public String[] kraj = new String[1];

	public String[] pusty = { "pusty" };

	public static void main(String[] args) {

		ReadCVS2 obj = new ReadCVS2();
		obj.run();

	}

	public void run() {

		String csvFile = "/home/norbert/java/snitker/src/test/java/Tabele/GeoIPCountryWhois.cvs";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);
				// System.out.println(country.length);
				System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
				kraj[0] = country[5];
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

	public String[] getCountry() {
		if (kraj != null) {
			return kraj;
		} else {
			return pusty;
		}
	}
}
