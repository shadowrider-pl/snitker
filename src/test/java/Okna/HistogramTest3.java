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

public class HistogramTest3 extends javax.swing.JFrame {

	private double minimum = 1;
	private double maximum = 9;
	private double tablica[] = { 6, 5, 3, 4, 4, 2, 2, 2, 1, 1, 1, 1, 1, 6, 6, 7, 7, 7, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9 };
	private int liczba_obserwacji = tablica.length;

	public HistogramTest3() {
		initComponents();
	}

	private void initComponents() {
		JFrame frame = new Rysuj3();
		frame.setTitle("Histogram");
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new HistogramTest4();
			}
		});

	}

	class Rysuj3 extends JFrame {
		public Rysuj3() {
			add(new HistogramComponent3());
			pack();
			setBounds(200, 200, 1100, 386);
		}
	}

	/**
	 * Komponent wywietlajšcy prostokąty i elipsy.
	 */
	class HistogramComponent3 extends JComponent {

		private int l_przedzialow = (int) round(sqrt(liczba_obserwacji));
		private double zakres = maximum - minimum;
		private double szer_przedz = zakres / l_przedzialow;
		private int przedzialy[] = new int[l_przedzialow];
		private double wartosc_dominanty = 0;
		private ArrayList<Rectangle2D> arrHist = new ArrayList<Rectangle2D>();

		public void paintComponent(Graphics g) {

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

			Graphics2D g2 = (Graphics2D) g;

			// Rysowanie prostokšta.

			double leftX = 30;
			double topY = 10;
			double width = 1000;
			double height = 280;

			double pX = 0;
			double pY = 0;
			double wys = 0;
			double szer = width / l_przedzialow;

			Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);

			g2.draw(rect);

			// Rectangle2D rect2 = new Rectangle2D.Double(leftX + 20, topY + 20,
			// width - 40, height - 30);
			// g2.draw(rect2);

			for (int i = 0; i < przedzialy.length; i++) {
				pX = leftX + i * szer;
				pY = topY + height - ((height * przedzialy[i]) / wartosc_dominanty);
				wys = ((height * przedzialy[i]) / wartosc_dominanty);
				arrHist.add(new Rectangle2D.Double(pX, pY, szer, wys));

				g2.setPaint(Color.RED);
				g2.fill((new Rectangle2D.Double(pX, pY, szer, wys)));
				g2.setPaint(Color.BLACK);
				g2.draw((new Rectangle2D.Double(pX, pY, szer, wys)));
				
			}

			// Oś X
			g2.draw(new Line2D.Double(leftX, height + 20, leftX + width, height + 20));

		}

	}
}
