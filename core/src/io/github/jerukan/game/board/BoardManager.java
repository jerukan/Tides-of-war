package io.github.jerukan.game.board;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.game.gameunits.unitdata.unitactions.UnitAction;
import io.github.jerukan.util.Position;

import java.util.*;

/** Most client side stuff with the board is handled here */

public class BoardManager {

    private static Position hoveredPosition = new Position();
    private static Position selectedPosition = new Position();

    private static ArrayList<Position> availableBuildPositions = new ArrayList<>();

    private static SelectType selectType = SelectType.SELECT;

    /** Sets the board position of the currently hovered tile
     * @param pos desired position to set */
    public static void setHoveredPosition(Position pos) {
        if(pos.isValid()) {
            hoveredPosition.setPos(pos);
        }
        else {
            hoveredPosition.reset();
        }
    }

    public static Position getHoveredPosition() {
        return hoveredPosition;
    }

    /** Determines what happens on the next tile selection with the left mouse button */
    public static void selectedPositionActionLeft() {
        switch (selectType) {
            case ACTION:
                Unit unit = GameState.instance.unitState.getSelectedUnit();
                UnitAction act = unit.getCurrentAction();
                if(act.requiresTarget) {
                    act.execute(unit, hoveredPosition);
                }
                unit.clearMoveLists();
                GameState.instance.unitState.killUnits();
                GameState.instance.unitState.resetSelectedUnit();
                selectType = SelectType.SELECT;
                selectedPosition.reset();
                GameState.instance.update();
                BoardManager.updateAvailableBuildPositions();
                break;
            case SELECT:
                selectedPosition.reset();
                GameState.instance.unitState.resetSelectedUnit();
        }
    }

    /** Determines what happens on the next tile selection with the right mouse button */
    public static void selectedPositionActionRight() {
        switch (selectType) {
            case SELECT:
                selectedPosition = new Position(hoveredPosition);
                GameState.instance.unitState.setSelectedUnit(GameState.instance.unitState.unitFromPosition(selectedPosition));
                break;
        }
    }

    public static void setSelectedPosition(Position pos) {
        selectedPosition = pos;
    }

    public static Position getSelectedPosition() {
        return selectedPosition;
    }

    public static void setSelectType(SelectType type) {
        selectType = type;
    }

    public static SelectType getSelectType() {
        return selectType;
    }

    public static void updateAvailableBuildPositions() {
        availableBuildPositions.clear();
        ArrayList<Unit> buildings = GameState.instance.unitState.unitsFromPlayer(GameState.instance.getCurrentPlayer(), (Unit u) -> u.baseunit.type == BaseUnit.Type.BUILDING);
        for(Unit b : buildings) {
            for(Position p : b.getPosition().getAdjacentPositions()) {
                if(!p.existsInArray(availableBuildPositions) && GameState.instance.unitState.positionAvailable(p)) {
                    availableBuildPositions.add(p);
                }
            }
        }
    }

    public static List<Position> getAvailableBuildPositions() {
        return availableBuildPositions;
    }

    public enum SelectType {
        SELECT,
        ACTION
    }
}
