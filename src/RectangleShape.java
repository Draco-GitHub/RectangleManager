import java.awt.*;

public class RectangleShape implements Comparable<RectangleShape> {
    private Color color;
    private final int ID;
    private int width;
    private int height;
    private final int X_POS;
    private final int Y_POS;

    public RectangleShape(int id, int width, int height, int xPos, int yPos, Color color) {
        this.ID = id;
        this.width = width;
        this.height = height;
        this.X_POS = xPos;
        this.Y_POS = yPos;
        this.color = color;
    }

    public int getId() {
        return ID;
    }

    public void draw(java.awt.Graphics g) {
        g.setColor(color);
        g.fillRect(X_POS, Y_POS, width, height);
    }

    public void updateRectangle(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public int compareTo(RectangleShape other) {
        return Integer.compare(this.ID, other.ID);
    }
}
