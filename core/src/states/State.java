package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class State {

    protected OrthographicCamera camera;
    public static StateManager manager = new StateManager();

    public State(){

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1080, 1920);

    }

    public abstract void create();
    public abstract void render(SpriteBatch batch);
    public abstract void dispose();


    public abstract void resize(int width, int height);
}
