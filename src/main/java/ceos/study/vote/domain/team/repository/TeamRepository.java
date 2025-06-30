package ceos.study.vote.domain.team.repository;

import ceos.study.vote.domain.team.entity.Team;
import ceos.study.vote.global.common.TeamType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByName(TeamType team);

    @Query("SELECT SUM(t.numVotes) FROM Team t")
    Integer getTotalVotes();

    List<Team> findAllByOrderByNumVotesDesc();
}
