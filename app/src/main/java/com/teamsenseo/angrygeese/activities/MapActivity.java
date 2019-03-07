package com.teamsenseo.angrygeese.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.teamsenseo.angrygeese.R;
import com.teamsenseo.angrygeese.utils.FileUtils;

import java.io.File;
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
    }

        final PolygonOptions options = new PolygonOptions();
        options.add(new LatLng(52.532327431071, 4.9698428582864),
                new LatLng(52.5363890459246, 4.97152173643456),
                new LatLng(52.5359060240486, 4.96928150387894),
                new LatLng(52.5334104595542, 4.96744278341884),
                new LatLng(52.5314914623206, 4.96633040367443),
                new LatLng(52.5319261071537, 4.96651718547247),
                new LatLng(52.5321208961933, 4.96596108874737),
                new LatLng(52.532327431071, 4.9698428582864));
        options.fillColor(Color.GREEN);
        final Polygon polygon = map.addPolygon(options);

        final FileUtils utils = new FileUtils(new File("..\\..\\..\\res\\raw\\coordinates.txt"));
        System.out.println(utils.read());
    }
}
