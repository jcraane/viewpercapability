package nl.capaxit.viewpercapability.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import nl.capaxit.viewpercapability.R;
import nl.capaxit.viewpercapability.domain.Movie;

/**
 * Display the details of a movie.
 *
 * Created by jamiecraane on 06/03/16.
 */
public class MovieDetails extends LinearLayout {
    private TextView title;

    public MovieDetails(final Context context) {
        super(context);
        init(context);
    }

    public MovieDetails(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        setOrientation(VERTICAL);
        final View view = View.inflate(context, R.layout.movie_details, this);
        title = (TextView) view.findViewById(R.id.title);
    }

    public void update(final Movie movie) {
        title.setText(movie.getTitle());
    }
}
