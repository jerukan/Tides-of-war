package io.github.jerukan.game.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.ui.buttongroups.TileSelectMenu;
import io.github.jerukan.game.ui.buttongroups.UnitBuildMenu;
import io.github.jerukan.util.Assets;
import io.github.jerukan.util.Constants;

public class GameScreen extends Screen {

    private final TextButton button;

    private TileSelectMenu tileSelectMenu;
    private UnitBuildMenu unitBuildMenu;

    public GameScreen(Stage stage) {
        super(stage);

        //-----------------------------------------------------//

        Table testTable = new Table();
        button = new TextButton("Click me", Assets.uiskin, "default");
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

        tileSelectMenu = new TileSelectMenu();

        tables.put("tileSelectionTable", tileSelectMenu.getTable());

        //-----------------------------------------------------//

        unitBuildMenu = new UnitBuildMenu();
        tables.put("unitsTable", unitBuildMenu.getTable());

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