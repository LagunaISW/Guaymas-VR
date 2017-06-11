package mx.itson.guaymas_vr;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsFragment extends Fragment implements OnMapReadyCallback {

    private static final int TAG_CODE_PERMISSION_LOCATION = 0;
    public static final String FILE = "image";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    private View rootView;
    GoogleMap mMap;
    MapView mMapView;

    public static MapsFragment newInstance() {
        return new MapsFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        try {
            rootView = inflater.inflate(R.layout.fragment_maps, container, false);
            MapsInitializer.initialize(this.getActivity());
            mMapView = rootView.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);
        } catch (InflateException e) {
            Log.e("LAGUNA", "Inflate exception");
        }
        return rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a markers in Guaymas and move the camera
        LatLng malecon = new LatLng(27.9217379, -110.8879509);
        LatLng tresPresidentes = new LatLng(27.9231772, -110.8888622);
        LatLng palacio = new LatLng(27.9233034, -110.8892776);
        LatLng casaDeLaCultura = new LatLng(27.9229336, -110.8979357);
        LatLng itson = new LatLng(27.9676399, -110.9178284);

        final int ICON_WIDTH = 66;
        final int ICON_HEIGHT = 100;

        final Marker markerMalecon = mMap.addMarker(new MarkerOptions()
                .position(malecon)
                .title("Malecon Turistico de Guaymas")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("malecon", ICON_WIDTH, ICON_HEIGHT))));

        final Marker markerTresPresidentes = mMap.addMarker(new MarkerOptions()
                .position(tresPresidentes)
                .title("Plaza de los Tres Presidentes")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("presidentes", ICON_WIDTH, ICON_HEIGHT))));

        final Marker markerPalacio = mMap.addMarker(new MarkerOptions()
                .position(palacio)
                .title("Palacio Municipal")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("palacio", ICON_WIDTH, ICON_HEIGHT))));

        final Marker markerCasaCultura = mMap.addMarker(new MarkerOptions()
                .position(casaDeLaCultura)
                .title("Casa de la Cultura \"Edmundo Valadés\"")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("cultura", ICON_WIDTH, ICON_HEIGHT))));

        final Marker markerItson = mMap.addMarker(new MarkerOptions()
                .position(itson)
                .title("Instituto Tecnológico de Sonora - Guaymas")
                .icon(BitmapDescriptorFactory.fromBitmap(resizeMapIcons("escuela", ICON_WIDTH, ICON_HEIGHT))));

        mMap.setMinZoomPreference(15);


        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{
                                android.Manifest.permission.ACCESS_FINE_LOCATION,
                                android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        TAG_CODE_PERMISSION_LOCATION);
            }
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(tresPresidentes));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if (marker.equals(markerTresPresidentes)) {
                    iniciarPanoActivity(getActivity(),
                            getString(R.string.presidetes_file),
                            getString(R.string.presidetes_title),
                            getString(R.string.presidetes_description));

                } else if (marker.equals(markerPalacio)) {
                    iniciarPanoActivity(getActivity(),
                            getString(R.string.palacio_file),
                            getString(R.string.palacio_title),
                            getString(R.string.palacio_description));
                } else if (marker.equals(markerMalecon)) {
                    iniciarPanoActivity(getActivity(),
                            getString(R.string.malecon_file),
                            getString(R.string.malecon_title),
                            getString(R.string.malecon_description));
                }

                return true;
            }
        });
    }

    public void iniciarPanoActivity(Activity activity, String file, String title, String description) {
        Intent intent = new Intent(activity, PanoActivity.class);
        intent.putExtra(MapsFragment.FILE, file);
        intent.putExtra(MapsFragment.TITLE, title);
        intent.putExtra(MapsFragment.DESCRIPTION, description);
        startActivity(intent);
    }

    public Bitmap resizeMapIcons(String iconName, int width, int height) {
        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(iconName, "drawable", getActivity().getPackageName()));
        return Bitmap.createScaledBitmap(imageBitmap, width, height, false);
    }
}
