package io.github.jerukan.game.ui.buttongroups;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.board.BoardManager;
import io.github.jerukan.game.gameunits.AllUnits;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Position;

public class TileSelectMenu extends ButtonGroup {

    private final TextButton buildButton;
    private final TextButton moveButton;
    private final TextButton attackButton;

    public TileSelectMenu() {
        buildButton = new TextButton("Build", Assets.uiskin, "default");
        buildButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                GameState.instance.unitManager.addUnit(AllUnits.getUnit("test"), null, new Position(GameState.instance.boardManager.getSelectedPosition()));
                GameState.instance.unitManager.setSelectedToLast();
                GameState.instance.unitManager.generateUnitMoves();
            }
        });
        moveButton = new TextButton("Move", Assets.uiskin, "default");
        moveButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.getSelectedPosition()) != null) {
                    GameState.instance.boardManager.setSelectType(BoardManager.SelectType.MOVE);
                }
                else {
                    GameState.instance.boardManager.setSelectType(BoardManager.SelectType.SELECT);
                }
            }
        });
        attackButton = new TextButton("Attack", Assets.uiskin, "default");
        attackButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(GameState.instance.unitManager.unitFromPosition(GameState.instance.boardManager.getSelectedPosition()) != null) {
                    GameState.instance.boardManager.setSelectType(BoardManager.SelectType.ATTACK);
                }
                else {
                    GameState.instance.boardManager.setSelectType(BoardManager.SelectType.SELECT);
                }
            }
        });
        table.align(Align.left);

        table.add(buildButton);
        table.row().pad(10);
        table.add(moveButton);
        table.row();
        table.add(attackButton);
    }

    @Override
    public void setVisibility() {

    }
}
