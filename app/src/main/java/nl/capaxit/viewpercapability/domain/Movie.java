package nl.capaxit.viewpercapability.domain;

import java.io.Serializable;

/**
 * Created by jamiecraane on 06/03/16.
 */
public final class Movie implements Serializable {
    private final String title;

    public Movie(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
