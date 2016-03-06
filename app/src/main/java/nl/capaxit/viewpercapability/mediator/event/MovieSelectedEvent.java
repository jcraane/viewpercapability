package nl.capaxit.viewpercapability.mediator.event;

import nl.capaxit.viewpercapability.domain.Movie;

/**
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

