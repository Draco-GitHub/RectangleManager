
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.HashMap;
import java.util.function.Consumer;

public class TopPanel extends JPanel {
    private final Color[] colorValues = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.MAGENTA};

    private final JTextPane idLabel = new JTextPane();
    private final JTextPane widthLabel = new JTextPane();
    private final JTextPane heightLabel = new JTextPane();
    private final JTextPane xLabel = new JTextPane();
    private final JTextPane yLabel = new JTextPane();
    private final JTextPane colorLabel = new JTextPane();

    private final JTextField idField = new JTextField(10);
    private final JTextField widthField = new JTextField(10);
    private final JTextField heightField = new JTextField(10);
    private final JTextField xField = new JTextField(10);
    private final JTextField yField = new JTextField(10);

    JComboBox<String> colorComboBox;
    private static final HashMap<String, Integer> values = new HashMap<>();
    private Color color;

    public TopPanel() {
        setLayout(new GridLayout(3, 4));
        setBackground(Color.LIGHT_GRAY);

        String[] colorNames = {"Red", "Orange", "Yellow", "Green", "Blue", "Violet"};
        colorComboBox = new JComboBox<>(colorNames);
        values.put("id", 0);
        values.put("width", 0);
        values.put("height", 0);
        values.put("x", 0);
        values.put("y", 0);
        color = colorValues[0];

        idLabel.setEditable(false);
        widthLabel.setEditable(false);
        heightLabel.setEditable(false);
        xLabel.setEditable(false);
        yLabel.setEditable(false);
        colorLabel.setEditable(false);

        idLabel.setContentType("text/html");
        widthLabel.setContentType("text/html");
        heightLabel.setContentType("text/html");
        xLabel.setContentType("text/html");
        yLabel.setContentType("text/html");
        colorLabel.setContentType("text/html");

        idLabel.setText("ID (Starts with 231)");
        widthLabel.setText("Width (Odd Only)");
        heightLabel.setText("Height");
        xLabel.setText("X Position");
        yLabel.setText("Y Position");
        colorLabel.setText("Color");

        setupField(idField, idLabel, "id", "ID (Starts with 231)");
        setupField(widthField, widthLabel, "width", "Width (Odd Only)");
        setupField(heightField, heightLabel, "height", "Height");
        setupField(xField, xLabel, "x", "X Position");
        setupField(yField, yLabel, "y", "Y Position");
        setupDropdown(colorComboBox);

        addComponents();
    }

    private void setupField(JTextField field, JTextPane label, String valID, String labelText) {

        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { onTextChange(); }
            @Override
            public void removeUpdate(DocumentEvent e) { onTextChange(); }
            @Override
            public void changedUpdate(DocumentEvent e) { onTextChange(); }

            private void onTextChange() {
                try {
                    int value;
                    if (field.getText().isEmpty()) {
                        value = 0;
                    } else {
                        value = Integer.parseInt(field.getText());
                    }
                    values.put(valID, value);
                    label.setText("<html>" + labelText + "</html>");
                } catch (NumberFormatException e) {
                    label.setText("<html>" + labelText + "<font color='red'> (Not A Number)</font> </html>");
                }
            }
        });
    }

    private void setupDropdown(JComboBox<String> dropdown) {
        dropdown.addItemListener(e -> {
            color = colorValues[dropdown.getSelectedIndex()];
        });
    }

    public void clearInputs() {
        values.put("id", 0);
        idField.setText("");
        values.put("width", 0);
        widthField.setText("");
        values.put("height", 0);
        heightField.setText("");
        values.put("x", 0);
        xField.setText("");
        values.put("y", 0);
        yField.setText("");
        color=colorValues[0];
        colorComboBox.setSelectedIndex(0);
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
        return values.get("id");
    }

    public int getWidthValue() {
        return values.get("width");
    }

    public int getHeightValue() {
        return values.get("height");
    }

    public int getXValue() {
        return values.get("x");
    }

    public int getYValue() {
        return values.get("y");
    }

    public Color getColor() {
        return color;
    }

}
