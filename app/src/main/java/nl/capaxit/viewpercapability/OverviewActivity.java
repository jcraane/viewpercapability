package nl.capaxit.viewpercapability;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import nl.capaxit.viewpercapability.mediator.MovieMediator;

/**
 * Has a single-view, the MovieMediator. MovieMediator is an interface with two implementations:
 * MoviePhoneMediator and MovieTabletMediator.
 *
 * The layout/activity_overview loads the MoviePhoneMediator and the /layout-sw670dp loads
 * the MovieTabletMediator.
 *
 * The mediators have the knowledge about the specific layout and mediate between the different views.
 *
 * Created by jamiecraane on 06/03/16.
 */
public class OverviewActivity extends AppCompatActivity {
    private MovieMediator movieMediator;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        movieMediator = (MovieMediator) findViewById(R.id.overviewMediator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        movieMediator.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        movieMediator.init();
    }

    @Override
    protected void onStop() {

        movieMediator.onStop();
        super.onStop();
    }
}
