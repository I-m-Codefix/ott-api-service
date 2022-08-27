package kr.imcf.ott.streaming;

import kr.imcf.ott.domain.entity.Streaming;
import kr.imcf.ott.persistence.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StreamingService {

    private final StreamingRepository streamingRepository;

    public List<StreamingDTO> getStreamingList(Long categoryId) {
        List<StreamingDTO> results = streamingRepository.findByAllCategoryId(categoryId).stream().map(StreamingDTO::of).collect(Collectors.toList());
        return results;
    }

}
