import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConsolePanel extends JPanel {

    JTextPane textArea = new JTextPane();
    String currentText = "";

    public ConsolePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setContentType("text/html");
        add(new JScrollPane(textArea));
    }

    public void enableDynamicResizing() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                Dimension size = getParent().getSize();
                setPreferredSize(new Dimension(size.width/4, size.height));
                revalidate();
            }
        });
    }

    public void setText(String text) {
        text = "<html>" + text.replace("\n", "<br>") + "</html>";
        textArea.setText(text);
    }

    public void appendText(String text) {
        text = text.replace("\n", "<br>");
        currentText = currentText.replace("</html", "") + text + "</html>";
        textArea.setText(currentText);
    }

    public void appendError(String text) {
        String formatted = "<html> <font color='red'>ERROR: " + text.replace("\n", "<br>") + "</font> </html>\n";
        currentText = currentText.replace("</html", "") + formatted + "</html>";
        textArea.setText(currentText);
    }

    public void resetTextArea() {
        textArea.setText("");
        currentText = "";
    }

}
