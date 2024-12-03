
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.function.Consumer;

public class TopPanel extends JPanel {

    private final String[] colorNames = {"Red", "Orange", "Yellow", "Green", "Blue", "Violet"};
    private final Color[] colorValues = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};


    private final JLabel idLabel = new JLabel("ID (Starts with 239)");
    private final JLabel widthLabel = new JLabel("Width (Even Only)");
    private final JLabel heightLabel = new JLabel("Height");
    private final JLabel xLabel = new JLabel("X Position");
    private final JLabel yLabel = new JLabel("Y Position");
    private final JLabel colorLabel = new JLabel("Colour");

    private final JTextField idField = new JTextField(10);
    private final JTextField widthField = new JTextField(10);
    private final JTextField heightField = new JTextField(10);
    private final JTextField xField = new JTextField(10);
    private final JTextField yField = new JTextField(10);

    JComboBox<String> colorComboBox;

    private static int id;
    private static int width;
    private static int height;
    private static int x;
    private static int y;
    private static Color color;

    public TopPanel() {
        setLayout(new GridLayout(3, 4));
        setBackground(Color.LIGHT_GRAY);

        colorComboBox = new JComboBox<>(colorNames);

        setupField(idField, idLabel, value -> id = value, "ID (Starts with 239)");
        setupField(widthField, widthLabel, value -> width = value, "Width (Even Only)");
        setupField(heightField, heightLabel, value -> height = value, "Height");
        setupField(xField, xLabel, value -> x = value, "X Position");
        setupField(yField, yLabel, value -> y = value, "Y Position");
        setupDropdown(colorComboBox);

        addComponents();
    }

    private void setupField(JTextField field, JLabel label, Consumer<Integer> onUpdate, String labelText) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { onTextChange(); }
            @Override
            public void removeUpdate(DocumentEvent e) { onTextChange(); }
            @Override
            public void changedUpdate(DocumentEvent e) { onTextChange(); }

            private void onTextChange() {
                try {
                    int value = Integer.parseInt(field.getText());
                    onUpdate.accept(value);
                    label.setText(labelText);
                } catch (NumberFormatException e) {
                    label.setText(labelText + " (Not a number)");
                }
            }
        });
    }

    private void setupDropdown(JComboBox<String> dropdown) {
        dropdown.addItemListener(e -> {
            color = colorValues[dropdown.getSelectedIndex()];
        });
    }

    private void addComponents() {
        add(idLabel);
        add(idField);
        add(widthLabel);
        add(widthField);
        add(heightLabel);
        add(heightField);
        add(xLabel);
        add(xField);
        add(yLabel);
        add(yField);
        add(colorLabel);
        add(colorComboBox);
    }

    public int getID() {
        return id;
    }

    public int getWidthValue() {
        return width;
    }

    public int getHeightValue() {
        return height;
    }

    public int getXValue() {
        return x;
    }

    public int getYValue() {
        return y;
    }

    public Color getColor() {
        return color;
    }

}
