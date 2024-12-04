
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.function.Consumer;

public class TopPanel extends JPanel {

    private final String[] colorNames = {"Red", "Orange", "Yellow", "Green", "Blue", "Violet"};
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

    private int id;
    private int width;
    private int height;
    private int x;
    private int y;
    private Color color;

    public TopPanel() {
        setLayout(new GridLayout(3, 4));
        setBackground(Color.LIGHT_GRAY);

        colorComboBox = new JComboBox<>(colorNames);
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

        setupField(idField, idLabel, value -> id = value, "ID (Starts with 231)");
        setupField(widthField, widthLabel, value -> width = value, "Width (Odd Only)");
        setupField(heightField, heightLabel, value -> height = value, "Height");
        setupField(xField, xLabel, value -> x = value, "X Position");
        setupField(yField, yLabel, value -> y = value, "Y Position");
        setupDropdown(colorComboBox);

        addComponents();
    }

    private void setupField(JTextField field, JTextPane label, Consumer<Integer> onUpdate, String labelText) {

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
                    onUpdate.accept(value);
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
