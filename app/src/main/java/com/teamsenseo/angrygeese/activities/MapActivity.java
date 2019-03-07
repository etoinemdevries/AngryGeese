package com.teamsenseo.angrygeese.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.teamsenseo.angrygeese.R;
import com.teamsenseo.angrygeese.utils.FileUtils;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.util.ArrayList;

/**
 * Map activity
 *
 * @author Robert
 */
public final class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap map;

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

        for(final File f : new File(Environment.DIRECTORY_DOWNLOADS).listFiles())
            System.out.println(f.getAbsolutePath());

        final File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "opdracht.kml");
        load(f);
    }

    /**
     * Loads a KML file
     */
    public final boolean load(final File f) {
        if (f == null || !f.exists() || !f.getName().endsWith(".kml")) {
            System.out.println("\nInvalid file: " + f.exists());
            return false;
        }

        final FileUtils utils = new FileUtils(f);
        final String lines[] = utils.read().replace("   ", "").split("\n");

        final ArrayList<LatLng> positions = new ArrayList<>();
        for (final String s : lines) {
            System.out.println("Line: " + s);
        }

        final Polygon polygon = map.addPolygon(new PolygonOptions().addAll(positions));
        polygon.setStrokeColor(Color.GREEN);
        polygon.setStrokeWidth(0.6F);
        return true;
    }
}
