package nl.capaxit.viewpercapability.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.capaxit.viewpercapability.domain.Movie;

/**
 * Just create some mock data.
 *
 * Created by jamiecraane on 06/03/16.
 */
public class MovieService {
    private static final String[] DATA = new String[]{"The Terminator", "Mad Max", "Interstellar", "Jurassic World", "Insurgent", "Django Unchained"};
    private static final Random RANDOM = new Random();
    private static final List<Movie> MOVIES = generateData();

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
}
