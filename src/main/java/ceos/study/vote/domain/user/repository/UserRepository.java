package ceos.study.vote.domain.user.repository;

import ceos.study.vote.domain.user.entity.User;
import ceos.study.vote.global.common.PartType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Integer countUserByLeaderVoteTrueAndPart(PartType part);
}
