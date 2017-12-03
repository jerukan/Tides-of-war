package io.github.jerukan.game.ui.gamescreen.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.jerukan.game.GameState;
import io.github.jerukan.game.ui.ButtonGroup;
import io.github.jerukan.util.Assets;

public class EndTurnMenu extends ButtonGroup {

    private Label currentPlayerLabel;
    private TextButton endTurnButton;

    public EndTurnMenu() {
        currentPlayerLabel = new Label(GameState.instance.getCurrentPlayer().name + "\'s turn", Assets.uiskin, "default");
        Pixmap labelColor = new Pixmap((int)currentPlayerLabel.getWidth(), (int)currentPlayerLabel.getHeight() + 10, Pixmap.Format.RGB888);
        labelColor.setColor(new Color(0.2f, 0.2f, 0.2f, 1f));
        labelColor.fill();
        currentPlayerLabel.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
        labelColor.dispose();

        TextButton genHeights = new TextButton("heights", Assets.uiskin, "default");
        genHeights.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameState.instance.boardManager.resetBoard();
            }
        });
        endTurnButton = new TextButton("End turn", Assets.uiskin, "default");
        endTurnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameState.instance.passTurn();
            }
        });

        table.add(genHeights);
        table.add(currentPlayerLabel).pad(10).row();
        table.add(endTurnButton);
        table.setPosition(Gdx.graphics.getWidth() - 100, 50);
    }

    @Override
    public void updateVisibility() {
        currentPlayerLabel.setText(GameState.instance.getCurrentPlayer().name + "\'s turn");
    }
}
