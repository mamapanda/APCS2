import java.awt.*;
import java.util.Random;
import javax.swing.JComponent;
/**
 * @author Daniel Phan
 */
public class SortBarGraph extends JComponent {
    public static final int delayms = 500;

    public SortBarGraph(int barCount, int frameW, int frameH) {
        Random random = new Random();
        values_ = new int[barCount];
        for (int i = 0; i < barCount; i++) {
            values_[i] = random.nextInt(MAX_VALUE - 4) + 5;
        }
        frameW_ = frameW;
        frameH_ = frameH;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle[] recs = createRectangles();
        for (int i = 0; i < recs.length; i++) {
            g2.setColor(Color.CYAN);
            g2.fill(recs[i]);
            g2.setColor(Color.BLACK);
            g2.draw(recs[i]);
        }
    }

    private int frameW_;
    private int frameH_;
    private int[] values_;
    private static final int MAX_VALUE = 100;

    private Rectangle[] createRectangles() {
        Rectangle[] recs = new Rectangle[values_.length];
        int barW = (int)Math.round(frameW_ * 1.0 / values_.length);
        double scale = frameH_ * 1.0 / MAX_VALUE;
        for(int i = 0; i < values_.length; i++) {
            int barH = (int)(scale * values_[i]);
            recs[i] = new Rectangle(barW * i, frameH_ - barH, barW, barH);
        }
        return recs;
    }

    public void insertionSort() {
        for (int i = 1; i < values_.length; i++) {
            int insert = values_[i];
            int insertPoint = i;
            while (insertPoint > 0 && values_[insertPoint - 1] > insert) {
                values_[insertPoint] = values_[insertPoint - 1];
                insertPoint--;
            }
            values_[insertPoint] = insert;
        }
    }
}
