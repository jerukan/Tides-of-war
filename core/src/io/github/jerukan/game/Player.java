package io.github.jerukan.game;

import com.badlogic.gdx.graphics.Color;
import io.github.jerukan.util.Constants;

/** This will be you */

public class Player {

    public final String name;
    public final Color color;

    private int unitCap;
    private int money;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
        //make sure color is solid
        color.set(color.r, color.g, color.b, 1f);

        unitCap = Constants.DEFAULT_UNIT_CAP;
        money = Constants.DEFAULT_START_MONEY;
    }

    public void onNewTurn() {
        money += Constants.DEFAULT_MONEY_PRODUCTION;
    }

    public boolean hasSufficientMoney(int money) {
        return this.money >= money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public boolean hasSufficientUpkeep(int cost) {
        return GameState.instance.unitState.totalUpkeepFromPlayer(this) + cost <= unitCap;
    }

    public int getUnitCap() {
        return unitCap;
    }

    public void addUnitCap(int val) {
        unitCap += val;
    }
}
