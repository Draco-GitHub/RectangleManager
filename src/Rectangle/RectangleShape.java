package Rectangle;

import java.awt.Color;

public class RectangleShape implements Comparable<RectangleShape> {
    private Color color = Color.BLACK;
    private final int id;
    private int width;
    private int height;
    private int xPos;
    private int yPos;

    public RectangleShape(int id, int width, int height, int xPos, int yPos, Color color) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.xPos = xPos;
        this.yPos = yPos;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void draw(java.awt.Graphics g) {
        g.setColor(color);
        g.fillRect(xPos, yPos, width, height);
    }

    public void updateRectangle(int width, int height, Color color) {
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    public int compareTo(RectangleShape other) {
        return Integer.compare(this.id, other.id);
    }
}
