/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Okna;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author norbert
 */

public class WybieraczPlikow {

	public String nazwa_pliku = "";
	public String nazwa_pliku_bez_sciezki = "";

	public void WybieraczPlikow(Okno parent) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter domyslny=new FileNameExtensionFilter("Pliki CSV", "csv");
//		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Pliki CSV", "csv"));
//		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
//		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MS Office", "xlsx"));
//		fileChooser.setAcceptAllFileFilterUsed(true);
		fileChooser.setFileFilter(domyslny);
		int result = fileChooser.showOpenDialog(parent);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			nazwa_pliku = selectedFile.getAbsolutePath();
			nazwa_pliku_bez_sciezki = selectedFile.getName();
		}
	}

	public String getNazwa_pliku_bez_sciezki() {
		return nazwa_pliku_bez_sciezki;
	}

	public String getNazwa_pliku() {
		return nazwa_pliku;
	}

}
