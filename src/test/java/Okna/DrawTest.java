package Okna;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * @version 1.32 2007-04-14
 * @author Cay Horstmann
 */
public class DrawTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new DrawFrame();
               frame.setTitle("DrawTest");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            } 
         });
   }
}

/**
 * Ramka zawierajšca panel z rysunkami.
 */
class DrawFrame extends JFrame
{
   public DrawFrame()
   {
      add(new DrawComponent());
      pack();
   }
}

/**
 * Komponent wywietlajšcy prostokšty i elipsy.
 */
class DrawComponent extends JComponent
{
   private static final int DEFAULT_WIDTH = 400;
   private static final int DEFAULT_HEIGHT = 400;

   @Override
   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;

      // Rysowanie prostokšta.

      double leftX = 100;
      double topY = 100;
      double width = 200;
      double height = 150;

      Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
      g2.draw(rect);


		g2.draw(new Line2D.Double(leftX, height + 20, leftX + width, height + 20));

		g2.draw(new Line2D.Double(leftX, height + 30, leftX + width, height + 30));
   }
   @Override
   public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}