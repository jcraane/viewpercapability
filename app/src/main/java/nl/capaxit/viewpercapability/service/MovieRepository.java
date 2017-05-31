package nl.capaxit.viewpercapability.service;

import nl.capaxit.viewpercapability.domain.Movie;
import rx.Observable;

import java.util.List;

/**
 * Created by jamiecraane on 31/05/2017.
 */

interface MovieRepository {
    Observable<List<Movie>> getMoviesObservable();
}
