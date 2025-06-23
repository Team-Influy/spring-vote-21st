package ceos.study.vote.domain.team.initializer;

import ceos.study.vote.domain.team.entity.Team;
import ceos.study.vote.global.common.TeamType;
import ceos.study.vote.domain.team.repository.TeamRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TeamInitializer implements CommandLineRunner {

    private final TeamRepository teamRepository;
    private final ObjectMapper objectMapper;

    @Getter
    @Setter
    public static class TeamJSON{
        private TeamType team;
        private String description;
    }

    @Override
    public void run(String... args) throws Exception {
        if (teamRepository.count() == 0) {
            ClassPathResource resource = new ClassPathResource("initializerData/TeamInfo.json");
            if (!resource.exists()) {
                throw new IOException("파일이 존재하지 않습니다.");
            }
            InputStream inputStream = resource.getInputStream();

            //JSON->JAVA 객체
            List<TeamInitializer.TeamJSON> teamDTOs = objectMapper.readValue(inputStream, new TypeReference<List<TeamInitializer.TeamJSON>>() {
            });
            for (TeamInitializer.TeamJSON teamDTO : teamDTOs) {
                System.out.println(teamDTO.team + ":" + teamDTO.description);
            }
            List<Team> teams = teamDTOs.stream().map(
                    teamDTO -> Team.builder()
                            .name(teamDTO.team)
                            .description(teamDTO.description)
                            .build()).toList();

            teamRepository.saveAll(teams);

        }
    }
}
