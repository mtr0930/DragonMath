package org.dragon.dragonmath.repository;

import org.dragon.dragonmath.model.User.User;
import org.dragon.dragonmath.model.User.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
    UserAuthority findUserAuthorityByUser (User user);
    List<UserAuthority> findUserAuthoritiesByUserUserId(String userId);
}
