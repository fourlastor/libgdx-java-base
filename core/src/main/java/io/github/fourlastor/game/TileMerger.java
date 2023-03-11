package io.github.fourlastor.game;

import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Comparator;

public class TileMerger {

    /**
     *  Merges a grid of tiles together
     *  See <a href="https://love2d.org/wiki/TileMerging">original implementation for LÖVE2D</a>
     */
    public static ArrayList<Rectangle> mergeTiles(boolean[][] tiles, int width, int height) {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (int x = -1; x <= width; x++) {
            int startY = -1;
            int endY = -1;
            for (int y = -1; y <= height; y++) {
                boolean inBounds = x >= 0 && x < width && y >= 0 && y < height;
                if (inBounds && tiles[x][y]) {
                    if (startY < 0) {
                        startY = y;
                    }
                    endY = y;
                } else if (startY >= 0) {
                    ArrayList<Rectangle> overlaps = new ArrayList<>();
                    for (Rectangle r : rectangles) {
                        if (r.width == x - 1 && startY <= r.y && endY >= r.height) {
                            overlaps.add(r);
                        }
                    }
                    overlaps.sort(RECTANGLE_COMPARATOR);
                    for (Rectangle r : overlaps) {
                        if (startY < r.y) {
                            rectangles.add(new Rectangle(x, startY, x, r.y - 1));
                            startY = (int) r.y;
                        }
                        if (startY == r.y) {
                            r.width += 1;
                            if (endY == r.height) {
                                startY = -1;
                                endY = -1;
                            } else if (endY > r.height) {
                                startY = (int) (r.height + 1);
                            }
                        }
                    }
                    if (startY >= 0) {
                        rectangles.add(new Rectangle(x, startY, x, endY));
                        startY = -1;
                        endY = -1;
                    }
                }
            }
        }
        return rectangles;
    }

    private static final Comparator<Rectangle> RECTANGLE_COMPARATOR = (a, b) -> (int) (a.y - b.y);
}
