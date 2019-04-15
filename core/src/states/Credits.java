package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by birkan on 16.05.2016.
 */
public class Credits extends State implements InputProcessor {

    private Texture credits;
    private Texture birkan;
    private Texture gokhan;
    private Texture erdogan;
    private Texture exitBtn;

    private Vector3 touch = new Vector3();

    private BitmapFont carterone;
    int xCord;
    int yCord;
    private static final int VIRTUAL_WIDTH = 1080;
    private static final int VIRTUAL_HEIGHT = 1920;

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void create() {
        credits = new Texture(Gdx.files.internal("credits.png"));
        birkan = new Texture(Gdx.files.internal("buton/88.png"));
        gokhan = new Texture(Gdx.files.internal("buton/38.png"));
        erdogan = new Texture(Gdx.files.internal("buton/30.png"));
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

        batch.draw(credits, 0,0);


        if(Gdx.input.isTouched()){
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0)); //<---

            xCord = (int) touch.x;
            yCord = 1920-(int) touch.y;

            if(xCord>145 & xCord<945 ){
                if(yCord>790 & yCord<927){
                    batch.draw(birkan, 187, 932);
                }else if(yCord>992 & yCord<1130){
                    batch.draw(gokhan, 187, 712);
                }if(yCord>1200 & yCord<1330){
                    batch.draw(erdogan, 187, 495);
                }
            }

            if(xCord>860 & xCord<1014 & yCord>548 & yCord<700){
                batch.draw(exitBtn, 854, 1160);
            }

        }
       // carterone.draw(batch, "X : " + xCord + " Y : "+yCord, 540, 1000 );

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
    public void dispose() {
        credits.dispose();
        carterone.dispose();
        birkan.dispose();
        gokhan.dispose();
        erdogan.dispose();
        exitBtn.dispose();
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
