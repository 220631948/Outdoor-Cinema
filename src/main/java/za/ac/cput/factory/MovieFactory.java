package za.ac.cput.factory;

import za.ac.cput.domain.Movie;
import za.ac.cput.domain.MovieGenreType;
import za.ac.cput.utils.HelperUtils;

public class MovieFactory {

    public static Movie createMovie(String title, MovieGenreType genre, int duration, String director) throws IllegalArgumentException {
        if (HelperUtils.isNullOrEmpty(title)) {
            throw new IllegalArgumentException("Invalid movie title");
        }
        if (HelperUtils.isNullOrEmpty(genre.ordinal())) {
            throw new IllegalArgumentException("Invalid movie genre");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (HelperUtils.isNullOrEmpty(director)) {
            throw new IllegalArgumentException("Invalid movie director");
        }

        return new Movie.Builder()
                .setTitle(title)
                .setGenre(genre)
                .setDuration(duration)
                .setDirector(director)
                .build();
    }

    public static Movie createMovieWithoutDirector(String title, MovieGenreType genre, int duration) {
        if (HelperUtils.isNullOrEmpty(title)) {
            throw new IllegalArgumentException("Invalid movie title");
        }
        if (HelperUtils.isNullOrEmpty(genre.ordinal())) {
            throw new IllegalArgumentException("Invalid movie genre");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }

        return new Movie.Builder()
                .setTitle(title)
                .setGenre(genre)
                .setDuration(duration)
                .build();

    }

    public static Movie createMovieWithoutGenre(String title, int duration, String director) {
        if (HelperUtils.isNullOrEmpty(title)) {
            throw new IllegalArgumentException("Invalid movie title");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        if (HelperUtils.isNullOrEmpty(director)) {
            throw new IllegalArgumentException("Invalid movie director");
        }

        return new Movie.Builder()
                .setTitle(title)
                .setDuration(duration)
                .setDirector(director)
                .build();
    }
}