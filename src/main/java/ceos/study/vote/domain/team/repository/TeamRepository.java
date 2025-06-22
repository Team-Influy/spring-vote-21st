package ceos.study.vote.domain.team.repository;

import ceos.study.vote.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
