package Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainFrame extends JFrame {
    private RectangleManager rectangleManager = new RectangleManager();
    private final JPanel topPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    
    public MainFrame() {
        setTitle("Rectangle Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set up panels for inputs, display, and buttons
        setupTopPanel();
        setupCenterPanel();
        setupBottomPanel();
    }


    private String currentId;
    private String currentWidth;
    private String currentHeight;
    private String currentY;
    private String currentX;
    private String currentColor;

    private HashMap<String, Color> colors = new HashMap<>();


    private void setupTopPanel() {
        // Create input fields for ID, width, height, xPos, yPos, and color
        // Add input fields to the top panel
        topPanel.setLayout(new GridLayout(3, 4));
        JLabel idLabel = new JLabel("ID (Starts with 239)");
        JLabel widthLabel = new JLabel("Width (Even Only)");
        JLabel heightLabel = new JLabel("Height");
        JLabel xLabel = new JLabel("X Position");
        JLabel yLabel = new JLabel("Y Position");
        JLabel colorLabel = new JLabel("Colour");

        JTextField idField = new JTextField(10);
        JTextField widthField = new JTextField(10);
        JTextField heightField = new JTextField(10);
        JTextField xField = new JTextField(10);
        JTextField yField = new JTextField(10);

        colors.put("Red", Color.RED);
        colors.put("Orange", Color.ORANGE);
        colors.put("Yellow", Color.YELLOW);
        colors.put("Green", Color.GREEN);
        colors.put("Blue", Color.BLUE);
        colors.put("Violet", Color.MAGENTA);

        JComboBox<String> colorJComboBox = new JComboBox<>(colors.keySet().toArray(new String[0]));

        idField.addActionListener((ActionEvent e) -> {currentId = idField.getText();});
        widthField.addActionListener((ActionEvent e) -> {currentWidth = widthField.getText();});
        heightField.addActionListener((ActionEvent e) -> {currentHeight = heightField.getText();});
        xField.addActionListener((ActionEvent e) -> {currentX = xField.getText();});
        yField.addActionListener((ActionEvent e) -> {currentY = yField.getText();});
        colorJComboBox.addActionListener((ActionEvent e) -> {currentColor = colorJComboBox.getSelectedItem().toString();});


        topPanel.add(idLabel);
        topPanel.add(idField);
        topPanel.add(widthLabel);
        topPanel.add(widthField);
        topPanel.add(heightLabel);
        topPanel.add(heightField);
        topPanel.add(xLabel);
        topPanel.add(xField);
        topPanel.add(yLabel);
        topPanel.add(yField);
        topPanel.add(colorLabel);
        topPanel.add(colorJComboBox);

        add(topPanel, BorderLayout.NORTH);
    }

    private void setupCenterPanel() {
        centerPanel.setBackground(Color.gray);
        add(centerPanel, BorderLayout.CENTER);
        JTextField centerTextField = new JTextField(30);
        add(centerTextField, BorderLayout.EAST);
    }

    private void setupBottomPanel() {
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.black);
        JButton addRectangleButton = new JButton("Add Rectangle");
        addRectangleButton.addActionListener((ActionEvent e) -> addRectangle());
        JButton searchRectangleButton = new JButton("Search Rectangle");
        searchRectangleButton.addActionListener((ActionEvent e) -> searchRectangle());
        JButton displayAllRectanglesButton = new JButton("Display All (Sorted)");
        displayAllRectanglesButton.addActionListener((ActionEvent e) -> displayAllRectangles());
        JButton updateRectangleButton = new JButton("Update Rectangle");
        updateRectangleButton.addActionListener((ActionEvent e) -> updateRectangle());
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addRectangle() {
        // Read values, validate, create RectangleShape, add to list
        try {
            int id = Integer.parseInt(currentId);
            int width = Integer.parseInt(currentWidth);
            int height = Integer.parseInt(currentHeight);
            int x = Integer.parseInt(currentX);
            int y = Integer.parseInt(currentY);

            if (! rectangleManager.validateId(id)) {
                JOptionPane.showMessageDialog(this, "Invalid ID");
                return;
            }

            if (! rectangleManager.validateWidth(width)) {
                JOptionPane.showMessageDialog(this, "Invalid Width");
                return;
            }

            rectangleManager.addRectangle(new RectangleShape(id, width, height, x, y, colors.get(currentColor)));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number");
        }
    }

    private void searchRectangle() {
        // Find rectangle by ID, display details, draw it
        if (rectangleManager.validateId(Integer.parseInt(currentId))) {
            rectangleManager.searchRectangle(Integer.parseInt(currentId));
        }
    }

    private void displayAllRectangles() {

    }

    private void sortRectangles() {
        // Sort list by ID and display on console
        ArrayList<RectangleShape> rectangles = rectangleManager.getSortedRectangles();
        StringBuilder builder = new StringBuilder();
        for (RectangleShape rectangle : rectangles) {
            builder.append(rectangle.toString()).append("\n");
        }
    }

    private void updateRectangle() {

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

    public JPanel getBottomPanel() {
        return bottomPanel;
    }

    public void setBottomPanel(JPanel bottomPanel) {
        this.bottomPanel = bottomPanel;
    }
}
