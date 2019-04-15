package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Stack;


public class StateManager {

    public static Stack<State> states;

    public StateManager()
    {
        states = new Stack<State>();
    }

    public void create()
    {
        states.peek().create();
    }

    public void render(SpriteBatch batch)
    {
        states.peek().render(batch);
    }

    public void resize(int width, int height){
        states.peek().resize( width, height);
    }

    public void dispose()
    {
        states.peek().dispose();
    }

    public void PushState(State state)
    {
        states.push(state);
    }

    public void PopState()
    {
        states.pop();
    }

}