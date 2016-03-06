package nl.capaxit.viewpercapability.mediator.event;

import nl.capaxit.viewpercapability.domain.Movie;

/**
 * Dispatched when a movie is selected in the MovieList.
 *
 * Created by jamiecraane on 06/03/16.
 */
public final class MovieSelectedEvent {
    private final Movie selectedMovie;

    public MovieSelectedEvent(final Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    public Movie getSelectedMovie() {
        return selectedMovie;
    }
}

