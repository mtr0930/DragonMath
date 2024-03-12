package org.dragon.dragonmath.service.User;

import lombok.AllArgsConstructor;
import org.dragon.dragonmath.model.User.Authority;
import org.dragon.dragonmath.model.User.CustomUserDetails;
import org.dragon.dragonmath.model.User.User;
import org.dragon.dragonmath.model.User.UserAuthority;
import org.dragon.dragonmath.repository.UserAuthorityRepository;
import org.dragon.dragonmath.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserId(username);

        if(user.getUserId() != null) {
            return new CustomUserDetails(user, user.getUserAuthorities().stream().map((auth) -> auth.getAuthority().getAuthorityName()).toList());
        }

        return null;
    }
}
