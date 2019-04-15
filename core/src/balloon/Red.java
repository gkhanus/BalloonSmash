package balloon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Red {

    public int point;
    public int hiz;
    public int xYon;
    public int yYon;
    public Rectangle cord = new Rectangle();
    public long createTime;

    public static int time = 3500 / 2;
    public static int maxValue = 7;

    public Red() {
        point = 10;
        hiz = 1000;
        xYon = MathUtils.randomSign();
        yYon = 1;
        cord.x = MathUtils.random(0, 1080);
        cord.y = -256;
        createTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
    }

    public void move(int level, int saniye){

        float deltaTime = Gdx.graphics.getDeltaTime();

        cord.y += yYon * hiz * deltaTime * (Math.log10(level+5)) * (1 + (saniye%30)*0.01);

        cord.x += xYon * hiz * deltaTime * (Math.log10(level+5)) * (1 + (saniye%30)*0.01);

        if(cord.x > 1080 - 224 + 5){
            xYon = -1;
        }else if (cord.x < - 5){
            xYon = 1;
        }

    }

    public void changeDirection(){

      //  if( MathUtils.random(0, 5) == 3 )
            yYon = MathUtils.randomSign();   // -1 ya da 1 dönzerir

       // if( MathUtils.random(10, 15) == 13 )
            xYon = MathUtils.randomSign();

    }

}