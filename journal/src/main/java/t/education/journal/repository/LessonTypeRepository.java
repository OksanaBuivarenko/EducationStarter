package t.education.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t.education.journal.entity.LessonType;

import java.util.Optional;

public interface LessonTypeRepository extends JpaRepository<LessonType, Long> {
    Optional<LessonType> findByType(String type);
}
