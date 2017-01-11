import javax.swing.*;
import java.awt.*;
/**
 *
 *
 * @author Daniel Phan
 *
 */
public class BarMSortMain {
    public static void main(String[] args) {
        int barCount = 50;

        JFrame frame = new JFrame();
        frame.setTitle("Merge Sort");
        frame.getContentPane().setPreferredSize(new Dimension(1000, 700));
        frame.getContentPane().setBackground(Color.BLACK);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BarMergeSorter thing = new BarMergeSorter(barCount, frame.getContentPane().getSize());
        frame.setVisible(true);
        frame.add(thing);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thing.run();
    }
}
