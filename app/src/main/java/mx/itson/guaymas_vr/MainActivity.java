package mx.itson.guaymas_vr;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import mx.itson.guaymas_vr.Entity.Locations;


public class MainActivity extends AppCompatActivity implements PhotoFragment.OnListFragmentInteractionListener {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            displayView(item.getItemId());
            return true;
        }

    };

    public void displayView(int viewId) {

        Fragment fragment = null;
        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.navigation_home:
                fragment = new MapsFragment();
                title = "Mapa";

                break;
            case R.id.navigation_dashboard:
                fragment = new PhotoFragment();
                title = "Galeria";
                break;
            default:
                fragment = new MapsFragment();
                title = "Mapa";

        }

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragment);
        ft.commit();

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        displayView(R.id.navigation_home);

    }

    @Override
    public void onListFragmentInteraction(Locations.Location item) {
        iniciarPanoActivity(this,
                getString(R.string.presidetes_file),
                getString(R.string.presidetes_title),
                getString(R.string.presidetes_description));
    }

    public void iniciarPanoActivity(Activity activity, String file, String title, String description) {
        Intent intent = new Intent(activity, PanoActivity.class);
        intent.putExtra(MapsFragment.FILE, file);
        intent.putExtra(MapsFragment.TITLE, title);
        intent.putExtra(MapsFragment.DESCRIPTION, description);
        startActivity(intent);
    }
}
