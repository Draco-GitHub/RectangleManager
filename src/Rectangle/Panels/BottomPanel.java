package Rectangle.Panels;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {

    JButton addRectangle = new JButton("Add Rectangle");
    JButton searchRectangle = new JButton("Search Rectangle");
    JButton deleteAllRectangles = new JButton("Delete All Rectangles");
    JButton updateRectangle = new JButton("Update Rectangle");

    public BottomPanel() {
        setLayout(new FlowLayout());
        setBackground(Color.BLACK);

        addComponents();
    }

    private void addComponents() {
        add(addRectangle);
        add(searchRectangle);
        add(deleteAllRectangles);
        add(updateRectangle);
    }
}
