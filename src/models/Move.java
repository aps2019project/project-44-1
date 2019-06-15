package models;

import models.Enums.Directions;
import view.View;

public interface Move {

    /**
     * checks if destination cell is free and at least one card could found with this features:
     * 1.it belongs to @param mover
     * 2. manhattan distance between its cell and destination equals to @param distance
     */
    default boolean invalidCoordination(int x, int y, int distance, Player mover) {
        for (Card c : mover.getMyMap().getPlayerCardsInMap(mover.getName())) {
            if (Map.getManhatanDistance(c.getMyCell(), mover.getMyMap().getCells()[x][y]) == distance &&
                    mover.getMyMap().getCells()[x][y].isFree())
                return false;
        }
        return true;
    }

    /**
     * if it wants to move and there is opponent cards in his way (only happens in moving 2 cells)...
     */
    default boolean superInvalidCoordination(int x, int y, Player mover) {
        if (mover.getMyMap().getCell(x, y).isFree())
            return true;
        int yy = mover.getSelectedCard().getMyCell().getY();
        int xx = mover.getSelectedCard().getMyCell().getX();
        try {
            if (x == xx) {
                switch (yy - y) {
                    case 2:
                        return mover.getMyMap().getCell(x, yy - 1).isFree();
                    case -2:
                        return mover.getMyMap().getCell(x, yy + 1).isFree();
                }
            } else if (y == yy) {
                switch (xx - x) {
                    case 2:
                        return mover.getMyMap().getCell(xx - 1, y).isFree();
                    case -2:
                        return mover.getMyMap().getCell(xx + 1, y).isFree();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            View.getInstance().sout(x + "\t" + xx + "\t" + y + "\t" + yy);
        }
        return canMoveDiagonal(x, y, xx, yy, mover.getMyMap());
    }

    private boolean canMoveDiagonal(int x, int y, int xx, int yy, Map map) throws ArrayIndexOutOfBoundsException {
        if (Math.abs(x - xx) == 1) {
            if (map.getCell(x, yy).isFree())
                return true;
            return map.getCell(xx, y).isFree();
        }
        return false;
    }

    default Directions[] movingDirections(int x, int y, int xx, int yy) {
        Directions[] directions = new Directions[2];
        if (x == xx) {
            switch (x - xx) {
                case -2:
                    break;
                case -1:
                    break;
                case 1:
                    break;
                case 2:
            }

        }
        return directions;
    }

}
