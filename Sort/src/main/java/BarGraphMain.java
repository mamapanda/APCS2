import javax.swing.JFrame;

public class BarGraphMain {
    public static void main(String[] args) {
        int barCount = 50;
        int frameW = 1000 + 16;
        int frameH =  700 + 38;

        SortBarGraph thing = new SortBarGraph(barCount, frameW, frameH);

        JFrame frame = new JFrame();
        frame.setSize(frameW, frameH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(thing);
        frame.setVisible(true);
    }
}
