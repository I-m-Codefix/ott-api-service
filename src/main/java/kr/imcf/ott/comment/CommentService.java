package kr.imcf.ott.comment;

import kr.imcf.ott.persistence.repository.AccountRepository;
import kr.imcf.ott.persistence.repository.CommentRepository;
import kr.imcf.ott.persistence.repository.CommentRepositoryJPA;
import kr.imcf.ott.persistence.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentRepositoryJPA commentRepositoryJPA;
    private final AccountRepository accountRepository;
    private final StreamingRepository streamingRepository;

    public List<CommentDTO> getAllCommentList() {
        List<CommentDTO> results = commentRepository.findAll().stream().filter(c -> c.getUseYn() == 'Y').map(CommentDTO::of).collect(Collectors.toList());
        return results;
    }

}
