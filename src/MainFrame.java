import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    private ArrayList<RectangleShape> rectangleList = new ArrayList<>();

    private final RectangleManager RECTANGLE_MANAGER = new RectangleManager();
    private final TopPanel topPanel = new TopPanel();
    private final JPanel centerPanel = new JPanel();
    private final ConsolePanel consolePanel = new ConsolePanel();
    private final JPanel bottomPanel = new JPanel();

    public MainFrame() {
        setTitle("Rectangle Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set up panels for inputs, display, and buttons
        setupTopPanel();
        setupCenterPanel();
        setupBottomPanel();
    }

    private void setupTopPanel() {
        add(topPanel, BorderLayout.NORTH);
        // Create input fields for ID, width, height, xPos, yPos, and color
        // Add input fields to the top panel
    }

    private void setupCenterPanel() {
        centerPanel.setBackground(Color.GRAY);
        consolePanel.enableDynamicResizing();

        add(centerPanel, BorderLayout.CENTER);
        add(consolePanel, BorderLayout.EAST);

        // Create and add a panel for displaying the rectangle
    }

    private void setupBottomPanel() {
        // Add buttons for Add, Search, Display, Sort, Update
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.LIGHT_GRAY);

        JButton addRectangle = new JButton("Add Rectangle");
        JButton searchRectangle = new JButton("Search Rectangle");
        JButton displaySorted = new JButton("Display All (Sorted)");
        JButton updateRectangle = new JButton("Update Rectangle");
        addRectangle.addActionListener(e -> addRectangle());
        searchRectangle.addActionListener(e -> searchRectangle());
        displaySorted.addActionListener(e -> displaySortedRectangles());
        updateRectangle.addActionListener(e -> updateRectangle());

        bottomPanel.add(addRectangle);
        bottomPanel.add(searchRectangle);
        bottomPanel.add(displaySorted);
        bottomPanel.add(updateRectangle);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addRectangle() {
        // Read values, validate, create RectangleShape, add to list
        int id = topPanel.getID();
        int width = topPanel.getWidthValue();
        int height = topPanel.getHeightValue();
        int x = topPanel.getXValue();
        int y = topPanel.getYValue();
        Color color = topPanel.getColor();


        consolePanel.resetTextArea();

        if (!RECTANGLE_MANAGER.validateId(id)) {
            consolePanel.appendError("Invalid ID, please enter a number larger than 239000");
            return;
        }

        if (width < 1) {
            consolePanel.appendError("Invalid Width, Please enter a number larger than 0");
            return;
        }

        if (height < 1) {
            consolePanel.appendError("Invalid Height, Please enter a number larger than 0");
            return;
        }

        if (!RECTANGLE_MANAGER.validateWidth(width)) {
            consolePanel.appendError("Invalid Width, Please enter an odd width");
            return;
        }

        if (x + width > centerPanel.getWidth()) {
            consolePanel.appendError("The values you have entered for the x and width are larger than the width of the display panel");
            return;
        }

        if (y + height > centerPanel.getHeight()) {
            consolePanel.appendError("The values you have entered for the y and height are larger than the height of the display panel");
            return;
        }

        if(RECTANGLE_MANAGER.addRectangle(new RectangleShape(id, width, height, x, y, color))) {
            consolePanel.setText("Rectangle Added");
            return;
        }

        consolePanel.setText("Rectangle ID already exists");
    }

    private void searchRectangle() {
        // Find rectangle by ID, display details, draw it
        int id = topPanel.getID();
        RectangleShape rectangle = RECTANGLE_MANAGER.searchRectangle(id);
        if(rectangle != null) {
            consolePanel.setText("Rectangle found: \n" + rectangle);
            return;
        }
        consolePanel.setText("Rectangle Not Found");
    }

    private void displaySortedRectangles() {
        // Sort list by ID and display on console
        rectangleList = RECTANGLE_MANAGER.getSortedRectangles();
        StringBuilder builder = new StringBuilder();
        for(RectangleShape rectangle : rectangleList) {
            builder.append(rectangle).append("\n");
        }
        consolePanel.setText(builder.toString());
    }
    private void updateRectangle() {
        int width = topPanel.getWidthValue();
        int height = topPanel.getHeightValue();
        Color color = topPanel.getColor();
        if (RECTANGLE_MANAGER.updateRectangle(width, height, color)) {
            consolePanel.setText("Rectangle Updated");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
