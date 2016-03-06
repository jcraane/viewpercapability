package nl.capaxit.viewpercapability.mediator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import de.greenrobot.event.EventBus;
import nl.capaxit.viewpercapability.R;
import nl.capaxit.viewpercapability.mediator.event.MovieSelectedEvent;
import nl.capaxit.viewpercapability.service.MovieService;
import nl.capaxit.viewpercapability.views.MovieDetails;
import nl.capaxit.viewpercapability.views.MovieList;

/**
 * Has both the MovieList and MovieDetails view. When a movie is selected it does not
 * navgate to the DetailsActivity but updates the MovieDetails view instead.
 *
 * Created by jamiecraane on 06/03/16.
 */
public class MovieTabletMediator extends FrameLayout implements MovieMediator {
    private MovieList movieList;
    private MovieDetails movieDetails;

    private MovieService movieService = new MovieService();

    public MovieTabletMediator(final Context context) {
        super(context);
        init(context);
    }

    public MovieTabletMediator(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        final View view = View.inflate(context, R.layout.mediator_overview, this);
        movieList = (MovieList) view.findViewById(R.id.movieList);
        movieDetails = (MovieDetails) view.findViewById(R.id.movieDetails);
    }

    public void onStart() {
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void init() {
        movieList.update(movieService.getMovies());
        movieDetails.update(movieService.getMovies().get(0));
    }

    public void onEvent(final MovieSelectedEvent event) {
        movieDetails.update(event.getSelectedMovie());
    }
}
