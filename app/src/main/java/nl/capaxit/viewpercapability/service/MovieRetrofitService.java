package nl.capaxit.viewpercapability.service;

import nl.capaxit.viewpercapability.domain.Movie;
import retrofit2.http.GET;
import rx.Observable;

import java.util.List;

/**
 * Created by jamiecraane on 31/05/2017.
 */

public interface MovieRetrofitService {
    @GET("/api/v1/movies")
    Observable<List<Movie>> getMovies();
}
