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
import com.google.maps.android.PolyUtil;
import com.teamsenseo.angrygeese.R;

import java.util.ArrayList;

/**
 * Map activity
 *
 * @author Robert
 */
public final class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnPolygonClickListener {
    private GoogleMap map;
    private final ArrayList<Polygon> polygons = new ArrayList<>();

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
        this.map = map;
        this.map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        final ArrayList<LatLng> lats = new ArrayList<>();

        /* Hardcoded points */
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
        final Polygon polygon = this.map.addPolygon(new PolygonOptions().addAll(lats));
        polygon.setFillColor(Color.argb(120, 65, 100, 65));
        polygon.setStrokeColor(Color.GREEN);
        polygon.setStrokeWidth(0.7F);

        /* Set zoom preferences */
        this.map.setMaxZoomPreference(19);
        this.map.setMinZoomPreference(16);

        if (!lats.isEmpty()) {
            /* Map bounds */
            final double[] bounds = {getLeft(lats).longitude, getTop(lats).latitude, getRight(lats).longitude, getBottom(lats).latitude};

            /* Move camera */
            map.setLatLngBoundsForCameraTarget(new LatLngBounds(new LatLng(bounds[1], bounds[0]), new LatLng(bounds[3], bounds[2])));
            map.moveCamera(CameraUpdateFactory.newLatLng(lats.get(0)));

            /* Draw grid over field */
            final long start = System.currentTimeMillis();
            System.out.println("Creating & drawing grid");
            doGrid(lats, bounds[0], bounds[1]);
            System.out.println("Took " + (System.currentTimeMillis() - start) + "ms to create & draw grid");
        }
    }

    @Override
    public final void onPolygonClick(final Polygon polygon) {
        switch(polygon.getFillColor()) {
            case Color.WHITE:
                polygon.setFillColor(Color.GREEN);
                break;

            case Color.GREEN:
                polygon.setFillColor(Color.YELLOW);
                break;

            case Color.YELLOW:
                polygon.setFillColor(Color.RED);
                break;

            case Color.RED:
                polygon.setFillColor(Color.WHITE);
                break;
        }
    }

    /* Create & draw grid */
    private final void doGrid(final ArrayList<LatLng> latlngs, final double startX, final double startY) {
        /* 10 Meter */
        final double lon = 0.0001424542009743867;
        final double lat = 0.00008988925643607076;

        /* Positions */
        final double posX = startX - (lon * 50);
        final double posY = startY - (lat * 50);

        /* Grid */
        final ArrayList<LatLng> grid = new ArrayList<>();
        int x = 0;

        for (int y = 0; y < 1000; ++x) {
            final ArrayList<LatLng> lats = new ArrayList<>();
            lats.add(new LatLng(posY + lat * y, posX + lon * x));
            lats.add(new LatLng(posY + lat * (y + 1), posX + lon * x));
            lats.add(new LatLng(posY + lat * (y + 1), posX + lon * (x + 1)));
            lats.add(new LatLng(posY + lat * y, posX + lon * (x + 1)));
            boolean flag = false;

            for (final LatLng latLng : lats) {
                if (PolyUtil.containsLocation(latLng, latlngs, false)) continue;
                flag = true;
                break;
            }

            if (flag) {
                if (x >= 1000) {
                    x = 0;
                    y++;
                }

                continue;
            }

            grid.addAll(lats);
            if (x < 1000) continue;

            x = 0;
            y++;
        }

        /* Draw all boxes */
        final ArrayList<LatLng> points = new ArrayList<>();

        for (final LatLng point : grid) {
            points.add(point);
            if (points.size() <= 3) continue;

            final PolygonOptions box = new PolygonOptions();
            box.fillColor(Color.WHITE);
            box.clickable(true);
            box.strokeWidth(2F);
            box.addAll(points);

            this.polygons.add(this.map.addPolygon(box));
            points.clear();
        }

        grid.clear();
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
