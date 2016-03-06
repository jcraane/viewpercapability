package nl.capaxit.viewpercapability;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import nl.capaxit.viewpercapability.domain.Movie;
import nl.capaxit.viewpercapability.views.MovieDetails;

/**
 * this activity has no mediator view since it only displays the movie details.
 * <p/>
 * Created by jamiecraane on 06/03/16.
 */
public class DetailsActivity extends AppCompatActivity {
    public static final String INTENT_MOVIE = "intent:movie";

    private MovieDetails movieDetails;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        movieDetails = (MovieDetails) findViewById(R.id.movieDetails);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Movie movie = (Movie) getIntent().getExtras().getSerializable(INTENT_MOVIE);
        movieDetails.update(movie);
    }
}
