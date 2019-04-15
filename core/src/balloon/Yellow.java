package balloon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

/**
 * Created by birkan on 11.05.2016.
 */
public class Yellow {

    public int point;

    public Rectangle cord = new Rectangle();
    public long createTime;

    public static int time = 4000 / 2;
    public static int maxValue = 3;
    public static Texture texture = new Texture(Gdx.files.internal("yellow.png"));

    public Yellow() {
        point = 20;
        cord.x = MathUtils.random(texture.getWidth(), 1080 - texture.getWidth());
        cord.y = MathUtils.random(texture.getHeight(), 1920 - texture.getHeight());
        createTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
    }

}
