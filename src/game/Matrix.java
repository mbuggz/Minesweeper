package game;

import enums.Box;

public class Matrix {
    private final Box[][] matrix;

    Matrix(Box defaultBox) {
        matrix = new Box[Ranges.getSize().getX()][Ranges.getSize().getY()];
        for(Coord coord : Ranges.getAllCoords()) {
            matrix[coord.getX()][coord.getY()] = defaultBox;
        }
    }

    Box get(Coord coord) {
        if(Ranges.inRange(coord)) {
            return matrix[coord.getX()][coord.getY()];
        }
        return null;
    }

    void set(Coord coord, Box box) {
        if(Ranges.inRange(coord)) {
            matrix[coord.getX()][coord.getY()] = box;
        }
    }
}
