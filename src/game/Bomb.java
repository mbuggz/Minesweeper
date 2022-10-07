package game;

import enums.Box;

public class Bomb {

    private Matrix bombMap;
    private int totalBomb;

    Bomb(int totalBomb) {
        this.totalBomb = totalBomb;
        fixBombsCount();
    }

    void start() {
        bombMap = new Matrix(Box.ZERO);
        for(int i = 0; i < totalBomb; i++) {
            placeBomb();
        }
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    int getTotalBombs() {
        return totalBomb;
    }

    private void fixBombsCount() {
        int maxCountBomb = Ranges.getSize().getX() * Ranges.getSize().getY() / 3;
        if(totalBomb > maxCountBomb) {
            totalBomb = maxCountBomb;
        }
    }

    private void placeBomb() {
        while(true) {
            Coord coord = Ranges.getRandomCoord();
            if(Box.BOMB == bombMap.get(coord)) {
                continue;
            }
            bombMap.set(coord, Box.BOMB);
            incNumberAroundBomb(coord);
            break;
        }
    }

    private void incNumberAroundBomb(Coord coord) {
        for(Coord around : Ranges.getCoordsAround(coord)) {
            if(Box.BOMB != bombMap.get(around)) {
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
            }
        }
    }
}
