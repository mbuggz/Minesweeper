package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ranges {
    private static Coord size;
    private static List<Coord> allCoords;
    private static final Random random = new Random();

    public static Coord getSize() {
        return size;
    }

    static void setSize(Coord _size) {
        size = _size;
        allCoords = new ArrayList<>();
        for(int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                allCoords.add(new Coord(x, y));
            }
        }
    }

    public static List<Coord> getAllCoords() {
        return allCoords;
    }

    public static boolean inRange(Coord coord) {
        return coord.getX() >= 0 && coord.getX() < size.getX() &&
                coord.getY() >= 0 && coord.getY() < size.getY();
    }

    static Coord getRandomCoord() {
        return new Coord(random.nextInt(size.getX()), random.nextInt(size.getY()));
    }

    static List<Coord> getCoordsAround(Coord coord) {
        Coord around;
        List<Coord> list = new ArrayList<>();
        for(int x = coord.getX() - 1; x <= coord.getX() + 1; x++) {
            for(int y = coord.getY() - 1; y <= coord.getY() + 1; y++) {
                if(inRange(around = new Coord(x, y))) {
                    if(!around.equals(coord))
                        list.add(around);
                }
            }
        }
        return list;
    }
}
