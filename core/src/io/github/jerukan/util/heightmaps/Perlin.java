package io.github.jerukan.util.heightmaps;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import io.github.jerukan.util.Constants;
import io.github.jerukan.util.Util;

public class Perlin {

    public static Vector2[][] vectors = new Vector2[Constants.BOARD_WIDTH][Constants.BOARD_HEIGHT];
    public static float max = 0;
    public static float min = 0;

    public static void randomizeVectors() {
        for(int x = 0; x < vectors.length; x++) {
            for(int y = 0; y < vectors[0].length; y++) {
                vectors[x][y] = new Vector2();
                vectors[x][y].set((float)(2 * Math.random() - 1), (float)(2 * Math.random() - 1));
            }
        }
    }

    public static float dotGradient(int x1, int y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        x1 = MathUtils.clamp(x1, 0, Constants.BOARD_WIDTH - 1);
        y1 = MathUtils.clamp(y1, 0, Constants.BOARD_HEIGHT - 1);

        return vectors[x1][y1].x * dx + vectors[x1][y1].y * dy;
    }

    public static float getNoise(float x, float y) {
        //i hav eno idea what i am doing
        //WHAT THE FUCK AM I DOINGNGG
        int intx = (int)Math.floor(x);
        int inty = (int)Math.floor(x);
        int x1 = intx + 1;
        int y1 = inty + 1;

        float weightx = x - intx;
        float weighty = y - inty;
        float n0 = dotGradient(intx, inty, x, y);
        float n1 = dotGradient(x1, inty, x, y);
        float ix1 = MathUtils.lerp(n0, n1, weightx);

        float n2 = dotGradient(intx, inty, x, y);
        float n3 = dotGradient(x1, y1, x, y);
        float ix2 = MathUtils.lerp(n2, n3, weightx);

        float out = MathUtils.lerp(ix1, ix2, weighty);
        //out = Util.map(out, 1, 2, 0, 1);
        if(out > max) {
            max = out;
        }else if(out < min) {
            min = out;
        }
        System.out.println(max);
        System.out.println(min);
        return out;
    }
}
