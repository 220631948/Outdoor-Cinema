package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Movie;

import java.util.Optional;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
     Optional<Movie> findById(Long id);


}