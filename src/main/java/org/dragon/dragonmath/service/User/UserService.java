package org.dragon.dragonmath.service.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dragon.dragonmath.dto.User.CreateUserRequestDto;
import org.dragon.dragonmath.model.User.Authority;
import org.dragon.dragonmath.model.User.User;
import org.dragon.dragonmath.model.User.UserAuthority;
import org.dragon.dragonmath.repository.AuthorityRepository;
import org.dragon.dragonmath.repository.UserAuthorityRepository;
import org.dragon.dragonmath.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void creatUser(CreateUserRequestDto createUserRequestDto){
        if(!userRepository.existsUserByUserId(createUserRequestDto.getUserId())) {
            User newUser = User.builder()
                    .email(createUserRequestDto.getEmail())
                    .userId(createUserRequestDto.getUserId())
                    .name(createUserRequestDto.getName())
                    .password(bCryptPasswordEncoder.encode(createUserRequestDto.getPassword()))
                    .phoneNumber(createUserRequestDto.getPhoneNumber())
                    .build();
            UserAuthority userAuthority = UserAuthority.builder()
                    .user(newUser)
                    .authority(Authority.builder()
                            .authorityName("ADMIN")
                            .build())
                    .build();
            userRepository.save(newUser);
            userAuthorityRepository.save(userAuthority);
        }
    }
}
