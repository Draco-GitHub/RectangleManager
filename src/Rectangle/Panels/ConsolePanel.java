package Rectangle.Panels;

import javax.swing.*;
import java.awt.*;

public class ConsolePanel extends JPanel {

    JTextArea textArea = new JTextArea();
    public ConsolePanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 200));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

}
