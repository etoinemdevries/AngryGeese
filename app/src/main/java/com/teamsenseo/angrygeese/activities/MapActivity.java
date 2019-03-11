package com.teamsenseo.angrygeese.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.teamsenseo.angrygeese.R;

import java.util.ArrayList;

/**
 * Map activity
 *
 * @author Robert
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
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        final ArrayList<LatLng> latLngs = new ArrayList<>();
        latLngs.add(new LatLng(52.532327431071, 4.9698428582864));
        latLngs.add(new LatLng(52.5363890459246, 4.97152173643456));
        latLngs.add(new LatLng(52.5359060240486, 4.96928150387894));
        latLngs.add(new LatLng(52.5334104595542, 4.96744278341884));
        latLngs.add(new LatLng(52.5314914623206, 4.96633040367443));
        latLngs.add(new LatLng(52.5319261071537, 4.96651718547247));
        latLngs.add(new LatLng(52.5321208961933, 4.96596108874737));
        latLngs.add(new LatLng(52.532327431071, 4.9698428582864));

        final Polygon polygon = map.addPolygon(new PolygonOptions().addAll(latLngs));
        polygon.setStrokeColor(Color.GREEN);

        drawGrid(latLngs);
    }

    private final void drawGrid(final ArrayList<LatLng> lats) {
        final int[] bounds = new int[3];

        for (LatLng lat : lats) {
            //if(bounds[0] == null || bounds[0] < lat.)
        }
    }
}
