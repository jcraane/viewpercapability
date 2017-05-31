package nl.capaxit.viewpercapability.mediator;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import de.greenrobot.event.EventBus;
import nl.capaxit.viewpercapability.DetailsActivity;
import nl.capaxit.viewpercapability.R;
import nl.capaxit.viewpercapability.mediator.event.MovieSelectedEvent;
import nl.capaxit.viewpercapability.service.MockMovieRepository;
import nl.capaxit.viewpercapability.views.MovieList;

/**
 * Has only the movieList view. When a movie is selected (MovieSelectedEvent) this mediator
 * knows how to display the movie details by navigating to the DetailsActivity (which
 * contains the MovieDetails view).
 * <p>
 * Created by jamiecraane on 06/03/16.
 */
public class MoviePhoneMediator extends FrameLayout implements MovieMediator {
    private static final String TAG = MoviePhoneMediator.class.getSimpleName();
    private MovieList movieList;
    private MockMovieRepository movieService = new MockMovieRepository();

    public MoviePhoneMediator(final Context context) {
        super(context);
        init(context);
    }

    public MoviePhoneMediator(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void onStart() {
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void init() {
        movieService.getMoviesObservable()
                .subscribe(movieList::update, throwable -> Log.e(TAG, "Error retrieving movies", throwable));
    }

    private void init(final Context context) {
        final View view = View.inflate(context, R.layout.mediator_overview, this);
        movieList = (MovieList) view.findViewById(R.id.movieList);
    }

    public void onEvent(final MovieSelectedEvent event) {
        final Intent intent = new Intent(getContext(), DetailsActivity.class);
        intent.putExtra(DetailsActivity.INTENT_MOVIE, event.getSelectedMovie());
        getContext().startActivity(intent);
    }
}
