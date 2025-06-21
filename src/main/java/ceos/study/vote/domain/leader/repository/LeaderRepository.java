package ceos.study.vote.domain.leader.repository;

import ceos.study.vote.domain.leader.entity.Leader;
import ceos.study.vote.global.common.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaderRepository extends JpaRepository<Leader, Long> {
    List<Leader> findAllByPart(Part part);
}
