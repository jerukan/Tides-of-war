realms_of_war

Something turn based "strategy" something

This branch was modified to suit the needs of a project in my APCS class

The bulk of the source code is in core/src/io/github/jerukan

In here, the main classes to look at are:
- Main
- everything in game/board file
- game/gameunits
    - UnitManager
    - UnitRenderer
    - UnitRegistry
    - UnitRenderer
    - gameunits/unitdata
        - BaseUnit
- game/ui
    - the abstract classes 
- game/GameState
- game/WorldRenderer
- util
    - Constants
    - Position
    - Util

The driver with the main method is in desktop/src/io/github/jerukan/desktop

Section 3 with the summary and stuff is in APCS_state3.text

Loop trace diagram is looptrace.png