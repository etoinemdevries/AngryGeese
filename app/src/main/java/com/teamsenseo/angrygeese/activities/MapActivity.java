package com.teamsenseo.angrygeese.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.teamsenseo.angrygeese.AngryGeese;
import com.teamsenseo.angrygeese.R;

/**
 * Map activity
 */
public final class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected final void onCreate(final @Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_maps);

        final SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }

    /**
     * Called when map is ready
     */
    @Override
    public final void onMapReady(final GoogleMap map) {
        AngryGeese.instance = new AngryGeese(getApplication(), map);
    }
}
