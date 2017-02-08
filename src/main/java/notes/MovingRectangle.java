package notes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingRectangle extends JComponent{
    public MovingRectangle() {
        rec_ = new Rectangle(0, 100, 100, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.draw(rec_);
    }

    public void moveBy(int x, int y) {
        rec_.translate(x, y);
        repaint(); //calls paintComponent
    }

    private Rectangle rec_;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        MovingRectangle r = new MovingRectangle();
        frame.add(r);
        frame.setVisible(true);

        class TimerListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                r.moveBy(100, 0);
            }
        }
        ActionListener listener = new TimerListener();
        Timer t = new Timer(500, listener); //delay by 500ms
        t.start();
    }
}
