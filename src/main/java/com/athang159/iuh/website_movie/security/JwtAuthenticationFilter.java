package com.athang159.iuh.website_movie.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {


        String token = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    token = cookie.getValue();
                }
            }
        }

//        System.out.println("token: " + token);

        if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                DecodedJWT jwt = jwtUtil.verifyToken(token);

                String username = jwt.getSubject();
                String role = jwt.getClaim("role").asString();

//                System.out.println("Token valid. User: " + username + ", Role: " + role);

                UserDetails userDetails = User.withUsername(username)
                        .password("")
                        .roles(role)
                        .build();
//                System.out.println("Authorities:");
//                userDetails.getAuthorities().forEach(auth -> System.out.println(" - " + auth.getAuthority()));

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        var auth = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("DEBUG AUTH - URI: " + request.getRequestURI() + " Authentication: " + auth);
        filterChain.doFilter(request, response);
    }
}
