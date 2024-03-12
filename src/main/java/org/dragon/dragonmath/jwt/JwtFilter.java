package org.dragon.dragonmath.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.dragon.dragonmath.model.User.Authority;
import org.dragon.dragonmath.model.User.CustomUserDetails;
import org.dragon.dragonmath.model.User.User;
import org.dragon.dragonmath.model.User.UserAuthority;
import org.dragon.dragonmath.repository.UserAuthorityRepository;
import org.dragon.dragonmath.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if(authorization == null || !authorization.startsWith("Bearer ")) {
            System.out.println("token null");
            filterChain.doFilter(request, response);
            return;
        }

        String token = null;
        if (authorization != null) {
            token = authorization.split(" ")[1];
        }

        if(token != null && jwtUtil.isExpired(token)) {
            System.out.println("token expired");
            filterChain.doFilter(request, response);
            return;
        }

        String userId = jwtUtil.getUserId(token);
        List<String> authorities = jwtUtil.getRole(token);

        User currentUser = userRepository.findUserByUserId(userId);

        CustomUserDetails customUserDetails = new CustomUserDetails(currentUser, authorities);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, currentUser.getPassword(), customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}
