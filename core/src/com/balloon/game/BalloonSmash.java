package com.balloon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import states.MenuState;
import states.State;
import states.StateManager;

public class BalloonSmash extends Game {

	public static StateManager manager;
	private SpriteBatch batch;
	public static Preferences prefs;

	private static final int VIRTUAL_WIDTH = 1080;
	private static final int VIRTUAL_HEIGHT = 1920;
	public static Music music;

	public void create() {
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		prefs = Gdx.app.getPreferences("preferences");

		State.manager.PushState(new MenuState());
		State.manager.create();
		batch = new SpriteBatch();
	}

	public void render() {
		State.manager.render(batch);
	}

	public void dispose() {
		music.dispose();
		State.manager.dispose();
		batch.dispose();
	}

	public void resize(int width, int height){

		State.manager.resize(width, height);

	}
}
