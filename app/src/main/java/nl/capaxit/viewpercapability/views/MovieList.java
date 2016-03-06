package nl.capaxit.viewpercapability.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import de.greenrobot.event.EventBus;
import nl.capaxit.viewpercapability.R;
import nl.capaxit.viewpercapability.domain.Movie;
import nl.capaxit.viewpercapability.mediator.event.MovieSelectedEvent;

/**
 * A list of movies.
 *
 * Created by jamiecraane on 06/03/16.
 */
public class MovieList extends RecyclerView {

    public MovieList(final Context context) {
        super(context);
        init(context);
    }

    public MovieList(final Context context, @Nullable final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(params);
    }

    public void update(final List<Movie> movies) {
        setAdapter(new MoviesAdapter(movies));
    }

    private static class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
        private final List<Movie> movies;

        private MoviesAdapter(final List<Movie> movies) {
            this.movies = movies;
        }

        @Override
        public MovieViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            final View view = View.inflate(parent.getContext(), R.layout.movie_row, null);
            final MovieViewHolder holder = new MovieViewHolder(view);
            holder.title = (TextView) view.findViewById(R.id.movieTitle);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MovieViewHolder holder, final int position) {
            final Movie movie = movies.get(position);
            holder.title.setText(movie.getTitle());
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(final View v) {
                    EventBus.getDefault().post(new MovieSelectedEvent(movie));
                }
            });
        }

        @Override
        public int getItemCount() {
            return movies.size();
        }

        public static class MovieViewHolder extends RecyclerView.ViewHolder {
            TextView title;

            public MovieViewHolder(final View itemView) {
                super(itemView);
            }
        }
    }
}
