package org.dragon.dragonmath.service.User;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.dragon.dragonmath.dto.User.CreateUserRequestDto;
import org.dragon.dragonmath.model.User.User;
import org.dragon.dragonmath.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Transactional
    public void creatUser(CreateUserRequestDto createUserRequestDto){
        User newUser = User.builder()
                        .email(createUserRequestDto.getEmail())
                        .id(createUserRequestDto.getId())
                        .name(createUserRequestDto.getName())
                        .password(createUserRequestDto.getPassword())
                        .phoneNumber(createUserRequestDto.getPhoneNumber())
                .build();
        userRepository.save(newUser);

    }
}
