package t.education.journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import t.education.journal.entity.Dancer;


public interface DancerRepository extends JpaRepository<Dancer, Long> {
}
