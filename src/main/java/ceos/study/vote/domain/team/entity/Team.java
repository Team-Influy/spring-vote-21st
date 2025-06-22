package ceos.study.vote.domain.team.entity;

import ceos.study.vote.global.common.BaseEntity;
import ceos.study.vote.global.common.TeamType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TeamType team;

    @NotNull
    private String description;

    @Builder.Default
    @Column(nullable = false)
    private Integer voteNum = 0;

    public void addVote() { voteNum++; }
}
