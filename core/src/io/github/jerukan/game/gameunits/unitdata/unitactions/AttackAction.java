package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

public class AttackAction extends UnitAction {

    public AttackAction(BaseUnit baseUnit) {
        super(baseUnit);
        name = "attack";
        speedConsumption = 1;
        requiresTarget = true;
    }

    @Override
    public void execute(Unit self) {

    }

    @Override
    public void execute(Unit self, Position target) {
        if(target.existsInArray(availableTargets)) {
            if(GameState.instance.unitState.unitFromPosition(target) != null) {
                if(GameState.instance.unitState.unitFromPosition(target).getOwner() != self.getOwner()) {
                    GameState.instance.unitState.unitFromPosition(target).takeDamage(self.getCurrentAttack());
                    self.setCurrentSpeed(self.getCurrentSpeed() - speedConsumption);
                }
            }
        }
    }

    @Override
    public void getTarget(Unit self, Position startpos, Position checkedpos, ArrayList<Position> moves, ArrayList<Integer> moveConsump, int aggregateConsump, int movesleft) {
        if(!checkedpos.existsInArray(moves) && !startpos.equals(checkedpos)) {
            moves.add(checkedpos);
        }
        if(movesleft <= 0) {
            return;
        }

        movesleft -= 1;

        if(checkedpos.getX() + 1 < Constants.BOARD_WIDTH) {
            Position newpos = new Position(checkedpos.getX() + 1, checkedpos.getY());
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if(checkedpos.getX() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX() - 1, checkedpos.getY());
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if(checkedpos.getY() + 1 < Constants.BOARD_HEIGHT) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() + 1);
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
        if(checkedpos.getY() - 1 >= 0) {
            Position newpos = new Position(checkedpos.getX(), checkedpos.getY() - 1);
            getTarget(self, startpos, newpos, moves, moveConsump, aggregateConsump, movesleft);
        }
    }

    @Override
    public void generateTargets(Unit self) {
        availableTargets.clear();
        getTarget(self, self.getPosition(), new Position(self.getPosition()), availableTargets, targetSpeedConsumptions, 0, self.getCurrentRange());
    }
}