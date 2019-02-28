package com.teamsenseo.angrygeese;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Main Angry Geese class
 * @author Robert
 */
public final class AngryGeese extends FragmentActivity implements OnMapReadyCallback {
    /** Angry Geese instance */
    public static AngryGeese instance;

    /** Gets the application instance */
    public final Application app;

    /** Google map */
    private GoogleMap map;

    protected AngryGeese() {
        /* Initializes Angry Geese instance */
        instance = this;

        /* Sets the application instance */
        this.app = getApplication();
    }

    /**
     * Called when interface is created
     */
    @Override
    protected final void onCreate(final @Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_maps);

        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Called when map is ready
     */
    @Override
    public final void onMapReady(final GoogleMap map) {
        this.map = map;
    }

    /** Gets the google map */
    public final GoogleMap getMap() {
        return this.map;
    }
}
