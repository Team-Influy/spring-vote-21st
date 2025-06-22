package ceos.study.vote.domain.leader.initializer;

import ceos.study.vote.domain.leader.entity.Leader;
import ceos.study.vote.domain.leader.repository.LeaderRepository;
import ceos.study.vote.global.common.PartType;
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
            ClassPathResource BEResource = new ClassPathResource("initializerData/BELeaderInfo.json");
            ClassPathResource FEResource = new ClassPathResource("initializerData/FELeaderInfo.json");
            if (!BEResource.exists()) {
                throw new IOException("BE 파일이 존재하지 않습니다.");
            }
            if(!FEResource.exists()){
                throw new IOException("FE 파일이 존재하지 않습니다.");
            }
            InputStream BEInputStream = BEResource.getInputStream();
            InputStream FEInputStream = FEResource.getInputStream();

            //JSON->JAVA 객체
            List<LeaderJSON> leaderDTOs = objectMapper.readValue(BEInputStream, new TypeReference<List<LeaderJSON>>() {});

            List<Leader> BELeaders = leaderDTOs.stream().map(
                    leaderDTO->Leader.builder()
                            .part(PartType.BE)
                            .name(leaderDTO.name)
                            .description(leaderDTO.description)
                            .build()).toList();

            leaderRepository.saveAll(BELeaders);

            leaderDTOs = objectMapper.readValue(FEInputStream, new TypeReference<List<LeaderJSON>>() {});

            List<Leader> FELeaders = leaderDTOs.stream().map(
                    leaderDTO->Leader.builder()
                            .part(PartType.FE)
                            .name(leaderDTO.name)
                            .description(leaderDTO.description)
                            .build()).toList();

            leaderRepository.saveAll(FELeaders);

        }
    }
}
