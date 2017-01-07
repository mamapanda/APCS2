import java.awt.*;
import java.util.Random;
import javax.swing.*;

/**
 * Creates a bar graph based on random values and sorts it
 * with the insertion sort.
 *
 * @author Daniel Phan
 * @version 1.8.16
 */
public class BarGraphSorter extends JComponent {
    private int[] values_; //the array of random values to sort
    private int insertPoint_; //the current location of the value to be inserted
    private boolean isSorted_; //whether or not the values are sorted or not
    private final Dimension frameDims_; //the dimensions of the JFrame's content pane
    private static final int MAX_VALUE = 100; //the upper bound of the random values
    private static final int DRAW_DELAY = 50; //the drawing delay in ms

    /**
     * Creates a new BarGraphSorter with the given number of values and the frame dimensions
     * (Postcondition: values_, frameDims_, and isSorted_ are initialized. Values contains random generated numbers.)
     * @param barCount the number of bars in the bar graph
     * @param frameDims the dimensions of the JFrame's content pane
     * (Precondition: barCount is positive and frameDims is nonnull.)
     */
    public BarGraphSorter(int barCount, Dimension frameDims) {
        Random random = new Random();
        values_ = new int[barCount];
        for (int i = 0; i < barCount; i++) {
            values_[i] = random.nextInt(MAX_VALUE - 5) + 5;
        }
        frameDims_ = frameDims;
        isSorted_ = false;
    }

    /**
     * Sorts values_ and repaints the bar graph.
     * (Postcondition: values_ is sorted.)
     * (Precondition: values_ is initialized.)
     */
    public void insertionSort() {
        for (int i = 1; i < values_.length; i++) {
            int insert = values_[i];
            insertPoint_ = i;
            while (insertPoint_ > 0 && values_[insertPoint_ - 1] > insert) {
                repaint();
                try {
                    Thread.sleep(DRAW_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int temp = values_[insertPoint_];
                values_[insertPoint_] = values_[insertPoint_ - 1];
                values_[--insertPoint_ ] = temp;
                repaint();
                try {
                    Thread.sleep(DRAW_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        isSorted_ = true;
        repaint();
    }

    /**
     * Paints the bar graph based on values_
     * (Postcondition: The bar graph is drawn on the JFrame.)
     * @param g the graphical utility to draw on the JFrame
     * (Precondition: isSorted_ and insertPoint_ are initialized.)
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle[] recs = createRectangles();
        for (int i = 0; i < recs.length; i++) {
            g2.setColor(isSorted_ ? Color.GREEN
                    : i == insertPoint_ ? Color.RED
                    : new Color(148, 0, 211));
            g2.fill(recs[i]);
            g2.setColor(Color.WHITE);
            g2.draw(recs[i]);
        }
    }

    /**
     * Creates an array of rectangles based on values_;
     * (Postcondition: An array of rectangles based on values_ is returned.)
     * @return an array of rectangles based on values_
     * (Precondition: values_ is initialized and contains only positive numbers.)
     */
    private Rectangle[] createRectangles() {
        Rectangle[] recs = new Rectangle[values_.length];
        int barW = (int) Math.round(frameDims_.height * 1.0 / values_.length);
        double scale = frameDims_.width * 1.0 / MAX_VALUE;
        for (int i = 0; i < values_.length; i++) {
            int barL = (int) (scale * values_[i]);
            recs[i] = new Rectangle(0, barW * i, barL, barW);
        }
        return recs;
    }
}
