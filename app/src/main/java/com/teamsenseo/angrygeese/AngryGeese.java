package com.teamsenseo.angrygeese;

import android.app.Application;

import com.google.android.gms.maps.GoogleMap;

/**
 * Main Angry Geese class
 *
 * @author Robert
 */
public final class AngryGeese {
    /**
     * Angry Geese instance
     */
    public static AngryGeese instance;

    /**
     * Application instance
     */
    public final Application app;

    /**
     * Google map
     */
    public final GoogleMap map;

    public AngryGeese(final Application app, final GoogleMap map) {
        log("Initializing");
        this.app = app;
        this.map = map;
    }

    /**
     * Logs a message to console
     */
    public static final void log(final String msg) {
        System.out.println("[Angry Geese] " + msg);
    }
}
