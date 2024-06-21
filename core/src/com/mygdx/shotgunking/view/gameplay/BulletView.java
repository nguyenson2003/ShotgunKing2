package com.mygdx.shotgunking.view.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.shotgunking.view.shared.TPanel;

public class BulletView extends TPanel {
    long startTime;
    int xStart,yStart,xEnd, yEnd;

    public BulletView(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void update() {
        super.update();
        long currentTime = System.currentTimeMillis()-startTime;

        if(currentTime>200){
            getParent().remove(this);
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
//        super.draw(batch);
        long currentTime = System.currentTimeMillis()-startTime;
        batch.end();
        ShapeRenderer debugRenderer = new ShapeRenderer();
        Gdx.gl.glLineWidth(2);
        debugRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(Color.RED);
        if(currentTime<50){
            debugRenderer.line(
                    this.getScreenX() + xStart,
                    Gdx.graphics.getHeight() - this.getScreenY() - yStart,
                    this.getScreenX() + (xEnd+xStart)/2,
                    Gdx.graphics.getHeight() - this.getScreenY() -(yEnd+yStart)/2
            );
        } else if (currentTime<100) {
            debugRenderer.line(
                    this.getScreenX() + (xEnd+xStart)/2,
                    Gdx.graphics.getHeight() - this.getScreenY() -(yEnd+yStart)/2,
                    this.getScreenX() + xEnd,
                    Gdx.graphics.getHeight() - this.getScreenY() -yEnd
            );
        }
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
        batch.begin();

//            g.drawLine(p1.x,p1.y,p12.x,p12.y);
//            try {
//                Thread.sleep(50);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            BoardView.this.repaint();
//            g.drawLine(p12.x,p12.y,p2.x,p2.y);
//            try {
//                Thread.sleep(150);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            BoardView.this.repaint();
    }
}
