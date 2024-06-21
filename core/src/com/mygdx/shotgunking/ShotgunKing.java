package com.mygdx.shotgunking;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.shotgunking.resource.FontManager;
import com.mygdx.shotgunking.resource.ImageManager;
import com.mygdx.shotgunking.view.gameplay.GameplayRoom;
import com.mygdx.shotgunking.view.home.HomeRoom;
import com.mygdx.shotgunking.view.shared.*;


public class ShotgunKing extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TRoom room;
	public static ShotgunKing instance;

	public static ShotgunKing getInstance() {
		return instance;
	}

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	private static int widthScreen;
	private static int heightScreen;

	public static int getWidthScreen() {
		return widthScreen;
	}

	public static int getHeightScreen() {
		return heightScreen;
	}

	@Override
	public void create () {
		instance = this;
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		setRoom(new HomeRoom());
//		setRoom(new TRoom());
//		TGridLayoutPanel center = new TGridLayoutPanel(5,2,10,10);
//		room.add(center);
//		for(int i=0;i<10;i++){
//			TPanel temp = new TPanel();
//			temp.setBackgroundColor(new Color((int)(0xffff00ff)));
//			temp.setOpaque(true);
//			center.add(temp);
//		}
	}

	public void setRoom(TRoom room) {
		this.room = room;
		Gdx.input.setInputProcessor(room);

		resize(widthScreen,heightScreen);
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
//		if(width==widthScreen && height==heightScreen)return;
		widthScreen=width;heightScreen=height;
		batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		int wight1 = width;
		int height1 = (int) (width*1.0/WIDTH*HEIGHT);
		int width2 = (int) (height*1.0/HEIGHT*WIDTH);
		int height2 = height;
		room.setSize(Math.min(wight1,width2),Math.min(height1,height2));
		room.setLocation(width / 2 - room.getWidth() / 2, height / 2 - room.getHeight() / 2);
//		panel.setLocation(100,100);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		room.update();
		TPanelAnimation.updateAll();
		batch.begin();
//		batch.draw(img, 0, 0);
		room.draw(batch);

//		FontManager.instance.default_font.getData().setScale(1);
//		FontManager.instance.default_font.setColor(255,255,255,255);
//		FontManager.instance.default_font.draw(batch,"hello",0,Gdx.graphics.getHeight());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
