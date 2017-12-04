package io.github.jerukan.game.gameunits.unitdata.unitactions;

import io.github.jerukan.game.GameState;
import io.github.jerukan.game.gameunits.Unit;
import io.github.jerukan.game.gameunits.unitdata.BaseUnit;
import io.github.jerukan.util.Position;
import io.github.jerukan.util.Util;

public class MoveAction extends UnitAction {

    public MoveAction(BaseUnit baseUnit) {
        this.baseUnit = baseUnit;
        name = "move";
        //speed is modified based on distance traveled
        requiresTarget = true;
    }

    @Override
    public void execute(Unit self) {

    }

    @Override
    public void execute(Unit self, Position target) {
        if(target.existsInArray(self.getAvailableMoves())) {
            if(GameState.instance.unitManager.positionAvailable(target)) {
                //Position prev = new Position(self.getPosition());
                int speedconsump = 0;
                //checks the corresponding speed the tile will consume
                for(int i = 0; i < self.getAvailableMoves().size(); i++) {
                    if(self.getAvailableMoves().get(i).equals(target)) {
                        speedconsump = self.getMoveConsumptions().get(i);
                    }
                }
                self.setCurrentSpeed(self.getCurrentSpeed() - speedconsump);
                self.setPosition(new Position(target));
                self.moveSprite(target);
                self.generateMovesAndAttacks();
            }
        }
    }
}