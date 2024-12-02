package Rectangle;

import Rectangle.Panels.BottomPanel;
import Rectangle.Panels.CenterPanel;
import Rectangle.Panels.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainFrame extends JFrame {
    private final RectangleManager RECTANGLE_MANAGER = new RectangleManager();

    TopPanel topPanel = new TopPanel();
    CenterPanel centerPanel = new CenterPanel();
    BottomPanel bottomPanel = new BottomPanel();

    public MainFrame() {
        setTitle("Rectangle Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupTopPanel();
        setupCenterPanel();
        setupBottomPanel();
    }

    private void setupTopPanel() {
        add(topPanel, BorderLayout.NORTH);
    }

    private void setupCenterPanel() {
        add(centerPanel, BorderLayout.CENTER);
    }

    private void setupBottomPanel() {
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addRectangle() {
//        // Read values, validate, create RectangleShape, add to list
        int id = topPanel.getId();
        int width = topPanel.getWidth();
        int height = topPanel.getHeight();
        int x = topPanel.getX();
        int y = topPanel.getY();
        Color color = topPanel.getColor();

        if (RECTANGLE_MANAGER.validateId(id) && RECTANGLE_MANAGER.validateWidth(width)) {
            RectangleShape rectangleShape = new RectangleShape(id, width, height, x, y, color);
            RECTANGLE_MANAGER.addRectangle(rectangleShape);
        }
    }

    private void searchRectangle() {
//        // Find rectangle by ID, display details, draw it

    }

    private void sortRectangles() {
//        // Sort list by ID and display on console
//        ArrayList<RectangleShape> rectangles = rectangleManager.getSortedRectangles();
//        StringBuilder builder = new StringBuilder();
//        for (RectangleShape rectangle : rectangles) {
//            builder.append(rectangle.toString()).append("\n");
//        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

}
