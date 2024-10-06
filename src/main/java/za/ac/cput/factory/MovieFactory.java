package za.ac.cput.factory;

import za.ac.cput.domain.Movie;
import za.ac.cput.domain.MovieGenreType;
import za.ac.cput.domain.Screening;
import za.ac.cput.utils.HelperUtils;

import java.util.Set;

public class MovieFactory {

    public static Movie createMovie(String title, MovieGenreType genre, int duration, String director) throws IllegalArgumentException {
        if (HelperUtils.isNullOrEmpty(title)
                || HelperUtils.isNullOrEmpty(genre.ordinal())
                || duration < 120
                || HelperUtils.isNullOrEmpty(director)) {
            return null;

        }

        return new Movie.Builder()
                .setTitle(title)
                .setGenre(genre)
                .setDuration(duration)
                .setDirector(director)
                .build();
    }

    public static Movie createMovieWithoutDirector(String title, MovieGenreType genre, int duration) {
        if (HelperUtils.isNullOrEmpty(title)
                || HelperUtils.isNullOrEmpty(genre.ordinal())
                || duration < 120) {
            return null;
        }

        return new Movie.Builder()
                .setTitle(title)
                .setGenre(genre)
                .setDuration(duration)
                .build();

    }

    // Movie factory design pattern with Screening Object
    public static Movie createMovieWithScreening(String title, MovieGenreType genre, int duration, String director, Set<Screening> screenings) {
        if (HelperUtils.isNullOrEmpty(title)
                || HelperUtils.isNullOrEmpty(genre.ordinal())
                || duration < 120
                || HelperUtils.isNullOrEmpty(director)
                || HelperUtils.isNullOrEmpty(screenings)) {
            return null;
        }

        return new Movie.Builder()
                .setTitle(title)
                .setGenre(genre)
                .setDuration(duration)
                .setDirector(director)
                .setScreening((Screening) screenings)
                .build();
    }

}