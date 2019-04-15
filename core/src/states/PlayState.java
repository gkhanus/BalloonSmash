package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.balloon.game.BalloonSmash;

import balloon.Black;
import balloon.Green;
import balloon.Red;
import balloon.Yellow;

public class PlayState extends State implements InputProcessor{

    private static final int VIRTUAL_WIDTH = 1080;
    private static final int VIRTUAL_HEIGHT = 1920;

    private Vector3 touch = new Vector3();

    private Texture background;
    private Texture pointTexture;
    private Texture levelTexture;
    private Texture levelup;
    private Texture gameover;
    private Texture star1;
    private Texture star2;
    private Texture star3;
    private Texture reloadBtn;
    private Texture menuBtn;
    private Texture forwardBtn;

    private Texture RedTexture ;
    private Texture YellowTexture ;
    private Texture GreenTexture ;
    private Texture BlackTexture ;


    private BitmapFont size32;
    private BitmapFont size40;
    private BitmapFont size75;
    private BitmapFont size100;
    private BitmapFont size120;
    private BitmapFont size200;
    private BitmapFont size230;
    private BitmapFont carterone;
    private BitmapFont carterone2;

    private Array<Red> redList;
    private Array<Yellow> yellowList;
    private Array<Green> greenList;
    private Array<Black> blackList;

    long startTime = 0;
    long redLastCreate = 0;
    long yellowLastCreate = 0;
    long greenLastCreate = 0;
    long blackLastCreate = 0;

    long redTime = 0;
    long yellowTime = 0;
    long greenTime = 0;
    long blackTime = 0;


    int index;
    int index2;
    boolean success;

    float deltaTime;
    long startSecond;
    long lastSecond;

    private Sound popMusic;

    String message = "";
    Rectangle inputPositon;

    int score;
    int point;
    int level;
    int redCount;
    int yellowCount;
    int greenCount;
    int blackCount;
    int reloadFlag;
    int forwardFlag;
    int menuFlag;
    int say;

    int xCord;
    int yCord;

    boolean isOver ;

    public PlayState(int level, int score) {
        this.level = level;
        this.score = score;
    }

    public void create()
    {
        popMusic = Gdx.audio.newSound(Gdx.files.internal("pop.mp3"));

        say = -50;
        success = false;
        isOver = false;
        reloadFlag = 0;
        forwardFlag = 0;
        menuFlag = 0;
        point = 0;
        redCount = 0;
        greenCount = 0;
        yellowCount = 0;
        blackCount = 0;

        Gdx.input.setInputProcessor(this);
        inputPositon = new Rectangle();

        RedTexture = new Texture(Gdx.files.internal("red.png"));
        YellowTexture = new Texture(Gdx.files.internal("yellow.png"));
        GreenTexture = new Texture(Gdx.files.internal("green.png"));
        BlackTexture = new Texture(Gdx.files.internal("black.png"));

        background = new Texture(Gdx.files.internal("bg.png"));
        pointTexture = new Texture(Gdx.files.internal("point.png"));
        levelTexture = new Texture(Gdx.files.internal("level.png"));
        levelup = new Texture(Gdx.files.internal("levelup.png"));
        gameover = new Texture(Gdx.files.internal("gameover.png"));
        star1 = new Texture(Gdx.files.internal("star1.png"));
        star2 = new Texture(Gdx.files.internal("star2.png"));
        star3 = new Texture(Gdx.files.internal("star3.png"));
        reloadBtn = new Texture(Gdx.files.internal("buton/reloadbtn.png"));
        forwardBtn = new Texture(Gdx.files.internal("buton/forwardbtn.png"));
        menuBtn = new Texture(Gdx.files.internal("buton/menubtn.png"));


        redList = new Array<Red>();
        yellowList = new Array<Yellow>();
        greenList = new Array<Green>();
        blackList = new Array<Black>();

        size32 = new BitmapFont(Gdx.files.internal("font/size32/size32.fnt"), false);
        size40 = new BitmapFont(Gdx.files.internal("font/size40/size40.fnt"), false);
        size75 = new BitmapFont(Gdx.files.internal("font/size75/size75.fnt"), false);
        size100 = new BitmapFont(Gdx.files.internal("font/size100/size100.fnt"), false);
        size120 = new BitmapFont(Gdx.files.internal("font/size120/size120.fnt"), false);
        size200 = new BitmapFont(Gdx.files.internal("font/size200/size200.fnt"), false);
        size230 = new BitmapFont(Gdx.files.internal("font/size230/size230.fnt"), false);
        carterone = new BitmapFont(Gdx.files.internal("font/carterone/carterone.fnt"), false);
        carterone2 = new BitmapFont(Gdx.files.internal("font/carterone/carterone2.fnt"), false);
        startSecond = System.currentTimeMillis()/1000;
    }


    public void render(SpriteBatch batch)
    {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        lastSecond = System.currentTimeMillis()/1000;
        startTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
        deltaTime = Gdx.graphics.getDeltaTime();


        batch.begin();    // çizime başla

        batch.draw(background, 0,0);
        size230.draw(batch, ""+ (30  - (lastSecond - startSecond)) , 540+10  , 960 + 150f , 0f, 1, true);

        for(Red red : redList ) {
            batch.draw(RedTexture, red.cord.x, red.cord.y);
        }

        for(Yellow yellow : yellowList ) {
            batch.draw(YellowTexture, yellow.cord.x, yellow.cord.y);
        }

        for(Green green : greenList ) {
            batch.draw(GreenTexture, green.cord.x, green.cord.y);
        }

        for(Black black : blackList) {
            batch.draw(BlackTexture, black.cord.x, black.cord.y);
        }


        batch.draw(pointTexture, 25, 1920-204);
        batch.draw(levelTexture, 633, 1920-204);
        carterone.draw(batch, ""+level, 230, 1920 - 130);
        carterone.draw(batch, ""+point, 870, 1920 - 130);

        batch.end();    // çizimi bitir


        if (((startTime - redLastCreate > Red.time) & (redList.size < Red.maxValue))) {
          if( MathUtils.random(0, 5) == 3 ){

            Red redBalloon = new Red();
            redList.add(redBalloon);  // kırmızı diziye yeni balon ekle

            redLastCreate = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
          }
        }

        if (((startTime - yellowLastCreate > Yellow.time) & (yellowList.size < Yellow.maxValue))) {
            if( MathUtils.random(5, 10) == 8 ){

                Yellow yellowBalloon = new Yellow();
                yellowList.add(yellowBalloon);  // sarı diziye yeni balon ekle

                yellowLastCreate = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
            }
        }

        if (((startTime - greenLastCreate > Green.time) & (greenList.size < Green.maxValue))) {
            if( MathUtils.random(10, 15) == 13 ){

                Green greenBalloon = new Green();
                greenList.add(greenBalloon);  // yeşil diziye yeni balon ekle

                greenLastCreate = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
            }
        }

        if (((startTime - blackLastCreate > Black.time) & (blackList.size < Black.maxValue))) {
            if( MathUtils.random(15, 30) == 20 ){

                Black blackBalloon = new Black();
                blackList.add(blackBalloon);  // siyah diziye yeni balon ekle

                blackLastCreate = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
            }
        }


        index = 0;

        for(Red balloon : redList ) {

            balloon.move(level, (int) (lastSecond - startSecond));  // kırmızı balonu hareket ettir

            if ((startTime - redTime   > 3000)){
                balloon.changeDirection();   //kırmızı balonun yönünü değiştir
                redTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
            }

            if(balloon.cord.y > 1920 || balloon.cord.y < -256){
                redList.removeIndex(index);  //kırmızı balon ekrandan çıkınca diziden sil
                break;
            }
            index++;
        }

        index = 0;

        for(Yellow balloon : yellowList ) {

            if ((startTime - balloon.createTime > 1000)){  // oluşturulma zamanından 1 sn sonra sil
                yellowList.removeIndex(index);
                break;
            }
            index++;
        }

        index = 0;

        for(Green balloon : greenList ) {

            balloon.move(level,  (int) (lastSecond - startSecond));  // yeşil balonu hareket ettir

            if ((startTime - greenTime   > 3000)){
                if( MathUtils.random(0, 5) == 1 ){

                    Black newBlackBalloon = new Black();
                    newBlackBalloon.cord.x = balloon.cord.x;
                    newBlackBalloon.cord.y = balloon.cord.y;

                    blackList.add(newBlackBalloon);
                    greenList.removeIndex(index);
                }
                greenTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
            }

            if( balloon.cord.y > 1920 ){
                greenList.removeIndex(index);  //yeşil balon ekrandan çıkınca diziden sil
            }
            index++;
        }

        index = 0;

        for(Black balloon : blackList ) {

            balloon.move(level,  (int) (lastSecond - startSecond));  // siyah  balonu hareket ettir

            if ((startTime - blackTime   > 3000)){
                if( MathUtils.random(0, 5) == 1 ){

                    Green newGreenBalloon = new Green();
                    newGreenBalloon.cord.x = balloon.cord.x;
                    newGreenBalloon.cord.y = balloon.cord.y;

                    greenList.add(newGreenBalloon);
                    blackList.removeIndex(index);
                    break;
                }
                blackTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
            }

            if( balloon.cord.y > 1920 ){
                blackList.removeIndex(index);  //siyah balon ekrandan çıkınca diziden sil
                break;
            }
            index++;
        }


        if(30  - (lastSecond - startSecond) <= 0){

            isOver = true;
            startTime = TimeUtils.nanosToMillis(TimeUtils.nanoTime());

            if (say<0) say++;

            if(say < point & say>=0){ score++; say++; }
            if(say < point & say>=0){ score++; say++; }
            if(say < point & say>=0){ score++; say++; }
            if(say < point & say>=0){ score++; say++; }
            if(say < point & say>=0){ score++; say++; }

            if(point >= 100 & redCount > 0 & yellowCount > 0 & greenCount > 0){

                success = true;
                batch.begin();
                batch.draw(levelup, 67, 1920-1450);

                if(point>=100 & point<200){
                    batch.draw(star1, 272, 1000);
                }else if(point>=200 & point<300){
                    batch.draw(star1, 272, 1000);
                    batch.draw(star2, 447, 1022);
                }else if(point>=300){
                    batch.draw(star1, 272, 1000);
                    batch.draw(star2, 447, 1022);
                    batch.draw(star3, 662, 1000);
                }

                carterone2.draw(batch, ""+level, 610, 935);
                carterone2.draw(batch, ""+score, 610, 840);


                batch.end();

            }else {
                success = false;
                batch.begin();
                batch.draw(gameover, 67, 1920-1450);

                if(score>=1000 & score<3000){
                    batch.draw(star1, 272, 1000);
                }else if(score>=3000 & score<5000){
                    batch.draw(star1, 272, 1000);
                    batch.draw(star2, 447, 1022);
                }else if(score>=5000){
                    batch.draw(star1, 272, 1000);
                    batch.draw(star2, 447, 1022);
                    batch.draw(star3, 662, 1000);
                }

                carterone2.draw(batch, ""+level, 610, 935);
                carterone2.draw(batch, ""+score, 610, 840);

                batch.end();

            }


            batch.begin();


            if(Gdx.input.isTouched()){
                camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0)); //<---

                xCord = (int) touch.x;
                yCord = 1920-(int) touch.y;

                if(success){
                    if(xCord>151 & xCord<345 & yCord>1184 & yCord<1382)
                        batch.draw(menuBtn, 132, 450);
                    else if(xCord>449 & xCord<643 & yCord>1184 & yCord<1382)
                        batch.draw(reloadBtn, 430, 450);
                    else if(xCord>745 & xCord<939 & yCord>1184 & yCord<1382)
                        batch.draw(forwardBtn, 722, 450);
                }else{
                    if(xCord>151 & xCord<345 & yCord>1184 & yCord<1382)
                        batch.draw(menuBtn, 132, 450);
                    else if(xCord>745 & xCord<939 & yCord>1184 & yCord<1382)
                        batch.draw(reloadBtn, 722, 450);
                }
            }

            batch.end();

        }


    }

    @Override
    public void resize(int width, int height) {

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

        camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0)); //<---

        xCord = (int) touch.x;
        yCord = (int) touch.y;

        inputPositon.x = xCord ;
        inputPositon.y = yCord;

        inputPositon.width = 25;
        inputPositon.height = 25;

        if(!isOver){

        Rectangle rct = new Rectangle();
        rct.height = RedTexture.getHeight();
        rct.width = RedTexture.getWidth();

        index2 = 0;

        for(Red r : redList){
            rct.x = r.cord.x;
            rct.y = r.cord.y;

            if(rct.overlaps(inputPositon)){
                popMusic.play();
                redCount++;
                point += r.point;
                redList.removeIndex(index2);
                break;
            }
            index2++;
        }

        index2 = 0;

        for(Yellow r : yellowList){

            rct.x = r.cord.x;
            rct.y = r.cord.y;

            if(rct.overlaps(inputPositon)){
                popMusic.play();
                yellowCount++;
                point += r.point;
                yellowList.removeIndex(index2);
                break;
            }
            index2++;
        }

        index2 = 0;

        for(Green r : greenList){

            rct.x = r.cord.x;
            rct.y = r.cord.y;

            if(rct.overlaps(inputPositon)){
                popMusic.play();
                greenCount++;
                point += r.point;
                greenList.removeIndex(index2);
                break;
            }
            index2++;
        }

        index2 = 0;

        for(Black r : blackList){

            rct.x = r.cord.x;
            rct.y = r.cord.y;

            if(rct.overlaps(inputPositon)){
                popMusic.play();
                blackCount++;
                point += r.point;
                blackList.removeIndex(index2);
                break;
            }
            index2++;
        }

    }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        camera.unproject(touch.set(Gdx.input.getX(), Gdx.input.getY(), 0)); //<---

        xCord = (int) touch.x;
        yCord = 1920-(int) touch.y;

      if(isOver){
        if( success){
            if(xCord>151 & xCord<345 & yCord>1184 & yCord<1382)
            {
                BalloonSmash.music.setVolume(1f);
                State.manager.PopState();
                State.manager.PushState(new MenuState());
                State.manager.create();

                // highscore işlemleri

                int one = BalloonSmash.prefs.getInteger("1" ,0);
                int two = BalloonSmash.prefs.getInteger("2" ,0);
                int three = BalloonSmash.prefs.getInteger("3",0);

                if(say == point){          //1 kere gir
                    if(score > one){
                        BalloonSmash.prefs.putInteger("1", score);
                        BalloonSmash.prefs.putInteger("2", one);
                        BalloonSmash.prefs.putInteger("3", two);
                    }else if(score < one & score > two){
                        BalloonSmash.prefs.putInteger("2", score);
                        BalloonSmash.prefs.putInteger("3", two);
                    }else if(score < two & score > three){
                        BalloonSmash.prefs.putInteger("3", score);
                    }
                    BalloonSmash.prefs.flush();
                }

            }
            else if(xCord>449 & xCord<643 & yCord>1184 & yCord<1382)
            {
                int deger;
                if(score-point < 0){
                    deger = 0;
                }else
                    deger = score-point;

                State.manager.PopState();
                State.manager.PushState(new PlayState(level,deger ));
                State.manager.create();
            }
            else if(xCord>745 & xCord<939 & yCord>1184 & yCord<1382)
            {
                State.manager.PopState();
                State.manager.PushState(new PlayState(++level, score));
                State.manager.create();
            }

        }else{

            // highscore işlemleri

            int one = BalloonSmash.prefs.getInteger("1" ,0);
            int two = BalloonSmash.prefs.getInteger("2" ,0);
            int three = BalloonSmash.prefs.getInteger("3",0);

            if(say == point){          //1 kere gir
                if(score > one){
                    BalloonSmash.prefs.putInteger("1", score);
                    BalloonSmash.prefs.putInteger("2", one);
                    BalloonSmash.prefs.putInteger("3", two);
                }else if(score < one & score > two){
                    BalloonSmash.prefs.putInteger("2", score);
                    BalloonSmash.prefs.putInteger("3", two);
                }else if(score < two & score > three){
                    BalloonSmash.prefs.putInteger("3", score);
                }
                BalloonSmash.prefs.flush();
            }

            if(xCord>151 & xCord<345 & yCord>1184 & yCord<1382)
            {
                BalloonSmash.music.setVolume(1f);
                State.manager.PopState();
                State.manager.PushState(new MenuState());
                State.manager.create();
            }
            else if(xCord>745 & xCord<939 & yCord>1184 & yCord<1382)
            {
                State.manager.PopState();
                State.manager.PushState(new PlayState(1, 0));
                State.manager.create();
            }
        }
      }
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


    public void dispose() {

        popMusic.dispose();
        background.dispose();
        pointTexture.dispose();
        levelTexture.dispose();
        levelup.dispose();
        gameover.dispose();
        star1.dispose();
        star2.dispose();
        star3.dispose();
        reloadBtn.dispose();
        menuBtn.dispose();
        forwardBtn.dispose();
        RedTexture.dispose();
        GreenTexture.dispose();
        YellowTexture.dispose();
        BlackTexture.dispose();


        size32.dispose();
        size40.dispose();
        size75.dispose();
        size100.dispose();
        size120.dispose();
        size200.dispose();
        size230.dispose();
        carterone.dispose();
        carterone2.dispose();

    }

}

