package com.teamsenseo.angrygeese.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
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
        final ArrayList<LatLng> lats = new ArrayList<>();

        /* Hardcoded locs */
        lats.add(new LatLng(52.7511461789121, 6.12571087666791));
        lats.add(new LatLng(52.7517247464209, 6.12863553192948));
        lats.add(new LatLng(52.7508871402523, 6.12921381476182));
        lats.add(new LatLng(52.7506688873892, 6.12962483326472));
        lats.add(new LatLng(52.75098321933, 6.13109943567541));
        lats.add(new LatLng(52.7520951970781, 6.13033625088224));
        lats.add(new LatLng(52.7521639794626, 6.13083508932424));
        lats.add(new LatLng(52.7501572372002, 6.13211626493806));
        lats.add(new LatLng(52.7473564299272, 6.13390512267482));
        lats.add(new LatLng(52.7475815692132, 6.13469093241193));
        lats.add(new LatLng(52.7458985365383, 6.12904645154673));
        lats.add(new LatLng(52.7511461789121, 6.12571087666791));

        /* Draw field polygon */
        final Polygon polygon = map.addPolygon(new PolygonOptions().addAll(lats));
        polygon.setFillColor(Color.argb(120, 65, 100, 65));
        polygon.setStrokeColor(Color.GREEN);
        polygon.setStrokeWidth(0.7F);

        /* Set zoom preferences */
        map.setMaxZoomPreference(19);
        map.setMinZoomPreference(16);

        if (!lats.isEmpty()) {
            /* Map bounds */
            final double[] bounds = {getLeft(lats).longitude, getTop(lats).latitude, getRight(lats).longitude, getBottom(lats).latitude};

            /* Move camera */
            map.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(bounds[1], bounds[0]), new LatLng(bounds[3], bounds[2])));
            map.moveCamera(CameraUpdateFactory.newLatLng(lats.get(0)));

            /* Draw grid over field */
            drawGrid(map, bounds);
        }
    }

    /* Draw grid over map */
    private final void drawGrid(final GoogleMap map, final double[] bounds) {
        final ArrayList<LatLng> gridLats = new ArrayList<>();
        final double longitude = 0.0001424542009743867;
        final double latitude = 0.00008988925643607076;

        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                gridLats.add(new LatLng(bounds[1] + latitude * y, bounds[0] + longitude * x));
                gridLats.add(new LatLng(bounds[1] + latitude * (y + 1), bounds[0] + longitude * x));
                gridLats.add(new LatLng(bounds[1] + latitude * (y + 1), bounds[0] + longitude * (x + 1)));
                gridLats.add(new LatLng(bounds[1] + latitude * y, bounds[0] + longitude * (x + 1)));
            }
        }

        System.out.println("Added " + gridLats.size() + " points");
        final ArrayList<LatLng> points = new ArrayList<>();

        for (final LatLng point : gridLats) {
            points.add(point);
            if (points.size() < 3) continue;

            final Polygon polygon = map.addPolygon(new PolygonOptions().addAll(points));
            polygon.setStrokeColor(Color.RED);
            polygon.setStrokeWidth(0.7F);
            points.clear();
        }

        System.out.println("Finished!");
    }

    private final LatLng getTop(final ArrayList<LatLng> lats) {
        LatLng lat = lats.get(0);

        for (final LatLng lat2 : lats) {
            if (lat2.latitude < lat.latitude)
                lat = lat2;
        }

        return lat;
    }

    private final LatLng getBottom(final ArrayList<LatLng> lats) {
        LatLng lat = lats.get(0);

        for (final LatLng lat2 : lats) {
            if (lat2.latitude > lat.latitude)
                lat = lat2;
        }

        return lat;
    }

    private final LatLng getLeft(final ArrayList<LatLng> lats) {
        LatLng lat = lats.get(0);

        for (final LatLng lat2 : lats) {
            if (lat2.longitude < lat.longitude)
                lat = lat2;
        }

        return lat;
    }

    private final LatLng getRight(final ArrayList<LatLng> lats) {
        LatLng lat = lats.get(0);

        for (final LatLng lat2 : lats) {
            if (lat2.longitude > lat.longitude)
                lat = lat2;
        }

        return lat;
    }
}
