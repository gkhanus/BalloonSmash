package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.balloon.game.BalloonSmash;

/**
 * Created by birkan on 16.05.2016.
 */
public class HighScore  extends State implements InputProcessor {

    private Texture highscore;
    private Texture exitBtn;
    private BitmapFont carterone;
    private static final int VIRTUAL_WIDTH = 1080;
    private static final int VIRTUAL_HEIGHT = 1920;

    private Vector3 touch = new Vector3();
    int xCord;
    int yCord;

    @Override
    public void create() {
        highscore = new Texture(Gdx.files.internal("highscore.png"));
        exitBtn = new Texture(Gdx.files.internal("buton/xbtn2.png"));
        carterone = new BitmapFont(Gdx.files.internal("font/carterone/carterone.fnt"), false);


        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(highscore, 0,0);

        if(Gdx.input.isTouched()){
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0)); //<---

            xCord = (int) touch.x;
            yCord = 1920-(int) touch.y;

            if(xCord>860 & xCord<1014 & yCord>548 & yCord<700){
                batch.draw(exitBtn, 854, 1160);
            }
        }

        int one = BalloonSmash.prefs.getInteger("1", 0);
        int two = BalloonSmash.prefs.getInteger("2", 0);
        int three = BalloonSmash.prefs.getInteger("3", 0);

        carterone.draw(batch, ""+one , 483, 1004);
        carterone.draw(batch, ""+two , 483, 825);
        carterone.draw(batch, ""+three , 483, 645);



        batch.end();

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0)); //<---

        xCord = (int) touch.x;
        yCord = 1920-(int) touch.y;

        if(xCord>860 & xCord<1014 & yCord>548 & yCord<700){
            State.manager.PopState();
            State.manager.PushState(new MenuState());
            State.manager.create();
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void dispose() {
        highscore.dispose();
        exitBtn.dispose();
        carterone.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
