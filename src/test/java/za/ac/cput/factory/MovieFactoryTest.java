package za.ac.cput.factory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName.class)
class MovieFactoryTest {

    // Venues
    public static Venue venue1 = new Venue.Builder()
            .setAddress(String.valueOf(VenueLocation.KIRSTENBOSCH_GARDENS))
            .setCapacity(300)
            .setName("KirstenBosch outdoor cinema")
            .build();

    // Screening 1
    public static Screening screening1 = new Screening.Builder()
            .date(new Date())
            .time("18:00")
            .venue(venue1)
            .build();

    // Screening 2
    public static Screening screening2 = new Screening.Builder()
            .date(new Date())
            .time("20:00")
            .venue(venue1)
            .build();


    // Movie 1
    public static Movie movie1 = new Movie.Builder()
            .setTitle("Inception")
            .setGenre(MovieGenreType.SCIENCE_FICTION)
            .setDuration(148)
            .setDirector("Christopher Nolan")
            .build();

    // Movie 2
    public static Movie movie2 = new Movie.Builder()
            .setTitle("The Godfather")
            .setGenre(MovieGenreType.DRAMA)
            .setDuration(175)
            .setDirector("Francis Ford Coppola")
            .build();

    // Movie 3
    public static Movie movie3 = new Movie.Builder()
            .setTitle("The Dark Knight")
            .setGenre(MovieGenreType.ACTION)
            .setDuration(152)
            .setDirector("Christopher Nolan")
            .build();

    @Test
    void createMovie() {
        Movie movie = MovieFactory.createMovie("The Matrix", MovieGenreType.SCIENCE_FICTION, 136, "The Wachowskis");

        assertNotNull(movie, "Movie should not be null");
        assertEquals("The Matrix", movie.getTitle(), "Title should be correct");
        assertEquals(MovieGenreType.SCIENCE_FICTION, movie.getGenre(), "Genre should be correct");
        assertEquals(136, movie.getDuration(), "Duration should be correct");
        assertEquals("The Wachowskis", movie.getDirector(), "Director should be correct");
    }

    @Test
    void testCreateMovieWithEmptyTitle() {
        String emptyTitle = "";
        MovieGenreType genre = MovieGenreType.ACTION;
        int duration = 150;
        String director = "John Doe";

        Movie movie = MovieFactory.createMovie(emptyTitle, genre, duration, director);

        assertNull(movie, "Movie should be null when the input title is empty or null");
    }

    @Test
    void testCreateMovieWithBoundaryValues() {
        String title = "Test Movie";
        MovieGenreType genre = MovieGenreType.ACTION;
        int duration = 120;
        String director = "John Doe";

        Movie movie = MovieFactory.createMovie(title, genre, duration, director);

        assert movie != null;
        assertEquals(title, movie.getTitle(), "Title should be correct");
        assertEquals(genre, movie.getGenre(), "Genre should be correct");
        assertEquals(duration, movie.getDuration(), "Duration should be correct");
        assertEquals(director, movie.getDirector(), "Director should be correct");
    }

    @Test
    void createMovieWithoutDirector() {
        String title = "Directorless Movie";
        MovieGenreType genre = MovieGenreType.ACTION;
        int duration = 150;

        Movie movie = MovieFactory.createMovieWithoutDirector(title, genre, duration);

        //assertNotNull(movie, "Movie should not be null without a director");
        assertEquals(title, movie.getTitle(), "Title should be correct");
        assertEquals(genre, movie.getGenre(), "Genre should be correct");
        assertEquals(duration, movie.getDuration(), "Duration should be correct");
    }

    @Test
    void createMovieWithShortDuration() {
        String title = "Short Movie";
        MovieGenreType genre = MovieGenreType.HORROR;
        int duration = 100;
        String director = "Jane Doe";

        Movie movie = MovieFactory.createMovie(title, genre, duration, director);

        assertNull(movie, "Movie should be null when the duration is less than 120");
    }

    @Test
    void createMovieWithNullTitle() {
        MovieGenreType genre = MovieGenreType.COMEDY;
        int duration = 140;
        String director = "Jane Doe";

        Movie movie = MovieFactory.createMovie(null, genre, duration, director);

        assertNull(movie, "Movie should be null when the title is null");
    }

    @Test
    void createMovieWithScreenings() {

        Venue venue = new Venue.Builder()
                .setAddress("Kirstenbosch Gardens")
                .setCapacity(300)
                .setName("Grass Area")
                .build();

        Set<Screening> screenings = Set.of(
                new Screening.Builder()
                        .date(new Date())
                        .time("18:00")
                        .venue(venue)
                        .movie(movie1)
                        .build()
        );

        Movie movie = MovieFactory.createMovieWithScreening
                ("The Avengers", MovieGenreType.ACTION, 143, "Joss Whedon", screenings);

        assertNotNull(movie, "Movie should not be null");
        assertEquals("The Avengers", movie.getTitle(), "Title should be correct");

        }


}