package Rectangle.Panels;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import Rectangle.*;

import static Rectangle.MainFrame.RECTANGLE_MANAGER;

public class BottomPanel extends JPanel {

    JButton addRectangle = new JButton("Add Rectangle");
    JButton searchRectangle = new JButton("Search Rectangle");
    JButton displaySorted = new JButton("Display All (Sorted)");
    JButton updateRectangle = new JButton("Update Rectangle");

    public BottomPanel() {
        setLayout(new FlowLayout());
        setBackground(Color.LIGHT_GRAY);

        addRectangle.addActionListener(e -> addRectangle());
        searchRectangle.addActionListener(e -> searchRectangle());
        displaySorted.addActionListener(e -> displaySorted());
        updateRectangle.addActionListener(e -> updateRectangle());

        addComponents();
    }

    private void addRectangle() {

    }

    private void searchRectangle() {
        MainFrame.consolePanel.setText("test");
    }

    private void displaySorted() {
        ArrayList<RectangleShape> rectangles = RECTANGLE_MANAGER.getSortedRectangles();
        StringBuilder builder = new StringBuilder();
        for (RectangleShape rectangle : rectangles) {
            builder.append(rectangle.toString()).append("\n");
        }
        MainFrame.consolePanel.setText(builder.toString());
    }

    private void updateRectangle() {

        if (RECTANGLE_MANAGER.updateRectangle()) {

        }

    }

    private void addComponents() {
        add(addRectangle);
        add(searchRectangle);
        add(displaySorted);
        add(updateRectangle);
    }
}
