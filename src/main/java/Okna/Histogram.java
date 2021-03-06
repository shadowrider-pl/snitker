package Okna;

import snitker.PierwszeFunkcje;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

import Funkcje.ZaokrDo2Miejsc;

import static java.lang.Math.*;

public class Histogram extends javax.swing.JFrame {

	private double dominanta = PierwszeFunkcje.getDominanta()[0];
	private double minimum = PierwszeFunkcje.getMinimum();
	private double maximum = PierwszeFunkcje.getMaximum();
	private int liczba_obserwacji = PierwszeFunkcje.getLiczba_obserwacji();
	private double tablica[] = PierwszeFunkcje.getTablica();
	// private int l_przedzialow = (int) round(sqrt(liczba_obserwacji));
	private double zakres = maximum - minimum;
	// private double szer_przedz = zakres / l_przedzialow;
	private int przedzialy[] = new int[(int) zakres];
	private double wartosc_dominanty = 0;
	// private ArrayList<Rectangle2D> arrHist = new
	// ArrayList<Rectangle2D>();

	JFrame frame = new Rysuj();

	public Histogram() {
		initComponents();
	}

	private void initComponents() {

		for (int i = 0; i < tablica.length; i++) {
			for (int j = 0; j < zakres; j++) {
				if (tablica[i] >= (minimum + j) && tablica[i] < (minimum + (j + 1))) {
					przedzialy[j]++;
					break;
				}

				// gdy maximum nie wchodzi w granice ostatniego przedziału
				if (j == (zakres - 1) && tablica[i] >= (minimum + (j + 1))) {
					przedzialy[j]++;
				}
			}
		}

		// dominanta przedziałowa
		for (int i = 0; i < przedzialy.length; i++) {
			if (wartosc_dominanty < przedzialy[i]) {
				wartosc_dominanty = przedzialy[i];
			}
		}

		frame.setTitle("Histogram");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(false);
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Histogram();
			}
		});

	}

	class Rysuj extends JFrame {
		public Rysuj() {
			add(new HistogramComponent());
			pack();
			setBounds(300, 300, 1100, 386);
		}
	}

	/**
	 * Komponent wywietlajšcy prostokąty i elipsy.
	 */
	class HistogramComponent extends JComponent {

		public void paintComponent(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;

			// Rysowanie prostokšta.

			double leftX = 70;
			double topY = 20;
			double width = 1000;
			double height = 280;

			double pX = 0;
			double pY = 0;
			double wys = 0;
			double szer = width / zakres;

			ZaokrDo2Miejsc zaokr = new ZaokrDo2Miejsc();

			Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
			g2.draw(rect);

			for (int i = 0; i < przedzialy.length; i++) {
				pX = leftX + i * szer;
				pY = topY + height - ((height * przedzialy[i]) / wartosc_dominanty);
				wys = ((height * przedzialy[i]) / wartosc_dominanty);
				// arrHist.add(new Rectangle2D.Double(pX, pY, szer, wys));
				g2.setPaint(Color.RED);
				g2.fill((new Rectangle2D.Double(pX, pY, szer, wys)));
				g2.setPaint(Color.BLACK);
				g2.draw((new Rectangle2D.Double(pX, pY, szer, wys)));
				g2.draw(new Line2D.Double(pX, height + 30, pX, height + 50));
				g2.drawString(Double.toString(zaokr.zaokraglij(minimum + i)), (int) round(pX),
						(int) round(height + 60));
			}

			g2.draw(new Line2D.Double(pX + szer, height + 30, pX + szer, height + 50));

			// Oś y
			double xOsiy = leftX - 15;
			double j = 0.25;
			g2.draw(new Line2D.Double(xOsiy, topY, xOsiy, topY + height));
			g2.draw(new Line2D.Double(xOsiy - 10, topY, xOsiy + 10, topY));
			g2.drawString(Double.toString(zaokr.zaokraglij(wartosc_dominanty)), (int) round(xOsiy - 45), (int) topY);

			for (int i = 1; i <= 4; i++) {
				g2.draw(new Line2D.Double(xOsiy - 10, (topY + j * height), xOsiy + 10, (topY + j * height)));
				g2.drawString(Double.toString(zaokr.zaokraglij((wartosc_dominanty * (4 - i) / 4))),
						(int) round(xOsiy - 45), (int) (topY + j * height));
				j = j + 0.25;
			}

			// Oś X
			g2.draw(new Line2D.Double(leftX, height + 40, leftX + width, height + 40));
//			g2.drawString(
//					"Liczba minimum:" + minimum + ", maximum: " + maximum + ", liczba_obserwacji: " + liczba_obserwacji
//							+ ", dominanta: " + dominanta + ", tablica.length: " + tablica.length + ", ",
//					(int) leftX, (int) (height + 100));
//			g2.drawString("przedzialy.length: " + przedzialy.length + ", pY: " + pY, (int) leftX,
//					(int) (height + 100 + 30));

		}

	}
}
