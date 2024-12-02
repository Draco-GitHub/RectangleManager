package Rectangle;

import Rectangle.Panels.BottomPanel;
import Rectangle.Panels.CenterPanel;
import Rectangle.Panels.ConsolePanel;
import Rectangle.Panels.TopPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainFrame extends JFrame {
    public static final RectangleManager RECTANGLE_MANAGER = new RectangleManager();

    public static TopPanel topPanel = new TopPanel();
    public static CenterPanel centerPanel = new CenterPanel();
    public static ConsolePanel consolePanel = new ConsolePanel();
    public static BottomPanel bottomPanel = new BottomPanel();

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
        add(consolePanel, BorderLayout.EAST);
    }

    private void setupBottomPanel() {
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

}
