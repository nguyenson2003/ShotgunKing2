package com.mygdx.shotgunking.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.shotgunking.ShotgunKing;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                // Resizable application, uses available space in browser
                return new GwtApplicationConfiguration(true);
                // Fixed size application:
                //return new GwtApplicationConfiguration(480, 320);
//                com.badlogic.gwtref.client.ReflectionCache
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new ShotgunKing();
        }
}