package t.education.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t.education.journal.entity.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepository extends JpaRepository <Lesson, Long> {
    List<Lesson> findByDateBetweenOrderByDateDesc(LocalDate from, LocalDate to);
}
