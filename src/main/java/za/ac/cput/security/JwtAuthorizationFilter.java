package za.ac.cput.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import za.ac.cput.util.JwtUtil;
import za.ac.cput.utils.AuthenticationUtil;

import java.io.IOException;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAuthorizationFilter(UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        String username = getUsernameFromJwt(jwt);
        authenticateUser(username, jwt, request);
        chain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the request header.
     *
     * @param request the HTTP request
     * @return the JWT token or null if not present
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    /**
     * Extracts the username from the JWT token.
     *
     * @param jwt the JWT token
     * @return the username or null if not present
     */
    private String getUsernameFromJwt(String jwt) {
        return jwtUtil.extractUsername(jwt);
    }

    /**
     * Authenticates the user based on the JWT token and username.
     *
     * @param username the username extracted from the JWT token
     * @param jwt the JWT token
     * @param request the HTTP request
     */
    private void authenticateUser(String username, String jwt, HttpServletRequest request) {
        AuthenticationUtil.authenticateUser(userDetailsService, jwtUtil, username, jwt, request);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (Boolean.TRUE.equals(jwtUtil.validateToken(jwt, userDetails.getUsername()))) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
    }
}