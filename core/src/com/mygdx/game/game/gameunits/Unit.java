package com.mygdx.game.game.gameunits;

/*
* The unit that is actually placed on the board and handled
* Not to be confused with the higher pH version of itself
* Ok that was bad
* */

import com.mygdx.game.game.Player;

public class Unit {

    private BaseUnit baseunit;

    private int currentHealth;
    private int currentAttack;
    private int currentSpeed;
    private int currentRange;

    private int[] position;

    private int[][] availableMoves;
    private int[][] availableAttacks;

    private Player owner;

    public Unit(BaseUnit unit, Player owner, int[] position) {
        baseunit = unit;

        currentHealth = unit.baseHealth;
        currentAttack = unit.baseAttack;
        currentSpeed = unit.baseSpeed;
        currentRange = unit.baseRange;

        this.owner = owner;

        this.position = position;
    }
}
