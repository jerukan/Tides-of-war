package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

public class MoveFlyAction extends UnitAction {

    public MoveFlyAction(BaseUnit baseUnit) {
        super(baseUnit);
        name = "fly";
        speedConsumption = 0; //changed based on distance
        requiresTarget = true;
    }

    @Override
    public void execute(Unit self) {

    }

    @Override
    public void execute(Unit self, Position target) {
        if(target.existsInArray(availableTargets)) {
            if(GameState.instance.unitState.positionAvailable(target)) {
                self.setCurrentSpeed(self.getCurrentSpeed() - target.distanceToPosition(self.getPosition()));
                self.setPosition(new Position(target));
                self.moveSprite(target);
            }
        }
    }

    @Override
    public void getTarget(Unit self, Position startpos, Position checkedpos, ArrayList<Position> moves, ArrayList<Integer> moveConsump, int aggregateConsump, int movesleft) {
        if(!startpos.equals(checkedpos)) {
            if (movesleft < 0) {
                return;
            }

            if (!checkedpos.existsInArray(moves)) {
                moves.add(checkedpos);
            }
        }
        movesleft -= 1;

        if (checkedpos.getX() + 1 < Constants.BOARD_WIDTH) {
            Position newpos = new Position(checkedpos.getX() + 1, checkedpos.getY());
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if (checkedpos.getX() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX() - 1, checkedpos.getY());
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if (checkedpos.getY() + 1 < Constants.BOARD_HEIGHT) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() + 1);
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if (checkedpos.getY() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() - 1);
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
    }

    @Override
    public void generateTargets(Unit self) {
        availableTargets.clear();
        getTarget(self, self.getPosition(), new Position(self.getPosition()), availableTargets, targetSpeedConsumptions, 0, self.getCurrentSpeed());
    }
}
