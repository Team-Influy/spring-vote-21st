package ceos.study.vote.domain.leader.entity;

import ceos.study.vote.global.common.BaseEntity;
import ceos.study.vote.global.common.PartType;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Leader extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private PartType part;

    @Builder.Default
    private Integer voteNum = 0;

    @Nullable
    private String description;

    public void addVote(){
        this.voteNum++;
    }
}
