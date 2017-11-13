package com.mygdx.game.game.board.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.game.GameState;
import com.mygdx.game.game.board.BoardManager;
import com.mygdx.game.game.gameunits.AllUnits;
import com.mygdx.game.util.Assets;
import com.mygdx.game.util.Constants;

public class GameScreen extends Screen {

    public GameScreen(Stage stage) {
        super(stage);

        //-----------------------------------------------------//

        Table testTable = new Table();
        final TextButton button = new TextButton("Click me", Assets.uiskin, "default");
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                button.setText("You clicked the button");
            }
        });
        testTable.setWidth(stage.getWidth());
        testTable.align(Align.center|Align.top);
        testTable.setPosition(0, Gdx.graphics.getHeight());

        testTable.add(button);
        tables.put("test", testTable);

        //-----------------------------------------------------//

        Table tileSelectionTable = new Table();
        final TextButton buildButton = new TextButton("Build", Assets.uiskin, "default");
        buildButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                GameState.instance.unitManager.addUnit(AllUnits.getUnit("test"), null, GameState.instance.boardManager.getSelectedPosition());
                GameState.instance.unitManager.generateUnitMoves();
            }
        });
        final TextButton moveButton = new TextButton("Move", Assets.uiskin, "default");
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
        tileSelectionTable.align(Align.bottomLeft);

        tileSelectionTable.add(buildButton).pad(10);
        tileSelectionTable.row();
        tileSelectionTable.add(moveButton);

        tables.put("tileSelectionTable", tileSelectionTable);

        //-----------------------------------------------------//

        addTablesToStage();
    }

    @Override
    public void init() {
        tables.get("tileSelectionTable").setVisible(false);
    }

    @Override
    public void updateVisibility() {
        if(GameState.instance.boardManager.getSelectedPosition().isValid()) {
            GameState.instance.boardManager.updateOffsets();
            Sprite s = GameState.instance.boardManager.tileFromPosition(GameState.instance.boardManager.getSelectedPosition()).getSprite();
            Table t = tables.get("tileSelectionTable");
            t.setPosition(s.getX() + s.getWidth() + Constants.TILE_MENU_OFFSET - GameState.instance.boardManager.camOffsetX, s.getY() - GameState.instance.boardManager.camOffsetY);
            if(!t.isVisible()) {
                t.setVisible(true);
            }
        }
        else {
            tables.get("tileSelectionTable").setVisible(false);
        }
    }
}