package Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

public class RectangleManager {

    private ArrayList<RectangleShape> rectangles = new ArrayList<>();

    public boolean addRectangle(RectangleShape rectangle) {
        if (searchRectangle(rectangle.getId()) == null) {
            return false;
        }
        rectangles.add(rectangle);
        return true;
    }

    public RectangleShape searchRectangle(int id) {
        for (RectangleShape rectangle : rectangles) {
            if (rectangle.getId() == id) {
                return rectangle;
            }
        }
        return null;
    }

    public ArrayList<RectangleShape> getSortedRectangles() {
        Collections.sort(rectangles);
        return rectangles;
    }

    public boolean updateRectangle(int width, int height, Color color) {
        RectangleShape rectangle = getCurrentRectangle();
        rectangle.updateRectangle(width, height, color);
        return true;
    }

    public boolean validateId(int id) {
        return id <= 239000;
    }

    public boolean validateWidth(int width) {
        return width %2 != 0;
    }

    public ArrayList<RectangleShape> getRectangles() {
        return rectangles;
    }

    public RectangleShape getCurrentRectangle() {
        return rectangles.get(0);
    }
}