import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    private final RectangleManager RECTANGLE_MANAGER = new RectangleManager();
    private final TopPanel topPanel = new TopPanel();
    private final CenterPanel centerPanel = new CenterPanel(RECTANGLE_MANAGER);
    private final ConsolePanel consolePanel = new ConsolePanel();
    private final JPanel bottomPanel = new JPanel();

    public MainFrame() {
        setTitle("Shape Manager - ID:2313281 - df23933");
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
        // Create and add a panel for displaying the rectangle
        consolePanel.enableDynamicResizing();

        add(centerPanel, BorderLayout.CENTER);
        add(consolePanel, BorderLayout.EAST);
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
        Integer id = topPanel.getID();
        Integer width = topPanel.getWidthValue();
        Integer height = topPanel.getHeightValue();
        Integer x = topPanel.getXValue();
        Integer y = topPanel.getYValue();
        Color color = topPanel.getColor();

        consolePanel.resetTextArea();


        if (id == null) {
            consolePanel.appendError("Invalid ID, please enter an ID");
            return;
        }

        if (width == null) {
            consolePanel.appendError("Invalid Width, please enter a width");
            return;
        }

        if (height == null) {
            consolePanel.appendError("Invalid Height, please enter a height");
            return;
        }

        if (x == null) {
            consolePanel.appendError("Invalid X, please enter a x position");
            return;
        }

        if (y == null) {
            consolePanel.appendError("Invalid Y, please enter a y position");
            return;
        }



        if (!RECTANGLE_MANAGER.validateId(id)) {
            consolePanel.appendError("Invalid ID, please enter a number larger than 231000 and smaller than 232000");
            return;
        }

        if (width < 1) {
            consolePanel.appendError("Invalid Width, Please enter a number larger than 0");
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

        if (height < 1) {
            consolePanel.appendError("Invalid Height, Please enter a number larger than 0");
            return;
        }

        if (y + height > centerPanel.getHeight()) {
            consolePanel.appendError("The values you have entered for the y and height are larger than the height of the display panel");
            return;
        }

        if(RECTANGLE_MANAGER.addRectangle(new RectangleShape(id, width, height, x, y, color))) {
            consolePanel.setText("Rectangle Added");
            centerPanel.repaint();
            topPanel.clearInputs();
            return;
        }

        consolePanel.setText("Rectangle ID already exists");
    }

    private void searchRectangle() {
        // Find rectangle by ID, display details, draw it
        String input = JOptionPane.showInputDialog(this, "Enter the ID you are searching for.", "Search (ID)", JOptionPane.QUESTION_MESSAGE);
        try {
            int id = Integer.parseInt(input);
            RectangleShape rectangle = RECTANGLE_MANAGER.searchRectangle(id);
            if (rectangle != null) {
                consolePanel.setText("Rectangle Found: \n" + rectangle);
                topPanel.clearInputs();
                return;
            }
            consolePanel.setText("Rectangle Not Found");
        } catch (NumberFormatException e) {
            consolePanel.appendError("Invalid ID");
        }
    }

    private void displaySortedRectangles() {
        // Sort list by ID and display on console
        ArrayList<RectangleShape> rectangleList = RECTANGLE_MANAGER.getSortedRectangles();
        StringBuilder builder = new StringBuilder();
        for(RectangleShape rectangle : rectangleList) {
            builder.append(rectangle).append("\n");
        }
        consolePanel.setText(builder.toString());
        topPanel.clearInputs();
    }
    private void updateRectangle() {
        if (RECTANGLE_MANAGER.getCurrentRectangle() == null) {
            consolePanel.appendError("You must search for a rectangle first before editing it");
            return;
        }
        int width = topPanel.getWidthValue();
        int height = topPanel.getHeightValue();
        Color color = topPanel.getColor();
        if (RECTANGLE_MANAGER.updateRectangle(width, height, color)) {
            consolePanel.setText("Rectangle Updated");
            centerPanel.repaint();
            topPanel.clearInputs();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
