package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByUsername(String username);
    User findByEmailAndPassword(String email, String password);
    User findByUsernameAndPassword(String username, String password);
    User findByEmailOrUsername(String email, String username);
}