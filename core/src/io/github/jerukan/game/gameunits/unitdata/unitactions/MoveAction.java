package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Position;

import java.util.ArrayList;

public class MoveAction extends UnitAction {

    public MoveAction(BaseUnit baseUnit) {
        super(baseUnit);
        name = "move";
        speedConsumption = 0;   //speed is modified based on distance traveled
        requiresTarget = true;
    }

    @Override
    public void execute(Unit self) {

    }

    @Override
    public void execute(Unit self, Position target) {
        if(target.existsInArray(availableTargets)) {
            if(GameState.instance.unitState.positionAvailable(target)) {
                int speedconsump = 0;
                //checks the corresponding speed the tile will consume
                for(int i = 0; i < self.getAvailableTargets().size(); i++) {
                    if(self.getAvailableTargets().get(i).equals(target)) {
                        speedconsump = targetSpeedConsumptions.get(i);
                    }
                }
                self.setCurrentSpeed(self.getCurrentSpeed() - speedconsump);
                self.setPosition(new Position(target));
                self.moveSprite(target);
            }
        }
    }

    @Override
    public void getTarget(Unit self, Position startpos, Position checkedpos, ArrayList<Position> moves, ArrayList<Integer> moveConsump, int aggregateConsump, int movesleft) {
        int speedconsump = GameState.instance.boardState.tileFromPosition(checkedpos).getSpeedConsump();

        if(!startpos.equals(checkedpos)) {
            if (movesleft < speedconsump) {
                return;
            }

            aggregateConsump += speedconsump;
            movesleft -= speedconsump;

            if (!checkedpos.existsInArray(moves)) {
                moves.add(checkedpos);
                moveConsump.add(aggregateConsump);
            }
        }

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
        targetSpeedConsumptions.clear();
        getTarget(self, self.getPosition(), new Position(self.getPosition()), availableTargets, targetSpeedConsumptions, 0, self.getCurrentSpeed());
    }
}