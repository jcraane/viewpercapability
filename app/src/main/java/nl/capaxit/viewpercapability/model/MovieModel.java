package nl.capaxit.viewpercapability.model;

/**
 * Created by jamiecraane on 30/05/2017.
 */

public class MovieModel {
    private static final MovieModel INSTANCE = new MovieModel();

    public static MovieModel getInstance() {
        return INSTANCE;
    }
}
