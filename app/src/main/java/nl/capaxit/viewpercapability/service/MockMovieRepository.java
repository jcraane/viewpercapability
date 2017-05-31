package nl.capaxit.viewpercapability.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.mockwebserver.MockResponse;
import nl.capaxit.viewpercapability.domain.Movie;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Just create some mock data.
 *
 * Created by jamiecraane on 06/03/16.
 */
public class MockMovieRepository implements MovieRepository {
    private static final String[] DATA = new String[]{"The Terminator", "Mad Max", "Interstellar", "Jurassic World", "Insurgent", "Django Unchained"};
    private static final Random RANDOM = new Random();
    private static final List<Movie> MOVIES = generateData();
    private static final Gson GSON = new Gson();

    private static List<Movie> generateData() {
        final ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            movies.add(new Movie(DATA[RANDOM.nextInt(DATA.length)]));
        }
        return movies;
    }

    public List<Movie> getMovies() {
        return MOVIES;
    }

    @Override
    public Observable<List<Movie>> getMoviesObservable() {
        MockServer.mockResponse.onNext(new MockResponse().setBody(GSON.toJson(MOVIES)).setResponseCode(200));
        final MovieRetrofitService movieRetrofitService = new Retrofit.Builder()
                .baseUrl(MockServer.mockWebServer.url("/").toString())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build().create(MovieRetrofitService.class);

        return movieRetrofitService.getMovies();
    }
}
