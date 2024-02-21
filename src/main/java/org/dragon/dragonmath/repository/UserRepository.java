package org.dragon.dragonmath.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.dragon.dragonmath.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
