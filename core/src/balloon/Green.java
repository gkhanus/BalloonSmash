package balloon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by birkan on 12.05.2016.
 */
public class Green {

    public int point;
    public int hiz;
    public int xYon;
    public int yYon;
    public Rectangle cord = new Rectangle();
    public long createTime;

    public static int time = 3000 / 2;
    public static int maxValue = 5;
    public static Texture texture = new Texture(Gdx.files.internal("green.png"));

    public Green() {
        point = 5;
        hiz = 800;
        xYon = 1;
        yYon = 1;
        cord.x = MathUtils.random(0, 1080 - texture.getWidth());
        cord.y = -256;
        createTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
    }

    public void move(int level, int saniye){
        float deltaTime = Gdx.graphics.getDeltaTime();
        cord.y += yYon * hiz * deltaTime * (Math.log10(level+5)) * (1 + (saniye%30)*0.04);
    }
}
