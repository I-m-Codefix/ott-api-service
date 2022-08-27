package kr.imcf.ott.persistence.repository;

import kr.imcf.ott.domain.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StreamingRepository extends JpaRepository <Streaming, Long> {
    List<Streaming> findByAllCategoryId(Long categoryId);
    Optional<Streaming> findById(Long id);
}
