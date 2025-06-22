package ceos.study.vote.domain.user.entity;

import ceos.study.vote.global.common.BaseEntity;
import ceos.study.vote.global.common.PartType;
import ceos.study.vote.global.common.TeamType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 25)
    private String email;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TeamType team;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PartType part;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Builder.Default
    private Boolean leaderVote = false;

    @Builder.Default
    private Boolean teamVote = false;

    public void setLeaderVoteTrue(){
        this.leaderVote = true;
    }

    public void setTeamVoteTrue(){
        this.teamVote = true;
    }

}
