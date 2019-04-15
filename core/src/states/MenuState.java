package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.balloon.game.BalloonSmash;

public class MenuState extends State implements InputProcessor{

    private BitmapFont font;
    private Texture menubg;


    private Texture playBtn;
    private Texture creditsBtn;
    private Texture exitBtn;
    private Texture highscoreBtn;
    private Texture musiconBtn;
    private Texture musicon2Btn;
    private Texture musicoffBtn;
    private Texture musicoff2Btn;
    private Texture credits;
    private Viewport viewport;
    Vector3 touch = new Vector3();

    private BitmapFont carterone;
    int xCord ;
    int yCord;

    private static final int VIRTUAL_WIDTH = 1080;
    private static final int VIRTUAL_HEIGHT = 1920;
    private static final float ASPECT_RATIO = VIRTUAL_WIDTH / VIRTUAL_HEIGHT;


    public void create()
    {
        font = new BitmapFont();
        font.setColor(Color.RED);
        menubg = new Texture(Gdx.files.internal("menu.png"));

        playBtn = new Texture(Gdx.files.internal("buton/play2.png"));
        creditsBtn = new Texture(Gdx.files.internal("buton/credits2.png"));
        exitBtn = new Texture(Gdx.files.internal("buton/exit2.png"));
        musicoffBtn = new Texture(Gdx.files.internal("buton/musicoff.png"));
        musicoff2Btn = new Texture(Gdx.files.internal("buton/musicoff2.png"));
        musiconBtn = new Texture(Gdx.files.internal("buton/musicon.png"));
        musicon2Btn = new Texture(Gdx.files.internal("buton/musicon2.png"));
        highscoreBtn = new Texture(Gdx.files.internal("buton/highscore2.png"));

        credits = new Texture(Gdx.files.internal("credits.png"));

        carterone = new BitmapFont(Gdx.files.internal("font/carterone/carterone.fnt"), false);
        Gdx.input.setInputProcessor(this);
    }

    public void render(SpriteBatch batch)
    {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();


        batch.setProjectionMatrix(camera.combined);

            if (BalloonSmash.prefs.getBoolean("music", true)) {
                BalloonSmash.music.play();
                BalloonSmash.music.setLooping(true);
            } else {
                BalloonSmash.music.stop();
                BalloonSmash.music.setLooping(true);
            }

        batch.begin();

        batch.draw(menubg, 0, 0);

        if(BalloonSmash.prefs.getBoolean("music", true)){
            batch.draw(musiconBtn, 252, 725);
        }else
            batch.draw(musicoffBtn, 252, 725);

        if(Gdx.input.isTouched()){
            camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0)); //<---

            xCord = (int) touch.x;
            yCord = 1920-(int) touch.y;

            Gdx.app.log("Mouse Event","Click at " + xCord + "," + yCord);

            if(xCord<826 & xCord>255  ) {
                if (yCord >630 & yCord <780){
                    batch.draw(playBtn, 220, 1125);
                }
                else if (yCord >830  & yCord <980){
                    batch.draw(highscoreBtn, 220, 925);
                }
                else if (yCord >1040 & yCord <1195){
                    if(BalloonSmash.prefs.getBoolean("music", true)){
                        BalloonSmash.music.stop();
                        BalloonSmash.music.setLooping(true);
                        batch.draw(musicon2Btn, 220, 725);
                    }else{
                        BalloonSmash.music.play();
                        BalloonSmash.music.setLooping(true);
                        batch.draw(musicoff2Btn, 220, 725);
                    }

                }
                else if (yCord >1230 & yCord <1380){
                    batch.draw(creditsBtn, 220, 525);
                }
                else if (yCord >1430 & yCord <1580){
                    batch.draw(exitBtn, 220, 325);
                }
            }
        }

        batch.end();
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0)); //<---

        xCord = (int) touch.x;
        yCord = 1920-(int) touch.y;

        if(xCord<826 & xCord>255  ) {
            if (yCord >630 & yCord <780){  // play
                BalloonSmash.music.setVolume(0.3f);
                State.manager.PopState();
                State.manager.PushState(new PlayState(1 , 0));
                State.manager.create();
            }
            else if (yCord >830  & yCord <980){  // high score
                //BalloonSmash.music.stop();
                State.manager.PopState();
                State.manager.PushState(new HighScore());
                State.manager.create();
            }
            else if (yCord >1040 & yCord <1195){    // music
                if(BalloonSmash.prefs.getBoolean("music")){
                    BalloonSmash.prefs.putBoolean("music", false);
                    BalloonSmash.prefs.flush();
                }else{
                    BalloonSmash.prefs.putBoolean("music", true);
                    BalloonSmash.prefs.flush();
                }
            }
            else if (yCord >1230 & yCord <1380){   // credits
              //  BalloonSmash.music.stop();
                State.manager.PopState();
                State.manager.PushState(new Credits());
                State.manager.create();
            }
            else if (yCord >1430 & yCord <1580){  // exit
                Gdx.app.exit();
            }
        }

        return false;
    }

    public void dispose()
    {
        font.dispose();
        menubg.dispose();
        playBtn.dispose();
        creditsBtn.dispose();
        exitBtn.dispose();
        highscoreBtn.dispose();
        musiconBtn.dispose();
        musicon2Btn.dispose();
        musicoffBtn.dispose();
        musicoff2Btn.dispose();
        credits.dispose();
        carterone.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {  return false;  }

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
