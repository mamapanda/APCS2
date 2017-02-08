package sort;

import javax.swing.*;
import java.awt.*;

/**
 * Main class to run the application.
 *
 * @author Daniel Phan
 * @version 1.8.16
 */
public class BarGraphMain {
    public static void main(String[] args) {
        int barCount = 40;

        JFrame frame = new JFrame();
        frame.setTitle("Insertion Sort");
        frame.getContentPane().setPreferredSize(new Dimension(1000, 700));
        frame.getContentPane().setBackground(Color.BLACK);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BarGraphSorter thing = new BarGraphSorter(barCount, frame.getContentPane().getSize());
        frame.setVisible(true);
        frame.add(thing);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thing.insertionSort();
    }
}
