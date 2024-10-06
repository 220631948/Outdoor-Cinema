package za.ac.cput.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.user.User;
import za.ac.cput.repository.UserRepository;

@Service
public class CustomUserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement user loading logic by using userRepository
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not Found");
        }

        return (UserDetails) new User(user.getUsername(), user.getPassword());

    }
}