package ceos.study.vote.domain.leader.initializer;

import ceos.study.vote.domain.leader.entity.Leader;
import ceos.study.vote.domain.leader.repository.LeaderRepository;
import ceos.study.vote.global.common.Part;
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
public class LeaderInitializer implements CommandLineRunner {

    private final LeaderRepository leaderRepository;
    private final ObjectMapper objectMapper;

    @Getter
    @Setter
    public static class LeaderJSON{
        private String name;
        private String description;
    }

    @Override
    public void run(String... args) throws Exception {
        if (leaderRepository.count()==0){
            ClassPathResource resource = new ClassPathResource("initializerData/BELeaderInfo.json");
            if (!resource.exists()) {
                throw new IOException("파일이 존재하지 않습니다.");
            }
            InputStream inputStream = resource.getInputStream();

            //JSON->JAVA 객체
            List<LeaderJSON> leaderDTOs = objectMapper.readValue(inputStream, new TypeReference<List<LeaderJSON>>() {});
            for (LeaderJSON leaderDTO : leaderDTOs) {
                System.out.println(leaderDTO.name+":"+leaderDTO.description);
            }
            List<Leader> leaders = leaderDTOs.stream().map(
                    leaderDTO->Leader.builder()
                            .part(Part.BE)
                            .name(leaderDTO.name)
                            .description(leaderDTO.description)
                            .build()).toList();

            leaderRepository.saveAll(leaders);

        }
    }
}
