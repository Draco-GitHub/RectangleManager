import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    private final RectangleManager rectangleManager;

    CenterPanel(RectangleManager rectangleManager) {
        this.rectangleManager = rectangleManager;
        setBackground(Color.GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (RectangleShape rectangle : rectangleManager.getRectangles()) {
            rectangle.draw(g);
        }
    }
}
