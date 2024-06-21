package com.mygdx.shotgunking.view.shared;


import com.badlogic.gdx.graphics.Color;

import java.util.HashMap;

public class TPanelAnimation {
    private TPanelAnimation() {
    }

    public static void setLocation(TPanel a, int x, int y, int ms) {
        new SetLocationAnimation(a, x, y, ms);
    }

    public static void setSize(TPanel a, int width, int height, int ms) {
        new SetSizeAnimation(a, width, height, ms);
    }

    public static void setBackground(TPanel a, Color c, int ms) {
        new SetBackgroundAnimation(a, c, ms);
    }

    public static void shake(TPanel a,int xShake,int yShake,int ms,int delayMs){new ShakeAnimation(a,xShake,yShake,ms,delayMs);}
    public static void shakeInfinity(TPanel a,int xShake,int yShake,int delayMs){new ShakeAnimation(a,xShake,yShake,Integer.MAX_VALUE,delayMs);}
    public static void shakeStop(TPanel a){new ShakeAnimation(a,0,0,-1,10).stop();}
    public static void twink(TPanel a,int ms,int delayMs){new TwinklingAnimation(a,ms,delayMs);}
    public static void updateAll(){
        for(HashMap<Class<? extends Setnable>,Setnable> v1 : Setnable.runningMap.values()){
            for(Setnable v2:v1.values()){
                if(v2.stop)continue;
                long currentTime = System.currentTimeMillis()-v2.startTime;
                if(currentTime>v2.ms){
                    v2.stop();
                    v2.end();
                    continue;
                }
                v2.update(currentTime);
            }
        }
    }
    private abstract static class Setnable {
        private static HashMap<TPanel,HashMap<Class<? extends Setnable>,Setnable>> runningMap = new HashMap<>();
        TPanel a;
        int ms;
        long startTime;
        boolean stop = false;
//        final int delay_fpMs = 1000 / 60;
        public Setnable(TPanel a,int ms){
            this.a=a;
            this.ms=ms;
            this.startTime = System.currentTimeMillis();
            this.start();
        }

        /**
         * @return trả về Setnable đang chạy của TPanel a
         */
        private Setnable getRunning(){
            if(runningMap.get(a)==null)return null;
            return runningMap.get(a).get(this.getClass());
        }

        private void setRunning(Setnable s){
            runningMap.computeIfAbsent(a, k -> new HashMap<>());
            runningMap.get(a).put(this.getClass(),this);
        }

        public void stop() {
            stop = true;
        }
        private void start() {
            if (getRunning() != null) {
                getRunning().stop();
            }
            stop = false;
            setRunning(this);
        }
        abstract void update(long currentTime);
        abstract void end();
    }

    private static class SetLocationAnimation extends Setnable {
        private int x_start, y_start, x_end, y_end;

        public SetLocationAnimation(TPanel a, int x, int y, int ms) {
            super(a,ms);
            this.x_start = a.getX();
            this.y_start = a.getY();
            this.x_end = x;
            this.y_end = y;
        }
        @Override
        void update(long currentTime) {
            a.setLocation(
                    (int) ((x_start - x_end) / 2 * Math.cos(Math.PI / ms * currentTime)) + x_start
                            + (x_end - x_start) / 2,
                    (int) ((y_start - y_end) / 2 * Math.cos(Math.PI / ms * currentTime)) + y_start
                            + (y_end - y_start) / 2);
        }
        @Override
        void end() {
            a.setLocation(x_end, y_end);
        }
    }

    private static class SetSizeAnimation extends Setnable {
        private int width_start, height_start, width_end, height_end;

        public SetSizeAnimation(TPanel a, int width, int height, int ms) {
            super(a,ms);
            this.width_start = a.getWidth();
            this.height_start = a.getHeight();
            this.width_end = width;
            this.height_end = height;
        }

        @Override
        void update(long currentTime) {
            a.setSize(
                    (int) ((width_start - width_end) / 2 * Math.cos(Math.PI / ms * currentTime)) + width_start
                            + (width_end - width_start) / 2,
                    (int) ((height_start - height_end) / 2 * Math.cos(Math.PI / ms * currentTime)) + height_start
                            + (height_end - height_start) / 2);
        }

        @Override
        void end() {
            a.setSize(width_end, height_end);

        }
    }

    private static class SetBackgroundAnimation extends Setnable {
        private Color color_start, color_end;

        public SetBackgroundAnimation(TPanel a, Color c, int ms) {
            super(a,ms);
            this.color_start = a.getBackgroundColor();
            this.color_end = c;
        }

        @Override
        void update(long currentTime) {
            a.setBackgroundColor(
                    new Color(
                            ((color_end.r - color_start.r) * currentTime / ms
                                    + color_start.r),
                            ((color_end.g - color_start.g) * currentTime / ms
                                    + color_start.g),
                            ((color_end.b - color_start.b) * currentTime / ms
                                    + color_start.b),
                            ((color_end.a - color_start.a) * currentTime / ms
                                    + color_start.a)
                    )
            );
        }

        @Override
        void end() {
            a.setBackgroundColor(color_end);
        }
    }

    private static class ShakeAnimation extends Setnable{
        @Override
        void update(long currentTime) {
            a.setLocation(
                    (int) (x_start+x_shake*(currentTime/delayMs%2)),
                    (int) (y_start+y_shake*(currentTime/delayMs%2))
            );
        }

        @Override
        void end() {
            a.setLocation(x_start,y_start);
//            a.setLocation(0,0);
        }

        private int x_start, y_start,x_shake,y_shake,delayMs;

        public ShakeAnimation(TPanel a, int x_shake,int y_shake, int ms,int delayMs) {
            super(a,ms);
            this.x_start=a.getX();
            this.y_start=a.getY();
            this.x_shake=x_shake;
            this.y_shake=y_shake;
            this.delayMs=delayMs;
        }
    }

    private static class TwinklingAnimation extends Setnable{
        @Override
        void update(long currentTime) {
            if(ms==Integer.MAX_VALUE)startTime+=currentTime;
            a.setVisible(currentTime/delayMs%2==1);
        }

        @Override
        void end() {
            a.setVisible(true);
        }

        int delayMs;

        public TwinklingAnimation(TPanel a, int ms,int delayMs) {
            super(a,ms);
            this.delayMs=delayMs;
        }
    }

}
