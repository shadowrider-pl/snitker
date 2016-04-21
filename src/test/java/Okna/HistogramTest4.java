package Okna;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

import static java.lang.Math.*;

public class HistogramTest4 extends javax.swing.JFrame {

	private double minimum = 1;
	private double maximum = 9;
	private double tablica[] = { 6, 5, 3, 4, 4, 2, 2, 2, 1, 1, 1, 1, 1, 6, 6, 7, 7, 7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 };
	private int liczba_obserwacji = tablica.length;

	private int l_przedzialow = (int) round(sqrt(liczba_obserwacji));
	private double zakres = maximum - minimum;
	private double szer_przedz = zakres / l_przedzialow;
	private int przedzialy[] = new int[l_przedzialow];
	private double wartosc_dominanty = 0;
	

	private double leftX = 30;
	private double topY = 10;
	private double width = 1000;
	private double height = 280;

	private double pX = 0;
	private double pY = 0;
	private double wys = 0;
	private double szer = width / l_przedzialow;

	public HistogramTest4() {
		initComponents();
	}

	private void initComponents() {
		JFrame frame = new Rysuj4();
		frame.setTitle("Histogram");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);


		for (int i = 0; i < tablica.length; i++) {
			for (int j = 0; j < przedzialy.length; j++) {
				if (tablica[i] > minimum + j * szer_przedz && tablica[i] <= minimum + (j + 1) * szer_przedz) {
					przedzialy[j]++;
					break;
				}
			}
		}
		// dominanta przedzialowa
		for (int i = 0; i < przedzialy.length; i++) {
			if (wartosc_dominanty < przedzialy[i]) {
				wartosc_dominanty = przedzialy[i];
			}
		}
	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HistogramTest4();
			}
		});

	}

	class Rysuj4 extends JFrame {
		public Rysuj4() {
			add(new HistogramComponent4());
			pack();
			setBounds(200, 200, 1100, 486);
		}
	}

	/**
	 * Komponent wywietlajšcy prostokąty i elipsy.
	 */
	class HistogramComponent4 extends JComponent {

//		private ArrayList<Rectangle2D> arrHist = new ArrayList<Rectangle2D>();

		public void paintComponent(Graphics g) {


			Graphics2D g2 = (Graphics2D) g;

			// Rysowanie prostokšta.


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
				g2.drawString(Double.toString(round(minimum + i * szer_przedz)), (int) round(pX), (int) round(height + 60));
			}
			g2.draw(new Line2D.Double(pX + szer, height + 30, pX + szer, height + 50));
			// Oś X
			g2.draw(new Line2D.Double(leftX, height + 40, leftX + width, height + 40));
			// g2.draw(new Line2D.Double(leftX, height + 40, leftX + width,
			// height + 40));
			// g2.drawString("Etykieta", leftX, height + 40);
			g2.drawString(
					"Liczba minimum:" + minimum + ", maximum: " + maximum + ", liczba_obserwacji: " + liczba_obserwacji
							+ ", l_przedzialow: " + l_przedzialow + ", wartosc_dominanty: " + wartosc_dominanty
							+ ", tablica.length: " + tablica.length+ ", szer: " + szer,
					(int) leftX, (int) (height + 100));

		}

	}
}
